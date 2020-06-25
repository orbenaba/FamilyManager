package Views;


import Models.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;


public class MyProfileParentView extends MyProfileHumanView {
    public String username;
    public JLabel jobNameLabel,salaryLabel;
    public JTextField jobNameField,salaryField;
    public JCheckBox isMarried;
    public Parent parent;
    public JButton limitButton=null,unLimitButton;

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
        jobNameLabel = new JLabel("Job name:");
        jobNameLabel.setFont(new Font("David", Font.ITALIC, 30));
        jobNameLabel.setForeground(Color.black);
        jobNameLabel.setBounds(width / 2 - 400, 670, 150, 35);
        jobNameField = new JTextField(parent.jobName);
        jobNameField.setBounds(width / 2 - 250, 670, 300, 50);
        jobNameField.setFont(new Font("David", Font.ITALIC, 30));
        jobNameField.setBackground(Color.orange);
        isMarried = new JCheckBox("Married?");
        isMarried.setHorizontalTextPosition(SwingConstants.LEFT);
        isMarried.setFont(new Font("David", Font.ITALIC, 30));
        isMarried.setBackground(new Color(219, 102, 0));
        //get rid of the border which is drawn by default around the checkBox
        isMarried.setFocusPainted(false);
        isMarried.setOpaque(true);
        isMarried.setForeground(Color.black);
        isMarried.setSelected(parent.isMarried);
        isMarried.setBounds(width / 2 - 500, 740, 150, 50);

        salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(new Font("David", Font.ITALIC, 30));
        salaryLabel.setForeground(Color.black);
        salaryLabel.setBounds(width / 2 - 330, 745, 150, 35);
        salaryField = new JTextField(String.valueOf(parent.salary));
        salaryField.setBounds(width / 2 - 220, 745, 280, 50);
        salaryField.setFont(new Font("David", Font.ITALIC, 30));
        salaryField.setBackground(Color.orange);

        /**Adding to ability for a parent user to limit his kids in managing the family data*/
        if(parent.isLimitChildren()){
            unLimitButton = new JButton("UnLimit my children");
            unLimitButton.setBounds(30, getHeight() - 330, 300, 50);
            unLimitButton.setBackground(new Color(12, 75, 250));
            unLimitButton.setFont(new Font("David", Font.ITALIC, 25));
            unLimitButton.setForeground(Color.black);
            unLimitButton.setFocusPainted(false);
            add(unLimitButton);
        }
        else {
            limitButton = new JButton("Limit my children");
            limitButton.setBounds(30, getHeight() - 330, 250, 50);
            limitButton.setBackground(new Color(12, 75, 250));
            limitButton.setFont(new Font("David", Font.ITALIC, 25));
            limitButton.setForeground(Color.black);
            limitButton.setFocusPainted(false);
            add(limitButton);
        }
        add(salaryField);
        add(salaryLabel);
        add(isMarried);
        add(jobNameLabel);
        add(jobNameField);
        add(background);
        setVisible(true);
    }
    public void addSalary8Limit(KeyAdapter mal){
        salaryField.addKeyListener(mal);
    }
    public void addEnforcingSalary(KeyAdapter mal){
        salaryField.addKeyListener(mal);
    }
    public void addJobName20Limit(KeyAdapter mal){
        jobNameField.addKeyListener(mal);
    }
    public void addLimitAction(ActionListener mal){
        limitButton.addActionListener(mal);
    }
    public void addUnLimitAction(ActionListener mal){
        unLimitButton.addActionListener(mal);
    }

}
