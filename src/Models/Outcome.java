package Models;


import java.sql.Date;

public class Outcome {
    protected int id;//an identifier of the object
    public String username;//connect with family class
    public int price;
    public Date purchasedDate;//presents the date with seconds
    /**Saving the description in a file whose name is "id.txt" in Outcomes directory*/
    public Outcome(int id,int price,Date purchasedDate, String username) {
        this.id = id;
        this.username = username;
        this.price = price;
        this.purchasedDate = purchasedDate;
    }
}
