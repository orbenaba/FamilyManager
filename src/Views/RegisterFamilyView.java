package Views;

import Models.Family;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import static Views.RegisterView.addTitle;
import static Views.RegisterView.decorateBackground;
import static Views.StartView.init_Exit_Minimize;

public class RegisterFamilyView extends Jframe {
    public Family family;
    JPanel p1,p2;
    JLabel title;
    public JTextField username,lastname;
    public JPasswordField createPassword,confirmPassword;
    public JLabel lastnameLabel, usernameLabel,confirmPasswordLabel, createPasswordLabel,loginContext;
    public JButton create;


    public RegisterFamilyView()
    {
        super(700);
        //get rid of the ugly frame which is given by default
        setUndecorated(true);
        setSize(700,650);
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        p1=new JPanel();
        p2=new JPanel();
        decorateBackground(p1,p2,this,getWidth()-30,getHeight());
        title=new JLabel("Create a family account!");
        addTitle(title,170,15,360,80);


        lastnameLabel=new JLabel("Last name:");
        usernameLabel=new JLabel("Username:");
        createPasswordLabel=new JLabel("Password:");
        confirmPasswordLabel=new JLabel("Confirm password:");
        addJLabels(lastnameLabel,usernameLabel,createPasswordLabel,confirmPasswordLabel,60,160);
        lastname=new JTextField();
        username=new JTextField();
        confirmPassword=new JPasswordField();
        createPassword=new JPasswordField();
        addFields(lastname,username,confirmPassword,createPassword,250,160);
        //Add  create button
        create=new JButton("Create");
        create.setBounds(150,570,300,50);
        create.setForeground(Color.white);
        create.setFont(new Font("Arial",Font.BOLD,22));
        create.setBackground(new Color(0,84,104));
        create.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /**Add a context to login view*/
        loginContext=new JLabel("Already have an account?");
        loginContext.setFont(new Font("Arial",Font.BOLD,15));
        loginContext.setBounds(200,500,185,20);
        loginContext.setForeground(Color.BLUE);


        add(loginContext);
        add(create);
        add(lastnameLabel);
        add(usernameLabel);
        add(createPasswordLabel);
        add(confirmPasswordLabel);
        add(lastname);
        add(username);
        add(createPassword);
        add(confirmPassword);
        add(title);
        add(p1);
        add(p2);
        setVisible(true);
    }

    public void addFields(JTextField lastname,JTextField username,JPasswordField createPassword,JPasswordField confirmPassword,int x,int y){
        Font f=new Font("Arial",Font.BOLD,20);
        lastname.setBounds(x,y,230,36);
        username.setBounds(x,y+71,230,36);
        createPassword.setBounds(x,y+142,230,36);
        confirmPassword.setBounds(x,y+213,230,36);
        lastname.setFont(f);
        username.setFont(f);
        createPassword.setFont(f);
        confirmPassword.setFont(f);
    }
    public void addJLabels(JLabel lastnameLabel,JLabel usernameLabel,JLabel createPasswordLabel,JLabel confirmPasswordLabel,int x,int y) {
        Font f = new Font("Arial", Font.BOLD, 20);
        lastnameLabel.setBounds(x, y, 180, 30);
        usernameLabel.setBounds(x, y + 71, 180, 30);
        createPasswordLabel.setBounds(x, y + 142, 220, 30);
        confirmPasswordLabel.setBounds(x, y + 213, 220, 30);
        lastnameLabel.setFont(f);
        usernameLabel.setFont(f);
        createPasswordLabel.setFont(f);
        confirmPasswordLabel.setFont(f);
    }
    //Submitting the form, verifying the input is valid, if so- updating the DB
    public void addCreateAction(ActionListener mal){
        create.addActionListener(mal);
    }
    //When hovering above the button it changes its color
    public void addCreateMouse(MouseAdapter mal) {
        create.addMouseListener(mal);
    }
    public void addLoginContextListener(MouseAdapter mal){
        loginContext.addMouseListener(mal);
    }

}
