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
import static Models.User.isUsernameExist;
import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class RegisterChildController extends RegisterHumanController {
    private RegisterChildView rview;


    public RegisterChildController(RegisterChildView rview) {
        super(rview);
        this.rview = rview;
        rview.addStatusListener(new StatusListener());
        rview.addParentViewListener(new ParentViewListener());
        rview.addCreateChildAction(new CreateActionChild());
        rview.addStatusLimit20(new StatusLimit20());
    }
    class StatusLimit20 extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (rview.status.getText().length() >= 20) // limit textfield to 20 characters
                e.consume();
        }
    }

    class StatusListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.status.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
            rview.statusLabel.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.status.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
            rview.statusLabel.setForeground(rview.fore);
        }
    }

    class ParentViewListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.parentView.setBorder(null);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.parentView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, rview.fore));
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
            if (!verifyEmptyFields()) {
                if (verifyPasswords()) {
                    String statement=checkValidPassword(String.valueOf(rview.password.getPassword()));
                    if (statement.equals("")) {
                        if (!isUsernameExist(username, false, "")) {
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
                            Connection con;
                            try {
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
                                ps = con.prepareStatement(registerQuery);
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
                                ps.setInt(10, -1);
                                ps.setString(11, status);
                                ps.setBoolean(12, false);//isLimited

                                if (ps.executeUpdate() != 0) {
                                    mappingTextareaIntoFile(username, rview.bio, "Biographies");//saving bio as file
                                    //                       Child child = new Child(pass,username,firstname,genderId,familyUsername,bioNameFile,rview.imagePath,birthday,status,isSingle);
                                    /**Needs to update the counter*/
                                    String updateCounter = "UPDATE family SET Counter=Counter+1 WHERE Username= ?";
                                    ps = con.prepareStatement(updateCounter);
                                    ps.setString(1, familyUsername);
                                    ps.executeUpdate();
                                    new HomeController(new HomeView(rview.username.getText()));
                                    rview.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error!!!");
                                }

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "This username is already taken", "Taken username.", 2);

                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, statement, "Password invalid", 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords not match!", "Passwords not match!", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "One or more fields are empty.", "Empty fields", 2);
            }
        }
    }

    @Override
    protected boolean verifyEmptyFields() {
        return super.verifyEmptyFields()||rview.status.getText().trim().equals("");
    }
}
