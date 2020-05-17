package Controllers;

import Views.MyProfileView;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class MyProfileController {
    private MyProfileView mpview;


    public MyProfileController(MyProfileView mpview){
        this.mpview=mpview;
        addMinimizeAction(new RegisterController.MinimizeListeners(mpview, true), mpview.minimize);
        addExitAction(new RegisterController.ExitListeners(mpview, true), mpview.exit);
    }

}
