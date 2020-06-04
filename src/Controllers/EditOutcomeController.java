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
        //Only unbounded users can use thus privileges
        if (!eoview.readOnly) {
            eoview.addEditOutcomeAction(new EditOutcomeAction());
            eoview.addEnforcingPrice(new EnforcingPrice());
            eoview.addPrice8Limit(new Price8Limit());
            eoview.addTitle30Limit(new Title30Limit());
        }
    }

    //Limit the price of the outcome to maximum 8 digits
    class Price8Limit extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (eoview.price.getText().length() >= 8) // limit textfield to 8 characters
                e.consume();
        }
    }

    class Title30Limit extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            if (eoview.titleText.getText().length() >= 30) // limit textfield to 8 characters
                e.consume();
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
                java.util.Date minDate=new java.util.Date();
                //Displaying all the outcomes in the last year by default
                minDate.setMonth(minDate.getMonth()-1);
                new ShoppingCartController(new ShoppingCartView(eoview.username, null,minDate));
                eoview.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "You must insert non-negative number to price");
            }
        }

        /**
         * Helper function which intended to check if price is empty
         */
        public boolean verify() {
            return !eoview.price.getText().trim().equals("");
        }

    }
}
