package Models;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;


public class Child extends Human {
    public String status;
    public boolean isSingle;

    public Child(String username) {
        super(username, false);
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
                this.image = new ImageIcon();
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

    /**Input: New details of an existing user account*/
    /**
     * Output: Empty string in case that the update failed
     * New Username in case that the update succeed
     */
    public String updateAccount(String username, String password, String firstName, String status, java.sql.Date birthday,
                                boolean isSingle, InputStream image, boolean flag) {
        /**First, we need to ensure that there is no other user with this username*/
        if (!isUsernameExist(username, true, this.username)) {
            try {
                String query;
                if (flag == false)
                    query = "UPDATE human SET Username=?,Password=?,FirstName=?,Status=?,Birthday=?,IsObligated=?,Image=? WHERE Username=?";
                else
                    query = "UPDATE human SET Username=?,Password=?,FirstName=?,Status=?,Birthday=?,IsObligated=? WHERE Username=?";
                PreparedStatement ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);

                this.username=username;
                this.password=password;
                this.firstName=firstName;
                this.status=status;
                this.birthday=birthday;
                this.isSingle=isSingle;
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, firstName);
                ps.setString(4, status);
                ps.setDate(5, birthday);
                ps.setBoolean(6, isSingle);
                if (flag == false) {
                    if (image != null)
                        ps.setBlob(7, image);
                    else
                        ps.setNull(7, Types.NULL);
                    ps.setString(8, this.username);
                }
                else
                    ps.setString(7,this.username);
                ps.executeUpdate();
                ps.close();
                return username;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}
