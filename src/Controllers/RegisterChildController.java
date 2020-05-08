package Controllers;

import Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;

import static Controllers.RegisterController.isUsernameExist;
import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class RegisterChildController extends RegisterHumanController {
    private RegisterChildView rview;


    public RegisterChildController(RegisterChildView rview) {
        super(rview);
        this.rview = rview;
        rview.addStatusListener(new StatusListener());
        rview.addParentViewListener(new ParentViewListener());
        rview.addCreateChildAction(new CreateActionChild());
    }

    class StatusListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.status.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
            rview.statusLabel.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.status.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
            rview.statusLabel.setForeground(Color.green);
        }
    }

    class ParentViewListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.parentView.setBorder(null);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.parentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.green));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            new RegisterParentController(new RegisterParentView(rview.familyUsername));
            rview.dispose();
        }
    }

    class CreateActionChild implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = rview.username.getText();
            if (!isUsernameExist(username)) {
                String bioNameFile = username + ".txt";
                java.sql.Date birthday = new java.sql.Date(rview.dateChooser.getDate().getTime());

                String familyUsername = rview.familyUsername;
                String firstname = rview.firstName.getText();
                byte genderId;
                {
                    String gender = rview.genders.getSelectedItem().toString();
                    genderId = (byte) (gender.equals("Male") ? 0 : gender.equals("Female") ? 1 : gender.equals("Bisexual") ? 2 : 3);
                }
                boolean isSingle = rview.isSingle.isSelected();
                String status = rview.status.getText();
                String pass = String.valueOf(rview.password.getPassword());
                String confirmPass = String.valueOf(rview.confirmPassword.getPassword());


                PreparedStatement ps;
                ResultSet rs;
                String registerQuery = "INSERT INTO human(Username,Birthday,FamilyUsername,FirstName," +
                        "GenderId,Image,IsObligated,JobName,Password,Salary,Status,isLimited)" +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

                try {
                    ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(registerQuery);
                    ps.setString(1, username);
                    ps.setDate(2, birthday);
                    ps.setString(3, familyUsername);
                    ps.setString(4, firstname);
                    ps.setByte(5, genderId);
                    //save the image profile as a blob in DB
                    if (rview.imagePath != null) {
                        InputStream image = new FileInputStream(new File(rview.imagePath));
                        ps.setBlob(6, image);
                    } else {
                        ps.setNull(6, Types.NULL);
                    }

                    ps.setBoolean(7, isSingle);
                    ps.setNull(8, Types.NULL);
                    ps.setString(9, pass);
                    ps.setNull(10, Types.NULL);
                    ps.setString(11, status);
                    ps.setBoolean(12, false);//isLimited

                    if (ps.executeUpdate() != 0) {
                        mappingTextareaIntoFile(username, rview.bio);//saving bio in file
                        new HomeController(new HomeView());
                        rview.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error!!!");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
}
