package Controllers;

import Views.AreYouChildOrParentView;
import Views.LoginView;
import Views.RegisterFamilyView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Controllers.RegisterController.isUsernameExist;

public class RegisterFamilyController extends JframeController{
    private RegisterFamilyView rfview;

    public RegisterFamilyController(RegisterFamilyView rfview) {
        super(rfview);
        this.rfview = rfview;
        rfview.addCreateMouse(new CreateMouseListener());
        rfview.addCreateAction(new CreateMouseAction());
        rfview.addLoginContextListener(new LoginContextListener());
    }
    class CreateMouseListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rfview.create.setBackground(new Color(0, 84, 104));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rfview.create.setBackground(new Color(0, 101, 183));
        }

    }

    class CreateMouseAction implements ActionListener {
        private String confirmPassword;
        public boolean verify(String lastName,String username,String createPassword) {
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
            String username=rfview.username.getText();
            String createPassword= String.valueOf(rfview.createPassword.getPassword());
            String lastName=rfview.lastname.getText();
            confirmPassword = String.valueOf(rfview.confirmPassword.getPassword());
            if (verify(lastName,username,createPassword))
                if (!isUsernameExist(username)) {
                    PreparedStatement ps;
                    ResultSet rs;
                    String registerFamilyQuery = "INSERT INTO family(Username,Counter,CurrentMonthProfit,LastName,Password) VALUES(?,?,?,?,?)";
                    try {
                        ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(registerFamilyQuery);
                        ps.setString(1, username);
                        ps.setString(2, String.valueOf(0));
                        ps.setString(3, String.valueOf(0));
                        ps.setString(4, lastName);
                        ps.setString(5, createPassword);
                        if (ps.executeUpdate() != 0) {
                            new AreYouChildOrParentController(new AreYouChildOrParentView(username));
                            rfview.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed in creating your account");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
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
