package Controllers;

import Views.MyProfileChildView;
import com.company.CircleButton;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;


public class MyProfileChildController {
    private MyProfileChildView mpcview;


    public MyProfileChildController(MyProfileChildView mpcview) {
        this.mpcview=mpcview;
        addMinimizeAction(new RegisterController.MinimizeListeners(mpcview, true), mpcview.minimize);
        addExitAction(new RegisterController.ExitListeners(mpcview, true), mpcview.exit);
        mpcview.addRemovePhotoListener(new RemovePhotoListener());

    }

    class RemovePhotoListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e){
            mpcview.removePhotoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        public void mouseClicked(MouseEvent e){
            /**Getting rid of old components*/
            mpcview.getContentPane().remove(mpcview.imageContainer);
            mpcview.getContentPane().remove(mpcview.removePhotoLabel);
            /**Adding new components*/
            ImageIcon image=new ImageIcon(getClass().getResource("/Icons/profile2.png"));
            mpcview.imageContainer=new JLabel(image);
            mpcview.imageContainer.setBounds(300,10,250,250);
            mpcview.addImage=new CircleButton("");
            mpcview.addImage.setBounds(385, 150, 78, 78);//Covers the plus that belongs to the image
            mpcview.add(mpcview.imageContainer);
            mpcview.add(mpcview.addImage);
            mpcview.addImageAction(new AddImage_action());
            mpcview.repaint();
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
                String imagePath;
                imagePath=chooser.getSelectedFile().getAbsolutePath();
                int width = mpcview.imageContainer.getWidth(), height = mpcview.imageContainer.getHeight();
                int x=mpcview.imageContainer.getX(),y=mpcview.imageContainer.getY();
                //Remove old components- profile image and circle button
                mpcview.getContentPane().remove(mpcview.imageContainer);
                mpcview.getContentPane().remove(mpcview.addImage);
                //Identify the selected image file which was chosen by the user
                File selectedImage = chooser.getSelectedFile();
                //Add again the picture, but this time the selected image
                mpcview.imageContainer = new JLabel(mpcview.child.image);
                mpcview.imageContainer.setBounds(x-30, y, width+60, height);
                //Fitting the picture
                mpcview.imageContainer.setIcon(new ImageIcon(
                        new ImageIcon(selectedImage.getAbsolutePath()).getImage().getScaledInstance(mpcview.imageContainer.getWidth(),
                                mpcview.imageContainer.getHeight(), Image.SCALE_DEFAULT)));
                //Adding the bin trash near the selected picture
                mpcview.removePhoto = new ImageIcon(getClass().getResource("/Icons/removePhoto.png"));
                mpcview.removePhotoLabel=new JLabel(mpcview.removePhoto);
                mpcview.removePhotoLabel.setBounds(x+width+50,y,40,50);
                mpcview.add(mpcview.removePhotoLabel);
                mpcview.add(mpcview.imageContainer);
                mpcview.addRemovePhotoListener(new RemovePhotoListener());
                //Refresh view
                mpcview.repaint();
            }
        }
    }

}
