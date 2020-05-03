package Controllers;

import Views.HomeView;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class HomeController {
    private HomeView homeView;
    public HomeController(HomeView homeView){
        this.homeView=homeView;

        addMinimizeAction(new RegisterController.MinimizeListeners(homeView,true),homeView.minimize);
        addExitAction(new RegisterController.ExitListeners(homeView,true),homeView.exit);

    }
}
