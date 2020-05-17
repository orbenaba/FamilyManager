package Controllers;

import Views.FamilyTreeView;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class FamilyTreeController {
    private FamilyTreeView ftview;


    public FamilyTreeController(FamilyTreeView ftview){
        this.ftview=ftview;
        addMinimizeAction(new RegisterController.MinimizeListeners(ftview, true), ftview.minimize);
        addExitAction(new RegisterController.ExitListeners(ftview, true), ftview.exit);
    }
}
