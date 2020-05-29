package Views;


import com.company.CircleButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.io.*;
import java.util.Calendar;
import static Models.Gender.getGenders;


public class RegisterHumanView extends Jframe {
    public JLabel imageContainer, firstNameLabel, birthdayLabel,removePhotoLabel,bioLabel,usernameLabel, passwordLabel, confirmPasswordLabel;;
    public JTextField firstName,username;
    public ImageIcon image,removePhoto;
    public CircleButton addImage;
    public JPasswordField password, confirmPassword;
    public JComboBox genders;
    //Birthday
    public JDateChooser dateChooser;
    public Calendar calendar;
    public JScrollPane pane;
    //Submit
    public JButton create;
    //bio
    public JTextArea bio;
    //image path
    public String imagePath=null;
    public JLabel background;
    public Color fore,back;
    public Font f;


    public RegisterHumanView() {
        super(((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()));
        //Changing the caret in the entire text fields
        UIManager.put("TextField.caretForeground", new ColorUIResource(Color.red));
        setExtendedState(MAXIMIZED_BOTH);
        fore=new Color(7, 0, 204).brighter();
        back=new Color(128, 153, 255).brighter();
        f=new Font("David",Font.ITALIC,30);
        int width=getWidth(),height=getHeight();

        setLocationRelativeTo(null);
        background=new JLabel();
        background.setIcon(new ImageIcon(getClass().getResource("/Icons/registerBackground.jpg")));
        background.setBounds(0,0,width,height);
        image = new ImageIcon(getClass().getResource("/Icons/profile3.png"));
        imageContainer = new JLabel(image);
        imageContainer.setBounds(getWidth()/2-239, 20, 478, 300);
        addImage = new CircleButton("",Color.ORANGE);
        addImage.setBounds(width/2-38, 190, 78, 78);//Covers the plus that belongs to the image

        firstNameLabel = new JLabel("First name:");
        firstNameLabel.setFont(f);
        firstNameLabel.setForeground(fore);
        firstNameLabel.setBounds(130, 350, 150, 35);
        firstName = new JTextField(10);
        firstName.setFont(f);
        firstName.setForeground(fore);
        firstName.setBackground(back);
        firstName.setBounds(280, 350, 250, 50);
        firstName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, fore));

