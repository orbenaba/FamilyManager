package Views;


import Models.Child;
import com.company.CircleButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import static Views.StartView.init_Exit_Minimize;

public class MyProfileChildView extends Jframe {
    public JLabel exit, minimize,removePhotoLabel;
    public Border frameExMin;
    public String username;
    public Child child;

    public CircleButton addImage;
    public ImageIcon removePhoto;
    public JLabel imageContainer;
    public JButton deleteAccount;
    public JLabel backToHome;
    public JLabel usernameLabel,passwordLabel;
    public JTextField usernameField,passwordField;


    @Override
    public JLabel getExit() {
        return this.exit;
    }

    @Override
    public JLabel getMinimize() {
        return this.minimize;
    }

    public MyProfileChildView(String username) {
        this.username = username;
        Font font = new Font("Arial", Font.BOLD, 40);
        Color bordo=new Color(123, 61, 61);


        setUndecorated(true);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);//Full screen undependable platform
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(new Color(36, 16, 71));
        frameExMin = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        exit = new JLabel("X");
        minimize = new JLabel("-");
        init_Exit_Minimize(getWidth() - 70, 0, 35, exit, minimize, frameExMin, true);//prevent code replication
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        child = getChild(username);
        /**Loading child's image-if there is*/
        imageContainer=new JLabel();
        imageContainer.setBounds(375, 10, 250, 250);
        if (child.image == null) {
            imageContainer.setIcon(new ImageIcon(getClass().getResource("/Icons/profile2.png")));
            addImage = new CircleButton("");
            addImage.setBounds(448, 150, 78, 78);//Covers the plus that belongs to the image
            add(imageContainer);
            add(addImage);
        } else {
            Image temp = child.image.getImage();
            Image newImage = temp.getScaledInstance(imageContainer.getWidth(), imageContainer.getHeight(), java.awt.Image.SCALE_SMOOTH);
            imageContainer.setIcon(new ImageIcon(newImage));
            //adding bin trash
            removePhoto = new ImageIcon(getClass().getResource("/Icons/removePhoto.png"));
            removePhotoLabel=new JLabel(removePhoto);
            removePhotoLabel.setBounds(imageContainer.getX()+imageContainer.getWidth()+50,imageContainer.getY(),40,50);
            add(removePhotoLabel);
            add(imageContainer);
        }
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        //delete personal account button
        deleteAccount=new JButton("Delete account");
        deleteAccount.setFont(new Font("Arial",Font.BOLD,20));
        deleteAccount.setBounds(10,getHeight()-70,200,50);
        deleteAccount.setBackground(bordo);
        deleteAccount.setForeground(Color.black);
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/

        //Adding an icon- back to home view
        backToHome=new JLabel();
        backToHome.setBounds(10,10,60,60);
        backToHome.setIcon(new ImageIcon(getClass().getResource("/Icons/homeIcon.png")));
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        Font regFont=new Font("Arial",Font.BOLD,25);
        usernameLabel=new JLabel("Username:");
        passwordLabel=new JLabel("Password:");
        usernameLabel.setFont(regFont);
        passwordLabel.setFont(regFont);
        usernameLabel.setForeground(bordo);
        passwordLabel.setForeground(bordo);
        usernameLabel.setBounds(20,270,150,40);
        passwordLabel.setBounds(500,270,150,40);
        /////////////////////////
        /////////////////////////
        usernameField=new JTextField();
        usernameField.setBounds(170,270,250,40);
        passwordField=new JTextField();
        passwordField.setBounds(650,270,250,40);
        usernameField.setFont(regFont);
        passwordField.setFont(regFont);
        usernameLabel.setForeground(bordo);
        passwordLabel.setForeground(bordo);
        usernameField.setBackground(Color.orange);
        passwordField.setBackground(Color.orange);
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/
        /**-----------------------------------------------------------------------------------------------------*/


        add(passwordField);
        add(usernameField);
        add(usernameLabel);
        add(passwordLabel);
        add(backToHome);
        add(deleteAccount);
        add(minimize);
        add(exit);


        setVisible(true);


    }

    public static Child getChild(String username) {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query;
        /**Child's details*/
        String password = null, familyUsername = null, firstName = null, status = null;
        byte genderId = 1;
        java.sql.Date birthday = null;
        ImageIcon image = new ImageIcon();

        boolean isSingle = true;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            query = "SELECT Birthday,FamilyUsername,FirstName,GenderId,Image,Password,Status,IsObligated FROM human WHERE Username=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            birthday = rs.getDate("Birthday");
            familyUsername = rs.getString("FamilyUsername");
            firstName = rs.getString("FirstName");
            genderId = rs.getByte("GenderId");
            ////////////////
            /////Image/////
            ////////////////
            Blob b=rs.getBlob("Image");
            if(b!=null) {
                InputStream in = b.getBinaryStream();
                BufferedImage img = ImageIO.read(in);
                image.setImage(img);
            }
            else
                image=null;
            ////////////////
            ////////////////
            ////////////////
            password = rs.getString("Password");
            status = rs.getString("Status");
            isSingle = rs.getBoolean("IsObligated");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return new Child(password, username, firstName, genderId, familyUsername, birthday, status, isSingle, image);
    }

    public void addRemovePhotoListener(MouseAdapter mal){
        removePhotoLabel.addMouseListener(mal);
    }

    public void addImageAction(ActionListener mal) {
        addImage.addActionListener(mal);
    }
    //Delete account action
    public void addDeleteAccountAction(ActionListener mal){
        deleteAccount.addActionListener(mal);
    }


    public void addBackHomeListener(MouseAdapter mal){
        backToHome.addMouseListener(mal);
    }
}
