package Controllers;

import Views.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static Views.FamilyTreeView.TreeNode;


public class FamilyTreeController extends BaseForHomeSeqController {
    private FamilyTreeView ftview;

    public FamilyTreeController(FamilyTreeView ftview) {
        super(ftview);
        this.ftview=ftview;
        ftview.addLeavesListener(new LeavesListener());
        ftview.addLeafAction(new LeafAction());

    }
    //For each leaf we set the cursor into HAND type
    class LeavesListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            Cursor cursor=new Cursor(Cursor.HAND_CURSOR);
            ftview.parents.forEach(parent->parent.button.setCursor(cursor));
            ftview.children.forEach(child->child.button.setCursor(cursor));
        }
    }

    //Identifying the clicked leaf and display its match user
    class LeafAction implements ActionListener {
        String usernameProfile, myUsername;

        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            TreeNode clickedButton = null;
            boolean clickedOnParent=false;
            for (TreeNode button : ftview.children) {
                if (src == button.button) {
                    clickedButton = button;
                    break;
                }
            }
            if (clickedButton == null) {
                for (TreeNode button : ftview.parents) {
                    if (src == button.button) {
                        clickedButton = button;
                        clickedOnParent=true;
                        break;
                    }
                }
            }
            myUsername = ftview.username;
            usernameProfile = clickedButton.uname;
            if (myUsername.equals(usernameProfile)) {
                if (ftview.isParent) {
                    new MyProfileParentController(new MyProfileParentView(ftview.username));
                } else
                    new MyProfileChildController(new MyProfileChildView(ftview.username));
            } else
                new showProfileByIdController(new showProfileByIdView(myUsername, usernameProfile,ftview.isParent,clickedOnParent));
            ftview.dispose();
        }
    }
}
