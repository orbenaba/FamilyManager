package Views;

import Models.Child;
import Models.Human;
import Models.User;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

import static Models.Gender.getGenderById;
import static Views.MyProfileChildView.getChild;
import static Views.StartView.init_Exit_Minimize;

public class showProfileByIdView extends Jframe {
    public String myUsername, watchedUsername;
    public Human human;
    public JLabel imgContainer;
    public JLabel nameLabel,usernameLabel;
    public JLabel genderLabel,birthdayLabel,relationLabel,bioLabel;

    public JLabel exit, minimize;
    public Border frameExMin;

    @Override
    public JLabel getExit() {
        return exit;
    }

    @Override
    public JLabel getMinimize() {
        return minimize;
    }

    public showProfileByIdView(String myUsername, String watchedUsername,boolean amIParent,boolean isHeParent) {
        this.myUsername = myUsername;
        this.watchedUsername = watchedUsername;
        Font font = new Font("Arial", Font.BOLD, 40);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);//Full screen undependable platform
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);
        getContentPane().setBackground(new Color(122, 205, 19));

        frameExMin = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
        exit = new JLabel("X");
        minimize = new JLabel("-");
        init_Exit_Minimize(getWidth() - 70, 0, 35, exit, minimize, frameExMin, true);//prevent code replication
        human=getUserData(watchedUsername);
        imgContainer=new JLabel();
        if(human.image==null){
            imgContainer.setIcon(new ImageIcon(getClass().getResource("/Icons/noProfile.jpg")));
            imgContainer.setBounds(getWidth()/2-239,20,478,300);
            imgContainer.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.blue));
        }
        else{
            imgContainer.setBounds(getWidth()/2-239,20,478,300);
            Image temp = human.image.getImage();
            Image newImage = temp.getScaledInstance(imgContainer.getWidth(), imgContainer.getHeight(), java.awt.Image.SCALE_SMOOTH);
            imgContainer.setIcon(new ImageIcon(newImage));
        }
        addPersonalDataToView(isHeParent,amIParent);





        add(imgContainer);
        add(minimize);
        add(exit);
        setVisible(true);
    }

    public static Human getUserData(String username) {
        Connection con = null;
        PreparedStatement ps;
        ResultSet rs;
        Human human;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root");
            String query = "SELECT*FROM human WHERE Username=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();
            rs.next();
            if (rs.getInt("Salary") < 0) {
                con.close();
                return getChild(username);
            }
            // return getParent(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void addPersonalDataToView(boolean amIParent,boolean isHeParent){
        usernameLabel=new JLabel("Username: "+human.username);
        nameLabel=new JLabel("Name: "+human.firstName);
        birthdayLabel=new JLabel("Birthday: "+human.birthday);
        genderLabel=new JLabel("Gender: "+getGenderById(human.genderId));
        relationLabel=new JLabel("Relation to me: "+findRelation(amIParent,isHeParent,human.genderId));
        Font f=new Font("Arial",Font.BOLD,25);
        usernameLabel.setFont(f);
        nameLabel.setFont(f);
        birthdayLabel.setFont(f);
        genderLabel.setFont(f);
        relationLabel.setFont(f);
        usernameLabel.setBounds(getWidth()/2-400,400,400,30);
        nameLabel.setBounds(getWidth()/2-400,470,400,30);
        birthdayLabel.setBounds(getWidth()/2-400,540,400,30);
        genderLabel.setBounds(getWidth()/2-400,610,400,30);
        relationLabel.setBounds(getWidth()/2-400,680,400,30);
        genderLabel.setForeground(Color.blue);
        birthdayLabel.setForeground(Color.blue);
        nameLabel.setForeground(Color.blue);
        usernameLabel.setForeground(Color.blue);
        relationLabel.setForeground(Color.blue);

        /***/
        /***/
        /***/
        StringBuilder bioContent=new StringBuilder();
        File file=new File(watchedUsername+".txt");
        Scanner myScan= null;
        try {
            myScan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(myScan.hasNextLine())
            bioContent.append(myScan.nextLine());
        JLabel bio=new JLabel("Bio:");
        bio.setFont(f);
        bio.setForeground(Color.blue);
        bio.setBounds(getWidth()/2+100,360,50,30);
        bioLabel=new JLabel(bioContent.toString());
        bioLabel.setFont(f);
        bioLabel.setBackground(new Color(122, 205, 119));
        bioLabel.setForeground(Color.blue);
        bioLabel.setBounds(getWidth()/2+100,400,400,400);
        bioLabel.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.blue));

        myScan.close();
        add(bio);
        add(bioLabel);
        add(relationLabel);
        add(nameLabel);
        add(birthdayLabel);
        add(genderLabel);
        add(usernameLabel);
    }
    public static String findRelation(boolean amIParent,boolean isHeParent,byte gender){
        return amIParent?(isHeParent?(gender==1?("My wife"):("My husband")):(gender==1?("My daughter"):("My son"))):
                (isHeParent?(gender==1)?("My mother"):("My father"):(gender==1)?("My sister"):("My brother"));

    }
}