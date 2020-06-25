package Views;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;


public class LoginView extends Jframe {
     JPanel p1, p2;
     public JLabel title, passIcon1, userIcon, registerContext;
     public JTextField username;
     public JPasswordField createPassword;
     public Border frameTitle, frameTextfield;
     public JButton login;
     Font f;

     public LoginView() {
          super(700);
          f=new Font("David",Font.ITALIC,30);
          //get rid of the ugly frame which is given by default
          setSize(700, 500);
          setLocationRelativeTo(null);
          p1=new JPanel();
          p2=new JPanel();
          getContentPane().setBackground(new Color(0,7,204));
          p1.setBackground(new Color(153, 0, 92));
          p1.setBounds(15,15,getWidth()-30,100);
          p2.setBackground(Color.ORANGE);
          p2.setBounds(15,115,getWidth()-30,getHeight()-130);
          title=new JLabel("Login");
          title.setFont(new Font("David",Font.ITALIC,40));
          title.setForeground(Color.white);
          title.setHorizontalAlignment(SwingConstants.CENTER);
          title.setVerticalAlignment(SwingConstants.CENTER);
          title.setBackground(new Color(0,7,204));
          title.setOpaque(true);//The default is the background is transparent
          title.setBounds(240,15,210,80);
          addSubmitBTN();
          addLoginForm();
          add(login);
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
     public void addLoginForm(){
          //username+password icons
          ImageIcon u=new ImageIcon(getClass().getResource("/Icons/userIcon.png"));
          ImageIcon pii1=new ImageIcon(getClass().getResource("/Icons/passIcon2.png"));
          passIcon1=new JLabel();
          userIcon=new JLabel();
          passIcon1.setIcon(pii1);
          userIcon.setIcon(u);
          username=new JTextField("username");
          createPassword=new JPasswordField("password");
          username.setFont(f);
          createPassword.setFont(f);
          //Border title
          frameTitle=BorderFactory.createMatteBorder(0,4,4,4,Color.ORANGE);
          title.setBorder(frameTitle);
          //Borders for each text field
          frameTextfield =BorderFactory.createMatteBorder(1,1,1,1,Color.blue);
          userIcon.setBounds(20,120,80,80);
          passIcon1.setBounds(30,230,50,50);
          username.setBounds(150,130,300,50);
          createPassword.setBounds(150,230,300,50);
          //create a context to our register view in case the current user does not have account
          registerContext=new JLabel("No account? Create new one!");
          registerContext.setFont(f);
          registerContext.setBounds(150,340,350,30);
          registerContext.setForeground(new Color(0,7,204));
     }
     public void addSubmitBTN(){
          //button for submit the whole details which absorbed by the user
          login=new JButton("Log in");
          login.setBounds(150,420,300,50);
          login.setForeground(Color.white);
          login.setFont(f);
          login.setBackground(new Color(0,7,204));
          login.setCursor(new Cursor(Cursor.HAND_CURSOR));
     }
     //Add listeners
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