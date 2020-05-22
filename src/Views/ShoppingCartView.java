package Views;


import Models.Outcome;
import Models.ShoppingCart;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ShoppingCartView extends BaseForHomeSeqView {
    public ShoppingCart shoppingCart;
    public String username;
    public JLabel title;
    public JPanel outcomesPanel;
    public JButton addOutcome;
    public JLabel totalOutcomes;

    public JList<JButton> outcomeButtons;


    public JScrollPane outcomeScroller;

    @Override
    public String getUsername() {
        return this.username;
    }


    public ShoppingCartView(String username) {
        this.username = username;
        getContentPane().setBackground(new Color(6, 103, 172));
        /**Title*/
        title = new JLabel("Manage your outcomes!");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.ITALIC, 50));
        title.setBounds(getWidth() / 2 - 300, 20, 600, 60);
        title.setForeground(new Color(176, 221, 252));


        /**Shopping cart*/
        shoppingCart = new ShoppingCart(username);
        totalOutcomes = new JLabel("Total outcomes: " + shoppingCart.calculateShoppingCart());
        totalOutcomes.setForeground(new Color(4, 62, 103));
        totalOutcomes.setFont(new Font("Arial", Font.PLAIN, 30));
        totalOutcomes.setBounds(500, 120, 400, 40);


        /**Add new outcome button*/
        addOutcome = new JButton("New outcome");
        addOutcome.setFont(new Font("Arial", Font.PLAIN, 30));
        addOutcome.setBackground(new Color(176, 221, 252));
        addOutcome.setForeground(new Color(4, 62, 103));
        addOutcome.setBounds(200, 120, 250, 100);

        /**Out comes panel*/
        outcomesPanel = new JPanel();
        outcomesPanel.setLayout(new GridLayout(0, 1, 5, 10));
        outcomeButtons = convertListToButtons(shoppingCart.getOutcomes());
        outcomeScroller = new JScrollPane(outcomesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outcomeScroller.setBounds(getWidth() / 2 - 500, 250, 1000, getHeight() - 250);
        outcomeScroller.setPreferredSize(new Dimension(1000, getHeight() - 250));
        /**Increasing the speed of the scrolling:*/
        outcomeScroller.getVerticalScrollBar().setUnitIncrement(16);


        add(outcomeScroller);
        add(totalOutcomes);
        add(addOutcome);
        add(title);
    }

    public void addOutcomeAction(ActionListener mal) {
        addOutcome.addActionListener(mal);
    }





    public JList<JButton> convertListToButtons(LinkedList<Outcome> objects) {
        int i = 0;
        JList<JButton> buttons = new JList<>();
        for (Outcome ob : objects) {
            JButton btn = new JButton(ob.title);
            btn.setPreferredSize(new Dimension(1000, 100));
            buttons.add(btn);
            outcomesPanel.add(btn, BorderLayout.CENTER);
            i++;
            System.out.println(ob.title);
        }
        return buttons;
    }


    private class RowInShoppingCart{
        JButton title,edit,delete;
        public RowInShoppingCart(JButton title, JButton edit, JButton delete) {
            this.title = title;
            this.edit = edit;
            this.delete = delete;
        }

        public RowInShoppingCart() {
            title=new JButton();
            edit=new JButton();
            delete=new JButton();
        }
    }
}
