package Controllers;

import Models.Outcome;
import Models.ShoppingCart;
import Views.IncomeView;
import Views.ShoppingCartView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.Calendar;

public class IncomeController {
    private IncomeView iview;
    private JFrame oldView;

    public IncomeController(IncomeView iview,JFrame oldView) {
        this.iview = iview;
        this.oldView=oldView;
        iview.addSubmitAction(new AmountListener());
        iview.addAmountListener(new SubmitAction());
        iview.addIncomeLimit8(new Amount8Limit());
    }

    class AmountListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!iview.amount.getText().trim().equals("")){
                /**Wi'll distinguish between income and outcome by the minus of the income*/
                Outcome in=new Outcome(-1*Integer.parseInt(iview.amount.getText()),new Date(Calendar.getInstance().getTimeInMillis()),iview.username,"");
                /**Using the default ctor just for mediation*/
                ShoppingCart sc=new ShoppingCart();
                sc.addOutcome(in);
                oldView.getContentPane().remove(iview);
                iview.dispose();
                oldView.dispose();
                new ShoppingCartController(new ShoppingCartView(iview.username,null));

            }
        }
    }
    class Amount8Limit extends KeyAdapter{
        @Override
        public void keyTyped(KeyEvent e) {
            if (iview.amount.getText().length() >= 8) // limit textfield to 8 characters
                e.consume();
        }
    }


    class SubmitAction extends KeyAdapter {
        public void keyTyped(KeyEvent e) {
            if (!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
    }
}
