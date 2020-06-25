package Models;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Parent extends Human {
    public int salary;
    public String jobName;
    public boolean isMarried, isLimited;

    public Parent(String username) {
        //No significance to e:false
        super(username, false);
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query;
        /**Parent's details*/
        try {
            con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            query = "SELECT Birthday,FamilyUsername,FirstName,GenderId,Image,Password,JobName,IsObligated,Salary,isLimited FROM human WHERE Username=?";
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
            this.jobName = rs.getString("JobName");
            this.isMarried = rs.getBoolean("IsObligated");
            this.salary = rs.getInt("Salary");
            this.isLimited = rs.getBoolean("isLimited");
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
    public String updateAccount(String username, String password, String firstName, String jobName, java.sql.Date birthday,
                                boolean isMarried, int Salary, InputStream image, boolean flag) {
        /**First, we need to ensure that there is no other user with this username*/
        if (!isUsernameExist(username, true, this.username)) {
            try {
                String query;
                if (flag == false)
                    query = "UPDATE human SET Username=?,Password=?,FirstName=?,JobName=?,Birthday=?,IsObligated=?,Salary=?,Image=? WHERE Username=?";
                else
                    query = "UPDATE human SET Username=?,Password=?,FirstName=?,JobName=?,Birthday=?,IsObligated=?,Salary=? WHERE Username=?";
                Connection con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
                PreparedStatement ps =con.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, firstName);
                ps.setString(4, jobName);
                ps.setDate(5, birthday);
                ps.setBoolean(6, isMarried);
                ps.setInt(7, Salary);
                if (flag == false) {
                    if (image != null)
                        ps.setBlob(8, image);
                    else {
                        ps.setNull(8, Types.NULL);
                        this.image = null;
                    }
                    ps.setString(9, this.username);
                } else
                    ps.setString(8, this.username);
                ps.executeUpdate();
                ps.close();
                con.close();
                this.username = username;
                this.password = password;
                this.firstName = firstName;
                this.jobName = jobName;
                this.birthday = birthday;
                this.isMarried = isMarried;
                this.salary = Salary;
                return username;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    //Checking whether a given username is a parent or not, if its salary is negative,
    //Namely is a child.
    public static boolean isParent(String username) {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT Salary FROM human WHERE Username=?";
        boolean ret = false;
        try {
            con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            if (rs.getInt("Salary") >= 0)
                ret = true;
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


    //Bounding parent's children
    public boolean limitChildren() {
        try {
            //salary can distinct between child and parent
            String query = "UPDATE human SET isLimited = 1 WHERE FamilyUsername = ? AND Salary < 0";
            Connection con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.familyUsername);
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Unbound parent's children
    public boolean unLimitChildren() {
        try {
            //salary can distinct between child and parent
            String query = "UPDATE human SET isLimited = 0 WHERE FamilyUsername = ? AND Salary < 0";
            Connection con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.familyUsername);
            ps.executeUpdate();
            ps.close();
            con.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Checking if the children in a given ***FAMILY*** are limited
    public boolean isLimitChildren() {
        boolean ret = false;
        try {
            //salary can distinct between child and parent
            String query = "SELECT isLimited FROM human WHERE FamilyUsername = ? AND Salary < 0";
            Connection con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, this.familyUsername);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                ret = rs.getBoolean("isLimited");
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    //Checking if the children in a given ***User in a family*** are limited
    public static boolean isLimitChildren(String username) {
        boolean ret = false;
        try {
            //salary can distinct between child and parent
            String query = "SELECT isLimited FROM human WHERE FamilyUsername =ANY(SELECT FamilyUsername FROM human WHERE Username = ?)AND Salary < 0 ";
            Connection con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            PreparedStatement ps =con.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                ret = rs.getBoolean("isLimited");
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
