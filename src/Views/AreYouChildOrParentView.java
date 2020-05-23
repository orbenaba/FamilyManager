package Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static Views.RegisterView.addTitle;

public class AreYouChildOrParentView extends Jframe {
    public JLabel title,title2;
    public JButton child,parent;
    public String familyUsername;



    public AreYouChildOrParentView(String familyUsername){
        super(500);
        this.familyUsername=familyUsername;
        setSize(500,500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(85, 246, 44));
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
