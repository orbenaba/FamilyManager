package Models;

import Controllers.ShoppingCartController;
import Views.ShoppingCartView;

import javax.swing.*;
import java.io.File;
import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;

import static Views.RegisterHumanView.mappingTextareaIntoFile;

public class ShoppingCart {
    public String familyUsername;
    public LinkedList<Outcome> outcomes;

    public ShoppingCart(String username) {
        outcomes = new LinkedList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT*FROM outcome WHERE Username=" +
                "ANY(SELECT Username FROM human WHERE FamilyUsername=" +
                "ANY(SELECT FamilyUsername FROM human WHERE Username= ?));";

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Outcome outcome = new Outcome(rs.getInt("id"), rs.getInt("Price"), rs.getDate("PurchasedDate"), rs.getString("Username"), rs.getString("Title"));
                outcomes.add(outcome);
            }
            query = "SELECT FamilyUsername FROM human WHERE Username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            familyUsername = rs.getString("FamilyUsername");
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Internal function which calculates the total outcomes which were spent
     */
    public int calculateShoppingCart() {
        int sum = 0;
        for (Outcome c : outcomes)
            sum += c.price;
        return sum;
    }

    public LinkedList<Outcome> getOutcomes() {
        return outcomes;
    }
    /**Deleting an outcome by its id form the shoppingCart*/
    /**
     * Returned true in case the delete was a successful operation
     */
    public boolean deleteOutcome(int id) {
        Connection con;
        PreparedStatement ps;
        String query = "DELETE FROM outcome WHERE id = ?;";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            con.close();
            /**Deleting the file which holds the description of the outcome*/
            File deleted = new File("Outcomes\\" + id + ".txt");
            deleted.delete();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**Function returns the new id of the added outcome*/
    public Integer addOutcome(Outcome added) {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query;
        Integer id = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            query = "INSERT INTO outcome(Username,Price,PurchasedDate,Title) VALUES(?,?,?,?)";
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, added.username);
            ps.setInt(2, added.price);
            ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            ps.setString(4, added.title);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    //Deletes all the files of the deleted human
    public static boolean deleteShoppingCart(String username){
        try {
            String query="SELECT id FROM outcome WHERE Username = ?;";
            PreparedStatement ps= DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                File file=new File("Outcomes\\"+rs.getInt("id")+".txt");
                System.out.println(file.delete());
            }
            rs.close();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
