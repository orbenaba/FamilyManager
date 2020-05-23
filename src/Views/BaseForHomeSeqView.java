package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;


public class BaseForHomeSeqView extends Jframe {
    public JLabel backToHome;
    public String getUsername(){
        return null;
    }
    public BaseForHomeSeqView(){
        super((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
//        setExtendedState(JFrame.MAXIMIZED_BOTH);//Full screen undependable platform
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        backToHome=new JLabel();
        backToHome.setBounds(10,10,60,60);
        backToHome.setIcon(new ImageIcon(getClass().getResource("/Icons/homeIcon.png")));
        add(backToHome);
    }

    public void addBackHomeListener(MouseAdapter mal){
        backToHome.addMouseListener(mal);
    }
}
