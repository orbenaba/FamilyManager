package Testings;

import Models.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Random;

import static Models.Human.createAccount;
import static Models.Human.getHumanData;
import static Models.User.loginFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HumanUpdateAccountsTest {
    /**
     * username1,human1 tests child position
     * username2,human2 tests parent position
     */
    private static Family family;
    private static Human h1, h2;//h1 is child, h2 id parent
    private static int id;
    @BeforeAll
    public static void setUp() {
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        id = random.nextInt(10000000);
        family = new Family("Test" + id, String.valueOf(id), String.valueOf(0), String.valueOf(id), String.valueOf(0));
        createAccount(1 + family.username, new Date(2000, 1, 1), family.username,
                "Johnny2", (byte)1, null, false,"",
                "Johnny2", -1, false,"Football player");
        h1=getHumanData(1+family.username);
        createAccount(2 + family.username, new Date(2000, 1, 1), family.username,
                "Johnny3", (byte)1, null, false,"Teacher",
                "Johnny3", 12000, false,"");
        h2=getHumanData(2+family.username);
    }

    @Test
    public void testUpdateAccounts() {
        int dig=5;
        //We are not allow to the user to rename its username to another taken username
        assertEquals("", ((Child) h1).updateAccount(h2.username, h1.password, h1.firstName, ((Child) h1).status, h1.birthday, ((Child) h1).isSingle, null, false));
        assertEquals("", ((Parent) h2).updateAccount(h1.username, h2.password, h2.firstName, ((Parent) h2).jobName, h2.birthday, ((Parent) h2).isMarried, ((Parent) h2).salary, null, false));
        assertEquals((dig + family.username), ((Child)h1).updateAccount((dig + family.username), h1.password, h1.firstName, ((Child) h1).status, h1.birthday, ((Child) h1).isSingle, null, false));
        assertEquals(4 + family.username, ((Parent) h2).updateAccount(4 + family.username, h2.password, h2.firstName, ((Parent) h2).jobName, h2.birthday, ((Parent) h2).isMarried, ((Parent) h2).salary, null, false));
        assertEquals(3 + family.username, ((Child) h1).updateAccount(3 + family.username, "blabla", "blabla", ((Child) h1).status, h1.birthday, !((Child) h1).isSingle, null, false));
        assertEquals(4 + family.username, ((Parent) h2).updateAccount(4 + family.username, "blabla", "blabla", ((Parent) h2).jobName, h2.birthday, !((Parent) h2).isMarried, ((Parent) h2).salary, null, false));
        assertTrue(loginFunction(3 + family.username, "blabla") instanceof User);
        assertTrue(loginFunction(4 + family.username, "blabla") instanceof User);
    }

    @AfterAll
    public static void tearDown(){
        h1.deleteAccount();
        h2.deleteAccount();
    }
}