package Views;


import Models.Child;
import com.company.CircleButton;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;


public class MyProfileChildView extends BaseForHomeSeqView {
    public JLabel removePhotoLabel;
    public String username;
    public Child child;

    public CircleButton addImage;
    public ImageIcon removePhoto;
    public JLabel imageContainer;
    public JButton deleteAccount,updateAccount;
    public JLabel usernameLabel,passwordLabel,firstNameLabel,birthdayLabel,statusLabel;
    public JTextArea bioArea;
    public JTextField usernameField,passwordField,firstNameField,statusField;
    public JCheckBox isSingle;
    //image path
    public String imagePath=null;

    /**Birthday calendar */
    public JDateChooser dateChooser;
    public Calendar calendar;
    public int width,height;
    @Override
    public String getUsername(){
        return this.username;
    }
    public MyProfileChildView(String username) {
        this.username = username;
        Font font = new Font("Arial", Font.BOLD, 40);
        Color bordo=new Color(219, 0, 40);
        width=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        height=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

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
        deleteAccount.setFocusPainted(false);
        /**-----------------------------------------------------------------------------------------------------*/
        addView();
        /**Now we must add the qualities which characteristic the child from parent*/
        statusLabel=new JLabel("Status:");
        statusLabel.setFont(new Font("Arial",Font.BOLD,25));
        statusLabel.setForeground(Color.black);
        statusLabel.setBounds(width/2-400, 670, 150, 35);
        statusField=new JTextField(child.status);
        statusField.setBounds(width/2-250,670,300,50);
        statusField.setFont(new Font("Arial",Font.BOLD,25));
        statusField.setBackground(Color.orange);

        isSingle=new JCheckBox("Single?");
        isSingle.setHorizontalTextPosition(SwingConstants.LEFT);
        isSingle.setFont(new Font("Arial",Font.BOLD,25));
        isSingle.setBackground(new Color(219, 102, 0));
        //get rid of the border which is drawn by default around the checkBox
        isSingle.setFocusPainted(false);
        isSingle.setOpaque(true);
        isSingle.setForeground(Color.black);
        isSingle.setSelected(child.isSingle);
        isSingle.setBounds(width/2-397,740,300,50);



        add(isSingle);
        add(statusLabel);
        add(statusField);
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
        calendar = Calendar.getInstance();

        /**Set the date in the calendar to the previous date of birth which the user was insert before*/
        dateChooser = new JDateChooser(child.birthday);
        //enforcing the user to choose a valid date. I range [NOW,NOW-120]
        java.util.Date currentDate= new java.util.Date();
        java.util.Date minDate=new java.util.Date(currentDate.getYear()-120,currentDate.getMonth(),currentDate.getDay());
        dateChooser.setMaxSelectableDate(currentDate);
        dateChooser.setMinSelectableDate(minDate);
        dateChooser.setBounds(width/2-250, 610, 170, 35);
        dateChooser.setFont(new Font("Arial", Font.BOLD, 17));
        dateChooser.setBackground(new Color(1,23,122));
        dateChooser.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        birthdayLabel=new JLabel("Birthday:");
        birthdayLabel.setFont(regFont);
        birthdayLabel.setForeground(Color.black);
        birthdayLabel.setBounds(width/2-400, 610, 150, 35);
        /**----------------------------------------------------------------------------------*/
        updateAccount=new JButton("Update account");
        updateAccount.setFont(new Font("Arial",Font.BOLD,20));
        updateAccount.setBounds(width/2+400,250,200,50);
        updateAccount.setBackground(new Color(122, 5, 69));
        updateAccount.setForeground(Color.black);
        updateAccount.setFocusPainted(false);

        /**Set all the data*/
        usernameField.setText(child.username);
        passwordField.setText(child.password);
        firstNameField.setText(child.firstName);
        add(updateAccount);
        add(birthdayLabel);
        add(dateChooser);
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

    public void addUpdateAccountAction(ActionListener mal){
        updateAccount.addActionListener(mal);
    }

}
