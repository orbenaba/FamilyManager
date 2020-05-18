package Controllers;

import Views.MyProfileParentView;


public class MyProfileParentController extends RegisterParentController {
    private MyProfileParentView mpview;


    public MyProfileParentController(MyProfileParentView mpview){
        super(mpview);
        this.mpview=mpview;
    }

}
