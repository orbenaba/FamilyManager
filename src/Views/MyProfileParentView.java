package Views;


import Models.Parent;

public class MyProfileParentView extends RegisterParentView {

    public MyProfileParentView(Parent parent){
        super(parent.familyUsername);
    }
}
