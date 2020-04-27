package Views;

import Models.Family;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class LoginView extends JFrame{
   public Family family;
    JPanel p1,p2;
    JLabel title,passIcon1,userIcon,exit,minimize,registerContext;
    JTextField username;
    JPasswordField createPassword;
    Border frameTitle,frameExMin, frameTextfield;

    JButton login;


 /*   public LoginView()
    {
        //get rid of the ugly frame which is given by default
        setUndecorated(true);
        setSize(700,500);
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
        setLayout(null);
        //Yellow background
        getContentPane().setBackground(Color.yellow);
        p1=new JPanel();
        p2=new JPanel();
        title=new JLabel("Login");
    }*/
}


    /*  JPanel p1,p2;
    JLabel title,passIcon1,userIcon,exit,minimize,registerContext;
    JTextField username;
    JPasswordField createPassword;
    Border frameTitle,frameExMin, frameTextfield;

    JButton login;

    ******************

    public LoginView()
    {
        //get rid of the ugly frame which is given by default
        setUndecorated(true);
        setSize(700,500);
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
        setLayout(null);
        //Yellow background
        getContentPane().setBackground(Color.yellow);
        p1=new JPanel();
        p2=new JPanel();
        title=new JLabel("Login");


        p1.setBackground(new Color(116,119,139));
        p1.setBounds(15,15,670,100);

        p2.setBackground(new Color(205,206,213));
        p2.setBounds(15,115,670,370);

        //Label for "Register" title
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(0,84,104));
        //The default is the background is transparent
        title.setOpaque(true);
        title.setBounds(245,15,210,80);
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

        //exit && minimize the screen
        exit=new JLabel("X");
        minimize=new JLabel("-");
        exit.setBounds(640,32,32,32);
        minimize.setBounds(605,32,32,32);
        exit.setFont(new Font("Arial",Font.BOLD,28));
        minimize.setFont(new Font("Arial",Font.BOLD,28));
        exit.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        exit.setVerticalAlignment(SwingConstants.CENTER);
        minimize.setVerticalAlignment(SwingConstants.CENTER);

        //Border title
        frameTitle=BorderFactory.createMatteBorder(0,2,2,2,Color.yellow);
        title.setBorder(frameTitle);
        //Border minimize and exit
        frameExMin=BorderFactory.createMatteBorder(1,1,1,1,Color.black);
        exit.setBorder(frameExMin);
        minimize.setBorder(frameExMin);
        //Borders for each text field
        frameTextfield =BorderFactory.createMatteBorder(1,1,1,1,Color.blue);
        //button for submit the whole details which absorbed by the user
        login=new JButton("Log in");
        login.setBounds(150,420,300,50);
        login.setForeground(Color.white);
        login.setFont(new Font("Arial",Font.BOLD,22));
        login.setBackground(new Color(0,84,104));
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));

        userIcon.setBounds(30,130,50,50);
        passIcon1.setBounds(30,230,50,50);
        username.setBounds(150,130,300,50);
        createPassword.setBounds(150,230,300,50);
        //create a context to our register view in case the current user does not have account
        registerContext=new JLabel("No account? Create new one!");
        registerContext.setFont(new Font("Arial",Font.BOLD,15));
        registerContext.setBounds(150,320,210,20);
        registerContext.setForeground(Color.BLUE);




        //Keep non-overriding
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

        minimize.addMouseListener(new MouseAdapter() {
            //When moving the cursor in the [-] area, all the black content turns into white
            @Override
            public void mouseEntered(MouseEvent e) {
                Border whiteMinimizeB=BorderFactory.createMatteBorder(1,1,1,1,Color.white);
                minimize.setBorder(whiteMinimizeB);
                minimize.setForeground(Color.white);
                //In addition, in the aforementioned area the cursor turns into hand cursor
                minimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            //when turns around the whole area, the [-] area backs to his original color
            @Override
            public void mouseExited(MouseEvent e){
                minimize.setBorder(frameExMin);
                minimize.setForeground(Color.black);
            }
            //minimize the form when clicking the minimize button
            @Override
            public void mouseClicked(MouseEvent e)
            {
                setState(JFrame.ICONIFIED);
            }
        });
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border whiteExitB = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
                exit.setBorder(whiteExitB);
                exit.setForeground(Color.white);
                exit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            @Override
            public void mouseExited(MouseEvent e){
                exit.setBorder(frameExMin);
                exit.setForeground(Color.black);
            }
            //exit from the form when clicking the button
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

        });


        //clear the text field-username if it is "username"
        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (username.getText().trim().toLowerCase().equals("username")) {
                    username.setText("");
                    username.setForeground(Color.black);
                    //set a border to text field-username
                    username.setBorder(frameTextfield);
                }
                //set a yellow border to the username icon
                Border frameYellow=BorderFactory.createMatteBorder(2,2,2,2,Color.yellow);
                userIcon.setBorder(frameYellow);
            }

            //if the text is equal to username or to an empty string
            @Override
            public void focusLost(FocusEvent e) {
                if(username.getText().trim().toLowerCase().equals("username")||
                        username.getText().trim().equals(""))
                {
                    username.setText("username");
                    username.setForeground(new Color(153,153,153));
                    //remove the border from the text field
                    username.setBorder(null);
                }
                //remove the border from the icon label
                userIcon.setBorder(null);
            }
        });


        //clear the text field-username if it is "username"
        createPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                String pass=String.valueOf(createPassword.getPassword());
                if(pass.trim().toLowerCase().equals("password")) {
                    createPassword.setText("");
                    createPassword.setForeground(Color.black);
                    //set a border to text password field
                    createPassword.setBorder(frameTextfield);
                }
                //set a yellow border to the username icon
                Border frameYellow=BorderFactory.createMatteBorder(2,2,2,2,Color.yellow);
                passIcon1.setBorder(frameYellow);
            }

            //if the text is equal to username or to an empty string
            @Override
            public void focusLost(FocusEvent e) {
                String pass=String.valueOf(createPassword.getPassword());
                if(pass.trim().toLowerCase().equals("password")||
                        pass.trim().equals(""))
                {
                    createPassword.setText("password");
                    createPassword.setForeground(new Color(153,153,153));
                    //remove the border from the text field
                    createPassword.setBorder(null);
                }
                //remove the border from the icon label
                passIcon1.setBorder(null);
            }
        });



        //control the background of "register" button
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                login.setBackground(new Color(0,84,104));
            }
            @Override
            public void mouseEntered(MouseEvent e){
                login.setBackground(new Color(0,101,183));
            }
        });



        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement st;
                ResultSet rs;
                //get the username and password
                String uname = username.getText();
                String pword = String.valueOf(createPassword.getPassword());
                //create a select query to check if the username and the password exist in the DB
                String query = "SELECT * FROM users WHERE username= ? AND password = ?";
                //show a message if the username or the password fields are empty
                if(uname.trim().equals("username")){
                    JOptionPane.showMessageDialog(null,"Enter your username","Empty username",2);
                }
                else if(pword.trim().equals("password"))
                {
                    JOptionPane.showMessageDialog(null,"Enter your password","Empty password",2);
                }
                else{
                    try {
                        st = DriverManager.getConnection("jdbc:mysql://localhost:3306/family", "root", "root").prepareStatement(query);
                        st.setString(1, uname);
                        st.setString(2, pword);
                        rs = st.executeQuery();
                        if (rs.next())//username and password are correct
                        {
                            System.out.println("dkkkkkkkkk");
                        //here display new form
                        //etc:
                           menu f=new menu();
                           f.setVisible(true);
                           f.pack();
                           f.setLocationRelativeTo(null);
                           //closing the current form
                           this.dispose();

                        } else {
                            //error message
                            JOptionPane.showMessageDialog(null, "Invalid username/password", "Login Error", 2);
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }

        });


        registerContext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Border fr=BorderFactory.createMatteBorder(0,0,1,0,Color.blue);
                registerContext.setBorder(fr);
            }
            @Override
            public void mouseExited(MouseEvent e){
                registerContext.setBorder(null);
            }
            @Override
            public void mouseClicked(MouseEvent e){
                RegisterView rv=new RegisterView();
                dispose();
            }
        });




        //Very very important to be at the end of this constructor
        setVisible(true);
    }

    //Log in after receiving the info from the user
    @Override
    public void actionPerformed(ActionEvent e){

    }
}*/