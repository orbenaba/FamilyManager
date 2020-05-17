package Controllers;

import Views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            new MyProfileController(new MyProfileView(homeView.username));
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
            new TasksController(new TasksView(homeView.username));
            homeView.dispose();
        }
    }
}
