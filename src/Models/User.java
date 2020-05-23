package Models;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public String password;
    public String username;

    public User()
    {
        password=username="";
    }
    public User(String username,String password){
        this.password=password;
        this.username=username;
    }
    /** Checking if the given username exists neither in family nor in human Tables:*/
    public static boolean isUsernameExist(String username){
        PreparedStatement st;
        ResultSet rs;
        boolean exist = false;
        String query = "(SELECT Username FROM family WHERE username= ?)UNION(SELECT Username FROM human WHERE username=?) ";
        try {
            st = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            st.setString(1, username);
            st.setString(2,username);
            rs = st.executeQuery();
            if (rs.next()) {
                exist = true;
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
}
