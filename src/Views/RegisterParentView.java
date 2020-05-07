package Views;


import Models.Family;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class RegisterParentView extends RegisterHumanView {
    public String familyUsername;

    public JTextField jobName,salary;
    public JLabel jobNameLabel,salaryLabel;
    public JCheckBox isMarried;
    public JLabel childView;


    public RegisterParentView(String familyUsername) {
        this.familyUsername=familyUsername;
        System.out.println("Family name= "+this.familyUsername);
        Border frame=BorderFactory.createMatteBorder(1,1,1,1,Color.green);
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

        salary.setBorder(frame);
        jobName.setBorder(frame);

        childView=new JLabel("Actually, I'm a child");
        childView.setForeground(Color.green);
        childView.setFont(new Font("Arial",Font.ITALIC,20));
        childView.setBounds(50,50,180,25);


        //add
        add(childView);
        add(salary);
        add(salaryLabel);
        add(isMarried);
        add(jobName);
        add(jobNameLabel);
    }

    public void addJobnameListener(MouseAdapter mal){
        jobName.addMouseListener(mal);
        jobNameLabel.addMouseListener(mal);
    }
    public void addSalaryListener(MouseAdapter mal){
        salary.addMouseListener(mal);
        salaryLabel.addMouseListener(mal);
    }
    public void addChildViewListener(MouseAdapter mal){
        childView.addMouseListener(mal);
    }

    public void addCreateActionParent(ActionListener mal){
        create.addActionListener(mal);
    }
}