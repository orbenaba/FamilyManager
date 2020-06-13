package Controllers;

import Views.MyProfileChildView;
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


public class MyProfileChildController extends MyProfileHumanController {
    private MyProfileChildView mpcview;

    public MyProfileChildController(MyProfileChildView mpcview) {
        super(mpcview);
        this.mpcview = mpcview;
        mpcview.addUpdateAccountAction(new UpdateAccountAction());
        mpcview.addLimit20CharactersStatus(new Limit20CharactersStatus());
    }

    class Limit20CharactersStatus extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (mpcview.statusField.getText().length() >= 20) // limit textfield to 18 characters
                e.consume();
        }
    }

    class UpdateAccountAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Verifies user's desire
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
                if(!checkEmptyFields()) {
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
                            String newUsername = mpcview.child.updateAccount(mpcview.usernameField.getText(), mpcview.passwordField.getText(),
                                    mpcview.firstNameField.getText(), mpcview.statusField.getText(), new java.sql.Date(mpcview.dateChooser.getDate().getTime()),
                                    mpcview.isSingle.isSelected(), image, flag);
                            if (!newUsername.equals("")) {
                                /**In case that the user does not change his username, we don't need to delete the old file*/
                                if (mpcview.child.username.equals(newUsername))
                                    mappingTextareaIntoFile(newUsername, mpcview.bioArea, "Biographies");//saving bio as file
                                else {
                                    /**Now mapping the new bio to a new file*/
                                    mappingTextareaIntoFile(newUsername, mpcview.bioArea, "Biographies");
                                    File f = new File("Biographies\\" + mpcview.child.username + ".txt");
                                    f.delete();
                                }
                                new MyProfileChildController(new MyProfileChildView(newUsername));
                                mpcview.dispose();
                                /**Displays a success message*/
                                JOptionPane.showMessageDialog(null, "Your account has successfully been updated", "Successful updating", 1);
                            } else {
                                JOptionPane.showMessageDialog(null, "This username is already taken", "Taken username.", 2);
                            }
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, statement, "Invalid password", 2);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty fields.", 2);
                }
            }
        }
    }

    @Override
    protected boolean checkEmptyFields(){
        return super.checkEmptyFields()||mpcview.statusField.getText().trim().equals("");
    }
}
