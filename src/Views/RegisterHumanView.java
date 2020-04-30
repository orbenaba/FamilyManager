package Views;


import Models.Family;
import com.company.CircleButton;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import static Views.StartView.init_Exit_Minimize;


public class RegisterHumanView extends Jframe {
    public JLabel exit, minimize, imageContainer, firstNameLabel,birthdayLabel;
    public JLabel usernameLabel, passwordLabel, confirmPasswordLabel;

    public Border frameExMin;
    public JTextField firstName;
    public ImageIcon image;
    public CircleButton addImage;

    public JTextField username;
    public JPasswordField password, confirmPassword;
    public JComboBox genders;
    //Birthday
    public JDateChooser dateChooser;
    public Calendar calendar;
    //Bio
    public JTextArea bio;

    @Override
    public JLabel getMinimize() {
        return this.minimize;
    }
    @Override
    public JLabel getExit() {
        return this.exit;
    }

    public RegisterHumanView() {
        //get rid of the ugly frame which is given by default
        setUndecorated(true);
        setSize(850, 750);
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.black);
        exit = new JLabel("X");
        minimize = new JLabel("-");
        frameExMin = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        init_Exit_Minimize(getWidth() - 85, 15, 35, exit, minimize, frameExMin, false);//prevent code replication
        image = new ImageIcon(getClass().getResource("/Icons/profile2.png"));
        imageContainer = new JLabel(image);
        imageContainer.setBounds(300, 10, 250, 250);
        addImage = new CircleButton("");
        addImage.setBounds(385, 150, 82, 82);//Covers the plus that belongs to the image
        firstNameLabel = new JLabel("First name:");
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        firstNameLabel.setForeground(Color.green);
        firstNameLabel.setBounds(50, 270, 150, 35);
        firstName = new JTextField();
        firstName.setFont(new Font("Arial", Font.BOLD, 25));
        firstName.setForeground(Color.green);
        firstName.setBackground(new Color(48, 48, 48));
        firstName.setBounds(200, 270, 200, 35);
        firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));


        java.util.List<String> gendersArray = getGenders();

        genders = new JComboBox(gendersArray.toArray());
        genders.setBounds(450, 270, 240, 35);
        genders.setFont(new Font("Arial", Font.BOLD, 25));
        genders.setForeground(Color.green);
        genders.setBackground(new Color(48, 48, 48));
        genders.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
        genders.setSelectedItem("Select gender");//By default

        addUsernamePassword(50, 350, 50);
        birthdayLabel=new JLabel("Date of birth:");
        birthdayLabel.setBounds(50,480,180,35);
        birthdayLabel.setForeground(Color.green);
        birthdayLabel.setFont(new Font("Arial",Font.BOLD,25));
        ///////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////~~~ADD BIRTHDAY FIELD~~~////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
        calendar=Calendar.getInstance();
        dateChooser=new JDateChooser(calendar.getTime());
        dateChooser.setBounds(230,480,170,35);
        dateChooser.setFont(new Font("Arial",Font.BOLD,17));
        dateChooser.setBackground(new Color(48,48,48));
        dateChooser.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.green));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        add(dateChooser);
        ///////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////~~~ADD BIO FIELD~~~////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
        bio=new JTextArea(4,30);
        bio.setBounds(200,540,450,200);
        bio.setFont(new Font("Arial",Font.BOLD,20));
        bio.setBackground(new Color(48,48,48));
        bio.setForeground(Color.green);

        add(bio);
        add(birthdayLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(confirmPasswordLabel);
        add(username);
        add(confirmPassword);
        add(password);
        add(genders);
        add(firstNameLabel);
        add(firstName);
        add(imageContainer);
        add(addImage);
        add(exit);
        add(minimize);
        setVisible(true);
    }

    public void addImageAction(ActionListener mal) {
        addImage.addActionListener(mal);
    }

    public void addFirstNameListener(MouseAdapter mal) {
        firstNameLabel.addMouseListener(mal);
        firstName.addMouseListener(mal);
    }

    public void addGendersListener(MouseAdapter mal) {
        genders.addMouseListener(mal);
    }

    public void handleComboAction(ActionListener mal) {
        genders.addActionListener(mal);
    }

    public static java.util.List<String> getGenders() {
        List<String> lst = new ArrayList<>();
        String query = "SELECT*FROM gender";
        try {
            PreparedStatement con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ResultSet rs = con.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }


    //Listeners
    public void addUsernamePassword(int x, int y, int iconSize) {
        username = new JTextField();
        password = new JPasswordField();
        confirmPassword = new JPasswordField();
        password.setFont(new Font("Arial", Font.BOLD, 25));
        confirmPassword.setFont(new Font("Arial", Font.BOLD, 25));
        username.setFont(new Font("Arial", Font.BOLD, 25));
        password.setForeground(Color.green);
        confirmPassword.setForeground(Color.green);
        username.setForeground(Color.green);
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        confirmPasswordLabel = new JLabel();
        passwordLabel.setIcon(new ImageIcon(getClass().getResource("/Icons/passIcon.png")));
        confirmPasswordLabel.setIcon(new ImageIcon(getClass().getResource("/Icons/passIcon.png")));
        usernameLabel.setIcon(new ImageIcon(getClass().getResource("/Icons/userIcon.png")));
        usernameLabel.setBounds(x, y, iconSize, iconSize);


        passwordLabel.setBounds(x, y + iconSize + 10, iconSize, iconSize);
        confirmPasswordLabel.setBounds(x+330, y +  iconSize + 10, iconSize, iconSize);

        password.setBackground(new Color(48, 48, 48));
        confirmPassword.setBackground(new Color(48, 48, 48));
        username.setBackground(new Color(48, 48, 48));


        password.setBounds(x + iconSize + 10, y + iconSize + 10, 200, iconSize - 10);
        confirmPassword.setBounds(x + iconSize + 340, y + iconSize + 10, 200, iconSize - 10);
        username.setBounds(x + iconSize + 10, y, 200, iconSize - 10);
    }
    public void addPasswordListener(MouseAdapter mal){
        password.addMouseListener(mal);
    }
    public void addConfirmPasswordListener(MouseAdapter mal){
        confirmPassword.addMouseListener(mal);
    }
    public void addUsernameListener(MouseAdapter mal){
        username.addMouseListener(mal);
    }
    public void addCalendarListener(MouseAdapter mal){
        birthdayLabel.addMouseListener(mal);
        dateChooser.addMouseListener(mal);
    }
}