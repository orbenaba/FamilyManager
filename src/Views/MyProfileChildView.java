package Views;


import Models.Child;
import com.company.CircleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MyProfileChildView extends BaseForHomeSeqView {
    public JLabel removePhotoLabel;
    public String username;
    public Child child;

    public CircleButton addImage;
    public ImageIcon removePhoto;
    public JLabel imageContainer;
    public JButton deleteAccount;
    public JLabel usernameLabel,passwordLabel,firstNameLabel;
    public JTextArea bioArea;
    public JTextField usernameField,passwordField,firstNameField;
    public int width;
    @Override
    public String getUsername(){
        return this.username;
    }
    public MyProfileChildView(String username) {
        this.username = username;
        Font font = new Font("Arial", Font.BOLD, 40);
        Color bordo=new Color(219, 0, 40);
        width=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();

        getContentPane().setBackground(new Color(219, 102, 0));

        child = new Child(username);
        /**Loading child's image-if there is*/
        imageContainer=new JLabel();
        if (child.image == null) {
            imageContainer.setBounds(width/2-110, 22, 250, 300);
            imageContainer.setIcon(new ImageIcon(getClass().getResource("/Icons/profile2.png")));
            addImage = new CircleButton("",new Color(219, 102, 0));
            addImage.setBounds(width/2-38, 190, 78, 78);//Covers the plus that belongs to the image            add(imageContainer);
            add(imageContainer);
            add(addImage);
        } else {
            imageContainer.setBounds(width/2-239, 20, 478, 300);
            Image temp = child.image.getImage();
            Image newImage = temp.getScaledInstance(imageContainer.getWidth(), imageContainer.getHeight(), java.awt.Image.SCALE_SMOOTH);
            imageContainer.setIcon(new ImageIcon(newImage));
            //adding bin trash
            removePhoto = new ImageIcon(getClass().getResource("/Icons/removePhoto.png"));
            removePhotoLabel=new JLabel(removePhoto);
            removePhotoLabel.setBounds(imageContainer.getX()+imageContainer.getWidth()+50,imageContainer.getY(),40,50);
            add(removePhotoLabel);
            add(imageContainer);
        }
        /**-----------------------------------------------------------------------------------------------------*/

        //delete personal account button
        deleteAccount=new JButton("Delete account");
        deleteAccount.setFont(new Font("Arial",Font.BOLD,20));
        deleteAccount.setBounds(10,getHeight()-70,200,50);
        deleteAccount.setBackground(bordo);
        deleteAccount.setForeground(Color.black);
        /**-----------------------------------------------------------------------------------------------------*/
        addView();

        add(backToHome);
        add(deleteAccount);


        setVisible(true);
    }

    public void addView(){
        Font regFont=new Font("Arial",Font.BOLD,25);
        usernameLabel=new JLabel("Username:");
        passwordLabel=new JLabel("Password:");
        usernameLabel.setFont(regFont);
        passwordLabel.setFont(regFont);
        usernameLabel.setForeground(Color.black);
        passwordLabel.setForeground(Color.black);
        usernameLabel.setBounds(width/2-400,400,150,30);
        usernameField=new JTextField();
        usernameField.setBounds(width/2-250,400,300,50);
        passwordLabel.setBounds(width/2-400,470,150,30);
        passwordField=new JTextField();
        passwordField.setBounds(width/2-250,470,300,50);
        usernameField.setFont(regFont);
        passwordField.setFont(regFont);
        passwordField.setBackground(Color.orange);
        usernameField.setBackground(Color.orange);
        /***/
        /***/
        /***/
        firstNameLabel=new JLabel("Name:");
        firstNameLabel.setFont(regFont);
        firstNameLabel.setForeground(Color.black);
        firstNameLabel.setBounds(width/2-400,540,150,30);
        firstNameField=new JTextField();
        firstNameField.setBounds(width/2-250,540,300,50);
        firstNameField.setFont(regFont);
        firstNameField.setBackground(Color.orange);
        /***/
        /***/
        /***/
        bioArea=new JTextArea();
        StringBuilder bioContent=new StringBuilder();
        File file=new File("Biographies\\"+child.username+".txt");
        Scanner myScan= null;
        try {
            myScan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(myScan.hasNextLine()) {
            bioContent.append(myScan.nextLine()+'\n');
        }
        JLabel bio=new JLabel("Bio:");
        bio.setFont(regFont);
        bio.setForeground(Color.black);
        bio.setBounds(width/2+100,360,50,30);
        bioArea=new JTextArea(bioContent.toString());
        bioArea.setFont(regFont);
        bio.setVerticalTextPosition(0);
        bio.setHorizontalTextPosition(0);
        bioArea.setBackground(new Color(122, 5, 69));
        bioArea.setForeground(Color.orange);
        bioArea.setBounds(width/2+100,400,400,400);
        bioArea.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.blue));

        /**Set all the data*/
        usernameField.setText(child.username);
        passwordField.setText(child.password);
        firstNameField.setText(child.firstName);
        add(bio);
        add(bioArea);
        add(firstNameLabel);
        add(firstNameField);
        add(passwordField);
        add(usernameField);
        add(usernameLabel);
        add(passwordLabel);
    }
    public void addRemovePhotoListener(MouseAdapter mal){
        removePhotoLabel.addMouseListener(mal);
    }

    public void addImageAction(ActionListener mal) {
        addImage.addActionListener(mal);
    }
    //Delete account action
    public void addDeleteAccountAction(ActionListener mal){
        deleteAccount.addActionListener(mal);
    }



}
