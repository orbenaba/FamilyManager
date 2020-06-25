package Controllers;


import Views.HomeView;
import Views.RegisterChildView;
import Views.RegisterParentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static Models.Human.createAccount;
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
                        java.sql.Date birthday = new java.sql.Date(rview.dateChooser.getDate().getTime());
                        String familyUsername = rview.familyUsername;
                        String firstName = rview.firstName.getText();
                        byte genderId;
                        {
                            String gender = rview.genders.getSelectedItem().toString();
                            genderId = (byte) (gender.equals("Male") ? 0 : gender.equals("Female") ? 1 : gender.equals("Bisexual") ? 2 : 3);
                        }
                        boolean isMarried = rview.isMarried.isSelected();
                        String jobName = rview.jobName.getText();
                        String pass = String.valueOf(rview.password.getPassword());
                        double salary = Double.parseDouble(rview.salary.getText());
                        try {
                            statement = createAccount(username, birthday, familyUsername, firstName, genderId, rview.imagePath == null ? null : new FileInputStream(new File(rview.imagePath))
                                    , isMarried, jobName, pass, (int) salary, false, "");
                            if (statement.equals("")) {
                                mappingTextareaIntoFile(username, rview.bio, "Biographies");//saving bio in file
                                new HomeController(new HomeView(rview.username.getText()));
                                rview.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "This username is already taken.", "Taken username", 2);
                            }
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
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
