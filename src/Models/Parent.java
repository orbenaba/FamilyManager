package Models;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Parent extends Human {
    public double salary;
    public String jobName;
    public boolean isMarried,isLimited;
    public Parent(String username, String password) {
        super(username,password);
    }

    public Parent(String username, String password, double salary, String jobName, boolean isMarried) {
        super(username, password);
        this.salary = salary;
        this.jobName = jobName;
        this.isMarried = isMarried;
    }

    public Parent(double salary, String jobName, boolean isMarried) {
        this.salary = salary;
        this.jobName = jobName;
        this.isMarried = isMarried;
    }

    public Parent(String password, String username, String firstName, byte genderId, String familyUsername,
                  Date birthday, ImageIcon image, double salary, String jobName, boolean isMarried,boolean isLimited) {
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
}
