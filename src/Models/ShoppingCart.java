package Models;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    public String familyUsername;
    public List<Outcome> outcomes;

    public ShoppingCart(String username) {
        outcomes=new LinkedList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query="SELECT*FROM outcome WHERE Username=" +
                "ANY(SELECT Username FROM human WHERE FamilyUsername=" +
                "ANY(SELECT FamilyUsername FROM human WHERE Username= ?));";

        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps=con.prepareStatement(query);
            ps.setString(1,username);
            rs=ps.executeQuery();
            while(rs.next()){
                Outcome outcome=new Outcome(rs.getInt("id"),rs.getInt("Price"),rs.getDate("PurchasedDate"),rs.getString("Username"));
                outcomes.add(outcome);
            }
            query="SELECT FamilyUsername FROM human WHERE Username = ?";
            ps=con.prepareStatement(query);
            ps.setString(1,username);
            rs=ps.executeQuery();
            rs.next();
            familyUsername=rs.getString("FamilyUsername");
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public int calculateShoppingCart(){
        int sum=0;
        for(Outcome c : outcomes)
            sum+=c.price;
        return sum;
    }

}
