package Controllers;

import Views.RegisterFamilyView;
import Views.StartView;
import Views.LoginView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class StartController extends JframeController{
    private StartView sview;

    public StartController(StartView sview) {
        super(sview);
        this.sview = sview;
        sview.addRegisterAction(new RegisterListener());
        sview.addLoginAction(new LoginListener());
        sview.addTitleListener(new TitleListener());
    }
    class RegisterListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            new RegisterFamilyController(new RegisterFamilyView());
            sview.dispose();
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            sview.register.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sview.register.setBorder(BorderFactory.createLineBorder(new Color(195, 0, 255),2,true));
            sview.register.setForeground(new Color(195, 0, 255));
        }
        @Override
        public void mouseExited(MouseEvent e) {
            sview.register.setForeground(Color.blue);
            sview.register.setBorder(null);
        }
    }
    class LoginListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            new LoginController(new LoginView());
            sview.dispose();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            sview.login.setBorder(BorderFactory.createLineBorder(new Color(195, 0, 255), 2, true));
            sview.login.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sview.login.setForeground(new Color(195, 0, 255));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            sview.login.setBorder(null);
            sview.login.setForeground(Color.blue);
        }
    }

    class TitleListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            sview.title.setForeground(new Color(195, 0, 255));
        }

        @Override
        public void mouseExited(MouseEvent e){
            sview.title.setForeground(Color.blue);
        }

    }

}
