package Models;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class Child extends Human {
    public String status;
    public boolean isSingle;

    public Child(String username, String password) {
        super(username,password);
    }

    public Child(String password, String username, String firstName, byte genderId, String familyUsername,
                 Date birthday, String status, boolean isSingle, ImageIcon image) {
        super(password, username, firstName, genderId, familyUsername, birthday,image);
        this.status = status;
        this.isSingle = isSingle;
    }

}
