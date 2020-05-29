package Controllers;

import Views.MyProfileHumanView;
import Views.StartView;
import com.company.CircleButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

//Creating a base class to both MyProfileChildController and MyProfileParentController
public abstract class MyProfileHumanController extends BaseForHomeSeqController {
    private MyProfileHumanView mphview;

    public MyProfileHumanController(MyProfileHumanView mpcview) {
        super(mpcview);
        this.mphview = mpcview;
        //Differentiate between users with image and users without
        if (mpcview.human.image != null)
            mpcview.addRemovePhotoListener(new RemovePhotoListener());
        else
            mpcview.addImageAction(new AddImage_action());
        mpcview.addDeleteAccountAction(new DeleteAccountAction());
        mpcview.addLimit18CharactersPass(new Limit18CharactersPass());
        mpcview.addLimit18CharactersUName(new Limit18CharactersUName());
        mpcview.addLimit12CharactersFName(new Limit12CharactersFName());
    }
    /***Create Key adapters in order to limit the user in inputting a too long input*/
    class Limit18CharactersPass extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (mphview.passwordField.getText().length() >= 18) // limit textfield to 18 characters
                e.consume();
        }
    }
    class Limit18CharactersUName extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (mphview.usernameField.getText().length() >= 18) // limit textfield to 18 characters
                e.consume();
        }
    }
    class Limit12CharactersFName extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (mphview.firstNameField.getText().length() >= 12) // limit textfield to 12characters
                e.consume();
        }
    }

    class RemovePhotoListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            mphview.removePhotoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            /**Getting rid of old components*/
            mphview.getContentPane().remove(mphview.imageContainer);
            mphview.getContentPane().remove(mphview.removePhotoLabel);
            /**Adding new components*/
            ImageIcon image = new ImageIcon(getClass().getResource("/Icons/profile3.png"));
            mphview.imageContainer = new JLabel(image);
            mphview.imageContainer.setBounds(mphview.width / 2 - 130, 22, 250, 300);
            mphview.addImage = new CircleButton("", Color.ORANGE);
            mphview.addImage.setBounds(mphview.width / 2 - 40, 190, 78, 78);//Covers the plus that belongs to the image            add(imageContainer);
            mphview.imagePath = null;
            //In order to keep the background we remove old image the add it again
            mphview.getContentPane().remove(mphview.background);
            mphview.add(mphview.imageContainer);
            mphview.add(mphview.addImage);
            mphview.add(mphview.background);
            mphview.addImageAction(new AddImage_action());
            mphview.repaint();
        }
    }

    class AddImage_action implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            //file extension
            FileNameExtensionFilter extension = new FileNameExtensionFilter("*Images", ".jpg", ".png", ".jpeg");
            chooser.addChoosableFileFilter(extension);
            int fileState = chooser.showSaveDialog(null);
            //check if the user select an image
            if (fileState == JFileChooser.APPROVE_OPTION) {
                mphview.imagePath = chooser.getSelectedFile().getAbsolutePath();
                int width = mphview.imageContainer.getWidth() + 150, height = mphview.imageContainer.getHeight();
                int x = mphview.imageContainer.getX(), y = mphview.imageContainer.getY();
                //Remove old components- profile image and circle button
                mphview.getContentPane().remove(mphview.imageContainer);
                mphview.getContentPane().remove(mphview.addImage);
                //Identify the selected image file which was chosen by the user
                File selectedImage = chooser.getSelectedFile();
                //Add again the picture, but this time the selected image
                mphview.imageContainer = new JLabel(mphview.human.image);
                mphview.imageContainer.setBounds(mphview.getWidth() / 2 - 239, 20, 478, 300);
                //Fitting the picture
                mphview.imageContainer.setIcon(new ImageIcon(
                        new ImageIcon(selectedImage.getAbsolutePath()).getImage().getScaledInstance(mphview.imageContainer.getWidth(),
                                mphview.imageContainer.getHeight(), Image.SCALE_DEFAULT)));
                //Adding the bin trash near the selected picture
                mphview.removePhoto = new ImageIcon(getClass().getResource("/Icons/removePhoto.png"));
                mphview.removePhotoLabel = new JLabel(mphview.removePhoto);
                mphview.removePhotoLabel.setBounds(x + width + 20, y, 40, 50);
                /**In order to keep the background after changes, we need to remove it and add it again cause we adding and removing other components*/
                mphview.getContentPane().remove(mphview.background);
                mphview.add(mphview.removePhotoLabel);
                mphview.add(mphview.imageContainer);
                mphview.add(mphview.background);
                mphview.addRemovePhotoListener(new RemovePhotoListener());
                //Refresh view
                mphview.repaint();
            }
        }
    }
    /**Deleting a user account by clicking the specified button*/
    class DeleteAccountAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /**Verifies the user at the last time if he really sure in deleting his account*/
            Object[] options = {"Delete account",
                    "Stay"};
            int n = JOptionPane.showOptionDialog(mphview,
                    "Are you sure you want to delete your account?",
                    "Delete account",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
            if (n == 0) {
                (mphview.human).deleteAccount();
                new StartController(new StartView());
                mphview.dispose();
            }
        }
    }
    /**Checking for empty fields*/
    protected boolean checkEmptyFields(){
        return mphview.usernameField.getText().trim().equals("")||mphview.firstNameField.getText().trim().equals("")
                ||mphview.passwordField.getText().equals("");
    }
}
