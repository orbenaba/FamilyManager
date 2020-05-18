package Controllers;

import Models.Parent;
import Models.User;
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


public class RegisterParentController extends RegisterHumanController {
    private RegisterParentView rview;

    public RegisterParentController(RegisterParentView rview) {
        super(rview);
        this.rview = rview;
        rview.addJobnameListener(new JobnameListener());
        rview.addSalaryListener(new SalaryListener());
        rview.addChildViewListener(new ChildViewListener());

        rview.addEnforcingSalary(new EnforcingSalary());
        /**Create the account*/
        rview.addCreateActionParent(new CreateActionParent());
    }


    class JobnameListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.jobName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
            rview.jobNameLabel.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.jobName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
            rview.jobNameLabel.setForeground(Color.green);
        }
    }

    class SalaryListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.salary.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
            rview.salaryLabel.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.salary.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
            rview.salaryLabel.setForeground(Color.green);
        }
    }

    class ChildViewListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.childView.setBorder(null);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.childView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.green));
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            new RegisterChildController(new RegisterChildView(rview.familyUsername));
            rview.dispose();
        }
    }

    /**
     * Enforcing the user to use only digits in Salary field
     */
    class EnforcingSalary extends KeyAdapter {
        public void keyTyped(KeyEvent e) {
            if (!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
    }

    class CreateActionParent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = rview.username.getText();
            if (!isUsernameExist(username)) {
                if(verify()) {
                    String bioNameFile = username + ".txt";
                    java.sql.Date birthday = new java.sql.Date(rview.dateChooser.getDate().getTime());

                    String familyUsername = rview.familyUsername;
                    String firstname = rview.firstName.getText();
                    byte genderId;
                    {
                        String gender = rview.genders.getSelectedItem().toString();
                        genderId = (byte) (gender.equals("Male") ? 0 : gender.equals("Female") ? 1 : gender.equals("Bisexual") ? 2 : 3);
                    }
                    boolean isMarried = rview.isMarried.isSelected();
                    String jobName = rview.jobName.getText();
                    String pass = String.valueOf(rview.password.getPassword());
                    String confirmPass = String.valueOf(rview.confirmPassword.getPassword());
                    double salary = Double.parseDouble(rview.salary.getText());


                    PreparedStatement ps;
                    ResultSet rs;
                    String registerQuery = "INSERT INTO human(Username,Birthday,FamilyUsername,FirstName," +
                            "GenderId,Image,IsObligated,JobName,Password,Salary,isLimited)" +
                            "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

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

                        ps.setBoolean(7, isMarried);
                        ps.setString(8, jobName);
                        ps.setString(9, pass);
                        ps.setDouble(10, salary);
                        ps.setBoolean(11, false);//isLimited

                        if (ps.executeUpdate() != 0) {
                            mappingTextareaIntoFile(username, rview.bio);//saving bio in file
                            Parent parent = new Parent(pass,username,firstname,genderId,isMarried,familyUsername,bioNameFile,rview.imagePath,birthday,jobName,salary);
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
                }
            }
        }

        public boolean verify(){
            return true;
        }
    }
}
