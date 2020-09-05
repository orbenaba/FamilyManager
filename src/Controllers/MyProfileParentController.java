package Controllers;


import Views.MyProfileParentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static Controllers.RegisterHumanController.checkValidPassword;
import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class MyProfileParentController extends MyProfileHumanController {
    private MyProfileParentView mpcview;


    public MyProfileParentController(MyProfileParentView mpcview) {
        super(mpcview);
        this.mpcview = mpcview;
        mpcview.addUpdateAccountAction(new UpdateAccountAction());
        if (mpcview.limitButton!=null)
            mpcview.addLimitAction(new LimitAction());
        else
            mpcview.addUnLimitAction(new UnLimitAction());
        mpcview.addJobName20Limit(new JobName20Limit());
        mpcview.addSalary8Limit(new Salary8Limit());
        mpcview.addEnforcingSalary(new EnforcingSalary());
    }
    class JobName20Limit extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (mpcview.jobNameField.getText().length() >= 20) // limit textfield to 8 characters
                e.consume();
        }
    }
    class Salary8Limit extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (mpcview.salaryField.getText().length() >= 8) // limit textfield to 8 characters
                e.consume();
        }
    }
    class EnforcingSalary extends KeyAdapter {
        public void keyTyped(KeyEvent e) {
            if (!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
    }
    class UpdateAccountAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Custom button text
            Object[] options = {"Save changes",
                    "Cancel"};
            int n = JOptionPane.showOptionDialog(mpcview,
                    "Are you sure you want to update your account?",
                    "Update account",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                if (!checkEmptyFields()) {
                    InputStream image = null;
                    String statement = checkValidPassword(mpcview.passwordField.getText());
                    if (statement.equals("")) {
                        try {
                            if (mpcview.imagePath != null)
                                image = new FileInputStream(new File(mpcview.imagePath));
                            else
                                image = null;
                            /**In case that the user had a profile image before and he didnt change it
                             * , We'll send another flag(boolean) which is true when we don't need to update the image*/
                            boolean flag = false;
                            if (image == null)
                                if (mpcview.imageContainer.getWidth() == 478)
                                    flag = true;
                            String newUsername = mpcview.parent.updateAccount(mpcview.usernameField.getText(), mpcview.passwordField.getText(),
                                    mpcview.firstNameField.getText(), mpcview.jobNameField.getText(), new java.sql.Date(mpcview.dateChooser.getDate().getTime()),
                                    mpcview.isMarried.isSelected(), Integer.parseInt(mpcview.salaryField.getText()), image, flag);
                            System.out.println("Date in calendar" + mpcview.dateChooser.getDate());
                            System.out.println("Date added: " + new java.sql.Date(mpcview.dateChooser.getDate().getTime()));
                            if (!newUsername.equals("")) {
                                /**In case that the user does not change his username, we don't need to delete the old file*/
                                if (mpcview.parent.username.equals(newUsername))
                                    mappingTextareaIntoFile(newUsername, mpcview.bioArea, "Biographies");//saving bio as file
                                else {
                                    mappingTextareaIntoFile(newUsername, mpcview.bioArea, "Biographies");
                                    File f = new File("Biographies\\" + mpcview.parent.username + ".txt");
                                    f.delete();
                                }
                                new MyProfileParentController(new MyProfileParentView(newUsername));
                                /**Now mapping the new bio to a new file*/
                                mpcview.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "This username is already taken", "Taken username.", 2);
                            }
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                        /**Displays a success message*/
                        JOptionPane.showMessageDialog(null, "Your account has successfully been updated", "Successful updating", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, statement, "Invalid password", 2);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty fields", 2);
                }
            }
        }
    }
    class LimitAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"Save changes",
                    "Cancel"};
            int n = JOptionPane.showOptionDialog(mpcview,
                    "Are you sure you want to limit your children?",
                    "Limit children",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                mpcview.parent.limitChildren();
                new MyProfileParentController(new MyProfileParentView(mpcview.parent.username));
                mpcview.dispose();
            }
        }
    }

    class UnLimitAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object[] options = {"Save changes",
                    "Cancel"};
            int n = JOptionPane.showOptionDialog(mpcview,
                    "Are you sure you want to unLimit your children?",
                    "UnLimit children",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                mpcview.parent.unLimitChildren();
                new MyProfileParentController(new MyProfileParentView(mpcview.parent.username));
                mpcview.dispose();
            }
        }
    }

    @Override
    protected boolean checkEmptyFields() {
        return super.checkEmptyFields()||mpcview.salaryField.getText().equals("");
    }
}
