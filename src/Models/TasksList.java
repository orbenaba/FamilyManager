package Models;

import java.sql.*;
import java.util.Calendar;
import java.util.LinkedList;

public class TasksList {
    public String familyUsername;
    public LinkedList<Task> tasks;

    public TasksList(String username) {
        tasks = new LinkedList<>();
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT*FROM task WHERE username=" +
                "ANY(SELECT Username FROM human WHERE FamilyUsername=" +
                "ANY(SELECT FamilyUsername FROM human WHERE Username= ?));";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getDate("executedDate"), rs.getString("username"), rs.getString("title"));
                tasks.add(task);
            }
            query = "SELECT FamilyUsername FROM human WHERE Username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            familyUsername = rs.getString("FamilyUsername");
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

    public Integer addTask(Task task) {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query;
        Integer id = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            query = "INSERT INTO task(Username,executedDate,Title) VALUES(?,?,?)";
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.username);
            ps.setDate(2, task.executedDate);
            ps.setString(3, task.title);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            ps.close();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }
}
