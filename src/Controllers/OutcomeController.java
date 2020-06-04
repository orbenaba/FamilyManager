package Controllers;

import Models.Outcome;
import Views.OutcomeView;
import Views.ShoppingCartView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.Calendar;
import static Views.RegisterHumanView.mappingTextareaIntoFile;


public class OutcomeController extends BaseForHomeSeqController{
    private OutcomeView ocview;

    public OutcomeController(OutcomeView ocview) {
        super(ocview);
        this.ocview=ocview;
        ocview.addEnforcingPrice(new EnforcingPrice());
        ocview.addAddOutcomeAction(new AddOutcomeAction());
        ocview.addPrice8Limit(new Price8Limit());
        ocview.addTitle30Limit(new Title30Limit());
    }

    //Limit the price of the outcome to maximum 8 digits
    class Price8Limit extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (ocview.price.getText().length() >= 8) // limit textfield to 8 characters
                e.consume();
        }
    }
    class Title30Limit extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (ocview.titleText.getText().length() >= 30) // limit textfield to 30 characters
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

    class AddOutcomeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (verify()) {
                Outcome outcome=new Outcome(Integer.parseInt(ocview.price.getText()),new Date(Calendar.getInstance().getTimeInMillis()), ocview.username,ocview.titleText.getText().trim().equals("")?"No title":ocview.titleText.getText());
                Integer id= ocview.shoppingCart.addOutcome(outcome);
                mappingTextareaIntoFile(id, ocview.description, "Outcomes");
                java.util.Date minDate=new java.util.Date();
                //Displaying all the outcomes in the last year by default
                minDate.setMonth(minDate.getMonth()-1);
                new ShoppingCartController(new ShoppingCartView(ocview.username,null,minDate));
                ocview.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "You must insert non-negative number to price");
            }
        }
        /**Helper function which intended to check if price is empty*/
        public boolean verify(){
            return !ocview.price.getText().trim().equals("");
        }

    }
}
