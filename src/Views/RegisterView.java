package Views;


import sun.misc.JavaLangAccess;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

import static java.sql.Types.NULL;

public class RegisterView extends JFrame implements ActionListener
{
    JPanel p1,p2;
    JLabel title,exit,minimize,fnameLabel,unameLabel,passLabel,passConfLabel,phoneLabel,genderLabel,imageLabel,loginContext;
    JTextField username,fullname,phone,gender;
    JPasswordField createPassword,confirmPassword;
    Border frameTitle,frameExMin, frameTextfield;


    JRadioButton male,female;
    JButton register,selectImage;

    //create a variable to set the image path in it
    String image_path=null;

    public RegisterView()
    {
        //get rid of the ugly frame which is given by default
        setUndecorated(true);
        setSize(700,650);
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
        setLayout(null);
        //Yellow background
        getContentPane().setBackground(Color.yellow);
        p1=new JPanel();
        p2=new JPanel();
        title=new JLabel("Register");


        p1.setBackground(new Color(116,119,139));
        p1.setBounds(15,15,670,100);

        p2.setBackground(new Color(205,206,213));
        p2.setBounds(15,115,670,520);

        //Label for "Register" title
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(0,84,104));
        //The default is the background is transparent
        title.setOpaque(true);
        title.setBounds(245,15,210,80);

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




