package Controllers;

import Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class RegisterParentController extends RegisterHumanController {
    private RegisterParentView rview;

    public RegisterParentController(RegisterParentView rview) {
        super(rview);
        this.rview=rview;
        rview.addJobnameListener(new JobnameListener());
        rview.addSalaryListener(new SalaryListener());
        rview.addChildViewListener(new ChildViewListener());
    }


    class JobnameListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.jobName.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.white));
            rview.jobNameLabel.setForeground(Color.white);
        }
        @Override
        public void mouseExited(MouseEvent e){
            rview.jobName.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.green));
            rview.jobNameLabel.setForeground(Color.green);
        }
    }
    class SalaryListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            rview.salary.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.white));
            rview.salaryLabel.setForeground(Color.white);
        }
        @Override
        public void mouseExited(MouseEvent e){
            rview.salary.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.green));
            rview.salaryLabel.setForeground(Color.green);
        }
    }

    class ChildViewListener extends MouseAdapter{
        @Override
        public void mouseExited(MouseEvent e){
            rview.childView.setBorder(null);
        }        @Override
        public void mouseEntered(MouseEvent e){
            rview.childView.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.green));
        }
        @Override
        public void mouseClicked(MouseEvent e){
            new RegisterChildController(new RegisterChildView(rview.family));
            rview.dispose();
        }
    }
}
