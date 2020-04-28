package Models;

public class User {
    public String password;
    public String username;

    public User()
    {
        password=username="";
    }
    public User(String username,String password){
        this.password=password;
        this.username=username;
    }
}
