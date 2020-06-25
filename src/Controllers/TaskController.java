package Controllers;


import Models.Task;
import Views.TaskView;
import Views.TasksListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Calendar;

import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class TaskController extends BaseForHomeSeqController {
    private TaskView tview;

    public TaskController(TaskView tview) {
        super(tview);
        this.tview = tview;

        tview.addTitle30Limit(new Title30Limit());
        tview.addAddTaskAction(new AddTaskAction());
    }
    class Title30Limit extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (tview.titleText.getText().length() >= 30) // limit textfield to 8 characters
                e.consume();
        }
    }

    class AddTaskAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Task task=new Task(new Date(Calendar.getInstance().getTimeInMillis()), tview.username,tview.titleText.getText().trim().equals("")?"No title":tview.titleText.getText());
            Integer id = (Integer)tview.tasksList.addTask(task);
            mappingTextareaIntoFile(id, tview.description, "Tasks");
            new TasksListController(new TasksListView(tview.username));
            tview.dispose();
        }
    }
}