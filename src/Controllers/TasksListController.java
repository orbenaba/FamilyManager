package Controllers;

import Views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TasksListController extends BaseForHomeSeqController {
    private TasksListView tlview;

    public TasksListController(TasksListView tlview) {
        super(tlview);
        this.tlview = tlview;
        if (!tlview.readOnly) {
            tlview.addTaskAction(new TaskAction());
            tlview.addDeletesListener(new DeletesListener());
            tlview.addEditsListener(new EditsListener());
        }
        tlview.addTitlesListener(new TitlesListener());
    }

    class TaskAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TaskController(new TaskView(tlview.username, tlview.tasksList));
            tlview.dispose();
        }
    }

    class DeletesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /**First, we must identify the button which was clicked in order to delete its associated Outcome*/
            Object src = e.getSource();
            TasksListView.RowInTasksList clicked = null;
            for (TasksListView.RowInTasksList row : tlview.taskButtons) {
                if (src == row.delete) {
                    clicked = row;
                    break;
                }
            }
            /**So far, we got the specific delete button which invoked*/
            if (clicked != null) {
                tlview.tasksList.deleteTask(clicked.task.id);
                /**Despite we delete the outcome from the shopping cart,
                 * We didn't yet update the view, so in order to create nice Look&Feel,
                 * we'll remove components*/
                new TasksListController(new TasksListView(clicked.task.username));
                tlview.dispose();
            }
        }
    }

    class EditsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /**First, we must identify the button which was clicked in order to delete its associated Outcome*/
            Object src = e.getSource();
            TasksListView.RowInTasksList clicked = null;
            for (TasksListView.RowInTasksList row : tlview.taskButtons) {
                if (src == row.edit) {
                    clicked = row;
                    break;
                }
            }
            /**So far, we got the specific edit button which invoked*/
            if (clicked != null) {
                /**Needs to update the username*/
                new EditTaskController(new EditTaskView(clicked.task, tlview.username,false));
                tlview.dispose();
            }
        }
    }

    class TitlesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            /**First, we must identify the button which was clicked in order to delete its associated Outcome*/
            Object src = e.getSource();
            TasksListView.RowInTasksList clicked = null;
            for (TasksListView.RowInTasksList row : tlview.taskButtons) {
                if (src == row.title) {
                    clicked = row;
                    break;
                }
            }
            /**So far, we got the specific edit button which invoked*/
            if (clicked != null) {
                /**Needs to update the username*/
                new EditTaskController(new EditTaskView(clicked.task, tlview.username,true));
                tlview.dispose();
            }

        }
    }
}
