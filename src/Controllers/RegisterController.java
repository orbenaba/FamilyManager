package Controllers;

import Views.LoginView;
import Views.RegisterView;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Models.User.isUsernameExist;
import static Views.RegisterView.*;
import static java.sql.Types.NULL;

public class RegisterController extends JframeController{
    private RegisterView rview;

    public RegisterController(RegisterView rview) {
        super(rview);
        this.rview = rview;

        rview.addUsernameFocus(new FocusUsernameListener());
        rview.addPasswordFocus(new FocusPasswordListener());
        rview.addConfirmPasswordFocus(new FocusPasswordListener());
        rview.addLoginContextAction(new LoginContextAction());
        rview.addRegisterListener(new RegisterExecutable());
        changeColor(new changeButtonColor(rview.register),rview.register);
        rview.enforcePhone(new enforcingPhoneDigits());
        rview.addSelectListener(new SelectImageAction());
    }

    class FocusUsernameListener extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            if (rview.username.getText().trim().toLowerCase().equals("username")) {
                rview.username.setText("");
                rview.username.setForeground(Color.black);
                //set a border to text field-username
                rview.username.setBorder(rview.frameTextfield);
            }
        }

        //if the text is equal to username or to an empty string
        @Override
        public void focusLost(FocusEvent e) {
            if (rview.username.getText().trim().toLowerCase().equals("username") ||
                    rview.username.getText().trim().equals("")) {
                rview.username.setText("username");
                rview.username.setForeground(new Color(153, 153, 153));
                //remove the border from the text field
                rview.username.setBorder(null);
            }
            //remove the border from the icon label
        }
    }

    class FocusPasswordListener extends FocusAdapter {
        //clear the text field-create password if it is "username"
        String pass = String.valueOf(rview.createPassword.getPassword());

        @Override
        public void focusGained(FocusEvent e) {
            if (pass.trim().toLowerCase().equals("password")) {
                rview.createPassword.setText("");
                rview.createPassword.setForeground(Color.black);
                rview.createPassword.setBorder(rview.frameTextfield);
            }
        }

        //if the text is equal to password or to an empty string
        @Override
        public void focusLost(FocusEvent e) {
            if (pass.trim().toLowerCase().equals("password") ||
                    pass.trim().equals("")) {
                rview.createPassword.setText("password");
                rview.createPassword.setForeground(new Color(153, 153, 153));
                rview.createPassword.setBorder(null);
            }
            //remove the border from the icon label
        }
    }

    class LoginContextAction extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            Border fr = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.blue);
            rview.loginContext.setBorder(fr);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.loginContext.setBorder(null);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            LoginView rv = new LoginView();
            LoginController lc = new LoginController(rv);
            rview.dispose();
        }
    }

    class RegisterExecutable implements ActionListener {
        //create a function to verify the empty fields
        public boolean verify() {
            String fname2 = rview.fullname.getText();
            String uname2 = rview.username.getText();
            String pone2 = rview.phone.getText();
            String pass1 = String.valueOf(rview.createPassword.getPassword());
            String pass2 = String.valueOf(rview.confirmPassword.getPassword());
            if (fname2.trim().equals("") || uname2.trim().equals("") ||
                    pone2.trim().equals("") || pass1.trim().equals("") || pass2.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty Fields", 2);
                return false;
            } else//check if the two given passwords are equal
                if (!pass1.equals(pass2)) {
                    JOptionPane.showMessageDialog(null, "Passwords don't match", "Confirm Password", 2);
                    return false;
                }

            return true;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String fname2 = rview.fullname.getText();
            String uname2 = rview.username.getText();
            String pone2 = rview.phone.getText();
            String pass1 = String.valueOf(rview.createPassword.getPassword());
            String pass2 = String.valueOf(rview.confirmPassword.getPassword());
            String gender = "Male";
            if (rview.female.isSelected()) {
                gender = "Female";
            }
            if (verify()) {
                if (!isUsernameExist(uname2)) {
                    PreparedStatement ps;
                    ResultSet rs;
                    String registerUserQuery = "INSERT INTO users(fullname,username,password,phone,gender,image) VALUES(?,?,?,?,?,?)";
                    try {
                        ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/family", "root", "root").prepareStatement(registerUserQuery);
                        ps.setString(1, fname2);
                        ps.setString(2, uname2);
                        ps.setString(3, pass1);
                        ps.setString(4, pone2);
                        ps.setString(5, gender);
                        //save the image as a blob in the DB
                        if (rview.image_path != null) {
                            InputStream image = new FileInputStream(new File(rview.image_path));
                            ps.setBlob(6, image);
                        } else {
                            ps.setNull(6, NULL);
                        }
                        if (ps.executeUpdate() != 0) {
                            JOptionPane.showMessageDialog(null, "Your account has been created successfully");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed in creating your account");
                        }
                    } catch (SQLException | FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                } else
                    JOptionPane.showMessageDialog(null, "This username is already exist", "Username validation", 2);
            }
        }
    }

    class SelectImageAction implements ActionListener {
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
                File selectedImage = chooser.getSelectedFile();
                rview.image_path = selectedImage.getAbsolutePath();

            }
        }
    }

    static class changeButtonColor extends MouseAdapter {
        private JButton btn;
        public changeButtonColor(JButton btn){
            this.btn=btn;
        }
        @Override
        public void mouseExited(MouseEvent e) {
            btn.setBackground(new Color(0, 84, 104));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            btn.setBackground(new Color(0, 101, 183));
        }
    }

    class enforcingPhoneDigits extends KeyAdapter {
        //allow to type only numbers
        @Override
        public void keyTyped(KeyEvent e) {
            if (!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
    }

}
