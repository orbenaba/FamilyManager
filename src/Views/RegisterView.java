package Views;

import Models.Family;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import static Views.StartView.init_Exit_Minimize;


public class RegisterView extends Jframe {
    JPanel p1,p2;
    public JLabel title,fnameLabel,unameLabel,passLabel,passConfLabel,phoneLabel,genderLabel,imageLabel,loginContext;
    public JTextField username,fullname,phone,gender;
    public JPasswordField createPassword,confirmPassword;
    public Border frameTitle, frameTextfield;
    public JRadioButton male,female;
    public JButton register,selectImage;
    //create a variable to set the image path in it
    public String image_path=null;
    public RegisterView()
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

        title=new JLabel("Register");
        addTitle(title,245,15,210,80);




        addRegisterForm();
        addRegisterForm();





        add(title);
        add(register);
        add(fnameLabel);
        add(unameLabel);
        add(passLabel);
        add(passConfLabel);
        add(phoneLabel);
        add(genderLabel);
        add(fullname);
        add(username);
        add(createPassword);
        add(confirmPassword);
        add(phone);
        add(male);
        add(female);
        add(selectImage);
        add(imageLabel);
        add(loginContext);
        add(title);
        add(p1);
        add(p2);
        setVisible(true);
    }
    public static void decorateBackground(JPanel p1,JPanel p2,JFrame This,int width,int height) {
        //Yellow background
        This.getContentPane().setBackground(Color.yellow);
        p1.setBackground(new Color(116,119,139));
        p1.setBounds(15,15,width,100);
        p2.setBackground(new Color(205,206,213));
        p2.setBounds(15,115,width,height-130);
    }
    public static void addTitle(JLabel title, int x, int y, int width, int height) {
        //Label for "Register" title
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(0,84,104));
        title.setOpaque(true);//The default is the background is transparent
        title.setBounds(x,y,width,height);
    }
    public void addRegisterForm(){
        //Initialize all labels' texts
        fnameLabel=new JLabel("Full Name:");
        unameLabel=new JLabel("UserName:");
        passLabel=new JLabel("Password:");
        passConfLabel=new JLabel("Confirm Password:");
        phoneLabel=new JLabel("Phone:");
        genderLabel=new JLabel("Gender:");
        //Initialize all text fields
        fullname=new JTextField();
        username=new JTextField();
        createPassword=new JPasswordField();
        confirmPassword=new JPasswordField();
        phone=new JTextField();
        //set bounds to the whole components in the current form and the font style
        fnameLabel.setBounds(30,150,150,30);
        fnameLabel.setFont(new Font("Arial",Font.BOLD,20));
        unameLabel.setBounds(30,200,150,30);
        unameLabel.setFont(new Font("Arial",Font.BOLD,20));
        passLabel.setBounds(30,250,150,30);
        passLabel.setFont(new Font("Arial",Font.BOLD,20));
        passConfLabel.setBounds(30,300,200,30);
        passConfLabel.setFont(new Font("Arial",Font.BOLD,20));
        phoneLabel.setBounds(30,350,150,30);
        phoneLabel.setFont(new Font("Arial",Font.BOLD,20));
        genderLabel.setBounds(30,400,115,30);
        genderLabel.setFont(new Font("Arial",Font.BOLD,20));
        imageLabel=new JLabel("Image:");
        imageLabel.setBounds(30,470,115,30);
        imageLabel.setFont(new Font("Arial",Font.BOLD,20));
        male=new JRadioButton("Male");
        male.setBounds(140,400,80,30);
        male.setFont(new Font("Arial",Font.BOLD,20));
        female=new JRadioButton("female");
        female.setBounds(250,400,120,30);
        female.setFont(new Font("Arial",Font.BOLD,20));
        selectImage=new JButton("Select Image");
        selectImage.setBounds(170,470,200,30);
        selectImage.setFont(new Font("Arial",Font.BOLD,20));
        fullname=new JTextField();
        fullname.setBounds(240,150,250,30);
        username=new JTextField();
        username.setBounds(240,200,250,30);
        createPassword=new JPasswordField();
        createPassword.setBounds(240,250,250,30);
        confirmPassword=new JPasswordField();
        confirmPassword.setBounds(240,300,250,30);
        phone=new JTextField();
        phone.setBounds(240,350,250,30);
        //Border title
        frameTitle=BorderFactory.createMatteBorder(0,2,2,2,Color.yellow);
        title.setBorder(frameTitle);
        //Borders for each text field
        frameTextfield =BorderFactory.createMatteBorder(1,1,1,1,Color.blue);
        //button for submit the whole details which absorbed by the user
        register=new JButton("Register");
        register.setBounds(150,570,300,50);
        register.setForeground(Color.white);
        register.setFont(new Font("Arial",Font.BOLD,22));
        register.setBackground(new Color(0,84,104));
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //create a context to the Log in view
        loginContext=new JLabel("Already have an account?");
        loginContext.setFont(new Font("Arial",Font.BOLD,15));
        loginContext.setBounds(150,530,185,20);
        loginContext.setBounds(150,530,185,20);
        loginContext.setForeground(Color.BLUE);
    }


    public void addUsernameFocus(FocusAdapter mal) {
        username.addFocusListener(mal);
    }
    public void addPasswordFocus(FocusAdapter mal) {
        createPassword.addFocusListener(mal);
    }
    public void addConfirmPasswordFocus(FocusAdapter mal) {
        confirmPassword.addFocusListener(mal);
    }
    public void addLoginContextAction(MouseAdapter mal){
        loginContext.addMouseListener(mal);
    }




    public void addRegisterListener(ActionListener mal){
        register.addActionListener(mal);
    }
    public static void changeColor(MouseAdapter mal,JButton btn) {
        btn.addMouseListener(mal);
    }





    public void enforcePhone(KeyAdapter mal){
        phone.addKeyListener(mal);
    }
    public void addSelectListener(ActionListener mal){
        selectImage.addActionListener(mal);
    }
}