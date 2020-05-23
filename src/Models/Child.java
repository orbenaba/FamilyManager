package Models;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Child extends Human {
    public String status;
    public boolean isSingle;

    public Child(String username, String password) {
        super(username, password);
    }

    public Child(String password, String username, String firstName, byte genderId, String familyUsername,
                 Date birthday, String status, boolean isSingle, ImageIcon image) {
        super(password, username, firstName, genderId, familyUsername, birthday, image);
        this.status = status;
        this.isSingle = isSingle;
    }

    public Child(String username) {
        super(username,false);
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query;
        /**Child's details*/
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            query = "SELECT Birthday,FamilyUsername,FirstName,GenderId,Image,Password,Status,IsObligated FROM human WHERE Username=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            this.birthday = rs.getDate("Birthday");
            this.familyUsername = rs.getString("FamilyUsername");
            this.firstName = rs.getString("FirstName");
            this.genderId = rs.getByte("GenderId");
            Blob b = rs.getBlob("Image");
            if (b != null) {
                InputStream in = b.getBinaryStream();
                BufferedImage img = ImageIO.read(in);
                this.image=new ImageIcon();
                this.image.setImage(img);
            } else
                this.image = null;
            this.password = rs.getString("Password");
            this.status = rs.getString("Status");
            this.isSingle = rs.getBoolean("IsObligated");
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
