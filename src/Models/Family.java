package Models;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Family extends User {
    public int counter;
    public String lastName;
    public int currentMonthProfit;

    public Family(String username, String password, String counter, String lastName, String currentMonthProfit) {
        PreparedStatement ps;
        String registerFamilyQuery = "INSERT INTO family(Username,Counter,CurrentMonthProfit,LastName,Password) VALUES(?,?,?,?,?)";
        try {
            ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(registerFamilyQuery);
            ps.setString(1, username);
            ps.setString(2, counter);
            ps.setString(3, currentMonthProfit);
            ps.setString(4, lastName);
            ps.setString(5, password);
            ps.executeUpdate();
            this.lastName = lastName;
            this.username = username;
            this.currentMonthProfit = Integer.valueOf(currentMonthProfit);
            this.counter = Integer.parseInt(counter);
            this.password = password;
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static String getFirstFamily() {
        String username = "";
        try {
            //selecting first child's username
            String query = "";
            query = "SELECT Username FROM family LIMIT 0,1;";
            PreparedStatement ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                username = rs.getString(1);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }
}
