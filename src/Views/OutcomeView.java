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
    public JLabel priceLabel,title,descriptionLabel,dateLabel,titleLabel,background;
    public JTextField price,titleText;
    public JButton addOutcome;
    public ShoppingCart shoppingCart;
    public JScrollPane pane;

    @Override
    public String getUsername(){
        return this.username;
    }

    public OutcomeView(String username,ShoppingCart shoppingCart) {
        this.shoppingCart=shoppingCart;
        this.username = username;
        getContentPane().setBackground(new Color(6, 103, 172));

        title=new JLabel("New outcome");
        title.setFont(new Font("David", Font.ITALIC,60));
        title.setBounds(getWidth()/2-200,20,400,60);
        title.setForeground(new Color(176, 221, 252));
        title.setBackground(new Color(168, 0, 0));
        title.setOpaque(true);

        background=new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/Icons/manageBack.jpg")));
        background.setBounds(0,0,getWidth(),getHeight());


        priceLabel=new JLabel("Price:");
        priceLabel.setFont(new Font("David",Font.ITALIC,36));
        priceLabel.setForeground(new Color(208, 0, 0));
        priceLabel.setBounds(getWidth()/2-220,200,100,50);
        price=new JTextField();
        price.setFont(new Font("David",Font.ITALIC,36));
        price.setBackground(new Color(238, 145, 145));
        price.setForeground(new Color(4, 62, 103));
        price.setBounds(getWidth()/2-120,200,300,50);






        titleLabel=new JLabel("Title:");
        titleLabel.setFont(new Font("David",Font.ITALIC,36));
        titleLabel.setForeground(new Color(208, 0, 0));
        titleLabel.setBounds(getWidth()/2+220,200,100,50);
        titleText=new JTextField();
        titleText.setFont(new Font("David",Font.ITALIC,35));
        titleText.setBackground(new Color(238, 145, 145));
        titleText.setForeground(new Color(4, 62, 103));
        titleText.setBounds(getWidth()/2+320,200,300,50);







        descriptionLabel=new JLabel("Description:");
        descriptionLabel.setFont(new Font("David",Font.ITALIC,36));
        descriptionLabel.setForeground(new Color(208, 0, 0));
        descriptionLabel.setBounds(getWidth()/2-200,300,220,50);
        description=new JTextArea(4,30);
        description.setBackground(new Color(238, 145, 145));
        description.setForeground(new Color(4, 62, 103));
        description.setFont(new Font("David",Font.ITALIC,35));
        description.setBounds(getWidth()/2-200,350,500,350);
        pane = new JScrollPane(description, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(getWidth()/2-200,350,500,350);
        pane.setPreferredSize(new Dimension(500,350));


        addOutcome=new JButton("Add");
        addOutcome.setBackground(new Color(238, 145, 145));
        addOutcome.setForeground(new Color(4, 62, 103));
        addOutcome.setFont(new Font("David",Font.ITALIC,36));
        addOutcome.setBounds(getWidth()/2-100,740,200,50);



        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        dateLabel=new JLabel("Today is "+ dateFormat.format(date));
        dateLabel.setForeground(new Color(208, 0, 0));
        dateLabel.setFont(new Font("David",Font.ITALIC,36));
        dateLabel.setBounds(100,400,320,30);



        add(titleLabel);
        add(titleText);
        add(dateLabel);
        add(addOutcome);
        add(pane);
        add(descriptionLabel);
        add(price);
        add(priceLabel);
        add(title);
        add(background);
    }

    public void addPrice8Limit(KeyAdapter mal){
        price.addKeyListener(mal);
    }
    public void addTitle30Limit(KeyAdapter mal){
        titleText.addKeyListener(mal);
    }

    public void addEnforcingPrice(KeyAdapter mal){
        price.addKeyListener(mal);
    }

    public void addAddOutcomeAction(ActionListener mal){
        addOutcome.addActionListener(mal);
    }
}
