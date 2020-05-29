package Controllers;

import Views.showProfileByIdView;


public class showProfileByIdController extends BaseForHomeSeqController {
    private showProfileByIdView spview;
    public showProfileByIdController(showProfileByIdView spview) {
        super(spview);
        this.spview = spview;
    }
}
