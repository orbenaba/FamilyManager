package Models;

import java.io.File;
import java.sql.*;
import java.util.LinkedList;

public class TasksList {
    public String familyUsername;
    public LinkedList<Task> tasks;
    //Constructor:
    //Retrieving all the existing tasks from the DB which belongs to the all family of the given username
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

    //Add a new Task into the tasksList
    //return->>Id of new task
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
            //Getting the auto-generate key of the task which is given automatically by the DB
            //we need this id in order to create a file with this name later on.
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
    //Deleting a task by its id, this function is used when the user clicked on "Delete" option in the TasksListView
    //return true=>>Successful delete
    //return false=>>Failed
    public boolean deleteTask(int id) {
        Connection con;
        PreparedStatement ps;
        String query = "DELETE FROM task WHERE id = ?;";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            con.close();
            /**Deleting the file which holds the description of the outcome*/
            File deleted = new File("Tasks\\" + id + ".txt");
            deleted.delete();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //Deletes all the files of the deleted human
    //cleaning redundant resources
    public static boolean deleteTasksList(String username){
        try {
            String query="SELECT id FROM task WHERE Username = ?;";
            PreparedStatement ps= DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ps.setString(1,username);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                File file=new File("Tasks\\"+rs.getInt(1)+".txt");
                file.delete();
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //checking whether the tasksList is empty
    public boolean isEmpty(){
        return this.tasks.isEmpty();
    }
}
