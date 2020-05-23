package Models;


import javax.swing.*;
import java.io.File;
import java.sql.*;


public abstract class Human extends User {
    public String firstName, familyUsername;
    public byte genderId;
    public java.sql.Date birthday;
    public ImageIcon image;


    public Human(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**Get an existing user account from DB by his name*/
    public static Human getHumanData(String username) {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        Human human;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            String query = "SELECT*FROM human WHERE Username=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            if (rs.getInt("Salary") < 0) {
                con.close();
                return new Child(username);
            }
            con.close();
            return new Parent(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Human(String password, String username, String firstName, byte genderId, String familyUsername,
                 java.sql.Date birthday, ImageIcon image) {
        super(password, username);
        this.firstName = firstName;
        this.genderId = genderId;
        this.familyUsername = familyUsername;
        this.birthday = birthday;
        this.image = image;
    }
    //e it's just a dummy parameter which intended to distinguish between this c'tor to an other one
    public Human(String username,boolean e){
        this.username=username;
    }
    public Human(){}

    public void deleteAccount() {
        Connection con;
        PreparedStatement ps;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            /**First of all we need to ensure that there are no other family members
             * if there are - no problem
             * if there are no members- you need to delete the family account*/
            //this statement deletes the family if and only if it has 1 member
            String deleteFamily = "DELETE FROM family WHERE Counter<=1 AND Username=?";
            ps = con.prepareStatement(deleteFamily);
            ps.setString(1, familyUsername);
            ps.execute();
            /**In any case- delete the user account*/
            String deleteUser = "DELETE FROM human WHERE Username = ?;";
            ps = con.prepareStatement(deleteUser);
            ps.setString(1, username);
            ps.executeUpdate();
            /**In case there were members, we need to decrease by 1 the counter*/
            String updateCounter = "UPDATE family SET Counter=Counter-1 WHERE Username= ?";
            ps = con.prepareStatement(updateCounter);
            ps.setString(1, familyUsername);
            ps.executeUpdate();
            ps.close();
            con.close();
            /**Delete the bio file*/
            File f = new File("Biographies\\" + username + ".txt");
            f.delete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


