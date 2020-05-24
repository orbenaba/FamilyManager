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
    public boolean isMarried,isLimited;
    public Parent(String username, String password) {
        super(username,password);
    }

    public Parent(String username, String password, int salary, String jobName, boolean isMarried) {
        super(username, password);
        this.salary = salary;
        this.jobName = jobName;
        this.isMarried = isMarried;
    }

    public Parent(int salary, String jobName, boolean isMarried) {
        this.salary = salary;
        this.jobName = jobName;
        this.isMarried = isMarried;
    }

    public Parent(String password, String username, String firstName, byte genderId, String familyUsername,
                  Date birthday, ImageIcon image, int salary, String jobName, boolean isMarried,boolean isLimited) {
        super(password, username, firstName, genderId, familyUsername, birthday, image);
        this.salary = salary;
        this.jobName = jobName;
        this.isMarried = isMarried;
        this.isLimited=isLimited;
    }

    public Parent(String username){
        //No significance to e:false
        super(username,false);
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query;
        /**Parent's details*/
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            query = "SELECT Birthday,FamilyUsername,FirstName,GenderId,Image,Password,JobName,IsObligated,Salary,isLimited FROM human WHERE Username=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            this.birthday = rs.getDate("Birthday");
            this.familyUsername = rs.getString("FamilyUsername");
            this.firstName = rs.getString("FirstName");
            this.genderId = rs.getByte("GenderId");
            Blob b=rs.getBlob("Image");
            if(b!=null) {
                InputStream in = b.getBinaryStream();
                BufferedImage img = ImageIO.read(in);
                this.image=new ImageIcon();
                this.image.setImage(img);
            }
            else
                this.image=null;
            this.password = rs.getString("Password");
            this.jobName = rs.getString("JobName");
            this.isMarried = rs.getBoolean("IsObligated");
            this.salary=rs.getInt("Salary");
            this.isLimited=rs.getBoolean("isLimited");
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
                                boolean isMarried,int Salary, InputStream image, boolean flag) {
        /**First, we need to ensure that there is no other user with this username*/
        if (!isUsernameExist(username, true, this.username)) {
            try {
                System.out.println("Date is going to be in DB: "+birthday);
                String query;
                if (flag == false)
                    query = "UPDATE human SET Username=?,Password=?,FirstName=?,JobName=?,Birthday=?,IsObligated=?,Salary=?,Image=? WHERE Username=?";
                else
                    query = "UPDATE human SET Username=?,Password=?,FirstName=?,JobName=?,Birthday=?,IsObligated=?,Salary=? WHERE Username=?";
                PreparedStatement ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
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
                    else
                        ps.setNull(8, Types.NULL);
                    ps.setString(9, this.username);
                }
                else
                    ps.setString(8,this.username);
                ps.executeUpdate();

                /**Temp code*/
  /*              query="SELECT Birthday FROM human WHERE Username = ?";
                ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
                ps.setString(1,username);
                ResultSet rs=ps.executeQuery();
                rs.next();
                System.out.println("BD in DB: "+rs.getDate("Birthday"));
*/
                ps.close();
                return username;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}
