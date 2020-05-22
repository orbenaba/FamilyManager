package Controllers;

import Views.TaskView;
import Views.TasksListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TasksListController extends BaseForHomeSeqController{
    private TasksListView tlview;

    public TasksListController(TasksListView tlview){
        super(tlview);
        this.tlview=tlview;
        tlview.addTaskAction(new TaskAction());
    }

    class TaskAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new TaskController(new TaskView(tlview.username));
            tlview.dispose();
        }
    }
}
