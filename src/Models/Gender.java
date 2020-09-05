package Models;

import java.sql.*;
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
            Connection con=DriverManager.getConnection(MagicStrings.url, MagicStrings.user,MagicStrings.password);
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps. executeQuery();
            while (rs.next())
                lst.add(rs.getString(2));
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lst;
    }
}