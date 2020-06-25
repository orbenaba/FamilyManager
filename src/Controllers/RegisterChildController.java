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
                        boolean isSingle = rview.isSingle.isSelected();
                        String status = rview.status.getText();
                        String pass = String.valueOf(rview.password.getPassword());
                        try {
                            statement = createAccount(username, birthday, familyUsername, firstName, genderId, rview.imagePath == null ? null : new FileInputStream(new File(rview.imagePath))
                                    , isSingle, "", pass, -1, false, status);
                            if (statement.equals("")) {
                                mappingTextareaIntoFile(username, rview.bio, "Biographies");//saving bio in file
                                new HomeController(new HomeView(rview.username.getText()));
                                rview.dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, statement, "Taken username.", 2);
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
            }
            else {
                JOptionPane.showMessageDialog(null, "One or more fields are empty.", "Empty fields", 2);
            }
        }
    }

    @Override
    protected boolean verifyEmptyFields() {
        return super.verifyEmptyFields()||rview.status.getText().trim().equals("");
    }
}
