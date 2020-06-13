package Testings.TestingsModel;

import Models.Child;
import Models.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.Random;
import static Models.Human.getFirstHuman;


class ChildTest {
 //   @Mock
    private static Child child;

    //tests child's ctor
    @BeforeAll
    public static void setChild()
    {
        Human human = getFirstHuman(false);
        child=new Child(human.username);
        Assertions.assertNotNull(child);
    }

    @Test
    public void updateAccount() throws FileNotFoundException {
        Random rnd=new Random();
        int day=rnd.nextInt(29);
        int month=rnd.nextInt(11);
        int year=rnd.nextInt(90);
        year+=1930;
        String username="John",password="John1234",firstName="John",status="Teacher";
        Date birthday = new java.sql.Date(year,month,day);
        InputStream image = new FileInputStream(new File("src\\Testings\\TestingsModel\\X2.jpg"));
        boolean isSingle=false;
        child.updateAccount(username,password,firstName,status,birthday,isSingle,image,false);
        Assertions.assertEquals(child.username,"John");
        Assertions.assertEquals(child.password,"John1234");
        Assertions.assertEquals(child.firstName,"John");
        Assertions.assertEquals(child.status,"Teacher");
        Assertions.assertNotNull(child.image);
        Assertions.assertEquals(child.isSingle,isSingle);
    }
}