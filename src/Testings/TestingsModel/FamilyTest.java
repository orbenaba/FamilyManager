package Testings.TestingsModel;

import Models.Family;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


class FamilyTest {
    private Family family;
    //tests ctor
    @Test
    public void setFamily(){
        Random random =new Random();
        //The chance for duplicate keys is small
        int id=random.nextInt(10000000);
        family=new Family("Test"+id,String.valueOf(id),String.valueOf(0),String.valueOf(id),String.valueOf(0));
        assertEquals(family.username,"Test"+id);
        assertEquals(Integer.valueOf(family.password),id);
        assertEquals(family.currentMonthProfit,0);
        assertEquals(Integer.valueOf(family.lastName),id);
        assertEquals(Integer.valueOf(family.counter),0);
    }
}