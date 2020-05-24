package Controllers;

import Views.MyProfileChildView;
import Views.StartView;
import com.company.CircleButton;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class MyProfileChildController extends MyProfileHumanController {
    private MyProfileChildView mpcview;


    public MyProfileChildController(MyProfileChildView mpcview) {
        super(mpcview);
        this.mpcview = mpcview;

        mpcview.addUpdateAccountAction(new UpdateAccountAction());

        /**==========Back to home label===============*/
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
                InputStream image = null;
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
                    System.out.println("Date in calendar" +mpcview.dateChooser.getDate());
                    System.out.println("Date added: "+new java.sql.Date(mpcview.dateChooser.getDate().getTime()));
                    if (!newUsername.equals("")) {
                        /**In case that the user does not change his username, we don't need to delete the old file*/
                        if (mpcview.child.username.equals(newUsername))
                            mappingTextareaIntoFile(newUsername, mpcview.bioArea, "Biographies");//saving bio as file
                        else {
                            mappingTextareaIntoFile(newUsername, mpcview.bioArea, "Biographies");
                            File f = new File("Biographies\\" + mpcview.child.username + ".txt");
                            f.delete();
                        }
                        new MyProfileChildController(new MyProfileChildView(newUsername));
                        /**Now mapping the new bio to a new file*/

                        mpcview.dispose();
                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
