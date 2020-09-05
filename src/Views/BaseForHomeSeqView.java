package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
//Base class to all four other classes.
//Using inheritance in order to not duplicate code.
public abstract class BaseForHomeSeqView extends Jframe {
    public JLabel backToHome;
    public abstract String getUsername();
    public BaseForHomeSeqView(){
        super((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        backToHome=new JLabel();
        backToHome.setBounds(10,10,60,60);
        backToHome.setIcon(new ImageIcon(getClass().getResource("/Icons/homeIcon.png")));
        add(backToHome);
        setVisible(true);
    }
    public void addBackHomeListener(MouseAdapter mal){
        backToHome.addMouseListener(mal);
    }
}
