package Models;


import java.io.File;
import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;

import static Models.Parent.isLimitChildren;
import static Models.Parent.isParent;

;


public class ShoppingCart {
    public String familyUsername;
    public LinkedList<Outcome> outcomes;

    //Constructor:
    //Retrieving all the existing outcomes from the DB which belongs to the all family of the given username
    //We are using a specific range of dates
    //Note: outcomes are with non-negative price in comparison to incomes.
    public ShoppingCart(String username,Date date) {
        outcomes = new LinkedList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT*FROM outcome WHERE PurchasedDate>=? AND price >0 AND Username=" +
                "ANY(SELECT Username FROM human WHERE FamilyUsername=" +
                "ANY(SELECT FamilyUsername FROM human WHERE Username= ?));";
        try {
            con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            ps = con.prepareStatement(query);
            //Displaying only relevant outcomes
            ps.setDate(1,date);
            ps.setString(2, username);
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
    public ShoppingCart(){}

    //Calculates all the amount of all the incomes
    public int calculateIncomes(String username,Date date){
        int sum=0;
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT*FROM outcome WHERE PurchasedDate>=? AND price <0 AND Username=" +
                "ANY(SELECT Username FROM human WHERE FamilyUsername=" +
                "ANY(SELECT FamilyUsername FROM human WHERE Username= ?));";
        try {
            con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            ps = con.prepareStatement(query);
            ps.setDate(1,date);
            ps.setString(2, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                sum+=rs.getInt("Price");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (-1)*sum;
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
    /**Deleting an outcome by its id form the shoppingCart
     * Returned true in case the delete was a successful operation
     */
    public boolean deleteOutcome(int id) {
        Connection con;
        PreparedStatement ps;
        String query = "DELETE FROM outcome WHERE id = ?;";
        try {
            con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
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
    //Checking if the user id limited from adding outcomes
    //returned null in case that the user isLimited
    public Object addOutcome(Outcome added) {
        if(!isLimitChildren(added.username)||isParent(added.username)) {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            String query;
            Integer id = new Integer(1);
            try {
                con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
                query = "INSERT INTO outcome(Username,Price,PurchasedDate,Title) VALUES(?,?,?,?)";
                ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, added.username);
                ps.setInt(2, added.price);
                ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                ps.setString(4, added.title);
                ps.executeUpdate();
                //retrieving the new generated key which incremented plus one
                rs = ps.getGeneratedKeys();
                rs.next();
                id = rs.getInt(1);
                this.outcomes.add(added);
                //Update the id value of the outcome
                added.id = id;
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return id;
        }
        return null;
    }

    //Deletes all the files of the deleted human
    public static Object deleteShoppingCart(String username){
        int countDeleted=0;
        try {
            String query="SELECT id FROM outcome WHERE Username = ?;";
            Connection con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            PreparedStatement ps= con.prepareStatement(query);
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            //rs.next();
            while(rs.next()){
                File file=new File("Outcomes\\"+rs.getInt("id")+".txt");
                file.delete();
                countDeleted++;
            }
            rs.close();
            ps.close();
            con.close();
            return countDeleted;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isEmpty(){
        return this.outcomes.isEmpty();
    }

    /**Calculates the sum of the all salaries of this specific Family*/
    public int getSalaries(){
        PreparedStatement ps;
        ResultSet rs;
        int salaries=0;
        try {
            String query="SELECT Salary FROM human WHERE FamilyUsername = ? AND Salary>=0";
            Connection con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            ps =con.prepareStatement(query);
            ps.setString(1,this.familyUsername);
            rs=ps.executeQuery();
            while(rs.next()){
                salaries+=rs.getInt("Salary");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salaries;
    }
}
