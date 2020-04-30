package Models;

import java.awt.image.BufferedImage;
import java.util.Calendar;

public abstract class Human extends User {
    public Address address;//The address of the current human in family
;;;;;;;;;;;;;;;    public String firstName;//as is
;;;;;;;;;;;;;;;    public byte genderId;//In practice we are retrieve the gender from the DB based on the genderId
    public Calendar birthday;
;;;;;;;;;;;;;;;    public BufferedImage image;
    public String bioFileName;//biography of the human, maximum 100 chars
;;;;;;;;;;    public Human(String username,String password){
;;;;;;;;;;        this.username=username;
;;;;;;;;;;        this.password=password;
    }
    public Human(){

    }
}
