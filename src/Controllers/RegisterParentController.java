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
        rview.addJobName20Limit(new JobName20Limit());
        rview.addSalary8Limit(new Salary8Limit());
    }

    class JobName20Limit extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (rview.jobName.getText().length() >= 20) // limit textfield to 8 characters
                e.consume();
        }
    }

    class Salary8Limit extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (rview.salary.getText().length() >= 8) // limit textfield to 8 characters
                e.consume();
        }
    }

    class JobnameListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            rview.jobName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white));
            rview.jobNameLabel.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            rview.jobName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
            rview.jobNameLabel.setForeground(rview.fore);
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
            rview.salary.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, rview.fore));
            rview.salaryLabel.setForeground(rview.fore);
        }
    }

    class ChildViewListener extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent e) {
            rview.childView.setBorder(null);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            rview.childView.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,rview.fore));
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
            if (!verifyEmptyFields()) {
                if (verifyPasswords()) {
                    String statement = checkValidPassword(String.valueOf(rview.password.getPassword()));
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
                            boolean isMarried = rview.isMarried.isSelected();
                            String jobName = rview.jobName.getText();
                            String pass = String.valueOf(rview.password.getPassword());
                            double salary = Double.parseDouble(rview.salary.getText());

                            PreparedStatement ps;
                            ResultSet rs;
                            String registerQuery = "INSERT INTO human(Username,Birthday,FamilyUsername,FirstName," +
                                    "GenderId,Image,IsObligated,JobName,Password,Salary,isLimited)" +
                                    "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
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

                                ps.setBoolean(7, isMarried);
                                ps.setString(8, jobName);
                                ps.setString(9, pass);
                                ps.setDouble(10, salary);
                                ps.setBoolean(11, false);//isLimited

                                if (ps.executeUpdate() != 0) {
                                    mappingTextareaIntoFile(username, rview.bio, "Biographies");//saving bio in file
                                    //Parent parent = new Parent(pass,username,firstname,genderId,isMarried,familyUsername,birthday,jobName,salary);
                                    /**Needs to update the counter*/
                                    String updateCounter = "UPDATE family SET Counter=Counter+1 WHERE Username= ?";
                                    ps = con.prepareStatement(updateCounter);
                                    ps.setString(1, familyUsername);
                                    ps.executeUpdate();
                                    con.close();
                                    new HomeController(new HomeView(rview.username.getText()));
                                    rview.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error!!!");
                                    con.close();
                                }

                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "This username is already taken.", "Taken username", 2);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, statement, "Password invalid", 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords not match!", "Passwords not match!", 2);
                }
            } else {
                JOptionPane.showMessageDialog(null, "One or more fields are empty.", "Empty Fields", 2);
            }
        }
    }

    //returns true if some field is empty
    @Override
    public boolean verifyEmptyFields(){
        return super.verifyEmptyFields()||rview.salary.getText().equals("");
    }
}
