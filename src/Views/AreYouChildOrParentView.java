package Views;

import Models.Family;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

import static Views.RegisterView.addTitle;
import static Views.StartView.init_Exit_Minimize;

public class AreYouChildOrParentView extends Jframe {
    public JLabel exit,minimize,title,title2;
    public Border frameExMin;
    public JButton child,parent;
    public Family family;

    @Override
    public JLabel getExit() {
        return this.exit;
    }
    @Override
    public JLabel getMinimize() {
        return this.minimize;
    }

    public AreYouChildOrParentView(Family family){
        this.family=family;
        setSize(500,500);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(85, 246, 44));
        setLayout(null);
        exit=new JLabel("X");
        minimize=new JLabel("-");
        frameExMin=BorderFactory.createMatteBorder(1,1,1,1, Color.black);
        init_Exit_Minimize(getWidth()-85,15,35,exit,minimize,frameExMin);//prevent code replication
        title=new JLabel("Are you considered as");
        addTitle(title,10,15,400,80);
        title2=new JLabel("a child or as a parent?");
        addTitle(title2,10,95,400,80);
        child=new JButton("Child");
        parent=new JButton("Parent");
        addButtons(15,200,400,142);
        add(child);
        add(parent);
        add(title2);
        add(title);
        add(exit);
        add(minimize);
        setVisible(true);
    }
    public void addButtons(int x,int y,int width,int height){
        Font f=new Font("Arial",Font.BOLD,30);
        Cursor c=new Cursor(Cursor.HAND_CURSOR);
        child.setBounds(x,y,width,height);
        parent.setBounds(x,y+height+10,width,height);
        child.setFont(f);
        parent.setFont(f);
        child.setCursor(c);
        parent.setCursor(c);
        child.setBackground(new Color(0,84,104));
        parent.setBackground(new Color(0,84,104));
        child.setForeground(Color.white);
        parent.setForeground(Color.white);
    }
    public void addActionChild(ActionListener mal){
        child.addActionListener(mal);
    }
    public void addActionParent(ActionListener mal){
        parent.addActionListener(mal);
    }
}
