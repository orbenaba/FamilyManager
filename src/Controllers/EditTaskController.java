package Controllers;

import Views.EditTaskView;
import Views.TasksListView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class EditTaskController extends BaseForHomeSeqController {
    private EditTaskView eoview;

    public EditTaskController(EditTaskView eoview) {
        super(eoview);
        this.eoview = eoview;
        if (!eoview.readOnly) {
            eoview.addTitle30Limit(new Title30Limit());
            eoview.addEditOutcomeAction(new EditOutcomeAction());
        }
    }
    class Title30Limit extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (eoview.titleText.getText().length() >= 30) // limit textfield to 8 characters
                e.consume();
        }
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
