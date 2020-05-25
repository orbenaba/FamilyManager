package Controllers;

import Views.*;
import com.company.CircleButton;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;


public class RegisterHumanController extends JframeController{
    private RegisterHumanView rview;

    public RegisterHumanController(RegisterHumanView rview) {
        super(rview);
        this.rview = rview;
        rview.addImageAction(new AddImage_action());
        rview.addFirstNameListener(new FirstNameListener());
        rview.handleComboAction(new Combo());
        rview.addGendersListener(new GendersListener());

        rview.addPasswordListener(new PasswordListener());
        rview.addUsernameListener(new UsernameListener());
        rview.addConfirmPasswordListener(new ConfirmPasswordListener());

        rview.addCalendarListener(new CalendarListener());
        rview.addCreateListener(new CreateListener());

        rview.addBioListener(new BioListener());
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
                rview.imagePath=chooser.getSelectedFile().getAbsolutePath();
                int width = rview.imageContainer.getWidth(), height = rview.imageContainer.getHeight();
                int x=rview.imageContainer.getX(),y=rview.imageContainer.getY();
                //Remove old components- profile image and circle button
                rview.getContentPane().remove(rview.imageContainer);
                rview.getContentPane().remove(rview.addImage);
                //Identify the selected image file which was chosen by the user
                File selectedImage = chooser.getSelectedFile();
                //Add again the picture, but this time the selected image
                rview.imageContainer = new JLabel(rview.image);
                rview.imageContainer.setBounds(x-30, y, width+60, height);
                //Fitting the picture
                rview.imageContainer.setIcon(new ImageIcon(
                        new ImageIcon(selectedImage.getAbsolutePath()).getImage().getScaledInstance(rview.imageContainer.getWidth(),
                                rview.imageContainer.getHeight(), Image.SCALE_DEFAULT)));
                //Adding the bin trash near the selected picture
                rview.removePhoto = new ImageIcon(getClass().getResource("/Icons/removePhoto.png"));
                rview.removePhotoLabel=new JLabel(rview.removePhoto);
                rview.removePhotoLabel.setBounds(x+width+50,y,40,50);
                /**Removing old background and add it again*/
                rview.getContentPane().remove(rview.background);
                rview.add(rview.removePhotoLabel);
                rview.add(rview.imageContainer);
                rview.add(rview.background);
                rview.addRemovePhotoListener(new RemovePhotoListenerr());
                //Refresh view
                rview.repaint();
            }
        }
    }

    class FirstNameListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
            rview.firstNameLabel.setForeground(rview.fore);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
            rview.firstNameLabel.setForeground(Color.white);
        }
    }

    class Combo implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String gender = (String) cb.getSelectedItem();
        }
    }

    class GendersListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.genders.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.genders.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class UsernameListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.username.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.username.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class PasswordListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,rview.fore));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class ConfirmPasswordListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.confirmPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,rview.fore));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.confirmPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class CalendarListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.dateChooser.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.white));
            rview.birthdayLabel.setForeground(Color.white);
        }
        @Override
        public void mouseExited(MouseEvent e){
            rview.dateChooser.setBorder(BorderFactory.createMatteBorder(1,1,1,1,rview.fore));
            rview.birthdayLabel.setForeground(rview.fore);
        }
    }

    class CreateListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.create.setBackground(Color.white);
        }
        @Override
        public void mouseExited(MouseEvent e){
            rview.create.setBackground(rview.back);
        }
    }

    class BioListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.bio.setBorder(BorderFactory.createMatteBorder(1,1,1,1,rview.fore));
            rview.bioLabel.setForeground(Color.white);
        }
        @Override
        public void mouseExited(MouseEvent e){
            rview.bio.setBorder(null);
            rview.bioLabel.setForeground(rview.fore);
        }
    }

    class RemovePhotoListenerr extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.removePhotoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        public void mouseClicked(MouseEvent e){
            /**Getting rid of old components*/
            rview.getContentPane().remove(rview.imageContainer);
            rview.getContentPane().remove(rview.removePhotoLabel);
            /**Adding new components*/
            rview.image=new ImageIcon(getClass().getResource("/Icons/profile3.png"));
            rview.imageContainer = new JLabel(rview.image);
            rview.imageContainer.setBounds(rview.getWidth()/2-239, 20, 478, 300);
            rview.addImage = new CircleButton("",Color.ORANGE);
            rview.addImage.setBounds(rview.getWidth()/2-38, 190, 78, 78);//Covers the plus that belongs to the image
            rview.getContentPane().remove(rview.background);
            rview.add(rview.imageContainer);
            rview.add(rview.addImage);
            rview.add(rview.background);
            rview.addImageAction(new AddImage_action());
            rview.repaint();
        }
    }
}
