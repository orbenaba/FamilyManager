package Testings;

import Models.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import static Models.Human.createAccount;
import static Models.Human.getHumanData;
import static Models.User.isUsernameExist;
import static Models.User.loginFunction;
import static org.junit.jupiter.api.Assertions.*;


class UserTest {
    //Section1
    private static User user1;//Located in the DB----->username belongs to Family
    private static User user2;//Located in the DB----->username belongs to Child
    private static User user3;//Located in the DB----->username belongs to Parent
    private static User user4;//Not located in the DB->username="blabla"
    private static User user5;//Empty username

    //Section2
    private static Family family;
    private static Human[] members;
    private static ShoppingCart shoppingCart;
    private static TasksList tasksList;


    @BeforeAll
    public static void setUp() {
        user1 = new Family("blablaFamily","blablaFamily", String.valueOf(0), "blablaFamily", String.valueOf(0));
        createAccount((user1.username+1), new Date(2000, 1, 1), user1.username,
                "Johnny1", (byte)1, null, true, "",
                "Johnny1",-1, false,"Football player");
        user2=getHumanData(user1.username+1);
        createAccount((user1.username+2), new Date(2000, 1, 1), user1.username,
                "Johnny2", (byte)0, null, true, "Computer scientist",
                "Johnny2",10000, false,"");
        user3=getHumanData(user1.username+2);

        user4 = new User("blabla", "blabla");
        user5 = new User("", "");
        if (user1 != null && user2 != null && user3 != null) {
            if (!user1.equals(""))
                assertTrue(isUsernameExist(user1.username, false, user1.username));
            if (!user2.equals(""))
                assertTrue(isUsernameExist(user2.username, false, user2.username));
            if (!user3.equals(""))
                assertTrue(isUsernameExist(user3.username, false, user3.username));
        }
        assertFalse(isUsernameExist(user4.username, false, user4.username));
        assertFalse(isUsernameExist(user5.username, false, user5.username));
    }

    @Test
    public void testLoginFunction() {
        assertTrue(loginFunction(user1.username, user1.password) instanceof Family);
        assertTrue(loginFunction(user2.username, user2.password) instanceof User);
        assertTrue(loginFunction(user3.username, user3.password) instanceof User);
        assertNull(loginFunction(user4.username, user4.password));
        assertNull(loginFunction(user5.username,user5.password));
    }

    //Trying to break the singleton rules by creating more then 10 members in one family
    //i%5==0<--> members[i] is Parent
    //else: members[i] is Child
    @Test
    public void testSingleton() {
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        int id = random.nextInt(10000000);
        family = new Family("TestSingleton"+id, "TestSingleton", String.valueOf(0), "TestSingleton", String.valueOf(0));
        members = new Human[10];
        for (int i = 0; i < 10; i++) {
            assertTrue(loginFunction(family.username, family.password) instanceof Family);
            createAccount(family.username + (i), new Date(2000, 1, 1), family.username,
                    "Johnny" + i, (byte) (i % 4), null, i % 3 == 0, i % 5 == 0 ? "Computer scientist" : "",
                    "Johnny" + i, i % 5 == 0 ? 10000 : -1, false, i % 5 == 0 ? "" : "Football player");
            members[i] = (i % 5 == 0 ? new Parent(family.username + (i)) : new Child(family.username + (i)));
        }
        //Now, we are getting to the businesses
        assertTrue(loginFunction(family.username, family.password) instanceof Integer);
    }

    //Check if the isLimit works as well as need
    @Test
    public void testLimitations() {
        //unlimited children
        tasksList=new TasksList(members[0].username);
        shoppingCart = new ShoppingCart(members[0].username, new Date(Calendar.getInstance().getTimeInMillis() - 100000));
        assertTrue(tasksList.isEmpty());
        assertTrue(shoppingCart.isEmpty());
        assertTrue(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "HW1")) instanceof Integer);
        assertTrue(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "Food1")) instanceof Integer);
        ((Parent) members[0]).limitChildren();
        /**Important to remember: members[0] is Parent &&  members[1] is Child*/
        //limited children
        assertNull(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "HW2")));
        assertTrue(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[0].username, "HW2")) instanceof Integer);

        assertNull(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "Food2")));
        assertTrue(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[0].username, "Food2")) instanceof Integer);
        ((Parent) members[0]).unLimitChildren();
        //unlimited children
        assertTrue(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "HW3")) instanceof Integer);
        assertTrue(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[0].username, "HW3")) instanceof Integer);

        assertTrue(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "Food3")) instanceof Integer);
        assertTrue(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[0].username, "Food4")) instanceof Integer);
        /**************************************************************************************************************/
        ((Parent) members[0]).deleteAccount();
        //Currently we have 8 children and 1 parent in the family
        assertTrue(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "HW5")) instanceof Integer);
        assertTrue(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[5].username, "HW6")) instanceof Integer);

        assertTrue(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "Food5")) instanceof Integer);
        assertTrue(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[5].username, "Food6")) instanceof Integer);
        ((Parent) members[5]).limitChildren();
        /**LIMITED CHILDREN*/
        assertNull(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "HW7")));

        assertNull(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "Food7")));
        //members[5] is also a parent
        ((Parent) members[5]).deleteAccount();
        //Currently we have 8 children in the family
        assertTrue(tasksList.addTask(new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "HW7")) instanceof Integer);

        assertTrue(shoppingCart.addOutcome(new Outcome(100, new Date(Calendar.getInstance().getTimeInMillis()), members[1].username, "Food7")) instanceof Integer);
    }


    @AfterAll
    public static void tearDown() {
        if (user1 instanceof Parent || user1 instanceof Child)
            ((Human) user1).deleteAccount();
        if (user2 instanceof Parent || user2 instanceof Child)
            ((Human) user2).deleteAccount();
        if (user3 instanceof Parent || user3 instanceof Child)
            ((Human) user3).deleteAccount();
        if (user4 instanceof Parent || user4 instanceof Child)
            ((Human) user4).deleteAccount();
        //Deleting all the outcomes:
        ShoppingCart currentShoppingCart = new ShoppingCart(members[1].username, new Date(Calendar.getInstance().getTimeInMillis()));
        TasksList currentTasksList=new TasksList(members[1].username);
        int size = currentShoppingCart.outcomes.size();
        for (int i = 0; i < size; i++)
            currentShoppingCart.deleteOutcome(currentShoppingCart.outcomes.get(i).id);
        for (int i = 0; i < size; i++)
            currentTasksList.deleteTask(currentTasksList.tasks.get(i).id);
        //delete all the redundant accounts
        for (int i = 1; i < 10; i++)
            if (i % 5 != 0)
                (members[i]).deleteAccount();
        assertFalse(isUsernameExist(family.username, false, family.username));
    }
}