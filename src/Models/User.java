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

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    /** Checking if the given username exists neither in family nor in human Tables:*/
    public static boolean isUsernameExist(String username,boolean checker,String oldUsername){
        /**Checker stands for update the username that already exist*/
        /**oldUsername stands for validate the case that the user doesn't change his username but it still found at the DB*/
        PreparedStatement st;
        ResultSet rs;
        boolean exist = false;
        String query;
        if(checker)
            query = "(SELECT Username FROM family WHERE username= ?)UNION(SELECT Username FROM human WHERE username=? AND Username NOT IN(SELECT Username FROM human WHERE username= ?));";
        else
            query = "(SELECT Username FROM family WHERE username= ?)UNION(SELECT Username FROM human WHERE username=?);";
        try {
            st = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            st.setString(1, username);
            st.setString(2,username);
            if(checker)
                st.setString(3,oldUsername);
            rs = st.executeQuery();
            if (rs.next())
                exist = true;
            //close opened connections
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }
}
