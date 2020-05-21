package Controllers;

import Views.OutcomeView;
import Views.ShoppingCartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingCartController extends BaseForHomeSeqController{
    private ShoppingCartView scview;

    public ShoppingCartController(ShoppingCartView scview){
        super(scview);
        this.scview=scview;
        scview.addOutcomeAction(new OutcomeAction());
    }

    class OutcomeAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new OutcomeController(new OutcomeView(scview.username));
            scview.dispose();
        }
    }
}
