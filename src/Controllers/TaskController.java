package Controllers;



import Views.TaskView;
import Views.TasksListView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;



import static Views.RegisterHumanView.mappingTextareaIntoFile;

public class TaskController extends BaseForHomeSeqController {
    private TaskView tview;

    public TaskController(TaskView tview) {
        super(tview);
        this.tview = tview;


        tview.addAddTaskAction(new AddTaskAction());
    }

    class AddTaskAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Connection con;
            PreparedStatement ps;
            ResultSet rs;
            String query;
            Integer id = null;
            try {

                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
                query = "INSERT INTO task(Username,executedDate,Title) VALUES(?,?,?)";
                ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, tview.username);
                ps.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
                ps.setString(3, tview.titleText.getText().trim().equals("") ? "No title" : tview.titleText.getText());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();
                id = rs.getInt(1);
                ps.close();
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            mappingTextareaIntoFile(id, tview.description, "Tasks");
            new TasksListController(new TasksListView(tview.username));
            tview.dispose();
        }
    }
}