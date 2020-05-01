package Controllers;

import Views.*;

public class RegisterParentController extends RegisterHumanController {
    private RegisterParentView rview;

    public RegisterParentController(RegisterParentView rview) {
        super(rview);
        this.rview=rview;
    }

}
