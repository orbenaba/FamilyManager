package Views;

import Models.Human;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static Models.Gender.getGenderById;
import static Models.Human.findRelation;
import static Models.Human.getHumanData;


public class showProfileByIdView extends BaseForHomeSeqView {
    public String myUsername, watchedUsername;
    public Human human;
    public JLabel imgContainer;
    public JLabel nameLabel,usernameLabel;
    public JLabel genderLabel,birthdayLabel,relationLabel;
    public JTextArea bioArea;
    public JScrollPane pane;


    @Override
    public String getUsername(){
        return this.myUsername;
    }

    public showProfileByIdView(String myUsername, String watchedUsername,boolean amIParent,boolean isHeParent) {
        this.myUsername = myUsername;
        this.watchedUsername = watchedUsername;
        getContentPane().setBackground(new Color(122, 205, 19));
        human=getHumanData(watchedUsername);
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
        addPersonalDataToView(amIParent,isHeParent);
        add(imgContainer);
        setVisible(true);
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

        StringBuilder bioContent=new StringBuilder();
        File file=new File("Biographies\\"+watchedUsername+".txt");
        if(file!=null) {
            Scanner myScan = null;
            try {
                myScan = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (myScan.hasNextLine()) {
                bioContent.append(myScan.nextLine() + '\n');
            }
            myScan.close();
        }
        JLabel bio=new JLabel("Bio:");
        bio.setFont(f);
        bio.setForeground(Color.blue);
        bio.setBounds(getWidth()/2+100,360,50,30);
        bioArea =new JTextArea(bioContent.toString());
        bioArea.setFont(f);
        bio.setVerticalTextPosition(0);
        bio.setHorizontalTextPosition(0);
        bioArea.setBackground(new Color(122, 5, 69));
        bioArea.setForeground(Color.blue);
        bioArea.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.blue));
        bioArea.setEditable(false);
        pane = new JScrollPane(bioArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(getWidth()/2+50,400,550,400);
        pane.setPreferredSize(new Dimension(550,400));

        add(bio);
        add(pane);
        add(relationLabel);
        add(nameLabel);
        add(birthdayLabel);
        add(genderLabel);
        add(usernameLabel);
    }
}