package Controllers;

import Views.StartView;
import Views.LoginView;
import Views.RegisterView;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class StartController extends MouseAdapter {
    private StartView sview;

    public StartController(StartView sview) {
        this.sview = sview;


        //Add listeners to minimize label
        addMinimizeAction(new RegisterController.MinimizeListeners(sview,true),sview.minimize);
        addExitAction(new RegisterController.ExitListeners(sview,true),sview.exit);
        sview.addRegisterAction(new RegisterListener());
        sview.addLoginAction(new LoginListener());
    }
    class RegisterListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            RegisterView rv = new RegisterView();
            RegisterController rc=new RegisterController(rv);
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
            LoginView lv=new LoginView();
            LoginController rv = new LoginController(lv);
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
