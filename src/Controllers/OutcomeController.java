package Controllers;

import Views.OutcomeView;

public class OutcomeController extends BaseForHomeSeqController{
    private OutcomeView ocview;

    public OutcomeController(OutcomeView ocview) {
        super(ocview);
        this.ocview=ocview;
    }
}
