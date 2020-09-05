package Views;


import Models.Outcome;
import Models.ShoppingCart;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.LinkedList;

import static Models.Parent.isLimitChildren;
import static Models.Parent.isParent;


public class ShoppingCartView extends BaseForHomeSeqView {
    public ShoppingCart shoppingCart;
    public String username;
    public JLabel title;
    public JPanel outcomesPanel;
    public JButton addOutcome, addIncome,selectDate;
    public JLabel totalOutcomes,totalSalaries,totalIncomes, noOutcomes, percentageComes, datesRangeLabel;
    public boolean readOnly = false;
    public JFrame addIn;
    public JPanel backTo;
    public LinkedList<RowInShoppingCart> outcomeButtons;
    public JLabel background;
    public JScrollPane outcomeScroller;
    public JDateChooser dateChooser;
    public Date minDate;

    @Override
    public String getUsername() {
        return this.username;
    }

    public ShoppingCartView(String username, JFrame addIn,Date minDate) {
        this.minDate=minDate;
        this.addIn = addIn;
        this.username = username;
        if (!isParent(username))
            if (isLimitChildren(username)) {
                readOnly = true;
            }
        background = new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/Icons/woodBack.jpg")));
        background.setBounds(0, 0, getWidth(), getHeight());

        getContentPane().setBackground(new Color(6, 103, 172));
        /**Title*/
        title = new JLabel(readOnly ? "View your outcomes" : "Manage your packet!");
        title.setForeground(Color.orange.brighter());
        title.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.orange.brighter()));
        title.setFont(new Font("David", Font.ITALIC, 70));
        title.setBounds(getWidth() / 2 - 350, 20, 620, 70);

        /**By default, we want to display only the outcomes which were bought in the last month!*/
       // Date date = new Date();
      //  date.setMonth(date.getMonth()-1);//CurrentMonth--
        java.sql.Date sqlDate = new java.sql.Date(minDate.getTime());
        java.sql.Date curDateSql=new java.sql.Date((new Date()).getTime());
        //A label that helps the user to better understanding the displayed outcomes
        datesRangeLabel=new JLabel(sqlDate.toString()+"<-->"+curDateSql);
        datesRangeLabel.setFont(new Font("David", Font.ITALIC, 36));
        datesRangeLabel.setOpaque(true);
        datesRangeLabel.setBackground(Color.orange.brighter());
        datesRangeLabel.setBounds(getWidth()/2-750,300,385,60);

        /**Permitting the user to choose his own date*/
        dateChooser = new JDateChooser(minDate);
        //enforcing the user to choose a valid date. I range [NOW,NOW-120]
        java.util.Date currentDate= new java.util.Date();
        java.util.Date minDate2=new java.util.Date(currentDate.getYear()-120,currentDate.getMonth(),currentDate.getDay());
        dateChooser.setMaxSelectableDate(currentDate);
        dateChooser.setMinSelectableDate(minDate2);
        dateChooser.setBounds(getWidth()/2-747, 370, 160, 30);
        dateChooser.setFont(new Font("David",Font.ITALIC,25));
        dateChooser.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.orange.brighter()));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        selectDate=new JButton("Select date");
        selectDate.setFocusPainted(false);
        selectDate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectDate.setBackground(Color.orange.brighter());
        selectDate.setFont(new Font("David",Font.ITALIC,30));
        selectDate.setBounds(getWidth()/2-580,370,200,35);


        /**Shopping cart*/
        shoppingCart = new ShoppingCart(username,new java.sql.Date(minDate.getTime()));
        int outcomes = shoppingCart.calculateShoppingCart();
        int incomes=shoppingCart.calculateIncomes(username,sqlDate);
        int salaries=shoppingCart.getSalaries();
        totalOutcomes = new JLabel("Total outcomes: " + outcomes);
        totalOutcomes.setOpaque(true);
        totalOutcomes.setBackground(Color.orange.darker());
        totalOutcomes.setFont(new Font("David", Font.ITALIC, 36));
        totalOutcomes.setBounds(getWidth()/2-750, 520, 400, 40);
        totalIncomes = new JLabel("Total incomes: " + incomes);
        totalIncomes.setOpaque(true);
        totalIncomes.setBackground(Color.orange.darker());
        totalIncomes.setFont(new Font("David", Font.ITALIC, 36));
        totalIncomes.setBounds(getWidth()/2-750, 570, 400, 40);
        totalSalaries = new JLabel("Total salaries: " + salaries);
        totalSalaries.setOpaque(true);
        totalSalaries.setBackground(Color.orange.darker());
        totalSalaries.setFont(new Font("David", Font.ITALIC, 36));
        totalSalaries.setBounds(getWidth()/2-750, 620, 400, 40);
        /**Showing the outcomes in relate to the incomes*/
        EncapsulteColorAndText en=calculatePercentage(outcomes,incomes+salaries);
        String statement = en.text;
        percentageComes=new JLabel(statement);
        percentageComes.setForeground(Color.black.brighter());
        percentageComes.setFont(new Font("David",Font.ITALIC,40));
        percentageComes.setBounds(0,0,0,0);
        backTo=new JPanel();
        backTo.setBackground(en.color);
        backTo.setBounds(680,180,830,55);
        backTo.add(percentageComes);
        /**Add new outcome button*/
        if (!readOnly) {
            addOutcome = new JButton("New outcome");
            addOutcome.setFocusPainted(false);
            addOutcome.setFont(new Font("David", Font.ITALIC, 36));
            addOutcome.setBackground(new Color(238, 145, 145));
            addOutcome.setForeground(new Color(4, 62, 103));
            addOutcome.setBounds(80, 120, 250, 100);
            addIncome = new JButton("New income");
            addIncome.setFocusPainted(false);
            addIncome.setFont(new Font("David", Font.ITALIC, 36));
            addIncome.setBackground(new Color(238, 145, 145));
            addIncome.setForeground(new Color(4, 62, 103));
            addIncome.setBounds(360, 120, 250, 100);
        }
        /**Outcomes panel*/
        outcomesPanel = new JPanel();
        if (!shoppingCart.isEmpty()) {
            outcomesPanel.setLayout(new GridLayout(0, readOnly ? 1 : 3, 0, 15));
            outcomesPanel.setBackground(Color.orange.darker());
            convertListToButtons(shoppingCart.getOutcomes());
            outcomeScroller = new JScrollPane(outcomesPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            outcomeScroller.setBounds(getWidth() / 2 - 300, 250, 1000, getHeight() - 250);
            outcomeScroller.setPreferredSize(new Dimension(1000, getHeight() - 250));
            /**Increasing the speed of the scrolling as we wish:*/
            outcomeScroller.getVerticalScrollBar().setUnitIncrement(12);
            add(outcomeScroller);
        } else {
            outcomesPanel.setBounds(getWidth() / 2 - 300, 250, 1000, getHeight() - 250);
            outcomesPanel.setBackground(Color.orange.darker());
            noOutcomes = new JLabel();
            noOutcomes.setIcon(new ImageIcon(getClass().getResource("/Icons/X2.jpg")));
            outcomesPanel.add(noOutcomes);
            noOutcomes.setBounds(0, 50, 500, 500);
            add(outcomesPanel);
        }
        add(selectDate);
        add(dateChooser);
        add(datesRangeLabel);
        add(totalSalaries);
        add(totalIncomes);
        add(backTo);
        add(totalOutcomes);
        if (!readOnly) {
            add(addOutcome);
            add(addIncome);
        }
        add(title);
        add(background);
    }
    public void addSelectDateAction(ActionListener mal){
        selectDate.addActionListener(mal);
    }

    public void addOutcomeAction(ActionListener mal) {
        addOutcome.addActionListener(mal);
    }

    public void addIncomeAction(ActionListener mal) {
        addIncome.addActionListener(mal);
    }

    public void convertListToButtons(LinkedList<Outcome> outcomes) {
        int i = 0;
        outcomeButtons = new LinkedList<RowInShoppingCart>();
        Font f = new Font("Arial", Font.ITALIC, 30);
        int red = 255, green = 31, blue = 31;
        if (!readOnly) {
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
        } else {
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

    public class RowInShoppingCart {
        public JButton title;
        public JButton edit, delete;
        public Outcome outcome;

        public RowInShoppingCart(Outcome outcome) {
            Font f = new Font("David", Font.ITALIC, 40);
            title = new JButton(outcome.title);
            edit = new JButton("Edit");
            delete = new JButton("Delete");
            title.setFont(f);
            edit.setFont(f);
            delete.setFont(f);
            this.outcome = outcome;
        }
    }
    /**
     * Adding a listener for each delete button
     */
    public void addDeletesListener(ActionListener mal) {
        for (RowInShoppingCart ob : outcomeButtons)
            ob.delete.addActionListener(mal);
    }
    /**
     * Adding a listener for each edit button
     */
    public void addEditsListener(ActionListener mal) {
        for (RowInShoppingCart ob : outcomeButtons)
            ob.edit.addActionListener(mal);
    }

    public void addTitlesListener(ActionListener mal) {
        for (RowInShoppingCart ob : outcomeButtons)
            ob.title.addActionListener(mal);
    }
    /**
     * Returns the  percentage of the incomes in relation to outcomes
     */
    public EncapsulteColorAndText calculatePercentage(int loss,int prof) {
        BigDecimal profit = new BigDecimal(prof);
        BigDecimal loss2 = new BigDecimal(loss);
        Color foreground;
        if (loss == 0) {
            foreground = new Color(135, 13, 255).darker();
            return new EncapsulteColorAndText(foreground, "No spending money");
        }
        if (profit.equals(0)) {
            foreground = new Color(255, 5, 5).brighter();
            return new EncapsulteColorAndText(foreground, "You have no incomes/salaries");
        }
        BigDecimal proposition2=null;
        if (profit.intValue()!=0) {
            proposition2 = loss2.divide(profit, 2, RoundingMode.HALF_UP);
        }
        else{
            foreground = new Color(135, 13, 255).darker();
            return new EncapsulteColorAndText(foreground, "No available incomes");
        }
        double proposition = proposition2.doubleValue();
        if (proposition > 1) {
            foreground = new Color(255, 5, 5);
            return new EncapsulteColorAndText(foreground, "You are facing with DEFICIT!");
        }
        if (proposition == 1) {
            foreground = new Color(255, 5, 5).brighter();
            return new EncapsulteColorAndText(foreground, "Your incomes cover exactly the outcomes");
        }
        if (proposition >= 0.9) {
            foreground = new Color(255, 35, 5).brighter();
            return new EncapsulteColorAndText(foreground, "More then 90% of your incomes were spent");
        }
        if (proposition >= 0.8) {
            foreground = new Color(255, 65, 5).brighter();
            return new EncapsulteColorAndText(foreground, "More then 80% of your incomes were spent");
        }
        if (proposition >= 0.7) {
            foreground = new Color(255, 95, 5).brighter();
            return new EncapsulteColorAndText(foreground, "More then 70% of your incomes were spent");
        }
        if (proposition >= 0.6) {
            foreground = new Color(255, 125, 5).brighter();
            return new EncapsulteColorAndText(foreground, "More then 60% of your incomes were spent");
        }
        if (proposition >= 0.5) {
            foreground = new Color(255, 165, 5).brighter();
            return new EncapsulteColorAndText(foreground, "More then 50% of your incomes were spent");
        }
        if (proposition >= 0.4) {
            foreground = new Color(255, 225, 5).brighter();
            return new EncapsulteColorAndText(foreground, "More then 40% of your incomes were spent");
        }
        if (proposition >= 0.3) {
            foreground = new Color(205, 255, 5).brighter();
            return new EncapsulteColorAndText(foreground, "More then 30% of your incomes were spent");
        }
        if (proposition >= 0.2) {
            foreground = new Color(150, 255, 15).brighter();
            return new EncapsulteColorAndText(foreground, "More then 20% of your incomes were spent");
        }
        if (proposition >= 0.1) {
            foreground = new Color(100, 255, 15).brighter();
            return new EncapsulteColorAndText(foreground, "More then 10% of your incomes were spent");
        }
        foreground = new Color(0, 255, 0).brighter();
        return new EncapsulteColorAndText(foreground, "Good Job! you spent less then 10% of your incomes");
    }
    private class EncapsulteColorAndText{
        Color color;
        String text;
        public EncapsulteColorAndText(Color color, String text) {
            this.color = color;
            this.text = text;
        }
    }
}