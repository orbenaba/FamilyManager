package Views;


import Models.Parent;

import javax.swing.*;
import java.awt.*;


public class MyProfileParentView extends MyProfileHumanView {
    public String username;
    public JLabel jobNameLabel,salaryLabel;
    public JTextField jobNameField,salaryField;
    public JCheckBox isMarried;
    public Parent parent;
    @Override
    public String getUsername() {
        return this.username;
    }

    public MyProfileParentView(String username) {
        super(username);
        this.username = username;
        //Up cast
        parent = (Parent) human;
        getContentPane().setBackground(new Color(219, 102, 0));
        /**-----------------------------------------------------------------------------------------------------*/
        /**Now we must add the qualities which characteristic the parent from child*/
        jobNameLabel = new JLabel("Status:");
        jobNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        jobNameLabel.setForeground(Color.black);
        jobNameLabel.setBounds(width / 2 - 400, 670, 150, 35);
        jobNameField = new JTextField(parent.jobName);
        jobNameField.setBounds(width / 2 - 250, 670, 300, 50);
        jobNameField.setFont(new Font("Arial", Font.BOLD, 25));
        jobNameField.setBackground(Color.orange);
        isMarried = new JCheckBox("Married?");
        isMarried.setHorizontalTextPosition(SwingConstants.LEFT);
        isMarried.setFont(new Font("Arial", Font.BOLD, 25));
        isMarried.setBackground(new Color(219, 102, 0));
        //get rid of the border which is drawn by default around the checkBox
        isMarried.setFocusPainted(false);
        isMarried.setOpaque(true);
        isMarried.setForeground(Color.black);
        isMarried.setSelected(parent.isMarried);
        isMarried.setBounds(width / 2 - 500, 740, 150, 50);


        salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(new Font("Arial", Font.BOLD, 25));
        salaryLabel.setForeground(Color.black);
        salaryLabel.setBounds(width / 2 - 330, 745, 150, 35);
        salaryField = new JTextField(String.valueOf(parent.salary));
        salaryField.setBounds(width / 2 - 220, 745, 280, 50);
        salaryField.setFont(new Font("Arial", Font.BOLD, 25));
        salaryField.setBackground(Color.orange);


        add(salaryField);
        add(salaryLabel);
        add(isMarried);
        add(jobNameLabel);
        add(jobNameField);
        setVisible(true);
    }
}
