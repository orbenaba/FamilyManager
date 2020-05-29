package Models;


import java.sql.*;

public class Outcome {
    public int id;//an identifier of the object
    public String username,title;//connect with family class
    public int price;
    public Date purchasedDate;//presents the date with seconds
    /**Saving the description in a file whose name is "id.txt" in Outcomes directory*/
    public Outcome(int id,int price,Date purchasedDate, String username,String title) {
        this.id = id;
        this.username = username;
        this.price = price;
        this.purchasedDate = purchasedDate;
        this.title=title;
    }
    public Outcome(int price,Date purchasedDate, String username,String title) {
        this.username = username;
        this.price = price;
        this.purchasedDate = purchasedDate;
        this.title=title;
    }
    public Outcome(String username,int id,Date purchasedDate) {
        this.username = username;
        this.id = id;
        this.purchasedDate = purchasedDate;
    }

    //This function is called after the local outcome is updated.
    //After it, we need to update the DB appropriately
    //returns true in case of success
    public boolean updateOutcome()
    {
        Connection con;
        PreparedStatement ps;
        String query="UPDATE outcome SET " +
                "price = ?, Title = ?, Username = ?" +
                "WHERE id = ?;";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps=con.prepareStatement(query);
            ps.setInt(1,this.price);
            ps.setString(2,this.title);
            ps.setString(3,this.username);
            ps.setInt(4,this.id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
