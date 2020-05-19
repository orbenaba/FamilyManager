package Models;


import javax.swing.*;
import java.awt.*;

public abstract class Human extends User {
    public String firstName,familyUsername;
    public byte genderId;
    public java.sql.Date birthday;
    public ImageIcon image;


    public Human(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Human() {

    }

    public Human(String password, String username, String firstName, byte genderId, String familyUsername,
                 java.sql.Date birthday,ImageIcon image) {
        super(password, username);
        this.firstName = firstName;
        this.genderId = genderId;
        this.familyUsername=familyUsername;
        this.birthday=birthday;
        this.image=image;
    }
}


