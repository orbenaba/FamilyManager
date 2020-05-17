package Controllers;

import Views.ShoppingCartView;

import static Views.RegisterView.addExitAction;
import static Views.RegisterView.addMinimizeAction;

public class ShoppingCartController {
    private ShoppingCartView scview;


    public ShoppingCartController(ShoppingCartView scview){
        this.scview=scview;

        addMinimizeAction(new RegisterController.MinimizeListeners(scview, true), scview.minimize);
        addExitAction(new RegisterController.ExitListeners(scview, true), scview.exit);
    }
}
