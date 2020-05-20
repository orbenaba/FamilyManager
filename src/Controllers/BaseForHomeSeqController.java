package Controllers;

import Views.BaseForHomeSeqView;
import Views.HomeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class BaseForHomeSeqController {
    private BaseForHomeSeqView baseView;

    public BaseForHomeSeqController(BaseForHomeSeqView baseView){
        this.baseView=baseView;
        addMinimizeAction(new RegisterController.MinimizeListeners(baseView, true), baseView.minimize);
        addExitAction(new RegisterController.ExitListeners(baseView, true), baseView.exit);
        baseView.addBackHomeListener(new BackToHomeListener());
    }
    class BackToHomeListener extends MouseAdapter {
        @Override
        /**when hovering over the home label, we change its color*/
        public void mouseEntered(MouseEvent e){
            baseView.backToHome.setCursor(new Cursor(Cursor.HAND_CURSOR));
            baseView.backToHome.setIcon(new ImageIcon(getClass().getResource("/Icons/homeIconBlue.png")));
        }
        /**when out of the home label, we change its color*/
        @Override
        public void mouseExited(MouseEvent e){
            baseView.backToHome.setIcon(new ImageIcon(getClass().getResource("/Icons/homeIcon.png")));
        }
        @Override
        public void mouseClicked(MouseEvent e){
            new HomeController(new HomeView(baseView.getUsername()));
            baseView.dispose();
        }
    }
}