        //Keep non-overriding
        add(register);
        add(exit);
        add(minimize);
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
            }
        });


        //clear the text field-create password if it is "username"
        createPassword.addFocusListener(new FocusListener() {
            String pass=String.valueOf(createPassword.getPassword());
            @Override
            public void focusGained(FocusEvent e) {
                if (pass.trim().toLowerCase().equals("password")) {
                    createPassword.setText("");
                    createPassword.setForeground(Color.black);
                    createPassword.setBorder(frameTextfield);
                }
                //set a yellow border to the username icon
                Border frameYellow = BorderFactory.createMatteBorder(2, 2, 2, 2, Color.yellow);
            }

            //if the text is equal to password or to an empty string
            @Override
            public void focusLost(FocusEvent e) {
                if(pass.trim().toLowerCase().equals("password")||
                        pass.trim().equals(""))
                {
                    createPassword.setText("password");
                    createPassword.setForeground(new Color(153,153,153));
                    createPassword.setBorder(null);
                }
                //remove the border from the icon label
            }
        });

        //clear the text field-confirm password if it is "password"
        confirmPassword.addFocusListener(new FocusListener() {
            String pass=String.valueOf(confirmPassword.getPassword());
            @Override
            public void focusGained(FocusEvent e) {
                if (pass.trim().toLowerCase().equals("password")) {
                    confirmPassword.setText("");
                    confirmPassword.setForeground(Color.black);
                    confirmPassword.setBorder(frameTextfield);
                }
                //set a yellow border to the username icon
                Border frameYellow=BorderFactory.createMatteBorder(2,2,2,2,Color.yellow);
            }

            //if the text is equal to password or to an empty string
            @Override
            public void focusLost(FocusEvent e) {
                if(pass.trim().toLowerCase().equals("password")||
                        pass.trim().equals(""))
                {
                    confirmPassword.setText("password");
                    confirmPassword.setForeground(new Color(153,153,153));
                    confirmPassword.setBorder(null);
                }
                //remove the border from the icon label
            }
        });


        //control the background of "register" button
        register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                register.setBackground(new Color(0,84,104));
            }
            @Override
            public void mouseEntered(MouseEvent e){
                register.setBackground(new Color(0,101,183));
            }
        });

        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreparedStatement st;
                ResultSet rs;
                //get the username and password
                String uname = username.getText();
                String pword = String.valueOf(createPassword.getPassword());
                //create a select query to check if the username and the password exist in the DB
                String query = "SELECT * FROM users WHERE username= ? AND password = ?";
                try {
                    st = DriverManager.getConnection("jdbc:mysql://localhost:3306/family", "root", "root").prepareStatement(query);
                    st.setString(1, uname);
                    st.setString(2, pword);
                    rs = st.executeQuery();
                    if (rs.next())//username and password are correct
                    {
                        System.out.println("dkkkkkkkkk");
/*//here display new form
//etc:
   menu f=new menu();
   f.setVisible(true);
   f.pack();
   f.setLocationRelativeTo(null);
   //closing the current form
   this.dispose();
*/
                    } else {
                        //error message
                        JOptionPane.showMessageDialog(null, "Invalid username/password", "Register Error", 2);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        });


        phone.addKeyListener(new KeyListener() {
            //allow to type only numbers
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar()))
                    e.consume();
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        selectImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String path=null;
                JFileChooser chooser=new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                //file extension
                FileNameExtensionFilter extension=new FileNameExtensionFilter("*Images",".jpg",".png",".jpeg");
                chooser.addChoosableFileFilter(extension);

                int fileState=chooser.showSaveDialog(null);

                //check if the user select an image
                if(fileState==JFileChooser.APPROVE_OPTION){
                    File selectedImage=chooser.getSelectedFile();
                    image_path=selectedImage.getAbsolutePath();

                }
            }
        });



        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname2=fullname.getText();
                String uname2=username.getText();
                String pone2=phone.getText();
                String pass1=String.valueOf(createPassword.getPassword());
                String pass2=String.valueOf(confirmPassword.getPassword());
                String gender="Male";
                if(female.isSelected()){
                    gender="Female";
                }
                if(verify())
                    if(!isUsernameExist(uname2))
                    {
                        PreparedStatement ps;
                        ResultSet rs;
                        String registerUserQuery="INSERT INTO users(fullname,username,password,phone,gender,image) VALUES(?,?,?,?,?,?)";
                        try {
                            ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/family", "root", "root").prepareStatement(registerUserQuery);
                            ps.setString(1, fname2);
                            ps.setString(2, uname2);
                            ps.setString(3, pass1);
                            ps.setString(4, pone2);
                            ps.setString(5, gender);
                            //save the image as a blob in the DB
                            if (image_path != null) {
                                InputStream image = new FileInputStream(new File(image_path));
                                ps.setBlob(6, image);
                            } else {
                                ps.setNull(6, NULL);
                            }
                            if(ps.executeUpdate()!=0)
                            {
                                JOptionPane.showMessageDialog(null,"Your account has been created successfully");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"Failed in creating your account");
                            }
                        } catch (SQLException | FileNotFoundException ex) {
                            ex.printStackTrace();
                        }

                    }
            }
        });
        loginContext.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered (MouseEvent e){
                Border fr = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.blue);
                loginContext.setBorder(fr);
            }
            @Override
            public void mouseExited (MouseEvent e){
                loginContext.setBorder(null);
            }
            @Override
            public void mouseClicked (MouseEvent e){
                LoginView rv = new LoginView();
                dispose();
            }
        });
        //Very very important to be at the end of this constructor
        setVisible(true);
    }


    //create a function to verify the empty fields
    public boolean verify()
    {
        String fname2=fullname.getText();
        String uname2=username.getText();
        String pone2=phone.getText();
        String pass1=String.valueOf(createPassword.getPassword());
        String pass2=String.valueOf(confirmPassword.getPassword());
        if(fname2.trim().equals("")||uname2.trim().equals("")||
                pone2.trim().equals("")||pass1.trim().equals("")||pass2.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"One or more fields are empty","Empty Fields",2);
            return false;
        }
        else//check if the two given passwords are equal
            if(!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(null, "Passwords don't match", "Confirm Password", 2);
                return false;
            }

        return true;
    }

    //Is the given username has already existed in our DB?
    public boolean isUsernameExist(String username)
    {
        PreparedStatement st;
        ResultSet rs ;
        boolean exist=false;
        String query="SELECT*FROM users WHERE username= ?";
        try{
            st = DriverManager.getConnection("jdbc:mysql://localhost:3306/family", "root", "root").prepareStatement(query);
            st.setString(1,username);
            rs=st.executeQuery();
            if(rs.next()) {
                exist = true;
                JOptionPane.showMessageDialog(null,"This username is already exist","Username validation",2);

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}