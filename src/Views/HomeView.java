package Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


public class HomeView extends Jframe {
    public JButton settingsButton,familyTreeButton,tasksButton,shoppingCartButton;
    public JLabel background,logOff;
    public String username;

    public HomeView(String username){
        super((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        this.username=username;
        Font font=new Font("David",Font.BOLD,50);
        /**Adding background*/
        int width=getWidth(),height=getHeight();
        background=new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/Icons/homeBack.jpg")));
        background.setBounds(0,0,width,height);
        /**Private user settings*/
        settingsButton=new JButton("Settings");
        settingsButton.setBackground(Color.blue);
        settingsButton.setForeground(Color.white);
        settingsButton.setFont(font);
        settingsButton.setBounds(width/2-450,height/2-300,400,250);
        settingsButton.setFocusPainted(false);
        settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /**Family tree*/
        familyTreeButton=new JButton("Family Tree");
        familyTreeButton.setBackground(Color.blue);
        familyTreeButton.setForeground(Color.white);
        familyTreeButton.setFont(font);
        familyTreeButton.setBounds(width/2,height/2-300,400,250);
        familyTreeButton.setFocusPainted(false);
        familyTreeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /**Family's tasks*/
        tasksButton=new JButton("Tasks");
        tasksButton.setBackground(Color.blue);
        tasksButton.setForeground(Color.white);
        tasksButton.setFont(font);
        tasksButton.setBounds(width/2-450,height/2,400,250);
        tasksButton.setFocusPainted(false);
        tasksButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /**Family's shopping cart*/
        shoppingCartButton=new JButton("Shopping cart");
        shoppingCartButton.setBackground(Color.blue);
        shoppingCartButton.setForeground(Color.white);
        shoppingCartButton.setFont(font);
        shoppingCartButton.setBounds(width/2,height/2,400,250);
        shoppingCartButton.setFocusPainted(false);
        shoppingCartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /**Adding an option to log off in homeView*/
        logOff=new JLabel("Log off");
        logOff.setFont(new Font("David",Font.ITALIC,35));
        logOff.setForeground(new Color(0, 30, 255));
        logOff.setBounds(width/2-65,750,130,40);

        add(logOff);
        add(settingsButton);
        add(familyTreeButton);
        add(shoppingCartButton);
        add(tasksButton);
        add(background);
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

    public void addLogoffListener(MouseAdapter mal){
        logOff.addMouseListener(mal);
    }

}
