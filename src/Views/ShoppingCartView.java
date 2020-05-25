package Views;


import Models.Outcome;
import Models.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import static Models.Parent.isLimitChildren;
import static Models.Parent.isParent;

public class ShoppingCartView extends BaseForHomeSeqView {
    public ShoppingCart shoppingCart;
    public String username;
    public JLabel title;
    public JPanel outcomesPanel;
    public JButton addOutcome;
    public JLabel totalOutcomes;
    public boolean readOnly=false;


    public LinkedList<RowInShoppingCart> outcomeButtons;


    public JScrollPane outcomeScroller;

    @Override
    public String getUsername() {
        return this.username;
    }


    public ShoppingCartView(String username) {
        this.username = username;
        if (!isParent(username))
            if (isLimitChildren(username)) {
                readOnly = true;
            }


        getContentPane().setBackground(new Color(6, 103, 172));
        /**Title*/
        title = new JLabel(readOnly?"View your outcomes":"Manage your outcomes!");
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
        if (!readOnly) {
            addOutcome = new JButton("New outcome");
            addOutcome.setFont(new Font("Arial", Font.PLAIN, 30));
            addOutcome.setBackground(new Color(176, 221, 252));
            addOutcome.setForeground(new Color(4, 62, 103));
            addOutcome.setBounds(200, 120, 250, 100);
        }
        /**Out comes panel*/
        outcomesPanel = new JPanel();
        outcomesPanel.setLayout(new GridLayout(0, readOnly?1:3, 0, 15));
        convertListToButtons(shoppingCart.getOutcomes());
        outcomeScroller = new JScrollPane(outcomesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outcomeScroller.setBounds(getWidth() / 2 - 500, 250, 1000, getHeight() - 250);
        outcomeScroller.setPreferredSize(new Dimension(1000, getHeight() - 250));
        /**Increasing the speed of the scrolling as we wish:*/
        outcomeScroller.getVerticalScrollBar().setUnitIncrement(12);


        add(outcomeScroller);
        add(totalOutcomes);
        if (!readOnly)
            add(addOutcome);
        add(title);
    }

    public void addOutcomeAction(ActionListener mal) {
        addOutcome.addActionListener(mal);
    }


    public void convertListToButtons(LinkedList<Outcome> outcomes) {
        int i = 0;
        outcomeButtons = new LinkedList<RowInShoppingCart>();
        Font f=new Font("Arial",Font.ITALIC,30);
        int red=255,green=31,blue=31;
        if(!readOnly) {
            for (Outcome oc : outcomes) {
                RowInShoppingCart row = new RowInShoppingCart(oc);
                row.title.setPreferredSize(new Dimension(300, 100));
                row.title.setVerticalTextPosition(SwingConstants.CENTER);
                row.title.setHorizontalTextPosition(SwingConstants.CENTER);
                Color currentRowColor = new Color(((red += 15) % 100) + 100, ((green += 25) % 100) + 100, 48);
                row.title.setFont(f);
                row.edit.setFont(f);
                row.delete.setFont(f);
                row.title.setBackground(currentRowColor);
                row.delete.setBackground(currentRowColor);
                row.edit.setBackground(currentRowColor);
                outcomesPanel.add(row.title, BorderLayout.CENTER);
                outcomesPanel.add(row.delete, BorderLayout.CENTER);
                outcomesPanel.add(row.edit, BorderLayout.CENTER);
                i++;
                /**Adding the new row at the created table into a list in order to listen to its buttons later on*/
                outcomeButtons.add(row);
            }
        }
        else{
            for (Outcome oc : outcomes) {
                RowInShoppingCart row = new RowInShoppingCart(oc);
                row.title.setPreferredSize(new Dimension(1000, 100));
                row.title.setVerticalTextPosition(SwingConstants.CENTER);
                row.title.setHorizontalTextPosition(SwingConstants.CENTER);
                Color currentRowColor = new Color(((red += 15) % 100) + 100, ((green += 25) % 100) + 100, 48);
                row.title.setFont(f);
                row.title.setBackground(currentRowColor);
                outcomesPanel.add(row.title, BorderLayout.CENTER);
                i++;
                /**Adding the new row at the created table into a list in order to listen to its buttons later on*/
                outcomeButtons.add(row);
            }
        }
    }


    public class RowInShoppingCart{
        public JButton title;
        public JButton edit,delete;
        public Outcome outcome;
        public RowInShoppingCart(JButton title, JButton edit, JButton delete,Outcome outcome) {
            this.title = title;
            this.edit = edit;
            this.delete = delete;
            this.outcome=outcome;
        }
        public RowInShoppingCart(Outcome outcome) {
            title=new JButton(outcome.title);
            edit=new JButton("Edit");
            delete=new JButton("Delete");
            this.outcome=outcome;
        }

    }


    /**Adding a listener for each delete button*/
    public void addDeletesListener(ActionListener mal){
        for(RowInShoppingCart ob : outcomeButtons)
            ob.delete.addActionListener(mal);
    }

    /**Adding a listener for each edit button*/
    public void addEditsListener(ActionListener mal){
        for(RowInShoppingCart ob : outcomeButtons)
            ob.edit.addActionListener(mal);
    }

    public void addTitlesListener(ActionListener mal){
        for(RowInShoppingCart ob : outcomeButtons)
            ob.title.addActionListener(mal);
    }
}
