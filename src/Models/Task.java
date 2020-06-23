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

    //This function is called after the local task is updated.
    //After it, we need to update the DB appropriately
    //returns true in case of success
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Returns true if a given id exists in the outcome Table
    public static Task taskExists(int id) {
        String query = "SELECT id,ExecutedDate,Title,Username FROM task WHERE id = ?";
        try {
            PreparedStatement ps = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return new Task(rs.getInt(1), rs.getDate(2),
                        rs.getString(4), rs.getString(3));
            else
                return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
