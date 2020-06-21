package Models;


import Controllers.HomeController;
import Views.HomeView;
import com.mysql.cj.log.NullLogger;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import static Models.ShoppingCart.deleteShoppingCart;
import static Models.TasksList.deleteTasksList;
import static Views.RegisterHumanView.mappingTextareaIntoFile;
import static java.sql.Types.NULL;


public abstract class Human extends User {
    public String firstName, familyUsername;
    public byte genderId;
    public java.sql.Date birthday;
    public ImageIcon image;

    /*****************************************************************************************************************
     * Get an existing user account from DB by his name*/
    /**Using Factory pattern, Explanation:
     * 1)We are getting dynamically the username of the user(Still don't know whether is a parent or child).
     * 2)We are checking the user's salary(we set by default all the users' salaries to -1 and in case we insert into
     * the DB a parent we initialize its salary into non-negative number).
     * So if the salary is negative, we are calling the Child's constructor and create one child.
     * Otherwise, we create a parent by calling its c'tor.
     *****************************************************************************************************************
     * The components in this factory pattern are:
     * Creator==>>>> getHumanData(..)
     * Products==>>> Parent and Child while Human helps as a common base to both classes.
     ******************************************************************************************************************/
    public static Human getHumanData(String username) {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
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

    //e it's just a dummy parameter which intended to distinguish between this c'tor to another one
    public Human(String username,boolean e){
        this.username=username;
    }


    public static String createAccount(String username,java.sql.Date birthday,String familyUsername,String firstName,byte genderId, InputStream image,
                              boolean isObligated, String jobName, String password, int salary, boolean isLimited,String status) {
        if (isUsernameExist(username, false, ""))
            return "This username is already taken";

        PreparedStatement ps;
        String registerQuery = "INSERT INTO human(Username,Birthday,FamilyUsername,FirstName," +
                "GenderId,Image,IsObligated,JobName,Password,Salary,Status,isLimited)" +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps = con.prepareStatement(registerQuery);
            ps.setString(1, username);
            ps.setDate(2, birthday);
            ps.setString(3, familyUsername);
            ps.setString(4, firstName);
            ps.setByte(5, genderId);
            //save the image profile as a blob in DB
            if(image!=null)
                ps.setBlob(6, image);
            else
                ps.setNull(6, NULL);

            ps.setBoolean(7, isObligated);
            if(jobName.equals(""))
                ps.setNull(8, NULL);
            else
                ps.setString(8,jobName);
            ps.setString(9, password);
            ps.setInt(10, salary);
            if(status.equals(""))
                ps.setNull(11, NULL);
            else
                ps.setString(11,status);
            ps.setBoolean(12, isLimited);//isLimited
            if (ps.executeUpdate() != 0) {
                /**Needs to update the counter*/
                String updateCounter = "UPDATE family SET Counter=Counter+1 WHERE Username= ?";
                ps = con.prepareStatement(updateCounter);
                ps.setString(1, familyUsername);
                ps.executeUpdate();
            } else {
                return "Error!!!";
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**Regulatory requirement-->>Deleting ALL associated data to this user*/
    public void deleteAccount() {
        Connection con;
        PreparedStatement ps;
        if(isUsernameExist(username,false,"")) {
            try {
                /**Delete the bio file*/
                /**Deleting all the X user outcome files*/
                deleteShoppingCart(this.username);
                deleteTasksList(this.username);
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
                /**First of all we need to ensure that there are no other family members
                 * if there are - no problem
                 * if there are no members- we need to delete the family account*/
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
                /**Edge case- if there are no more parents in the family, we need to update the isLimit field in the DB of the children*/
                String updateIsLimit = "UPDATE human SET isLimited=0 WHERE(FamilyUsername = ?) AND " +
                        "0=ANY(SELECT COUNT(*) FROM (SELECT h2.Username FROM" +
                        " human AS h2 WHERE h2.FamilyUSername = ? AND h2.Salary>=0)AS h3)";
                ps = con.prepareStatement(updateIsLimit);
                ps.setString(1, familyUsername);
                ps.setString(2, familyUsername);
                ps.executeUpdate();
                //close opened connections
                ps.close();
                con.close();
                //delete user's biography
                File f = new File("Biographies\\" + username + ".txt");
                f.delete();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////~~~~~~~~~~PAY ATTENTION - THIS METHODS DESTINED TO EASE TESTINGS PART~~~~~~~~~~///////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static Human getFirstHuman(boolean par)
    {
        Human human=null;
        String username="";
        try {
            //selecting first child's username
            String query="";
            if(par)
                 query="SELECT Username FROM human WHERE Salary>=0 LIMIT 0,1;";
            else
                query="SELECT Username FROM human WHERE Salary<0 LIMIT 0,1;";
            PreparedStatement ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                username = rs.getString(1);
                if (par)
                    human = new Parent(username);
                else
                    human = new Child(username);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return human;
    }

    //return first username from family
    public static String getFirstHuman(String familyUsername){
        String username="";
        try {
            //selecting first child's username
            String query="SELECT Username FROM Human WHERE FamilyUsername = ? LIMIT 0,1;";
            PreparedStatement ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ps.setString(1,familyUsername);
            ResultSet rs=ps.executeQuery();
            rs.next();
            username=rs.getString(1);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }
}