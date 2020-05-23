package Views;


import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionListener;


public class HomeView extends Jframe {

    public JButton settingsButton,familyTreeButton,tasksButton,shoppingCartButton;

    public String username;

    public HomeView(String username){
        super(600);
        this.username=username;
        Font font=new Font("Arial",Font.BOLD,40);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);//Full screen undependable platform
        setSize(600,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);

        /**Private user settings*/
        settingsButton=new JButton("Settings");
        settingsButton.setBackground(Color.black);
        settingsButton.setForeground(Color.white);
        settingsButton.setFont(font);
        settingsButton.setBounds(10,10,250,250);

        /**Family tree*/
        familyTreeButton=new JButton("Family Tree");
        familyTreeButton.setBackground(Color.black);
        familyTreeButton.setForeground(Color.white);
        familyTreeButton.setFont(font);
        familyTreeButton.setBounds(260,10,250,250);


        /**Family's tasks*/
        tasksButton=new JButton("Tasks");
        tasksButton.setBackground(Color.black);
        tasksButton.setForeground(Color.white);
        tasksButton.setFont(font);
        tasksButton.setBounds(10,260,250,250);


        /**Family's shopping cart*/
        shoppingCartButton=new JButton("Shopping cart");
        shoppingCartButton.setBackground(Color.black);
        shoppingCartButton.setForeground(Color.white);
        shoppingCartButton.setFont(font);
        shoppingCartButton.setBounds(260,260,250,250);





        add(settingsButton);
        add(familyTreeButton);
        add(shoppingCartButton);
        add(tasksButton);

        setVisible(true);
    }
    public void addSettingsAction(ActionListener mal){
        settingsButton.addActionListener(mal);
    }

    public void addFamilyTreeAction(ActionListener mal){
        familyTreeButton.addActionListener(mal);
    }

    public void addShoppingCartAction(ActionListener mal){
        shoppingCartButton.addActionListener(mal);
    }

    public void addTasksAction(ActionListener mal){
        tasksButton.addActionListener(mal);
    }



}