        java.util.List<String> gendersArray = getGenders();
        gendersArray.add("Select gender");
        genders = new JComboBox(gendersArray.toArray());
        genders.setBounds(580, 350, 240, 35);
        genders.setFont(f);
        genders.setForeground(fore);
        genders.setBackground(back);
        genders.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, fore));
        genders.setSelectedItem(("Select gender"));

        addUsernamePassword(130, 440, 55);

        bioLabel=new JLabel("Bio:");
        bioLabel.setFont(f);
        bioLabel.setForeground(fore);
        bioLabel.setBounds(910,365,60,30);
        bio = new JTextArea(4, 30);
        bio.setFont(f);
        bio.setBackground(back);
        bio.setForeground(fore);
        bio.setLineWrap(true);
        bio.setWrapStyleWord(true);
        bio.setForeground(fore);
        /**Adding a scroll bar to the TextArea in case there is no place*/
        pane = new JScrollPane(bio, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBounds(900, 400, 450, 300);
        pane.setPreferredSize(new Dimension(250,300));

        create=new JButton("Create");
        create.setBounds(1200,750,200,50);
        create.setBackground(back);
        create.setForeground(fore);
        create.setFont(f);
        create.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(dateChooser);
        add(create);
        add(bioLabel);
        add(pane);
        add(birthdayLabel);
        add(usernameLabel);
        add(passwordLabel);
        add(confirmPasswordLabel);
        add(username);
        add(confirmPassword);
        add(password);
        add(genders);
        add(firstNameLabel);
        add(firstName);
        add(imageContainer);
        add(addImage);

    }

    public void addImageAction(ActionListener mal) {
        addImage.addActionListener(mal);
    }

    public void addFirstNameListener(MouseAdapter mal) {
        firstNameLabel.addMouseListener(mal);
        firstName.addMouseListener(mal);
    }

    public void addGendersListener(MouseAdapter mal) {
        genders.addMouseListener(mal);
    }

    public void handleComboAction(ActionListener mal) {
        genders.addActionListener(mal);
    }


    //Listeners
    public void addLimit12CharactersFName(KeyAdapter mal){
        firstName.addKeyListener(mal);
    }
    public void addLimit18CharactersUName(KeyAdapter mal){
        username.addKeyListener(mal);
    }
    public void addLimit18CharactersPass(KeyAdapter mal){
        password.addKeyListener(mal);
    }
    public void addLimit12CharactersConfPass(KeyAdapter mal){
        confirmPassword.addKeyListener(mal);
    }


    public void addUsernamePassword(int x, int y, int iconSize) {
        Color color = new Color(48,48,48);
        username = new JTextField();
        password = new JPasswordField();
        confirmPassword = new JPasswordField();
        password.setFont(f);
        confirmPassword.setFont(f);
        username.setFont(f);
        password.setForeground(fore);
        confirmPassword.setForeground(fore);
        username.setForeground(fore);
        usernameLabel = new JLabel();
        passwordLabel = new JLabel();
        confirmPasswordLabel = new JLabel();
        passwordLabel.setIcon(new ImageIcon(getClass().getResource("/Icons/passIcon2.png")));
        confirmPasswordLabel.setIcon(new ImageIcon(getClass().getResource("/Icons/passIcon2.png")));
        usernameLabel.setIcon(new ImageIcon(getClass().getResource("/Icons/userIcon.png")));
        usernameLabel.setBounds(x-20, y, iconSize, iconSize);
        birthdayLabel = new JLabel("Date of birth:");
        birthdayLabel.setBounds(x+300, y+5, 180, 35);
        birthdayLabel.setForeground(fore);
        birthdayLabel.setFont(f);
        calendar = Calendar.getInstance();
        dateChooser = new JDateChooser(calendar.getTime());
        //enforcing the user to choose a valid date. I range [NOW,NOW-120]
        java.util.Date currentDate= new java.util.Date();
        java.util.Date minDate=new java.util.Date(currentDate.getYear()-120,currentDate.getMonth(),currentDate.getDay());
        dateChooser.setMaxSelectableDate(currentDate);
        dateChooser.setMinSelectableDate(minDate);
        dateChooser.setBounds(x+480, y, 170, 35);
        dateChooser.setFont(f);
        dateChooser.setBackground(back);
        dateChooser.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, fore));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        passwordLabel.setBounds(x, y + iconSize + 40, iconSize, iconSize);
        confirmPasswordLabel.setBounds(x + 360, y + iconSize + 40, iconSize, iconSize);
        password.setBackground(back);
        confirmPassword.setBackground(back);
        username.setBackground(back);
        password.setBorder(BorderFactory.createMatteBorder(1,1,1,1,fore));
        confirmPassword.setBorder(BorderFactory.createMatteBorder(1,1,1,1,fore));
        username.setBorder(BorderFactory.createMatteBorder(1,1,1,1,fore));
        password.setBounds(x + iconSize + 10, y + iconSize + 40, 200, iconSize - 10);
        confirmPassword.setBounds(x + iconSize + 370, y + iconSize + 40, 200, iconSize - 10);
        username.setBounds(x + iconSize + 10, y, 200, iconSize - 10);
    }

    public void addPasswordListener(MouseAdapter mal) {
        password.addMouseListener(mal);
    }

    public void addConfirmPasswordListener(MouseAdapter mal) {
        confirmPassword.addMouseListener(mal);
    }

    public void addUsernameListener(MouseAdapter mal) {
        username.addMouseListener(mal);
    }

    public void addCalendarListener(MouseAdapter mal) {
        birthdayLabel.addMouseListener(mal);
        dateChooser.addMouseListener(mal);
    }

    public void addCreateListener(MouseAdapter mal){
        create.addMouseListener(mal);
    }

    public void addBioListener(MouseAdapter mal){bio.addMouseListener(mal);}
    //Functionality
    //Returns the name of the text file
    public static String mappingTextareaIntoFile(Object username,JTextArea bio,String directory) {
        try {
            File file = new File(directory+"\\"+username+".txt");
            FileWriter fileWriter=new FileWriter(file,false);
            fileWriter.write(bio.getText());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return username + ".txt";
    }
    public void addRemovePhotoListener(MouseAdapter mal){
        removePhotoLabel.addMouseListener(mal);
    }

}