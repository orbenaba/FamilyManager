package Controllers;

import Views.AreYouChildOrParentView;
import Views.RegisterChildView;
import Views.RegisterParentView;
import Views.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Views.RegisterView.*;

public class AreYouChildOrParentController {
    private AreYouChildOrParentView cpview;
    public AreYouChildOrParentController(AreYouChildOrParentView cpview){
        this.cpview=cpview;
        addMinimizeAction(new RegisterController.MinimizeListeners(cpview), cpview.minimize);
        addExitAction(new RegisterController.ExitListeners(cpview), cpview.exit);
        changeColor(new RegisterController.changeButtonColor(cpview.child),cpview.child);//change the colors of both buttons when hovering above em
        changeColor(new RegisterController.changeButtonColor(cpview.parent),cpview.parent);
        cpview.addActionChild(new ChildAction());
        cpview.addActionParent(new ParentAction());
    }

    public class ChildAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new RegisterChildController(new RegisterChildView(cpview.family));
            cpview.dispose();
        }
    }
    public class ParentAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new RegisterParentController(new RegisterParentView(cpview.family));
            cpview.dispose();
        }
    }


}
