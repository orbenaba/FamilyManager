package Controllers;

import Views.TasksView;


public class TasksController extends BaseForHomeSeqController{
    private TasksView tview;

    public TasksController(TasksView tview){
        super(tview);
        this.tview=tview;
    }
}
