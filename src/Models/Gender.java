package Models;

public class Gender {
    public byte genderId;
    public String gender;
    public static String getGenderById(byte id){
        return id==0?"Male":id==1?"Female":id==2?"Bisexual":"Other";
    }

}