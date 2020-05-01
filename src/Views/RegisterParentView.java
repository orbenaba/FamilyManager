package Views;


import Models.Family;

import javax.swing.*;
import java.awt.*;

public class RegisterParentView extends RegisterHumanView {
    public JTextField jobName,salary;
    public JLabel jobNameLabel,salaryLabel;
    public JCheckBox isMarried;


    public RegisterParentView(Family family) {
        //Initialize
        salary=new JTextField();
        jobName=new JTextField();
        isMarried=new JCheckBox("Married?");
        jobNameLabel=new JLabel("Job name:");
        salaryLabel=new JLabel("Salary:");
        //set Bounds
        salary.setBounds(570,480,200,40);
        salaryLabel.setBounds(450,480,120,40);


        jobName.setBounds(490,350,200,40);
        jobNameLabel.setBounds(340,350,150,40);


        isMarried.setHorizontalTextPosition(SwingConstants.LEFT);
        isMarried.setBounds(620,550,180,40);


        //Style
        Font f=new Font("Arial",Font.BOLD,25);
        salaryLabel.setFont(f);
        jobNameLabel.setFont(f);
        salary.setFont(f);
        isMarried.setFont(f);
        jobName.setFont(f);

        Color color=new Color(48,48,48);
        salary.setBackground(color);
        jobName.setBackground(color);
        isMarried.setBackground(Color.black);
        //get rid of the border which is drawn by default around the checkBox
        isMarried.setFocusPainted(false);
        isMarried.setOpaque(true);

        salaryLabel.setForeground(Color.green);
        salary.setForeground(Color.green);
        isMarried.setForeground(Color.green);
        jobName.setForeground(Color.green);
        jobNameLabel.setForeground(Color.green);

        //add
        add(salary);
        add(salaryLabel);
        add(isMarried);
        add(jobName);
        add(jobNameLabel);
    }
}