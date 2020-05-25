package Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class AreYouChildOrParentView extends Jframe {
    public JLabel title,title2;
    public JButton child,parent;
    public String familyUsername;
    int width=700,height=600;
    public AreYouChildOrParentView(String familyUsername){
        super(700);
        this.familyUsername=familyUsername;
        setSize(700,600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.orange);
        title=new JLabel("Are you considered as");

        //Label for "Register" title
        title.setFont(new Font("David",Font.ITALIC,40));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(0,7,204));
        title.setOpaque(true);//The default is the background is transparent
        title.setBounds(width/2-275,15,500,80);
        title2=new JLabel("a child or as a parent?");
        //Label for "Register" title
        title2.setFont(new Font("David",Font.ITALIC,40));
        title2.setForeground(Color.white);
        title2.setHorizontalAlignment(SwingConstants.CENTER);
        title2.setVerticalAlignment(SwingConstants.CENTER);
        title2.setBackground(new Color(0,7,204));
        title2.setOpaque(true);//The default is the background is transparent
        title2.setBounds(width/2-275,95,500,80);
        title.setBorder(BorderFactory.createMatteBorder(8,8,0,8,new Color(153, 0, 92)));
        title2.setBorder(BorderFactory.createMatteBorder(0,8,8,8,new Color(153, 0, 92)));




        child=new JButton("Child");
        parent=new JButton("Parent");
        addButtons(width/2-275,230,550,150);
        add(child);
        add(parent);
        add(title2);
        add(title);
        setVisible(true);
    }
    public void addButtons(int x,int y,int width,int height){
        Font f=new Font("David",Font.ITALIC,40);
        Cursor c=new Cursor(Cursor.HAND_CURSOR);
        child.setBounds(x,y,width,height);
        parent.setBounds(x,y+height+30,width,height);
        child.setFont(f);
        parent.setFont(f);
        child.setCursor(c);
        parent.setCursor(c);
        parent.setFocusPainted(false);
        child.setFocusPainted(false);
        child.setBorder(BorderFactory.createMatteBorder(6,6,6,6,new Color(153, 0, 92)));
        parent.setBorder(BorderFactory.createMatteBorder(6,6,6,6,new Color(153, 0, 92)));

        child.setBackground(new Color(0,7,204));
        parent.setBackground(new Color(0,7,204));
        child.setForeground(Color.white);
        parent.setForeground(Color.white);
    }
    public void addActionChild(ActionListener mal){
        child.addActionListener(mal);
    }
    public void addActionParent(ActionListener mal){
        parent.addActionListener(mal);
    }

    public void addChildListener(MouseAdapter mal){
        child.addMouseListener(mal);
    }

    public void addParentListener(MouseAdapter mal){
        parent.addMouseListener(mal);
    }
}
