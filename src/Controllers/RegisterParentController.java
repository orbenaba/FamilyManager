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

public class RegisterParentController {
    private RegisterParentView rview;

    public RegisterParentController(RegisterParentView rview) {
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
                //Remove old components- profile image and circle button
                rview.getContentPane().remove(rview.imageContainer);
                rview.getContentPane().remove(rview.addImage);
                //Identify the selected image file which was chosen by the user
                File selectedImage = chooser.getSelectedFile();
                //Add again the picture, but this time the selected image
                rview.imageContainer = new JLabel(rview.image);
                rview.imageContainer.setBounds(175, 20, width, height);
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
}
