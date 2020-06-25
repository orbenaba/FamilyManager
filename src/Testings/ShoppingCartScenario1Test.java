package Testings;

import Models.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import static Models.Human.createAccount;
import static Models.Human.getHumanData;
import static Models.ShoppingCart.deleteShoppingCart;
import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartScenario1Test {
    private static Family family;
    private static Human[] members;
    private static ShoppingCart shoppingCart;
    private static Integer[] arrayOfIds;

    @BeforeAll
    private static void setUp() {
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        int id = random.nextInt(10000000);
        family = new Family("Test2" + id, String.valueOf(id), String.valueOf(0), String.valueOf(id), String.valueOf(0));
        /**
         * Testing the singleton pattern where one family has 10 members
         * First case: 5 parents& 5 children
         */
        members = new Human[10];
        //First we'll create 10 members in the family
        //i%2=0<--->member[i] is Child
        //i%2=1<--->member[i] is Parent
        for (int i = 0; i < 10; i++) {
            createAccount(family.username + (i), new Date(2000, 1, 1), family.username,
                    "Johnny" + i, (byte) (i % 4), null, i % 3 == 0, i % 2 == 0 ? "" : "Computer scientist",
                    "Johnny" + i, i % 2 == 0 ? -1 : 10000, false, i % 2 == 0 ? "Football player" : "");
            members[i] = getHumanData(family.username + (i));
        }
        shoppingCart = new ShoppingCart(members[0].username, new Date(Calendar.getInstance().getTimeInMillis() - 100000));
        //shoppingCart must be empty
        assertTrue(shoppingCart.isEmpty());
        //outcomes=0
        assertEquals(shoppingCart.calculateShoppingCart(), 0);
    }

    @Test
    public void testSalaries() {
        int expectedSalaries = 0;
        int currentSalaries = 0;
        for (int i = 0; i < 10; i++)
            currentSalaries += (members[i] instanceof Child ? 0 : ((Parent) members[i]).salary);
        expectedSalaries = shoppingCart.getSalaries();
        assertEquals(expectedSalaries, currentSalaries);
    }

    @Test
    public void testAdd_AND_DeleteOutcomeFromShoppingCart() {
        //Adding 10 products to the shoppingCart
        //Each person at the family add one outcome to the shoppingCart
        arrayOfIds = new Integer[10];
        for (int i = 0; i < 10; i++) {
            Outcome outcome = new Outcome(1000, new Date(Calendar.getInstance().getTimeInMillis()), members[i].username, "Table");
            arrayOfIds[i] = (Integer)shoppingCart.addOutcome(outcome);
        }
        shoppingCart = new ShoppingCart(members[0].username, new Date(Calendar.getInstance().getTimeInMillis() - 100000));
        assertFalse(shoppingCart.isEmpty());
        //The total expected outcomes is 10,000
        assertEquals(10000, shoppingCart.calculateShoppingCart());
        //The idArray must be a math sequence which its distance is 1
        for (int i = 0; i < 9; i++)
            assertEquals(1, arrayOfIds[i + 1] - arrayOfIds[i]);
        int len = arrayOfIds.length;
        for (int i = 0; i < len; i++) {
            assertTrue(shoppingCart.deleteOutcome(arrayOfIds[i]));
            //assertEquals(1,deleteShoppingCart(members[i].username));
            //Check if the file really deleted from the directory
            File file = new File("Outcomes\\" + arrayOfIds[i] + ".txt");
            assertFalse(file.exists());
        }
        shoppingCart = new ShoppingCart(members[0].username, new Date(Calendar.getInstance().getTimeInMillis() - 100000));
        assertTrue(shoppingCart.isEmpty());
    }

    @Test
    public void testAddIncomes() {
        //Allowing to each member in the family to add one income
        for (int i = 0; i < 10; i++) {
            Outcome in = new Outcome(-1000, new Date(Calendar.getInstance().getTimeInMillis()), members[i].username, "");
            shoppingCart.addOutcome(in);
        }
        int expectedIncomes = 10000;
        //Total incomes must be 10000!
        assertEquals(expectedIncomes, shoppingCart.calculateIncomes(members[0].username, new Date(Calendar.getInstance().getTimeInMillis() - 10000)));
    }

    @AfterAll
    public static void tearDown() {
        for (int i = 0; i < 10; i++) {
            //Check whether each income is deleted for each person
            assertEquals(1, deleteShoppingCart(members[i].username));
            members[i].deleteAccount();
        }
    }
}