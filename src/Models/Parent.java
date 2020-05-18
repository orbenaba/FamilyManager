package Models;



public class Parent extends Human {
    public double salary;
    public String jobName;
    public boolean isMarried;
    public Parent(String username, String password) {
        super(username,password);
    }


    public Parent(String password,String username,String firstName,byte genderId,boolean isMarried,String familyUsername,
                  String bioNameFile,String imagePath,java.sql.Date birthday,String jobName,double salary){
        super(password,username,firstName,genderId,familyUsername,bioNameFile,imagePath,birthday);
        this.isMarried=isMarried;
        this.jobName=jobName;
        this.salary=salary;
    }
}
