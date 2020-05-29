package Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;


public class RegisterFamilyView extends Jframe {
    JPanel p1,p2;
    JLabel title;
    public JTextField username,lastname;
    public JPasswordField createPassword,confirmPassword;
    public JLabel lastnameLabel, usernameLabel,confirmPasswordLabel, createPasswordLabel,loginContext;
    public JButton create;
    Font f;

    public RegisterFamilyView()
    {
        super(700);
        f=new Font("David",Font.ITALIC,30);
        //get rid of the ugly frame which is given by default
        setSize(700,650);
        setLocationRelativeTo(null);
        p1=new JPanel();
        p2=new JPanel();
        getContentPane().setBackground(new Color(0,7,204));
        p1.setBackground(new Color(153, 0, 92));
        p1.setBounds(15,15,getWidth()-30,100);
        p2.setBackground(Color.orange);
        p2.setBounds(15,115,getWidth()-30,getHeight()-130);

        title=new JLabel("Create a family account!");
        title.setFont(new Font("David",Font.ITALIC,40));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(0,7,204));
        title.setOpaque(true);//The default is the background is transparent
        title.setBounds(150,15,420,80);

        lastnameLabel=new JLabel("Last name:");
        usernameLabel=new JLabel("Username:");
        createPasswordLabel=new JLabel("Password:");
        confirmPasswordLabel=new JLabel("Confirm password:");
        addJLabels(lastnameLabel,usernameLabel,createPasswordLabel,confirmPasswordLabel,60,160);
        lastname=new JTextField();
        username=new JTextField();
        confirmPassword=new JPasswordField();
        createPassword=new JPasswordField();
        addFields(lastname,username,confirmPassword,createPassword,300,160);
        //Add  create button
        create=new JButton("Create");
        create.setBounds(150,570,300,50);
        create.setForeground(Color.white);
        create.setFont(f);
        create.setBackground(new Color(0,7,204));
        create.setCursor(new Cursor(Cursor.HAND_CURSOR));
        /**Add a context to login view*/
        loginContext=new JLabel("Already have an account?");
        loginContext.setFont(new Font("David",Font.ITALIC,20));
        loginContext.setBounds(200,515,215,20);
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
    /**Listeners*/
    public void addLimit12CharactersLName(KeyAdapter mal){
        lastname.addKeyListener(mal);
    }
    public void addLimit12CharactersUName(KeyAdapter mal){
        username.addKeyListener(mal);
    }
    public void addLimit18CharactersPass(KeyAdapter mal){
        createPassword.addKeyListener(mal);
    }
    public void addLimit18CharactersConfPass(KeyAdapter mal){
        confirmPassword.addKeyListener(mal);
    }

    public void addFields(JTextField lastname,JTextField username,JPasswordField createPassword,JPasswordField confirmPassword,int x,int y){
        lastname.setBounds(x,y,250,50);
        username.setBounds(x,y+91,250,50);
        createPassword.setBounds(x,y+182,250,50);
        confirmPassword.setBounds(x,y+273,250,50);
        lastname.setFont(f);
        username.setFont(f);
        createPassword.setFont(f);
        confirmPassword.setFont(f);
    }
    public void addJLabels(JLabel lastnameLabel,JLabel usernameLabel,JLabel createPasswordLabel,JLabel confirmPasswordLabel,int x,int y) {
        lastnameLabel.setBounds(x, y, 180, 30);
        usernameLabel.setBounds(x, y + 91, 180, 30);
        createPasswordLabel.setBounds(x, y + 182, 220, 30);
        confirmPasswordLabel.setBounds(x, y + 273, 250, 30);
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
