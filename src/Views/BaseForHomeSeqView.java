package Views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;

import static java.awt.Font.BOLD;

public class BaseForHomeSeqView extends Jframe {
    public JLabel exit,minimize,backToHome;
    public Border frameExMin;

    @Override
    public JLabel getExit() {
        return exit;
    }
    @Override
    public JLabel getMinimize() {
        return minimize;
    }

    public String getUsername(){
        return null;
    }
    public BaseForHomeSeqView(){
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);//Full screen undependable platform
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        frameExMin=BorderFactory.createMatteBorder(1,1,1,1, Color.black);
        exit=new JLabel("X");
        minimize=new JLabel("-");
        init_Exit_Minimize(getWidth()-70,0,35);//prevent code replication

        backToHome=new JLabel();
        backToHome.setBounds(10,10,60,60);
        backToHome.setIcon(new ImageIcon(getClass().getResource("/Icons/homeIcon.png")));

        add(backToHome);
        add(exit);
        add(minimize);
    }

    public void init_Exit_Minimize(int x,int y,int size)
    {
        minimize.setBounds(x,y,size,size);
        exit.setBounds(x+size,y,size,size);
        exit.setFont(new Font("Arial", BOLD,size));
        minimize.setFont(new Font("Arial", BOLD,size));
        exit.setForeground(Color.black);
        minimize.setForeground(Color.black);
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setBorder(frameExMin);
        minimize.setBorder(frameExMin);
    }
    public void addBackHomeListener(MouseAdapter mal){
        backToHome.addMouseListener(mal);
    }
}
