package Models;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
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
        String query = "SELECT*FROM task WHERE executedDate >= ? AND username=" +
                "ANY(SELECT Username FROM human WHERE FamilyUsername=" +
                "ANY(SELECT FamilyUsername FROM human WHERE Username= ?));";
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps = con.prepareStatement(query);
            /**Automatically showing all the tasks to the users only the last 30 days from the current date*/
            java.util.Date curDate=new java.util.Date();
            curDate.setMonth(curDate.getMonth()-1);
            java.sql.Date last30Days=new java.sql.Date(curDate.getTime());
            ps.setDate(1,last30Days);
            ps.setString(2, username);
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
    /**Returns the order of the task-executes
     * first: most activist.
     * last: less activist.
     *Problem: It seems to be very resemble to the "Majority problem", but it just seems
     * In a given list, we need to find the most/less common element.
     * */
    public String[] getActivistData() {
        //We'll use in this kind of hashMap: (username,howManyTasks)
        //java initialize all the counters to zero
        HashMap<String, Integer> counters = new HashMap<>();
        for (Task task : tasks) {
            //the user doesn't exist in the hashMap
            if (!counters.containsKey(task.username))
                counters.put(task.username, 0);
            else
                counters.put(task.username, counters.get(task.username) + 1);
        }
        int size = counters.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            //Catching each iteration the maximum counter
            arr[i] = counters.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
            counters.remove(arr[i]);
        }
        //temp code
        for (int i = 0; i < size; i++)
            System.out.println(arr[i]);
        return arr;
    }

    //checking whether the tasksList is empty
    public boolean isEmpty(){
        return this.tasks.isEmpty();
    }
}
