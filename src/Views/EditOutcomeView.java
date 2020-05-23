package Views;


import Models.Outcome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class EditOutcomeView extends BaseForHomeSeqView{
    public Outcome outcome;
    public JTextArea description;
    public JLabel priceLabel,title,descriptionLabel,dateLabel,titleLabel;
    public JTextField price,titleText;
    public JButton editOutcome;
    public String username;
    @Override
    public String getUsername() {
        return this.username;
    }

    public EditOutcomeView(Outcome oldOutcome,String username) {
        //new username
        this.username=username;
        this.outcome=new Outcome(username,oldOutcome.id,oldOutcome.purchasedDate);

        getContentPane().setBackground(new Color(6, 103, 172));
        title=new JLabel("Edit outcome");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.ITALIC,50));
        title.setBounds(getWidth()/2-200,20,400,60);
        title.setForeground(new Color(176, 221, 252));



        priceLabel=new JLabel("Price:");
        priceLabel.setFont(new Font("Arial",Font.ITALIC,30));
        priceLabel.setForeground(new Color(176, 221, 252));
        priceLabel.setBounds(getWidth()/2-220,200,100,50);
        /**Setting the price text as the current price*/
        System.out.println("Outcome price is: "+outcome.price);
        price=new JTextField(String.valueOf(oldOutcome.price));
        price.setFont(new Font("Arial",Font.ITALIC,30));
        price.setBackground(new Color(176, 221, 252));
        price.setForeground(new Color(4, 62, 103));
        price.setBounds(getWidth()/2-120,200,300,50);






        titleLabel=new JLabel("Title:");
        titleLabel.setFont(new Font("Arial",Font.ITALIC,30));
        titleLabel.setForeground(new Color(176, 221, 252));
        titleLabel.setBounds(getWidth()/2+220,200,100,50);
        /**Setting the title text as the current title*/
        titleText=new JTextField(oldOutcome.title);
        titleText.setFont(new Font("Arial",Font.ITALIC,30));
        titleText.setBackground(new Color(176, 221, 252));
        titleText.setForeground(new Color(4, 62, 103));
        titleText.setBounds(getWidth()/2+320,200,300,50);







        descriptionLabel=new JLabel("Description:");
        descriptionLabel.setFont(new Font("Arial",Font.ITALIC,30));
        descriptionLabel.setForeground(new Color(176, 221, 252));
        descriptionLabel.setBounds(getWidth()/2-200,300,220,50);
        /**Reading the description from the required file in order to fulfill the textArea*/
        StringBuilder outcomeContent=new StringBuilder();
        File file=new File("Outcomes\\"+this.outcome.id+".txt");
        Scanner myScan= null;
        try {
            myScan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(myScan.hasNextLine()) {
            outcomeContent.append(myScan.nextLine()+'\n');
        }
        myScan.close();


        description=new JTextArea(outcomeContent.toString());
        description.setBackground(new Color(176, 221, 252));
        description.setForeground(new Color(4, 62, 103));
        description.setFont(new Font("Arial",Font.ITALIC,30));
        description.setBounds(getWidth()/2-200,350,500,350);


        editOutcome=new JButton("Apply changes");
        editOutcome.setBackground(new Color(176, 221, 252));
        editOutcome.setForeground(new Color(4, 62, 103));
        editOutcome.setFont(new Font("Arial",Font.ITALIC,30));
        editOutcome.setBounds(getWidth()/2-100,740,250,50);



        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        dateLabel=new JLabel("Date purchased: "+ dateFormat.format(this.outcome.purchasedDate));
        dateLabel.setForeground(new Color(176, 221, 252));
        dateLabel.setFont(new Font("Arial",Font.ITALIC,30));
        dateLabel.setBounds(100,200,400,30);



        add(titleLabel);
        add(titleText);
        add(dateLabel);
        add(editOutcome);
        add(description);
        add(descriptionLabel);
        add(price);
        add(priceLabel);
        add(title);
    }
    public void addEnforcingPrice(KeyAdapter mal){
        price.addKeyListener(mal);
    }

    public void addEditOutcomeAction(ActionListener mal){
        editOutcome.addActionListener(mal);
    }

}
