package Controllers;


import Models.Family;
import Models.User;
import Views.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static Models.User.loginFunction;

public class LoginController extends JframeController{
    public LoginView lview;

    public LoginController(LoginView lview) {
        super(lview);
        this.lview = lview;
        lview.addUsernameFocus(new FocusUsernameListener());
        lview.addPasswordFocus(new FocusPasswordListener());
        lview.addRegisterContextAction(new RegisterContextAction());
        lview.addLoginListener(new LoginExecutable());
        lview.addLoginMouse(new LoginMouseListener());
    }


    class FocusUsernameListener extends FocusAdapter {
        //clear the text field-username if it is "username"
        @Override
        public void focusGained(FocusEvent e) {
            if (lview.username.getText().trim().toLowerCase().equals("username")) {
                lview.username.setText("");
                lview.username.setForeground(Color.black);
                //set a border to text field-username
                lview.username.setBorder(lview.frameTextfield);
            }
        }

        //if the text is equal to username or to an empty string
        @Override
        public void focusLost(FocusEvent e) {
            if (lview.username.getText().trim().toLowerCase().equals("username") ||
                    lview.username.getText().trim().equals("")) {
                lview.username.setText("username");
                lview.username.setForeground(new Color(153, 153, 153));
                //remove the border from the text field
                lview.username.setBorder(null);
            }
            //remove the border from the icon label
            lview.userIcon.setBorder(null);
        }
    }

    class FocusPasswordListener extends FocusAdapter {
        @Override
        public void focusGained(FocusEvent e) {
            String pass = String.valueOf(lview.createPassword.getPassword());
            if (pass.trim().toLowerCase().equals("password")) {
                lview.createPassword.setText("");
                lview.createPassword.setForeground(Color.black);
                //set a border to text password field
                lview.createPassword.setBorder(lview.frameTextfield);
            }
        }

        //if the text is equal to username or to an empty string
        @Override
        public void focusLost(FocusEvent e) {
            String pass = String.valueOf(lview.createPassword.getPassword());
            if (pass.trim().toLowerCase().equals("password") ||
                    pass.trim().equals("")) {
                lview.createPassword.setText("password");
                lview.createPassword.setForeground(new Color(153, 153, 153));
                //remove the border from the text field
                lview.createPassword.setBorder(null);
            }
            //remove the border from the icon label
            lview.passIcon1.setBorder(null);
        }
    }

    class RegisterContextAction extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            Border fr = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.blue);
            lview.registerContext.setBorder(fr);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            lview.registerContext.setBorder(null);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            RegisterFamilyController rfc=new RegisterFamilyController(new RegisterFamilyView());
            lview.dispose();
        }
    }

    /**
     * Login as a family account or parent/child account
     */
    class LoginExecutable implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //get the username and password
            String uname = lview.username.getText();
            String pword = String.valueOf(lview.createPassword.getPassword());
            //show a message if the username or the password fields are empty
            if (uname.trim().equals("username")) {
                JOptionPane.showMessageDialog(null, "Enter your username", "Empty username", 2);
            } else if (pword.trim().equals("password")) {
                JOptionPane.showMessageDialog(null, "Enter your password", "Empty password", 2);
            } else {
                Object res = loginFunction(uname, pword);
                /**The user is actually a family*/
                if (res instanceof Family)//username and password are correct
                {
                    new AreYouChildOrParentController(new AreYouChildOrParentView(uname));
                    lview.dispose();
                }
                else {/**The user is actually a parent/child*/
                    if (res instanceof User) {
                        new HomeController(new HomeView(lview.username.getText()));
                        lview.dispose();
                    } else if (res instanceof Integer) {
                        JOptionPane.showMessageDialog(null, "Cannot create a new account cause one family=10 people at the most", "Overflow", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username/password", "Login Error", 2);
                    }
                }
            }
        }
    }

    class LoginMouseListener extends MouseAdapter{
        @Override
        public void mouseExited(MouseEvent e) {
            lview.login.setBackground(new Color(0,7,204));
        }
        @Override
        public void mouseEntered(MouseEvent e){
            lview.login.setBackground(new Color(0,101,183));
        }
    }
}

