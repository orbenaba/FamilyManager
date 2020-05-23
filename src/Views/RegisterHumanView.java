package Views;


import Models.Family;
import com.company.CircleButton;
import com.toedter.calendar.JDateChooser;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static Models.Gender.getGenders;


public class RegisterHumanView extends Jframe {
    public JLabel imageContainer, firstNameLabel, birthdayLabel,removePhotoLabel;
    public JLabel usernameLabel, passwordLabel, confirmPasswordLabel;

    public Border frameExMin;
    public JTextField firstName;
    public ImageIcon image,removePhoto;
    public CircleButton addImage;

    public JTextField username;
    public JPasswordField password, confirmPassword;
    public JComboBox genders;
    //Birthday
    public JDateChooser dateChooser;
    public Calendar calendar;
    //Bio
    public JTextArea bio;
    public JScrollPane pane;
    //Submit
    public JButton create;
    //image path
    public String imagePath=null;



    public RegisterHumanView() {
        super(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()));
        //Changing the caret in the entire text fields
        UIManager.put("TextField.caretForeground", new ColorUIResource(Color.red));
        setExtendedState(MAXIMIZED_BOTH);
        Color color=new Color(48,48,48);
        int width=getWidth(),height=getHeight();

        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.black);

        image = new ImageIcon(getClass().getResource("/Icons/profile2.png"));
        imageContainer = new JLabel(image);
        imageContainer.setBounds(getWidth()/2-239, 20, 478, 300);
        addImage = new CircleButton("",Color.black);
        addImage.setBounds(width/2-38, 190, 78, 78);//Covers the plus that belongs to the image


        firstNameLabel = new JLabel("First name:");
        firstNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        firstNameLabel.setForeground(Color.green);
        firstNameLabel.setBounds(150, 350, 150, 35);
        firstName = new JTextField();
        firstName.setFont(new Font("Arial", Font.BOLD, 25));
        firstName.setForeground(Color.green);
        firstName.setBackground(color);
        firstName.setBounds(300, 350, 200, 35);
        firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));


        java.util.List<String> gendersArray = getGenders();
        gendersArray.add("Select gender");

        genders = new JComboBox(gendersArray.toArray());
        genders.setBounds(580, 350, 240, 35);
        genders.setFont(new Font("Arial", Font.BOLD, 25));
        genders.setForeground(Color.green);
        genders.setBackground(color);
        genders.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
        genders.setSelectedItem(("Select gender"));


        addUsernamePassword(150, 420, 50);
        birthdayLabel = new JLabel("Date of birth:");
        birthdayLabel.setBounds(150, 610, 180, 35);
        birthdayLabel.setForeground(Color.green);
        birthdayLabel.setFont(new Font("Arial", Font.BOLD, 25));
        ///////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////~~~ADD BIRTHDAY FIELD~~~////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
        calendar = Calendar.getInstance();
        dateChooser = new JDateChooser(calendar.getTime());
        //enforcing the user to choose a valid date. I range [NOW,NOW-120]
        java.util.Date currentDate= new java.util.Date();
        java.util.Date minDate=new java.util.Date(currentDate.getYear()-120,currentDate.getMonth(),currentDate.getDay());
        dateChooser.setMaxSelectableDate(currentDate);
        dateChooser.setMinSelectableDate(minDate);
        dateChooser.setBounds(330, 610, 170, 35);
        dateChooser.setFont(new Font("Arial", Font.BOLD, 17));
        dateChooser.setBackground(color);
        dateChooser.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        add(dateChooser);
        ///////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////~~~ADD BIO FIELD~~~////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
        bio = new JTextArea(4, 30);
        bio.setFont(new Font("Arial", Font.BOLD, 20));
        bio.setBackground(color);
        bio.setForeground(Color.green);
        bio.setLineWrap(true);
        bio.setWrapStyleWord(true);
        bio.setForeground(Color.green);
        bio.setText("\t            Bio...");

        pane = new JScrollPane(bio, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(900, 400, 450, 300);
        pane.setPreferredSize(new Dimension(250,300));



        create=new JButton("Create");
        create.setBounds(1200,750,200,50);
        create.setBackground(color);
        create.setForeground(Color.green);
        create.setFont(new Font("Arial",Font.BOLD,25));
        create.setCursor(new Cursor(Cursor.HAND_CURSOR));



        add(create);
        add(pane);
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


    //Listeners
    public void addUsernamePassword(int x, int y, int iconSize) {
        Color color = new Color(48,48,48);
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


        passwordLabel.setBounds(x, y + iconSize + 40, iconSize, iconSize);
        confirmPasswordLabel.setBounds(x + 360, y + iconSize + 40, iconSize, iconSize);

        password.setBackground(color);
        confirmPassword.setBackground(color);
        username.setBackground(color);

        password.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.green));
        confirmPassword.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.green));
        username.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.green));

        password.setBounds(x + iconSize + 10, y + iconSize + 40, 200, iconSize - 10);
        confirmPassword.setBounds(x + iconSize + 370, y + iconSize + 40, 200, iconSize - 10);
        username.setBounds(x + iconSize + 10, y, 200, iconSize - 10);
    }

    public void addPasswordListener(MouseAdapter mal) {
        password.addMouseListener(mal);
    }

    public void addConfirmPasswordListener(MouseAdapter mal) {
        confirmPassword.addMouseListener(mal);
    }

    public void addUsernameListener(MouseAdapter mal) {
        username.addMouseListener(mal);
    }

    public void addCalendarListener(MouseAdapter mal) {
        birthdayLabel.addMouseListener(mal);
        dateChooser.addMouseListener(mal);
    }

    public void addCreateListener(MouseAdapter mal){
        create.addMouseListener(mal);
    }

    public void addBioListener(MouseAdapter mal){bio.addMouseListener(mal);}

    //Functionality
    //Returns the name of the text file
    public static String mappingTextareaIntoFile(Object username,JTextArea bio,String directory) {
        try {
            File file = new File(directory+"\\"+username+".txt");
            FileWriter fileWriter=new FileWriter(file,false);
            fileWriter.write(bio.getText());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username + ".txt";
    }

    public boolean verifyMustNotEmpty(){
        if(firstName.getText().trim().equals("")||username.getText().trim().equals("")
                ||String.valueOf(password.getPassword()).equals("")||String.valueOf(confirmPassword.getPassword()).equals(""))
            return false;
        return true;
    }
    public boolean verifyGender(){
        return genders.getSelectedItem().equals("Select gender");
    }

    public void addRemovePhotoListener(MouseAdapter mal){
        removePhotoLabel.addMouseListener(mal);
    }

}