package Controllers;

import Views.Jframe;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class JframeController  {
    private Jframe jframe;

    public JframeController(Jframe jframe){
        this.jframe=jframe;
        jframe.addMinimizeAction(new MinimizeListeners());
        jframe.addExitAction(new ExitListeners());
    }

    class MinimizeListeners extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            jframe.setState(Jframe.ICONIFIED);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            Border whiteMinimizeB = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
            jframe.minimize.setBorder(whiteMinimizeB);
            jframe.minimize.setForeground(Color.white);
            jframe.minimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            Border blackMinimizeB = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
            jframe.minimize.setBorder(blackMinimizeB);
            jframe.minimize.setForeground(Color.black);
        }
    }

    class ExitListeners extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.exit(0);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            Border whiteExitB = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
            jframe.exit.setBorder(whiteExitB);
            jframe.exit.setForeground(Color.white);
            jframe.exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            jframe.exit.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
            jframe.exit.setForeground(Color.black);
        }
    }

}
