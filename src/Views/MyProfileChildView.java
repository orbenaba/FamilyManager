package Views;


import Models.Child;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;


public class MyProfileChildView extends MyProfileHumanView {
    public String username;
    public JLabel statusLabel;
    public JTextField statusField;
    public JCheckBox isSingle;
    public Child child;
    @Override
    public String getUsername() {
        return this.username;
    }

    public MyProfileChildView(String username) {
        super(username);
        this.username = username;
        //Up cast
        child = (Child) human;
        getContentPane().setBackground(new Color(219, 102, 0));
        /**-----------------------------------------------------------------------------------------------------*/
        /**Now we must add the qualities which characteristic the child from parent*/
        statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("David", Font.ITALIC, 30));
        statusLabel.setForeground(Color.black);
        statusLabel.setBounds(width / 2 - 400, 670, 150, 35);
        statusField = new JTextField(child.status);
        statusField.setBounds(width / 2 - 250, 670, 300, 50);
        statusField.setFont(new Font("David", Font.ITALIC, 30));
        statusField.setBackground(Color.orange);
        isSingle = new JCheckBox("Single?");
        isSingle.setHorizontalTextPosition(SwingConstants.LEFT);
        isSingle.setFont(new Font("David", Font.ITALIC, 30));
        isSingle.setBackground(new Color(219, 102, 0));
        //get rid of the border which is drawn by default around the checkBox
        isSingle.setFocusPainted(false);
        isSingle.setOpaque(true);
        isSingle.setForeground(Color.black);
        isSingle.setSelected(child.isSingle);
        isSingle.setBounds(width / 2 - 397, 740, 300, 50);
        add(isSingle);
        add(statusLabel);
        add(statusField);
        add(background);
        setVisible(true);
    }

    public void addLimit20CharactersStatus(KeyAdapter mal){
        statusField.addKeyListener(mal);
    }
}
