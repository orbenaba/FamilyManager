package Views;


public class TasksView extends BaseForHomeSeqView {
    public String username;
    @Override
    public String getUsername(){
        return this.username;
    }

    public TasksView(String username)
    {
        this.username=username;
    }
}
