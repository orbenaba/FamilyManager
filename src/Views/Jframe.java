package Views;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;

import static java.awt.Font.BOLD;


public abstract class Jframe extends JFrame {
    public JLabel exit,minimize;
    public Border frameExMin;
    
    public Jframe(int width){
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(width==(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())//Full screen undependable platform
            setExtendedState(MAXIMIZED_BOTH);
        frameExMin=BorderFactory.createMatteBorder(1,1,1,1, Color.black);
        exit=new JLabel("X");
        minimize=new JLabel("-");
        int size=35;
        int x=width-70,y=0;
        minimize.setBounds(x-15,y+15,size,size);
        exit.setBounds(x+size-15,y+15,size,size);
        exit.setFont(new Font("Arial", BOLD,size));
        minimize.setFont(new Font("Arial", BOLD,size));
        exit.setForeground(Color.black);
        minimize.setForeground(Color.black);
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setBorder(frameExMin);
        minimize.setBorder(frameExMin);
        add(exit);
        add(minimize);
        setLayout(null);
        setVisible(true);
    }
    final public void addMinimizeAction(MouseAdapter mal) {
        minimize.addMouseListener(mal);
    }
    final public void addExitAction(MouseAdapter mal)
    {
        exit.addMouseListener(mal);
    }
}