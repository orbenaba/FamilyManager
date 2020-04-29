package Views;


import Models.Family;
import com.company.CircleButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;


import static Views.StartView.init_Exit_Minimize;


public class RegisterParentView extends Jframe {
    public JLabel exit,minimize,imageContainer;
    public Border frameExMin;


    public ImageIcon image;
    public CircleButton addImage;

    @Override
    public JLabel getMinimize(){
        return this.minimize;
    }
    @Override
    public JLabel getExit(){
        return this.exit;
    }
    public RegisterParentView(Family family)
    {
        //get rid of the ugly frame which is given by default
        setUndecorated(true);
        setSize(600,750);
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.black);


        exit=new JLabel("X");
        minimize=new JLabel("-");
        frameExMin=BorderFactory.createMatteBorder(1,1,1,1,Color.black);
        init_Exit_Minimize(getWidth()-85,15,35,exit,minimize,frameExMin,false);//prevent code replication


        image=new ImageIcon(getClass().getResource("/Icons/profile2.png"));




        imageContainer=new JLabel(image);
        imageContainer.setBounds(175,20,250,250);
        addImage=new CircleButton("");
        addImage.setBounds(260,160,82,82);//Covers the plus that belongs to the image




        add(imageContainer);
        add(addImage);
        add(exit);
        add(minimize);
        setVisible(true);
    }
    public void addImageAction(ActionListener mal){
        addImage.addActionListener(mal);
    }

}