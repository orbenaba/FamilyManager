package Controllers;

import Views.showProfileByIdView;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class showProfileByIdController {
    private showProfileByIdView spview;

    public showProfileByIdController(showProfileByIdView spview) {
        this.spview = spview;
        addMinimizeAction(new RegisterController.MinimizeListeners(spview, true), spview.minimize);
        addExitAction(new RegisterController.ExitListeners(spview, true), spview.exit);

    }
}
