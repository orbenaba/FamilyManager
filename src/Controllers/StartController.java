package Controllers;

import Views.StartView;
import Views.LoginView;
import Views.RegisterView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartController extends MouseAdapter {
    private StartView sview;

    public StartController(StartView sview) {
        this.sview = sview;


        //Add listeners to minimize label
        sview.addMinimizeAction(new MinimizeListeners());
        sview.addExitAction(new ExitListeners());
        sview.addRegisterAction(new RegisterListener());
        sview.addLoginAction(new LoginListener());
    }

    class MinimizeListeners extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            sview.setState(JFrame.ICONIFIED);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            Border whiteMinimizeB=BorderFactory.createMatteBorder(1,1,1,1,Color.white);
            sview.minimize.setBorder(whiteMinimizeB);
            sview.minimize.setForeground(Color.white);
            //In addition, in the aforementioned area the cursor turns into hand cursor
            sview.minimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            sview.minimize.setBorder(sview.frameExMin);
            sview.minimize.setForeground(Color.black);
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
            sview.exit.setBorder(whiteExitB);
            sview.exit.setForeground(Color.white);
            sview.exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            sview.exit.setBorder(sview.frameExMin);
            sview.exit.setForeground(Color.black);
        }
    }
    class RegisterListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            RegisterView rv = new RegisterView();
            sview.dispose();
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            Border fr = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red);
            sview.register.setBorder(fr);
        }
        @Override
        public void mouseExited(MouseEvent e) {
            sview.register.setBorder(null);
        }
    }
    class LoginListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            LoginView rv = new LoginView();
            sview.dispose();
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            Border fr = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.red);
            sview.login.setBorder(fr);
        }
        @Override
        public void mouseExited(MouseEvent e) {
            sview.login.setBorder(null);
        }
    }

}
