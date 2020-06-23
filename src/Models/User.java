package Models;


import java.sql.*;

public class User {
    public String password;
    public String username;

    public User() {
        password = username = "";
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }
    /**
     * Checking if the given username exists neither in family nor in human Tables:
     */
    public static boolean isUsernameExist(String username, boolean checker, String oldUsername) {
        /**Checker stands for update the username that already exist*/
        /**oldUsername stands for validate the case that the user doesn't change his username but it still found at the DB*/
        PreparedStatement ps;
        ResultSet rs;
        boolean exist = false;
        String query;
        if (checker)
            query = "(SELECT Username FROM family WHERE username= ?)UNION(SELECT Username FROM human WHERE username=? AND Username NOT IN(SELECT Username FROM human WHERE username= ?));";
        else
            query = "(SELECT Username FROM family WHERE username= ?)UNION(SELECT Username FROM human WHERE username=?);";
        try {
            ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, username);
            if (checker)
                ps.setString(3, oldUsername);
            rs = ps.executeQuery();
            if (rs.next())
                exist = true;
            //close opened connections
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    public static Object loginFunction(String username, String password) {
        Connection con;
        String humanQuery = "SELECT * FROM human WHERE username= ? AND password = ?";
        String familyQuery = "SELECT * FROM family WHERE username= ? AND password = ?";
        PreparedStatement ps1 = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps1 = con.prepareStatement(humanQuery);
            ps1.setString(1, username);
            ps1.setString(2, password);
            ResultSet rs = ps1.executeQuery();
            /**The user is actually a parent/child*/
            if (rs.next()) {
                con.close();
                ps1.close();
                rs.close();
                return new User();
            }
            PreparedStatement ps2 = con.prepareStatement(familyQuery);
            ps2.setString(1, username);
            ps2.setString(2, password);
            rs = ps2.executeQuery();
            /**The user is actually a family*/
            if (rs.next()) {
                /**Verifies that the current family does not contain 10 people. If is is, then an error will be thrown
                 Using singleton architecture.*/
                String singleton10people = "SELECT COUNT(*) AS rowsCount FROM human WHERE FamilyUsername = ?";
                ps1 = con.prepareStatement(singleton10people);
                ps1.setString(1, username);
                rs = ps1.executeQuery();
                /**Counts exactly the rows in human table */
                rs.next();
                if (rs.getInt(1) >= 10) {
                    con.close();
                    ps1.close();
                    ps2.close();
                    rs.close();
                    return -1;
                } else {
                    con.close();
                    ps1.close();
                    ps2.close();
                    rs.close();
                    return new Family();
                }
            }
            /**404~User not found*/
            con.close();
            ps2.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
