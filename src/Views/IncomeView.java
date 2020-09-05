package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;


public class IncomeView extends JFrame {
    public String username;
    public JLabel amountLabel;
    public JTextField amount;
    public JButton submit;

    public IncomeView(String username) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setLayout(null);
        setBounds(610, 220, 400, 150);
        getContentPane().setBackground(new Color(234, 168, 118));
        this.username = username;
        amount = new JTextField();
        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 80, 140, 50);
        amountLabel.setFont(new Font("David", Font.ITALIC, 30));
        amount.setBounds(150, 80, 200, 50);
        amount.setFont(new Font("David", Font.ITALIC, 30));
        amount.setBackground(new Color(238, 145, 145));
        submit = new JButton("Add");
        submit.setForeground(new Color(4, 62, 103));
        submit.setBounds(50, 20, 300, 50);
        submit.setFont(new Font("David", Font.ITALIC, 35));
        submit.setBackground(new Color(189, 61, 225));
        submit.setFocusPainted(false);
        add(submit);
        add(amount);
        add(amountLabel);
        setVisible(true);
    }
    public void addIncomeLimit8(KeyAdapter mal){
        amount.addKeyListener(mal);
    }
    public void addSubmitAction(ActionListener mal){
        submit.addActionListener(mal);
    }
    public void addAmountListener(KeyAdapter mal){
        amount.addKeyListener(mal);
    }
}
