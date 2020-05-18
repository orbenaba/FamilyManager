package Models;

import java.sql.Date;

public class Child extends Human {
    public String status;
    public boolean isSingle;

    public Child(String username, String password) {
        super(username,password);
    }

    public Child(String password, String username, String firstName, byte genderId, String familyUsername,
                 String bioFileName, String imagePath, Date birthday, String status, boolean isSingle) {
        super(password, username, firstName, genderId, familyUsername, bioFileName, imagePath, birthday);
        this.status = status;
        this.isSingle = isSingle;
    }
}
