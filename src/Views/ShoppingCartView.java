package Views;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ShoppingCartView extends BaseForHomeSeqView {
    public String username;
    public JLabel title;
    public JPanel outcomesPanel;
    public JButton addOutcome;

    public JScrollPane outcomeScroller;
    @Override
    public String getUsername(){
        return this.username;
    }



    public ShoppingCartView(String username){
        this.username=username;
        getContentPane().setBackground(new Color(6, 103, 172));
        /**Title*/
        title=new JLabel("Manage your outcomes!");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.ITALIC,50));
        title.setBounds(getWidth()/2-300,20,600,60);
        /**Out comes panel*/
        outcomesPanel=new JPanel();
        outcomesPanel.setBackground(new Color(176, 221, 252));
        outcomesPanel.setBounds(getWidth()/2-500,250,1000,getHeight()-250);
        outcomesPanel.setBorder(BorderFactory.createMatteBorder(2,2,0,2,new Color(4, 62, 103)));
        //Scroller in the jpanel

        outcomesPanel.setLayout(null);
        outcomeScroller=new JScrollPane();
        outcomeScroller.setBounds(outcomesPanel.getWidth()-40,0,40,outcomesPanel.getHeight());
        outcomeScroller.setBackground(Color.black);
        outcomesPanel.add(outcomeScroller);

        /**Add new outcome button*/
        addOutcome=new JButton("New outcome");
        addOutcome.setFont(new Font("Arial",Font.PLAIN,30));
        addOutcome.setBackground(new Color(176, 221, 252));
        addOutcome.setForeground(new Color(4, 62, 103));
        addOutcome.setBounds(200,120,250,100);


        add(addOutcome);
        add(outcomesPanel);
        add(title);
    }
    public void addOutcomeAction(ActionListener mal){
        addOutcome.addActionListener(mal);
    }
}
