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
        setUndecorated(true);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);//Full screen undependable platform
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(new Color(255, 236, 112));
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
        imageContainer.setBounds(400, 10, 250, 250);
        if (child.image == null) {
            imageContainer.setIcon(new ImageIcon(getClass().getResource("/Icons/profile2.png")));
            addImage = new CircleButton("");
            addImage.setBounds(385, 150, 78, 78);//Covers the plus that belongs to the image
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
        }
        add(imageContainer);
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
}
