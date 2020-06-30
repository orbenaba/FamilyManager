package Controllers;

import Views.LoginView;
import Views.RegisterFamilyView;
import Views.StartView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.nio.channels.Selector.open;


public class StartController extends JframeController {
    private StartView sview;

    public StartController(StartView sview) {
        super(sview);
        this.sview = sview;
        sview.addRegisterAction(new RegisterListener());
        sview.addLoginAction(new LoginListener());
        sview.addTitleListener(new TitleListener());
        sview.addLinkAction(new LinkAction());
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
            sview.register.setBorder(BorderFactory.createLineBorder(Color.white, 2, true));
            sview.register.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            sview.register.setForeground(Color.yellow);
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
            sview.login.setBorder(BorderFactory.createLineBorder(Color.white, 2, true));
            sview.login.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sview.login.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            sview.login.setBorder(null);
            sview.login.setForeground(Color.yellow);
        }
    }

    class TitleListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            sview.title.setForeground(Color.white);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            sview.title.setForeground(Color.yellow);
        }

    }

    class LinkAction extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            sview.link.setForeground(Color.white);
            sview.link.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            sview.link.setForeground(new Color(0, 0, 250).brighter());
            sview.link.setBorder(null);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            try {

                Desktop.getDesktop().browse(sview.linkToYoutube);

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
