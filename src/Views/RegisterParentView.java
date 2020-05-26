package Views;



import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class RegisterParentView extends RegisterHumanView {
    public String familyUsername;

    public JTextField jobName,salary;
    public JLabel jobNameLabel,salaryLabel;
    public JCheckBox isMarried;
    public JLabel childView;


    public RegisterParentView(String familyUsername) {
        this.familyUsername=familyUsername;
        Border frame=BorderFactory.createMatteBorder(1,1,1,1,fore);
        //Initialize
        salary=new JTextField();
        jobName=new JTextField();
        isMarried=new JCheckBox("Married?");
        jobNameLabel=new JLabel("Job name:");
        salaryLabel=new JLabel("Salary:");
        //set Bounds
        salary.setBounds(590,700,200,40);
        salaryLabel.setBounds(480,700,120,40);


        jobName.setBounds(275,650,200,40);
        jobNameLabel.setBounds(130,650,150,40);


        isMarried.setHorizontalTextPosition(SwingConstants.LEFT);
        isMarried.setBounds(620,610,180,40);


        salaryLabel.setFont(f);
        jobNameLabel.setFont(f);
        salary.setFont(f);
        isMarried.setFont(f);
        jobName.setFont(f);

        salary.setBackground(back);
        jobName.setBackground(back);
        isMarried.setBackground(back);
        //get rid of the border which is drawn by default around the checkBox
        isMarried.setFocusPainted(false);
        isMarried.setOpaque(true);

        salaryLabel.setForeground(fore);
        salary.setForeground(fore);
        isMarried.setForeground(fore);
        jobName.setForeground(fore);
        jobNameLabel.setForeground(fore);

        salary.setBorder(frame);
        jobName.setBorder(frame);

        childView=new JLabel("Actually, I'm a child");
        childView.setForeground(fore);
        childView.setFont(f);
        childView.setBounds(50,50,260,25);


        //add
        add(childView);
        add(salary);
        add(salaryLabel);
        add(isMarried);
        add(jobName);
        add(jobNameLabel);
        add(background);
        setVisible(true);
    }

    public void addSalary8Limit(KeyAdapter mal){
        salary.addKeyListener(mal);
    }

    public void addJobName20Limit(KeyAdapter mal){
        salary.addKeyListener(mal);
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

    public void addEnforcingSalary(KeyAdapter mal) {
        salary.addKeyListener(mal);
    }

    public void addCreateActionParent(ActionListener mal){
        create.addActionListener(mal);
    }
}