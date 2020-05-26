package Controllers;

import Views.*;
import com.company.CircleButton;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;

import java.io.File;


public class RegisterHumanController extends JframeController {
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


        rview.addLimit18CharactersPass(new Limit18CharactersPass());
        rview.addLimit18CharactersUName(new Limit18CharactersUName());
        rview.addLimit12CharactersConfPass(new Limit18CharactersConfPass());
        rview.addLimit12CharactersFName(new Limit12CharactersFName());
    }

    class Limit12CharactersFName extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (rview.firstName.getText().length() >= 12) // limit textfield to 12 characters
                e.consume();
        }
    }

    class Limit18CharactersPass extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (rview.password.getPassword().length >= 18) // limit textfield to 18 characters
                e.consume();
        }
    }

    class Limit18CharactersConfPass extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (rview.confirmPassword.getPassword().length >= 18) // limit textfield to 18 characters
                e.consume();
        }
    }

    class Limit18CharactersUName extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (rview.username.getText().length() >= 18) // limit textfield to 18 characters
                e.consume();
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
                rview.imagePath = chooser.getSelectedFile().getAbsolutePath();
                int width = rview.imageContainer.getWidth(), height = rview.imageContainer.getHeight();
                int x = rview.imageContainer.getX(), y = rview.imageContainer.getY();
                //Remove old components- profile image and circle button
                rview.getContentPane().remove(rview.imageContainer);
                rview.getContentPane().remove(rview.addImage);
                //Identify the selected image file which was chosen by the user
                File selectedImage = chooser.getSelectedFile();
                //Add again the picture, but this time the selected image
                rview.imageContainer = new JLabel(rview.image);
                rview.imageContainer.setBounds(x - 30, y, width + 60, height);
                //Fitting the picture
                rview.imageContainer.setIcon(new ImageIcon(
                        new ImageIcon(selectedImage.getAbsolutePath()).getImage().getScaledInstance(rview.imageContainer.getWidth(),
                                rview.imageContainer.getHeight(), Image.SCALE_DEFAULT)));
                //Adding the bin trash near the selected picture
                rview.removePhoto = new ImageIcon(getClass().getResource("/Icons/removePhoto.png"));
                rview.removePhotoLabel = new JLabel(rview.removePhoto);
                rview.removePhotoLabel.setBounds(x + width + 50, y, 40, 50);
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
            rview.password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.password.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class ConfirmPasswordListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.confirmPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.confirmPassword.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
        }
    }

    class CalendarListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.dateChooser.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
            rview.birthdayLabel.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.dateChooser.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
            rview.birthdayLabel.setForeground(rview.fore);
        }
    }

    class CreateListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.create.setBackground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.create.setBackground(rview.back);
        }
    }

    class BioListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.bio.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
            rview.bioLabel.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.bio.setBorder(null);
            rview.bioLabel.setForeground(rview.fore);
        }
    }

    class RemovePhotoListenerr extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.removePhotoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            /**Getting rid of old components*/
            rview.getContentPane().remove(rview.imageContainer);
            rview.getContentPane().remove(rview.removePhotoLabel);
            /**Adding new components*/
            rview.image = new ImageIcon(getClass().getResource("/Icons/profile3.png"));
            rview.imageContainer = new JLabel(rview.image);
            rview.imageContainer.setBounds(rview.getWidth() / 2 - 239, 20, 478, 300);
            rview.addImage = new CircleButton("", Color.ORANGE);
            rview.addImage.setBounds(rview.getWidth() / 2 - 38, 190, 78, 78);//Covers the plus that belongs to the image
            rview.getContentPane().remove(rview.background);
            rview.add(rview.imageContainer);
            rview.add(rview.addImage);
            rview.add(rview.background);
            rview.add(rview.background);
            rview.addImageAction(new AddImage_action());
            rview.repaint();
        }
    }

    //returns true if some field is empty
    protected boolean verifyEmptyFields() {
        return rview.firstName.getText().trim().equals("") || rview.username.getText().trim().equals("")
                || String.valueOf(rview.password.getPassword()).trim().equals("") || String.valueOf(rview.confirmPassword.getPassword()).trim().equals("");
    }

    //returns true in case the passwords match
    protected boolean verifyPasswords() {
        return String.valueOf(rview.confirmPassword.getPassword()).equals(String.valueOf(rview.password.getPassword()));
    }

    /**
     * This function checks whether a given password is valid,
     * namely, it contains at least 8 characters, no spaces,
     * at least one character and at least one digit
     * Output: 1)Empty string- password is valid --->keep the program
     * 2)password cant contain spaces
     * 3)password must contain at least one character and one digit
     * 4)password's length must be at least 8
     */
    public static String checkValidPassword(String password) {
        int len=password.length();
        if (len < 8)
            return "password's length must be at least 8";
        boolean ch=false,dig=false;
        for(int i=0;i<len;i++) {
            char current = password.charAt(i);
            if (current == ' ')
                return "password cant contain spaces";
            if (current >= 'a' && current <= 'z' || current >= 'A' && current <= 'Z') {
                ch = true;
                continue;
            }
            if (current >= '0' && current <= '9')
                dig = true;
        }
        if(dig&&ch)
            return "";
        return "password must contain at least one character and one digit";
    }
}
