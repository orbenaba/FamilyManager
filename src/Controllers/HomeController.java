package Controllers;

import Views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class HomeController {
    private HomeView homeView;
    public HomeController(HomeView homeView){
        this.homeView=homeView;

        addMinimizeAction(new RegisterController.MinimizeListeners(homeView,true),homeView.minimize);
        addExitAction(new RegisterController.ExitListeners(homeView,true),homeView.exit);
        homeView.addSettingsAction(new SettingsAction());
        homeView.addFamilyTreeAction(new FamilyTreeAction());
        homeView.addShoppingCartAction(new ShoppingCartAction());
        homeView.addTasksAction(new TasksAction());
    }

    class SettingsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isParent(homeView.username))
                new MyProfileParentController(new MyProfileParentView(homeView.username));
            else
                new MyProfileChildController(new MyProfileChildView(homeView.username));
            homeView.dispose();
        }
    }
    public static boolean isParent(String username) {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        String query = "SELECT Salary FROM human WHERE Username=?";
        System.out.println("Username= "+username);
        boolean ret = false;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            if (rs.getInt("Salary") >= 0)
                ret = true;
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


    class FamilyTreeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new FamilyTreeController(new FamilyTreeView(homeView.username));
            homeView.dispose();
        }
    }


    class ShoppingCartAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ShoppingCartController(new ShoppingCartView(homeView.username));
            homeView.dispose();
        }
    }


    class TasksAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new TasksListController(new TasksListView(homeView.username));
            homeView.dispose();
        }
    }
}
