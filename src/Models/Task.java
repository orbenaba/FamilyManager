package Models;

import java.sql.*;

public class Task {
    public int id;//an identifier of the object
    public String username,title;//connect with family class
    public Date executedDate;//presents the date with seconds
    public Task(int id,Date executedDate, String username,String title) {
        this.id = id;
        this.username = username;
        this.executedDate = executedDate;
        this.title=title;
    }
    public Task(Date executedDate, String username,String title) {
        this.username = username;
        this.executedDate = executedDate;
        this.title=title;
    }

    public Task(Date executedDate, String username,int id) {
        this.id = id;
        this.username = username;
        this.executedDate = executedDate;
    }

    public boolean updateTask()
    {
        Connection con;
        PreparedStatement ps;
        String query="UPDATE task SET " +
                "Title = ?, Username = ?" +
                "WHERE id = ?;";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps=con.prepareStatement(query);
            ps.setString(1,this.title);
            ps.setString(2,this.username);
            ps.setInt(3,this.id);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
