package Views;


import Models.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//This view in intended to both editing and displaying an existing task.
//When we just have a need to show(readOnly) the existing task, we'll send
//in the ctor a boolean value==>> readOnly=true
public class EditTaskView extends BaseForHomeSeqView{
    public Task task;
    public JTextArea description;
    public JLabel title,descriptionLabel,dateLabel,titleLabel,background;
    public JTextField titleText;
    public JButton editOutcome;
    public String username;
    public boolean readOnly;

    @Override
    public String getUsername() {
        return this.username;
    }

    public EditTaskView(Task oldTask, String username,boolean readOnly) {
        //new username
        this.readOnly=readOnly;
        this.username = username;
        this.task = new Task(oldTask.executedDate, username, oldTask.id);

        getContentPane().setBackground(new Color(6, 103, 172));
        title = new JLabel(readOnly?"Read - only:Task":"Edit task");
        title.setForeground(Color.white);
        title.setFont(new Font("David", Font.ITALIC, 60));
        title.setBounds(readOnly?getWidth() / 2 - 250 : getWidth()/2-200, 20, readOnly?500:400, 60);
        title.setForeground(new Color(176, 221, 252));
        title.setOpaque(true);
        title.setBackground(new Color(168, 0, 0));
        background=new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/Icons/manageBack.jpg")));
        background.setBounds(0,0,getWidth(),getHeight());
        titleLabel = new JLabel("Title:");
        titleLabel.setFont(new Font("David", Font.ITALIC, 36));
        titleLabel.setForeground(new Color(208, 0, 0));
        titleLabel.setBounds(getWidth() / 2 + 220, 200, 100, 50);
        /**Setting the title text as the current title*/
        titleText = new JTextField(oldTask.title);
        titleText.setFont(new Font("David", Font.ITALIC, 36));
        titleText.setBackground(new Color(238, 145, 145));
        titleText.setForeground(new Color(4, 62, 103));
        titleText.setBounds(getWidth() / 2 + 320, 200, 300, 50);
        titleText.setEditable(!readOnly);
        descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("David", Font.ITALIC, 36));
        descriptionLabel.setForeground(new Color(208, 0, 0));
        descriptionLabel.setBounds(getWidth() / 2 - 200, 300, 220, 50);
        /**Reading the description from the required file in order to fulfill the textArea*/
        StringBuilder outcomeContent = new StringBuilder();
        File file = new File("Tasks\\" + this.task.id + ".txt");
        Scanner myScan = null;
        try {
            myScan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (myScan.hasNextLine()) {
            outcomeContent.append(myScan.nextLine() + '\n');
        }
        myScan.close();
        description = new JTextArea(outcomeContent.toString());
        description.setBackground(new Color(238, 145, 145));
        description.setForeground(new Color(4, 62, 103));
        description.setFont(new Font("David", Font.ITALIC, 36));
        description.setBounds(getWidth() / 2 - 200, 350, 500, 350);
        description.setEditable(!readOnly);
        if (!readOnly) {
            editOutcome = new JButton("Apply changes");
            editOutcome.setBackground(new Color(238, 145, 145));
            editOutcome.setForeground(new Color(4, 62, 103));
            editOutcome.setFont(new Font("David", Font.ITALIC, 36));
            editOutcome.setBounds(getWidth() / 2 - 100, 740, 250, 50);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateLabel = new JLabel("Date executed: " + dateFormat.format(this.task.executedDate));
        dateLabel.setForeground(new Color(208, 0, 0));
        dateLabel.setFont(new Font("David", Font.ITALIC, 36));
        dateLabel.setBounds(90, 400, 400, 30);
        add(titleLabel);
        add(titleText);
        add(dateLabel);
        if (!readOnly)
            add(editOutcome);
        add(description);
        add(descriptionLabel);
        add(title);
        add(background);
    }
    public void addEditOutcomeAction(ActionListener mal){
        editOutcome.addActionListener(mal);
    }
    public void addTitle30Limit(KeyAdapter mal){
        titleText.addKeyListener(mal);
    }
}
