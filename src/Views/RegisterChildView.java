package Views;


import Models.Family;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class RegisterChildView extends RegisterHumanView {
    public String familyUsername;

    public JLabel statusLabel;
    public JCheckBox isSingle;
    public JTextField status;
    public JLabel parentView;


    public RegisterChildView(String familyUsername){
        this.familyUsername=familyUsername;
        isSingle=new JCheckBox("Single?");
        statusLabel=new JLabel("Status:");
        status=new JTextField();
        status.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.green));

        status.setBounds(490,350,200,40);
        statusLabel.setBounds(340,350,150,40);

        isSingle.setHorizontalTextPosition(SwingConstants.LEFT);
        isSingle.setBounds(620,550,180,40);

        Font f=new Font("Arial",Font.BOLD,25);
        status.setFont(f);
        statusLabel.setFont(f);
        isSingle.setFont(f);

        Color color=new Color(48,48,48);
        status.setBackground(color);
        isSingle.setBackground(Color.black);
        //get rid of the border which is drawn by default around the checkBox
        isSingle.setFocusPainted(false);
        isSingle.setOpaque(true);


        statusLabel.setForeground(Color.green);
        status.setForeground(Color.green);
        isSingle.setForeground(Color.green);

        parentView=new JLabel("Actually, I'm a parent");
        parentView.setForeground(Color.green);
        parentView.setFont(new Font("Arial",Font.ITALIC,20));
        parentView.setBounds(50,50,200,25);


        add(parentView);
        add(status);
        add(statusLabel);
        add(isSingle);
    }

    public void addStatusListener(MouseAdapter mal){
        statusLabel.addMouseListener(mal);
        status.addMouseListener(mal);
    }

    public void addParentViewListener(MouseAdapter mal){
        parentView.addMouseListener(mal);
    }

    public void addCreateChildAction(ActionListener mal){
        create.addActionListener(mal);
    }
}