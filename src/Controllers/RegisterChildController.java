package Controllers;

import Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RegisterChildController extends RegisterHumanController {
    private RegisterChildView rview;


    public RegisterChildController(RegisterChildView rview) {
        super(rview);
        this.rview = rview;
        rview.addStatusListener(new StatusListener());
        rview.addParentViewListener(new ParentViewListener());
    }

    class StatusListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.status.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.white));
            rview.statusLabel.setForeground(Color.white);
        }
        @Override
        public void mouseExited(MouseEvent e){
            rview.status.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.green));
            rview.statusLabel.setForeground(Color.green);
        }
    }

    class ParentViewListener extends MouseAdapter{
        @Override
        public void mouseExited(MouseEvent e){
            rview.parentView.setBorder(null);
        }        @Override
        public void mouseEntered(MouseEvent e){
            rview.parentView.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.green));
        }
        @Override
        public void mouseClicked(MouseEvent e){
            new RegisterParentController(new RegisterParentView(rview.family));
            rview.dispose();
        }
    }

}
