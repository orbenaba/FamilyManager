package Views;


import Models.Task;
import Models.TasksList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class TasksListView extends BaseForHomeSeqView {
    public TasksList tasksList;
    public String username;
    public JLabel title;
    public JPanel tasksPanel;
    public JButton addTask;

    public JList<RowInTasksList> taskButtons;


    public JScrollPane tasksScroller;
    @Override
    public String getUsername() {
        return this.username;
    }
    public TasksListView(String username)
    {
        this.username = username;
        getContentPane().setBackground(new Color(6, 103, 172));
        /**Title*/
        title = new JLabel("Manage your tasks!");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.ITALIC, 50));
        title.setBounds(getWidth() / 2 - 300, 20, 600, 60);
        title.setForeground(new Color(176, 221, 252));


        /**Shopping cart*/
        tasksList = new TasksList(username);



        /**Add new outcome button*/
        addTask = new JButton("New task");
        addTask.setFont(new Font("Arial", Font.PLAIN, 30));
        addTask.setBackground(new Color(176, 221, 252));
        addTask.setForeground(new Color(4, 62, 103));
        addTask.setBounds(200, 120, 250, 100);

        /**Out comes panel*/
        tasksPanel = new JPanel();
        tasksPanel.setLayout(new GridLayout(0, 3, 0, 15));
        convertListToButtons(tasksList.getTasks());
        tasksScroller = new JScrollPane(tasksPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tasksScroller.setBounds(getWidth() / 2 - 500, 250, 1000, getHeight() - 250);
        tasksScroller.setPreferredSize(new Dimension(1000, getHeight() - 250));
        /**Increasing the speed of the scrolling:*/
        tasksScroller.getVerticalScrollBar().setUnitIncrement(12);


        add(tasksScroller);
        add(addTask);
        add(title);
    }

    public void convertListToButtons(LinkedList<Task> tasks) {
        int i = 0;
        taskButtons= new JList<RowInTasksList>();
        Font f=new Font("Arial",Font.ITALIC,30);
        int red=255,green=31,blue=31;
        for (Task oc : tasks) {
            RowInTasksList row=new RowInTasksList(oc);
            row.title.setPreferredSize(new Dimension(300, 100));
            Color currentRowColor=new Color(((red+=15)%100)+100,((green+=25)%100)+100,48);

            row.title.setFont(f);
            row.edit.setFont(f);
            row.delete.setFont(f);

            row.title.setBackground(currentRowColor);
            row.delete.setBackground(currentRowColor);
            row.edit.setBackground(currentRowColor);

            tasksPanel.add(row.title, BorderLayout.CENTER);
            tasksPanel.add(row.delete, BorderLayout.CENTER);
            tasksPanel.add(row.edit, BorderLayout.CENTER);
            i++;
            System.out.println("In TaskListPanel "+oc.title);
        }
    }
    public void addTaskAction(ActionListener mal) {
        addTask.addActionListener(mal);
    }



    public class RowInTasksList{
        JButton title;
        JButton edit,delete;
        Task task;
        public RowInTasksList(JButton title, JButton edit, JButton delete,Task task) {
            this.title = title;
            this.edit = edit;
            this.delete = delete;
            this.task=task;
        }
        public RowInTasksList(Task task) {
            title=new JButton(task.title);
            edit=new JButton("Edit");
            delete=new JButton("Delete");
            this.task=task;
        }

    }
}
