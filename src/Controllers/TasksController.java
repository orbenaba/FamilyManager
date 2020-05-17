package Controllers;

import Views.TasksView;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class TasksController {
    private TasksView tview;

    public TasksController(TasksView tview){
        this.tview=tview;
        addMinimizeAction(new RegisterController.MinimizeListeners(tview, true), tview.minimize);
        addExitAction(new RegisterController.ExitListeners(tview, true), tview.exit);
    }
}
