package Controllers;

import Views.AreYouChildOrParentView;
import Views.RegisterChildView;
import Views.RegisterParentView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AreYouChildOrParentController extends JframeController{
    private AreYouChildOrParentView cpview;
    public AreYouChildOrParentController(AreYouChildOrParentView cpview){
        super(cpview);
        this.cpview=cpview;
        cpview.addChildListener(new ChildListener());
        cpview.addParentListener(new ParentListener());
        cpview.addActionChild(new ChildAction());
        cpview.addActionParent(new ParentAction());
    }

    public class ChildAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new RegisterChildController(new RegisterChildView(cpview.familyUsername));
            cpview.dispose();
        }
    }
    public class ParentAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new RegisterParentController(new RegisterParentView(cpview.familyUsername));
            cpview.dispose();
        }
    }

    class ChildListener extends MouseAdapter{
        @Override
        public void mouseExited(MouseEvent e) {
            cpview.child.setBackground(new Color(0,7,204));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            cpview.child.setBackground(new Color(0,101,183));
        }
    }

    class ParentListener extends MouseAdapter{
        @Override
        public void mouseExited(MouseEvent e) {
            cpview.parent.setBackground(new Color(0,7,204));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            cpview.parent.setBackground(new Color(0,101,183));
        }
    }


}
