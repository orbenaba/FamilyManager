package Controllers;

import Views.EditTaskView;
import Views.ShoppingCartView;
import Views.TasksListView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class EditTaskController extends BaseForHomeSeqController {
    private EditTaskView eoview;

    public EditTaskController(EditTaskView eoview) {
        super(eoview);
        this.eoview = eoview;
        eoview.addEditOutcomeAction(new EditOutcomeAction());
    }

    class EditOutcomeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            eoview.task.title = eoview.titleText.getText();
            mappingTextareaIntoFile(eoview.task.id, eoview.description, "Tasks");//saving bio in file
            eoview.task.updateTask();
            new TasksListController(new TasksListView(eoview.username));
            eoview.dispose();
        }
    }
}
