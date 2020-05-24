package Controllers;

import Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

import static Models.Parent.isParent;


public class HomeController extends JframeController{
    private HomeView homeView;
    public HomeController(HomeView homeView){
        super(homeView);
        this.homeView=homeView;

        homeView.addSettingsAction(new SettingsAction());
        homeView.addFamilyTreeAction(new FamilyTreeAction());
        homeView.addShoppingCartAction(new ShoppingCartAction());
        homeView.addTasksAction(new TasksAction());
        homeView.addLogoffListener(new LogoffListener());
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

    class LogoffListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e){
            homeView.logOff.setBorder(BorderFactory.createMatteBorder(0,0,2,0, Color.blue));
        }
        @Override
        public void mouseExited(MouseEvent e){
            homeView.logOff.setBorder(null);
        }
        @Override
        public void mouseClicked(MouseEvent e){
            new StartController(new StartView());
            homeView.dispose();
        }
    }
}
