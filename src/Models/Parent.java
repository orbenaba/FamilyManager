package Models;


import javax.swing.*;
import java.sql.Date;

public class Parent extends Human {
    public double salary;
    public String jobName;
    public boolean isMarried,isLimited;
    public Parent(String username, String password) {
        super(username,password);
    }

    public Parent(String username, String password, double salary, String jobName, boolean isMarried) {
        super(username, password);
        this.salary = salary;
        this.jobName = jobName;
        this.isMarried = isMarried;
    }

    public Parent(double salary, String jobName, boolean isMarried) {
        this.salary = salary;
        this.jobName = jobName;
        this.isMarried = isMarried;
    }

    public Parent(String password, String username, String firstName, byte genderId, String familyUsername,
                  Date birthday, ImageIcon image, double salary, String jobName, boolean isMarried,boolean isLimited) {
        super(password, username, firstName, genderId, familyUsername, birthday, image);
        this.salary = salary;
        this.jobName = jobName;
        this.isMarried = isMarried;
        this.isLimited=isLimited;
    }
}
