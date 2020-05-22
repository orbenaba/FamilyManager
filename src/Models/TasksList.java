package Models;

import java.sql.*;
import java.util.LinkedList;

public class TasksList {
    public String familyUsername;
    public LinkedList<Task> tasks;

    public TasksList(String username) {
        tasks=new LinkedList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query="SELECT*FROM task WHERE username=" +
                "ANY(SELECT Username FROM human WHERE FamilyUsername=" +
                "ANY(SELECT FamilyUsername FROM human WHERE Username= ?));";

        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps=con.prepareStatement(query);
            ps.setString(1,username);
            rs=ps.executeQuery();
            while(rs.next()){
                Task task=new Task(rs.getInt("id"),rs.getDate("executedDate"),rs.getString("username"),rs.getString("title"));
                tasks.add(task);
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


    public LinkedList<Task> getTasks() {
        return tasks;
    }
}
