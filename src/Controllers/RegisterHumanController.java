package Controllers;

import Views.*;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.File;
import static Views.RegisterView.*;

public class RegisterHumanController {
    private RegisterHumanView rview;

    public RegisterHumanController(RegisterHumanView rview) {
        this.rview = rview;
        addExitAction(new RegisterController.ExitListeners(rview, false), rview.exit);
        addMinimizeAction(new RegisterController.MinimizeListeners(rview, false), rview.minimize);
        rview.addImageAction(new AddImage_action());
        rview.addFirstNameListener(new FirstNameListener());
        rview.handleComboAction(new Combo());
        rview.addGendersListener(new GendersListener());

        rview.addPasswordListener(new PasswordListener());
        rview.addUsernameListener(new UsernameListener());
        rview.addConfirmPasswordListener(new ConfirmPasswordListener());

        rview.addCalendarListener(new CalendarListener());
        rview.addCreateAction(new CreateAction());
        rview.addCreateListener(new CreateListener());

        rview.addBioListener(new BioListener());
    }

    class AddImage_action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //String path=null;
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            //file extension
            FileNameExtensionFilter extension = new FileNameExtensionFilter("*Images", ".jpg", ".png", ".jpeg");
            chooser.addChoosableFileFilter(extension);
            int fileState = chooser.showSaveDialog(null);
            //check if the user select an image
            if (fileState == JFileChooser.APPROVE_OPTION) {
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
                rview.add(rview.imageContainer);
                //Refresh view
                rview.repaint();
            }
        }
    }

    class FirstNameListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
            rview.firstNameLabel.setForeground(Color.green);
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
            rview.genders.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.genders.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class UsernameListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.username.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.username.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class PasswordListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class ConfirmPasswordListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.confirmPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
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
            rview.dateChooser.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.green));
            rview.birthdayLabel.setForeground(Color.green);
        }
    }

    class CreateAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rview.mappingTextareaIntoFile();
        }
    }

    class CreateListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.create.setBackground(Color.white);
        }
        @Override
        public void mouseExited(MouseEvent e){
            rview.create.setBackground(new Color(48,48,48));
        }
    }

    class BioListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.bio.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.green));
            if(rview.bio.getText().equals("\t            Bio..."))
                rview.bio.setText("");
        }
        @Override
        public void mouseExited(MouseEvent e){
            rview.bio.setBorder(null);
            if(rview.bio.getText().equals(""))
                rview.bio.setText("\t            Bio...");
        }
    }
}
