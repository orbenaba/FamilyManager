package Models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;

import static Models.Human.createAccount;
import static Models.Human.getHumanData;
import static Models.Parent.isLimitChildren;
import static Models.User.loginFunction;
import static org.junit.jupiter.api.Assertions.*;


//Testing the family without any children
class OnlyParentsFamilyTest {
    private static Family family;
    private static Human[] members;

    @BeforeAll
    public static void setFamily() {
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        int id = random.nextInt(10000000);
        family = new Family("Test2" + id, String.valueOf(id), String.valueOf(0), String.valueOf(id), String.valueOf(0));
        /**
         * Testing the singleton pattern where one family has 10 members
         * First case: 5 parents& 5 children
         */
        //First we'll create 10 members in the family
        //member[i] is Parent
        members=new Human[10];
        for (int i = 0; i < 10; i++) {
            createAccount(family.username + (i), new Date(2000, 1, 1), family.username,
                    "Johnny" + i, (byte) (i % 4), null, i % 3 == 0,"Computer scientist",
                    "Johnny" + i,10000, false,"");
            members[i]=getHumanData(family.username+(i));
        }
        try {
            //Now, when trying to login as a family account again, we need to get error
            //note: Integer is returned from loginFunction in case of failure
            assertTrue(loginFunction(family.username, family.password) instanceof Integer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //This part checks if after deleting all the parents from the family,
    // the isLimit field which bounds the children in the family, is UPDATED
    @Test
    public void testNoParents_so_IsLimitIsTrue() {
        //first we need to make sure that the isLimit is true
        //deleting all the parents
        ((Parent)(members[0])).limitChildren();
        //No children in the family
        assertFalse(((Parent)members[0]).isLimitChildren());
        ((Parent)(members[0])).unLimitChildren();
        //No children in the family
        assertFalse(((Parent)members[0]).isLimitChildren());
        for (int j = 0; j < 10; j++)
            (members[j]).deleteAccount();
        assertFalse(isLimitChildren(members[0].username));
        assertFalse(((Parent)members[0]).isLimitChildren());
    }
}