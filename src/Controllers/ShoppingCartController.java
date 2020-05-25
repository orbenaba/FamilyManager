package Controllers;

import Views.EditOutcomeView;
import Views.OutcomeView;
import Views.ShoppingCartView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ShoppingCartController extends BaseForHomeSeqController{
    private ShoppingCartView scview;

    public ShoppingCartController(ShoppingCartView scview) {
        super(scview);
        this.scview = scview;
        if (!scview.shoppingCart.isEmpty()) {
            if (!scview.readOnly) {
                scview.addDeletesListener(new DeletesListener());
                scview.addEditsListener(new EditsListener());
            }
            scview.addTitlesListener(new TitlesListener());
        }
        if(!scview.readOnly)
            scview.addOutcomeAction(new OutcomeAction());
    }

    class OutcomeAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new OutcomeController(new OutcomeView(scview.username,scview.shoppingCart));
            scview.dispose();
        }
    }

    class DeletesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /**First, we must identify the button which was clicked in order to delete its associated Outcome*/
            Object src = e.getSource();
            ShoppingCartView.RowInShoppingCart clicked = null;
            for (ShoppingCartView.RowInShoppingCart row : scview.outcomeButtons) {
                if (src == row.delete) {
                    clicked = row;
                    break;
                }
            }
            /**So far, we got the specific delete button which invoked*/
            if (clicked != null) {
                scview.shoppingCart.deleteOutcome(clicked.outcome.id);
                /**Despite we delete the outcome from the shopping cart,
                 * We didn't yet update the view, so in order to create nice Look&Feel,
                 * we'll remove components*/
                new ShoppingCartController(new ShoppingCartView(clicked.outcome.username));
                scview.dispose();
            }
        }
    }
    class EditsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /**First, we must identify the button which was clicked in order to delete its associated Outcome*/
            Object src = e.getSource();
            ShoppingCartView.RowInShoppingCart clicked = null;
            for (ShoppingCartView.RowInShoppingCart row : scview.outcomeButtons) {
                if (src == row.edit) {
                    clicked = row;
                    break;
                }
            }
            /**So far, we got the specific edit button which invoked*/
            if (clicked != null) {
                /**Needs to update the username*/
                new EditOutcomeController(new EditOutcomeView(clicked.outcome,scview.username,scview.readOnly));
                scview.dispose();
            }
        }
    }

    class TitlesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            /**First, we must identify the button which was clicked in order to delete its associated Outcome*/
            Object src = e.getSource();
            ShoppingCartView.RowInShoppingCart clicked = null;
            for (ShoppingCartView.RowInShoppingCart row : scview.outcomeButtons) {
                if (src == row.title) {
                    clicked = row;
                    break;
                }
            }
            /**So far, we got the specific edit button which invoked*/
            if (clicked != null) {
                /**Needs to update the username*/
                new EditOutcomeController(new EditOutcomeView(clicked.outcome,scview.username,true));
                scview.dispose();
            }
        }
    }

}
