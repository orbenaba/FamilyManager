package Controllers;

import Views.EditOutcomeView;
import Views.ShoppingCartView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class EditOutcomeController extends BaseForHomeSeqController {
    private EditOutcomeView eoview;
    public EditOutcomeController(EditOutcomeView eoview) {
        super(eoview);
        this.eoview = eoview;
        if (!eoview.readOnly) {
            eoview.addEditOutcomeAction(new EditOutcomeAction());
            eoview.addEnforcingPrice(new EnforcingPrice());
        }
    }

    /**
     * Enforcing the user to use only digits in price field
     */
    class EnforcingPrice extends KeyAdapter {
        public void keyTyped(KeyEvent e) {
            if (!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
    }

    class EditOutcomeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (verify()) {
                eoview.outcome.price = Integer.parseInt(eoview.price.getText());
                eoview.outcome.title = eoview.titleText.getText();
                mappingTextareaIntoFile(eoview.outcome.id, eoview.description, "Outcomes");//saving bio in file
                eoview.outcome.updateOutcome();
                new ShoppingCartController(new ShoppingCartView(eoview.username,null));
                eoview.dispose();
            }
         else {
                JOptionPane.showMessageDialog(null, "You must insert non-negative number to price");
            }
    }


        /**Helper function which intended to check if price is empty*/
        public boolean verify(){
            return !eoview.price.getText().trim().equals("");
        }

    }

}
