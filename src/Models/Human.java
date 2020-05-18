package Models;


public abstract class Human extends User {
    public String firstName,familyUsername,bioFileName,imagePath;
    public byte genderId;
    public java.sql.Date birthday;

    public Human(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Human() {

    }

    public Human(String password, String username, String firstName, byte genderId, String familyUsername,
                 String bioFileName, String imagePath, java.sql.Date birthday) {
        super(password, username);
        this.firstName = firstName;
        this.genderId = genderId;
        this.familyUsername=familyUsername;
        this.bioFileName=bioFileName;
        this.imagePath=imagePath;
        this.birthday=birthday;
    }
}


