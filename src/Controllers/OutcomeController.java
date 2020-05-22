package Controllers;

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
                Connection con;
                PreparedStatement ps;
                ResultSet rs;
                String query;
                Integer id = null;
                try {

                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
                    query = "INSERT INTO outcome(Username,Price,PurchasedDate,Title) VALUES(?,?,?,?)";
                    ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, ocview.username);
                    ps.setInt(2, Integer.parseInt((ocview.price.getText())));
                    ps.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                    ps.setString(4,ocview.titleText.getText().trim().equals("")?"No title":ocview.titleText.getText());
                    ps.executeUpdate();
                    rs = ps.getGeneratedKeys();
                    rs.next();
                    id = rs.getInt(1);
                    ps.close();
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                mappingTextareaIntoFile(id, ocview.description, "Outcomes");
                new ShoppingCartController(new ShoppingCartView(ocview.username));
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
