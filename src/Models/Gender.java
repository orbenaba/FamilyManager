package Models;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Gender {
    public byte genderId;
    public String gender;
    public static String getGenderById(byte id){
        return id==0?"Male":id==1?"Female":id==2?"Bisexual":"Other";
    }

    //In case of regretting about some gender or in case of adding a new gender, it can be easily done by this structure.
    public static java.util.List<String>getGenders() {
        List<String> lst = new ArrayList<>();
        String query = "SELECT*FROM gender";
        try {
            PreparedStatement con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
            ResultSet rs = con. executeQuery();
            while (rs.next())
                lst.add(rs.getString(2));
            rs.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }
}