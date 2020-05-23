package Views;


import Models.TasksList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class TaskView extends BaseForHomeSeqView {
    public String username;
    public JTextArea description;
    public JLabel title,descriptionLabel,dateLabel,titleLabel;
    public JTextField titleText;
    public JButton addTask;
    public TasksList tasksList;
    public JScrollPane pane;

    @Override
    public String getUsername(){
        return this.username;
    }

    public TaskView(String username,TasksList tasksList) {
        this.tasksList=tasksList;
        this.username = username;
        getContentPane().setBackground(new Color(6, 103, 172));

        title=new JLabel("New task:");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.ITALIC,50));
        title.setBounds(getWidth()/2-200,20,400,60);
        title.setForeground(new Color(176, 221, 252));




        titleLabel=new JLabel("Title:");
        titleLabel.setFont(new Font("Arial",Font.ITALIC,30));
        titleLabel.setForeground(new Color(176, 221, 252));
        titleLabel.setBounds(getWidth()/2+220,200,100,50);
        titleText=new JTextField();
        titleText.setFont(new Font("Arial",Font.ITALIC,30));
        titleText.setBackground(new Color(176, 221, 252));
        titleText.setForeground(new Color(4, 62, 103));
        titleText.setBounds(getWidth()/2+320,200,300,50);







        descriptionLabel=new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial",Font.ITALIC,30));
        descriptionLabel.setForeground(new Color(176, 221, 252));
        descriptionLabel.setBounds(getWidth()/2-200,300,220,50);
        description=new JTextArea(4,30);
        description.setBackground(new Color(176, 221, 252));
        description.setForeground(new Color(4, 62, 103));
        description.setFont(new Font("Arial",Font.ITALIC,30));
    //    description.setBounds(getWidth()/2-200,350,500,350);
   //     description.setColumns(34);
        pane = new JScrollPane(description, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(getWidth()/2-200,350,500,350);
        pane.setPreferredSize(new Dimension(500,350));


        addTask=new JButton("Add");
        addTask.setBackground(new Color(176, 221, 252));
        addTask.setForeground(new Color(4, 62, 103));
        addTask.setFont(new Font("Arial",Font.ITALIC,30));
        addTask.setBounds(getWidth()/2-100,740,200,50);



        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateLabel=new JLabel("Today is "+ dateFormat.format(date));
        dateLabel.setForeground(new Color(176, 221, 252));
        dateLabel.setFont(new Font("Arial",Font.ITALIC,30));
        dateLabel.setBounds(100,200,320,30);



        add(titleLabel);
        add(titleText);
        add(dateLabel);
        add(addTask);
        add(pane);
        add(descriptionLabel);
        add(title);
    }


    public void addAddTaskAction(ActionListener mal){
        addTask.addActionListener(mal);
    }

}
