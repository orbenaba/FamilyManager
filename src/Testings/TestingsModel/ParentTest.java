package Testings.TestingsModel;


import Models.Human;
import Models.Parent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import java.sql.Date;
import java.util.Random;
import static Models.Human.getFirstHuman;
import static org.junit.jupiter.api.Assertions.*;


class ParentTest {
    private static Parent parent;

    //tests parent's ctor
    @BeforeAll
    public static void setParent()
    {
        Human human=getFirstHuman(true);
        parent=new Parent(human.username);
        assertNotNull(parent);
    }

    //Update the first parent in the DB with random values
    @Test
    public void updateAccount() {
        Random rnd=new Random();
        int day=rnd.nextInt(29);
        int month=rnd.nextInt(11);
        //randomize general title
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String string = new String(array, Charset.forName("UTF-8"));
        int year=rnd.nextInt(90);
        int salary=year;
        year+=1930;
        Date birthday = new Date(year,month,day);
        boolean isMarried=false;
        parent.updateAccount(string+year,string,string,string,birthday,isMarried,salary,null,false);
        Assertions.assertEquals(parent.username,string+year);
        Assertions.assertEquals(parent.password,string);
        Assertions.assertEquals(parent.firstName,string);
        Assertions.assertEquals(parent.jobName,string);
        assertEquals(parent.image,null);
        Assertions.assertEquals(parent.isMarried,isMarried);
    }

    //Check if the limiting actually works
    @Test
    public void isParent(){
        assertTrue(Parent.isParent(parent.username));
    }

    @Test
    public void limiting(){
        parent.limitChildren();
        assertTrue(parent.isLimitChildren());
    }

    @Test
    public void unLimiting(){
        parent.unLimitChildren();
        assertFalse(parent.isLimitChildren());
    }
}