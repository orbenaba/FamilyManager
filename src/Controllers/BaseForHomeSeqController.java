package Controllers;

import Views.BaseForHomeSeqView;
import Views.HomeView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public abstract class BaseForHomeSeqController extends JframeController{
    private BaseForHomeSeqView baseView;
    public BaseForHomeSeqController(BaseForHomeSeqView baseView){
        super(baseView);
        this.baseView=baseView;
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
