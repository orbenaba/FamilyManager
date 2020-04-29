package Views;

import Models.Address;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterHumanView extends JFrame {
    public static void displayHumanForm(JFrame frame)
    {
        JLabel firstnameLabel,addressLabel,genderIdLabel,birthday,image,bioFileName;
        JTextField firstname;
        try {
            JComboBox gender=new JComboBox(getGenders().toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        int width=frame.getWidth(),height=frame.getHeight();
        



    }
    public static List<String> getGenders() throws SQLException {
        List<String>lst=new ArrayList<>();
        String query="SELECT*FROM gender";
        PreparedStatement con = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwareproject", "root", "root").prepareStatement(query);
        ResultSet rs=con.executeQuery();
        while(rs.next()){
            lst.add(rs.getString(2));
        }
        return lst;
    }
}
