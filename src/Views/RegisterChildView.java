package Views;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;


public class RegisterChildView extends RegisterHumanView {
    public String familyUsername;

    public JCheckBox isSingle;
    public JTextField status;
    public JLabel parentView,statusLabel;


    public RegisterChildView(String familyUsername){
        super();
        this.familyUsername=familyUsername;
        isSingle=new JCheckBox("Single?");
        isSingle.setHorizontalTextPosition(SwingConstants.LEFT);
        isSingle.setBounds(620,610,180,40);
        isSingle.setFont(f);
        isSingle.setBackground(back);
        //get rid of the border which is drawn by default around the checkBox
        isSingle.setFocusPainted(false);
        isSingle.setOpaque(true);
        status=new JTextField();
        status.setBorder(BorderFactory.createMatteBorder(1,1,1,1, fore));
        status.setBackground(back);
        status.setFont(f);
        status.setForeground(fore);
        status.setBounds(240,650,240,50);
        statusLabel=new JLabel("Status:");
        statusLabel.setFont(f);
        statusLabel.setForeground(fore);
        statusLabel.setBounds(130,650,150,40);
        add(status);
        add(statusLabel);
        isSingle.setForeground(fore);
        parentView=new JLabel("Actually, I'm a parent");
        parentView.setForeground(fore);
        parentView.setFont(f);
        parentView.setBounds(50,50,270,25);
        add(parentView);
        add(isSingle);
        add(background);
        setVisible(true);
    }

    public void addStatusListener(MouseAdapter mal){
        statusLabel.addMouseListener(mal);
        status.addMouseListener(mal);
    }

    public void addStatusLimit20(KeyAdapter mal){
        status.addKeyListener(mal);
    }

    public void addParentViewListener(MouseAdapter mal){
        parentView.addMouseListener(mal);
    }

    public void addCreateChildAction(ActionListener mal){
        create.addActionListener(mal);
    }
}