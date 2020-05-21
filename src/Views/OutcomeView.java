package Views;

public class OutcomeView extends BaseForHomeSeqView {
    public String username;

    @Override
    public String getUsername(){
        return this.username;
    }

    public OutcomeView(String username) {
        this.username = username;
    }
}
