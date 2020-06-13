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
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Random;
import static Models.Human.getFirstHuman;


class ChildTest {
 //   @Mock
    private static Child child=null;

    //tests child's ctor
    @BeforeAll
    public static void setChild() {
        Human human = getFirstHuman(false);
        if (human != null) {
            child = new Child(human.username);
            Assertions.assertNotNull(child);
        }
    }

    @Test
    public void updateAccount() throws FileNotFoundException {
        if(child!=null) {
            Random rnd = new Random();
            int day = rnd.nextInt(29);
            int month = rnd.nextInt(11);
            //randomize general title
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String string = new String(array, Charset.forName("UTF-8"));
            int year = rnd.nextInt(90);
            Date birthday = new java.sql.Date(year, month, day);
            InputStream image = new FileInputStream(new File("src\\Testings\\TestingsModel\\X2.jpg"));
            boolean isSingle = false;
            child.updateAccount(string + year, string, string, string, birthday, isSingle, null, false);
            Assertions.assertEquals(child.username, string + year);
            Assertions.assertEquals(child.password, string);
            Assertions.assertEquals(child.firstName, string);
            Assertions.assertEquals(child.status, string);
            Assertions.assertNull(child.image);
            Assertions.assertEquals(child.isSingle, isSingle);
        }
    }
}