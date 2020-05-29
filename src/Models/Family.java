package Models;



import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Family extends User{
    private int counter;
    public String lastName;
    public int currentMonthProfit;
    public Family(String username,String password,String counter, String lastName, String currentMonthProfit) {
        PreparedStatement ps;
        String registerFamilyQuery = "INSERT INTO family(Username,Counter,CurrentMonthProfit,LastName,Password) VALUES(?,?,?,?,?)";
        try {
            ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(registerFamilyQuery);
            ps.setString(1, username);
            ps.setString(2, counter);
            ps.setString(3,currentMonthProfit);
            ps.setString(4, lastName);
            ps.setString(5, password);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
