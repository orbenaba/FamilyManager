package Controllers;

import Models.Family;
import Views.AreYouChildOrParentView;
import Views.LoginView;
import Views.RegisterFamilyView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static Controllers.RegisterHumanController.checkValidPassword;
import static Models.User.isUsernameExist;


public class RegisterFamilyController extends JframeController{
    private RegisterFamilyView rfview;

    public RegisterFamilyController(RegisterFamilyView rfview) {
        super(rfview);
        this.rfview = rfview;
        rfview.addCreateMouse(new CreateMouseListener());
        rfview.addCreateAction(new CreateMouseAction());
        rfview.addLoginContextListener(new LoginContextListener());
        rfview.addLimit18CharactersConfPass(new Limit18CharactersConfPass());
        rfview.addLimit18CharactersPass(new Limit18CharactersPass());
        rfview.addLimit12CharactersLName(new Limit12CharactersLName());
        rfview.addLimit12CharactersUName(new Limit12CharactersUName());
    }
    class Limit18CharactersConfPass extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (rfview.confirmPassword.getPassword().length >= 18) // limit textfield to 12 characters
                e.consume();
        }
    }
    class Limit18CharactersPass extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (rfview.createPassword.getPassword().length >= 18) // limit textfield to 12 characters
                e.consume();
        }
    }
    class Limit12CharactersLName extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (rfview.lastname.getText().length() >= 18) // limit textfield to 12 characters
                e.consume();
        }
    }
    class Limit12CharactersUName extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (rfview.username.getText().length() >= 18) // limit textfield to 12 characters
                e.consume();
        }
    }


    class CreateMouseListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rfview.create.setBackground(new Color(0,7,204));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rfview.create.setBackground(new Color(0, 101, 183));
        }

    }

    class CreateMouseAction implements ActionListener {
        private String confirmPassword;

        public boolean verify(String lastName, String username, String createPassword) {
            if (lastName.trim().equals("") || username.trim().equals("") ||
                    createPassword.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty Fields", 2);
                return false;
            } else//check if the two given passwords are equal
                if (!confirmPassword.equals(createPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords don't match", "Confirm Password", 2);
                    return false;
                }
            return true;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = rfview.username.getText();
            String createPassword = String.valueOf(rfview.createPassword.getPassword());
            String lastName = rfview.lastname.getText();
            confirmPassword = String.valueOf(rfview.confirmPassword.getPassword());
            if (verify(lastName, username, createPassword)) {
                if (confirmPassword.equals(createPassword)) {
                    String statement = checkValidPassword(createPassword);
                    if (statement.equals("")) {
                        if (!isUsernameExist(username, false, "")) {
                            new Family(username, createPassword, String.valueOf(0), lastName, String.valueOf(0));
                            new AreYouChildOrParentController(new AreYouChildOrParentView(username));
                            rfview.dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "This username is already taken", "Taken username.", 2);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, statement, "Invalid password", 2);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Passwords not match!", "Passwords not match!", 2);
                }
            }
        }
    }

    class LoginContextListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rfview.loginContext.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.blue));
        }
        @Override
        public void mouseExited(MouseEvent e){
            rfview.loginContext.setBorder(null);
        }
        @Override
        public void mouseClicked(MouseEvent e){
            LoginController lc=new LoginController(new LoginView());
            rfview.dispose();
        }
    }
}
