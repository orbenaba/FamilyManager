package Views;


import Models.Task;
import Models.TasksList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import static Models.Parent.isLimitChildren;
import static Models.Parent.isParent;


public class TasksListView extends BaseForHomeSeqView {
    public TasksList tasksList;
    public String username;
    public JLabel title;
    public JPanel tasksPanel;
    public JButton addTask;
    public LinkedList<RowInTasksList> taskButtons;
    public boolean readOnly=false;
    public JLabel background,titleActivists2,titleActivists1,noTasks;
    public JLabel[]activists;

    public JScrollPane tasksScroller;
    @Override
    public String getUsername() {
        return this.username;
    }

    public TasksListView(String username) {
        if (!isParent(username))
            if (isLimitChildren(username))
                readOnly = true;
        this.username = username;
        /**Title*/
        title = new JLabel(readOnly?"View your tasks!":"Manage your tasks!");
        title.setForeground(Color.orange.brighter());
        title.setBorder(BorderFactory.createMatteBorder(0,0,3,0,Color.orange.brighter()));
        title.setFont(new Font("David", Font.ITALIC, 70));
        title.setBounds(getWidth() / 2 - 290, 20, 580, 60);
        /**Shopping cart*/
        tasksList = new TasksList(username);
        /**Add new outcome button*/
        if (!readOnly) {
            addTask = new JButton("New task");
            addTask.setFocusPainted(false);
            addTask.setFont(new Font("David", Font.ITALIC, 36));
            addTask.setBackground(new Color(238, 145, 145));
            addTask.setForeground(new Color(4, 62, 103));
            addTask.setBounds(200, 120, 250, 100);
        }
        background=new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/Icons/woodBack.jpg")));
        background.setBounds(0,0,getWidth(),getHeight());

        if(!tasksList.isEmpty()) {
            String[] activistsStatistics = tasksList.getActivistData();
            int size = activistsStatistics.length, x = 60, y = 400;
            /**Activists labels*/
            titleActivists1 = new JLabel("Who complete the most");
            titleActivists2 = new JLabel("tasks in the last month?");
            titleActivists1.setFont(new Font("David", Font.ITALIC, 40));
            titleActivists2.setFont(new Font("David", Font.ITALIC, 40));
            titleActivists1.setBackground(Color.orange.brighter());
            titleActivists2.setBackground(Color.orange.brighter());
            titleActivists1.setOpaque(true);
            titleActivists2.setOpaque(true);
            titleActivists1.setBounds(x, y - 150, 410, 50);
            titleActivists2.setBounds(x, y - 100, 410, 50);
            add(titleActivists1);
            add(titleActivists2);
            activists = new JLabel[size];
            for (int i = 0; i < size; i++) {
                activists[i] = new JLabel(((i + 1) + ") " + activistsStatistics[i]));
                activists[i].setFont(new Font("David", Font.ITALIC, 30));
                activists[i].setOpaque(true);
                activists[i].setBackground(Color.orange.brighter());
                activists[i].setBounds(x, y + i * 50, 250, 40);
                add(activists[i]);
            }
        }

        /**Tasks list panel*/
        tasksPanel = new JPanel();
        if(!tasksList.isEmpty()) {
            tasksPanel.setLayout(new GridLayout(0, readOnly ? 1 : 3, 0, 15));
            convertListToButtons(tasksList.getTasks());
            tasksScroller = new JScrollPane(tasksPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            tasksScroller.setBounds(getWidth() / 2 - 300, 250, 1000, getHeight() - 250);
            tasksScroller.setPreferredSize(new Dimension(1000, getHeight() - 250));
            /**Increasing the speed of the scrolling:*/
            tasksScroller.getVerticalScrollBar().setUnitIncrement(12);
            add(tasksScroller);
        }
        else{
            tasksPanel.setBounds(getWidth()/2-300,250,1000,getHeight()-250);
            tasksPanel.setBackground(Color.gray.darker());
            noTasks=new JLabel();
            noTasks.setIcon(new ImageIcon(getClass().getResource("/Icons/X2.jpg")));
            tasksPanel.add(noTasks);
            noTasks.setBounds(0,50,500,500);
            add(tasksPanel);
        }
        if (!readOnly)
            add(addTask);
        add(title);
        add(background);
    }

    public void convertListToButtons(LinkedList<Task> tasks) {
        int i = 0;
        taskButtons= new LinkedList<RowInTasksList>();
        Font f=new Font("David",Font.ITALIC,36);
        int red=255,green=31,blue=31;
        if(!readOnly) {
            for (Task oc : tasks) {
                RowInTasksList row = new RowInTasksList(oc,false);
                row.title.setPreferredSize(new Dimension(300, 100));
                Color currentRowColor = new Color(((red += 15) % 100) + 100, ((green += 25) % 100) + 100, 48);
                row.title.setFont(f);
                row.edit.setFont(f);
                row.delete.setFont(f);
                row.title.setBackground(currentRowColor);
                row.delete.setBackground(currentRowColor);
                row.edit.setBackground(currentRowColor);
                tasksPanel.add(row.title, BorderLayout.CENTER);
                tasksPanel.add(row.delete, BorderLayout.CENTER);
                tasksPanel.add(row.edit, BorderLayout.CENTER);
                taskButtons.add(row);
                i++;
            }
        }
        else{
            for (Task oc : tasks) {
                RowInTasksList row = new RowInTasksList(oc,true);
                row.title.setPreferredSize(new Dimension(1000, 100));
                Color currentRowColor = new Color(((red += 15) % 100) + 100, ((green += 25) % 100) + 100, 48);
                row.title.setFont(f);
                row.title.setBackground(currentRowColor);
                tasksPanel.add(row.title, BorderLayout.CENTER);
                taskButtons.add(row);
                i++;
            }
        }
    }
    public void addTaskAction(ActionListener mal) {
        addTask.addActionListener(mal);
    }

    public class RowInTasksList{
        public JButton title;
        public JButton edit,delete;
        public Task task;
        public RowInTasksList(Task task,boolean readOnly) {
            title=new JButton(task.title);
            if(!readOnly) {
                edit = new JButton("Edit");
                delete = new JButton("Delete");
            }
            this.task=task;
        }

    }
    /**Adding a listener for each delete button*/
    public void addDeletesListener(ActionListener mal){
        for(RowInTasksList ob : taskButtons)
            ob.delete.addActionListener(mal);
    }
    /**Adding a listener for each edit button*/
    public void addEditsListener(ActionListener mal){
        for(RowInTasksList ob : taskButtons)
            ob.edit.addActionListener(mal);
    }

    /**Adding a listener for each title button*/
    public void addTitlesListener(ActionListener mal){
        for(RowInTasksList ob : taskButtons)
            ob.title.addActionListener(mal);
    }
}
