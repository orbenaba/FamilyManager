package Models;


import java.sql.*;
import java.util.HashMap;

public class Family extends User {
    public int counter;
    public String lastName;
    public int currentMonthProfit;

    public Family() {
    }

    public Family(String username){
        this.username=username;
    }

    public Family(String username, String password, String counter, String lastName, String currentMonthProfit) {
        PreparedStatement ps;
        Connection con;
        String registerFamilyQuery = "INSERT INTO family(Username,Counter,CurrentMonthProfit,LastName,Password) VALUES(?,?,?,?,?)";
        try {
            con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            ps = con.prepareStatement(registerFamilyQuery);
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
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**Holds an hash map of the all family members(first name and status), and the family username*/
    public static class FamilyMembers{
        public HashMap<String, memberData> members;
        public String FamilyUsername;
        public FamilyMembers() {
            this.members = new HashMap<>();
        }
    }

    /**Returns the Members' username and firstName of the specific user
     * Key= Username(String)
     * Data=[First name, isParent?](memberData)*/
    public static FamilyMembers getFamilyMembers(String username) {
        FamilyMembers familyMembers = new FamilyMembers();
        boolean flag=true;
        Connection con;
        ResultSet rs;
        PreparedStatement ps;
        try {
            con = DriverManager.getConnection(MagicStrings.url, MagicStrings.user, MagicStrings.password);
            String getMembersQuery = "SELECT FirstName,Username,Salary,FamilyUsername " +
                    "FROM human WHERE FamilyUsername=ANY(" +
                    "SELECT FamilyUsername FROM human WHERE Username = ?)";
            ps = con.prepareStatement(getMembersQuery);
            ps.setString(1, username);
            rs = ps.executeQuery();
            //Absorbing all the family members
            if (rs.next()) {
                familyMembers.FamilyUsername = rs.getString("FamilyUsername");
                do {
                    /**If salary is lower then zero so the user is child*/
                    familyMembers.members.put(rs.getString("Username"),
                            new memberData(rs.getString("FirstName"), rs.getInt("Salary") >= 0));
                } while (rs.next());
            }
            else
                flag=false;
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag?familyMembers:null;
    }

    /**Holds the first name of a user and his status- parent/child*/
    public static class memberData{
        public boolean isParent;
        public String firstName;
        public memberData(String firstName,boolean isParent) {
            this.isParent = isParent;
            this.firstName = firstName;
        }
    }
}
