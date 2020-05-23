package Views;

import Models.ShoppingCart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OutcomeView extends BaseForHomeSeqView {
    public String username;
    public JTextArea description;
    public JLabel priceLabel,title,descriptionLabel,dateLabel,titleLabel;
    public JTextField price,titleText;
    public JButton addOutcome;
    public ShoppingCart shoppingCart;

    @Override
    public String getUsername(){
        return this.username;
    }

    public OutcomeView(String username,ShoppingCart shoppingCart) {
        this.shoppingCart=shoppingCart;
        this.username = username;
        getContentPane().setBackground(new Color(6, 103, 172));

        title=new JLabel("New outcome");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.ITALIC,50));
        title.setBounds(getWidth()/2-200,20,400,60);
        title.setForeground(new Color(176, 221, 252));



        priceLabel=new JLabel("Price:");
        priceLabel.setFont(new Font("Arial",Font.ITALIC,30));
        priceLabel.setForeground(new Color(176, 221, 252));
        priceLabel.setBounds(getWidth()/2-220,200,100,50);
        price=new JTextField();
        price.setFont(new Font("Arial",Font.ITALIC,30));
        price.setBackground(new Color(176, 221, 252));
        price.setForeground(new Color(4, 62, 103));
        price.setBounds(getWidth()/2-120,200,300,50);






        titleLabel=new JLabel("Title:");
        titleLabel.setFont(new Font("Arial",Font.ITALIC,30));
        titleLabel.setForeground(new Color(176, 221, 252));
        titleLabel.setBounds(getWidth()/2+220,200,100,50);
        titleText=new JTextField();
        titleText.setFont(new Font("Arial",Font.ITALIC,30));
        titleText.setBackground(new Color(176, 221, 252));
        titleText.setForeground(new Color(4, 62, 103));
        titleText.setBounds(getWidth()/2+320,200,300,50);







        descriptionLabel=new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial",Font.ITALIC,30));
        descriptionLabel.setForeground(new Color(176, 221, 252));
        descriptionLabel.setBounds(getWidth()/2-200,300,220,50);
        description=new JTextArea();
        description.setBackground(new Color(176, 221, 252));
        description.setForeground(new Color(4, 62, 103));
        description.setFont(new Font("Arial",Font.ITALIC,30));
        description.setBounds(getWidth()/2-200,350,500,350);


        addOutcome=new JButton("Add");
        addOutcome.setBackground(new Color(176, 221, 252));
        addOutcome.setForeground(new Color(4, 62, 103));
        addOutcome.setFont(new Font("Arial",Font.ITALIC,30));
        addOutcome.setBounds(getWidth()/2-100,740,200,50);



        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateLabel=new JLabel("Today is "+ dateFormat.format(date));
        dateLabel.setForeground(new Color(176, 221, 252));
        dateLabel.setFont(new Font("Arial",Font.ITALIC,30));
        dateLabel.setBounds(100,200,320,30);



        add(titleLabel);
        add(titleText);
        add(dateLabel);
        add(addOutcome);
        add(description);
        add(descriptionLabel);
        add(price);
        add(priceLabel);
        add(title);
    }

    public void addEnforcingPrice(KeyAdapter mal){
        price.addKeyListener(mal);
    }

    public void addAddOutcomeAction(ActionListener mal){
        addOutcome.addActionListener(mal);
    }
}
