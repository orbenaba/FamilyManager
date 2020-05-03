package Views;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import static Views.StartView.init_Exit_Minimize;

public class HomeView extends Jframe {
    public JLabel exit,minimize;
    public Border frameExMin;

    @Override
    public JLabel getExit() {
        return this.exit;
     }

    @Override
    public JLabel getMinimize() {
        return this.minimize;
    }
    public HomeView(){
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);//Full screen undependable platform
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);


        frameExMin=BorderFactory.createMatteBorder(1,1,1,1,Color.black);
        exit=new JLabel("X");
        minimize=new JLabel("-");
        init_Exit_Minimize(getWidth()-70,0,35,exit,minimize,frameExMin,true);//prevent code replication

        add(minimize);
        add(exit);

        setVisible(true);
    }
}
