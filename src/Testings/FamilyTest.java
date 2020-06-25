package Testings;

import Models.Child;
import Models.Family;
import Models.Human;
import Models.Parent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Random;

import static Models.Family.getFamilyMembers;
import static Models.Human.*;
import static Models.Parent.isLimitChildren;
import static Models.User.isUsernameExist;
import static Models.User.loginFunction;
import static org.junit.jupiter.api.Assertions.*;


class FamilyTest {
    private static Family family;
    private static Human[] members;

    //tests ctor
    @BeforeAll
    public static void setFamily() {
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        int id = random.nextInt(10000000);
        family = new Family("Test" + id, String.valueOf(id), String.valueOf(0), String.valueOf(id), String.valueOf(0));
        assertEquals(family.username, "Test" + id);
        assertEquals(Integer.valueOf(family.password), id);
        assertEquals(family.currentMonthProfit, 0);
        assertEquals(Integer.valueOf(family.lastName), id);
        assertEquals(Integer.valueOf(family.counter), 0);
        /**
         * Testing the singleton pattern where one family has 10 members
         * First case: 5 parents& 5 children
         */
        //First we'll create 10 members in the family
        //i%2=0<--->member[i] is Child
        //i%2=1<--->member[i] is Parent
        members = new Human[10];
        for (int i = 0; i < 10; i++) {
            createAccount((family.username + i), new Date(2000, 1, 1), family.username,
                    "Johnny" + i, (byte) (i % 4), null, i % 3 == 0, i % 2 == 0 ? "" : "Computer scientist",
                    "Johnny" + i, i % 2 == 0 ? -1 : 10000, false, i % 2 == 0 ? "Football player" : "");
            members[i] = getHumanData(family.username + i);
        }
        //Now, when trying to login as a family account again, we need to get error
        //note: Integer is returned from loginFunction in case of failure
        assertTrue(loginFunction(family.username, family.password) instanceof Integer);
        //Check if isUsernameExist() works by trying to create an account with a taken human username
        String statement = createAccount((family.username + (3)), new Date(2000, 1, 1), family.username,
                "Joey" + 3, (byte) 3, null, true, "Computer scientist",
                "Joey" + 3, 10000, false, "");
        assertTrue(statement.equals("This username is already taken"));

        //Check if isUsernameExist() works by trying to create an account with a taken family username
        statement = createAccount(family.username, new Date(2000, 1, 1), family.username,
                "Joey" + 3, (byte) 3, null, true, "Computer scientist",
                "Joey" + 3, 10000, false, "");
        assertEquals("This username is already taken", statement);

        //First of all' we check if the isLimit function works
        ((Parent) members[1]).limitChildren();

        assertTrue(isLimitChildren(members[0].username));
        assertTrue(((Parent) members[1]).isLimitChildren());
        //Secondly, we check if the unLimit() function works
        ((Parent) members[1]).unLimitChildren();
        assertFalse(isLimitChildren(members[0].username));
        assertFalse(((Parent) members[1]).isLimitChildren());
        /**Checking the getFamilyMembers deeply*/
        Family.FamilyMembers familyMembers=getFamilyMembers(members[5].username);
        for(int i=0;i<10;i++){
            assertEquals(familyMembers.FamilyUsername,members[0].familyUsername);
            assertEquals(familyMembers.members.get(members[i].username).firstName,members[i].firstName);
        }
    }

    //This part checks if after deleting all the parents from the family,
    // the isLimit field which bounds the children in the family, is UPDATED
    @Test
    public void testNoParents_so_IsLimitIsTrue() {
        //first we need to make sure that the isLimit is true
        int i = 0;
        for (; i < 10; i++)
            if (members[i] instanceof Parent) {
                ((Parent) members[i]).limitChildren();
                break;
            }
        //deleting all the parents
        for (int j = 0; j < 10; j++)
            if (members[j] instanceof Parent)
                (members[j]).deleteAccount();
        int j = 0;
        for (; j < 10; j++)
            if (members[j] instanceof Child)
                break;
        assertFalse(isLimitChildren(members[j].username));
    }

    //No members at the family-->> the family username is vacant
    @Test
    public void testNoMembers() {
        //deleting all the children
        for (int j = 0; j < 10; j++)
            (members[j]).deleteAccount();
        assertFalse(isUsernameExist(family.username, false, ""));
        Family.FamilyMembers familyMembers=getFamilyMembers("");
        assertNull(familyMembers);
    }

    @Test
    public void testRelationships() {
        byte gender=1;
        assertEquals("My wife",findRelation(true, true,gender));
        gender=4;
        assertEquals("My husband",findRelation(true, true,gender));
        gender=1;
        assertEquals("My daughter",findRelation(true, false,gender));
        gender=3;
        assertEquals("My son",findRelation(true, false,gender));
        gender=1;
        assertEquals("My mother",findRelation(false, true,gender));
        gender=2;
        assertEquals("My father",findRelation(false, true,gender));
        gender=1;
        assertEquals("My sister",findRelation(false, false,gender));
        gender=4;
        assertEquals("My brother",findRelation(false, false,gender));
    }
}