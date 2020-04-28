package Views;

import Models.Family;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static Views.StartView.init_Exit_Minimize;


public class LoginView extends JFrame {
     public Family family;
     JPanel p1, p2;
     public JLabel title, passIcon1, userIcon, exit, minimize, registerContext;
     public JTextField username;
     public JPasswordField createPassword;
     public Border frameTitle, frameExMin, frameTextfield;

     public JButton login;

     public LoginView() {
          //get rid of the ugly frame which is given by default
          setUndecorated(true);
          setSize(700, 500);
          setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
          setLocationRelativeTo(null);
          setLayout(null);
          decorateBackground();
          addTitle();
          exit=new JLabel("X");
          minimize=new JLabel("-");
          frameExMin=BorderFactory.createMatteBorder(1,1,1,1,Color.black);
          init_Exit_Minimize(getWidth()-85,15,35,exit,minimize,frameExMin);//prevent code replication
          addSubmitBTN();
          addLoginForm();
          add(login);
          add(exit);
          add(minimize);
          add(username);
          add(createPassword);
          add(userIcon);
          add(passIcon1);
          add(title);
          add(registerContext);
          add(p1);
          add(p2);

          setVisible(true);
     }
     public void decorateBackground() {
          //Yellow background
          getContentPane().setBackground(Color.yellow);
          p1 = new JPanel();
          p2 = new JPanel();
          p1.setBackground(new Color(116,119,139));
          p1.setBounds(15,15,670,100);
          p2.setBackground(new Color(205,206,213));
          p2.setBounds(15,115,670,370);
     }
     public void addTitle() {
          //Label for "Login" title
          title = new JLabel("Login");
          title.setFont(new Font("Arial", Font.BOLD, 30));
          title.setForeground(Color.white);
          title.setHorizontalAlignment(SwingConstants.CENTER);
          title.setVerticalAlignment(SwingConstants.CENTER);
          title.setBackground(new Color(0, 84, 104));
          //The default is the background is transparent
          title.setOpaque(true);
          title.setBounds(245, 15, 210, 80);
     }
     public void addLoginForm(){
          //username+password icons
          ImageIcon u=new ImageIcon(getClass().getResource("/Icons/userIcon.jpg"));
          ImageIcon pii1=new ImageIcon(getClass().getResource("/Icons/passIcon.png"));
          passIcon1=new JLabel();
          userIcon=new JLabel();
          passIcon1.setIcon(pii1);
          userIcon.setIcon(u);
          username=new JTextField("username");
          createPassword=new JPasswordField("password");


          username.setFont(new Font("Arial",Font.BOLD,18));
          createPassword.setFont(new Font("Arial",Font.BOLD,18));

          //Border title
          frameTitle=BorderFactory.createMatteBorder(0,2,2,2,Color.yellow);
          title.setBorder(frameTitle);
          //Border minimize and exit
          frameExMin=BorderFactory.createMatteBorder(1,1,1,1,Color.black);
          exit.setBorder(frameExMin);
          minimize.setBorder(frameExMin);
          //Borders for each text field
          frameTextfield =BorderFactory.createMatteBorder(1,1,1,1,Color.blue);

          userIcon.setBounds(30,130,50,50);
          passIcon1.setBounds(30,230,50,50);
          username.setBounds(150,130,300,50);
          createPassword.setBounds(150,230,300,50);
          //create a context to our register view in case the current user does not have account
          registerContext=new JLabel("No account? Create new one!");
          registerContext.setFont(new Font("Arial",Font.BOLD,15));
          registerContext.setBounds(150,320,210,20);
          registerContext.setForeground(Color.BLUE);
     }

     public void addSubmitBTN(){
          //button for submit the whole details which absorbed by the user
          login=new JButton("Log in");
          login.setBounds(150,420,300,50);
          login.setForeground(Color.white);
          login.setFont(new Font("Arial",Font.BOLD,22));
          login.setBackground(new Color(0,84,104));
          login.setCursor(new Cursor(Cursor.HAND_CURSOR));
     }


     //Add mouse listeners
     public void addMinimizeAction(MouseAdapter mal) {
          minimize.addMouseListener(mal);
     }
     public void addExitAction(MouseAdapter mal){
          exit.addMouseListener(mal);
     }
     public void addUsernameFocus(FocusAdapter mal) {
          username.addFocusListener(mal);
     }
     public void addPasswordFocus(FocusAdapter mal) {
          createPassword.addFocusListener(mal);
     }
     public void addRegisterContextAction(MouseAdapter mal){
          registerContext.addMouseListener(mal);
     }
     public void addLoginListener(ActionListener mal){
          login.addActionListener(mal);
     }
     public void addLoginMouse(MouseAdapter mal) {
          login.addMouseListener(mal);
     }
}