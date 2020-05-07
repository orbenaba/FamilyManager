package Models;



public class Family extends User{
    private int counter;
    public String lastName;
    public int currentMonthProfit;


    public Family(String username,String password,int counter, String lastName, int currentMonthProfit) {
        super(username,password);
        this.counter = counter;
        this.lastName = lastName;
        this.currentMonthProfit = currentMonthProfit;
    }
    public Family(String username,String password,String lastName) {
        super(username,password);
        this.counter = 0;
        this.lastName = lastName;
        this.currentMonthProfit = 0;
    }
    public Family() {
        this.counter = 0;
        this.lastName = "";
        this.currentMonthProfit = 0;
    }
    //copy constructor
    public Family(Family other){
        this.username=other.username;
        this.lastName=other.lastName;
    }
}
