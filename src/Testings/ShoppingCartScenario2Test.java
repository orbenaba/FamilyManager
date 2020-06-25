package Testings;

import Models.Family;
import Models.Human;
import Models.Outcome;
import Models.ShoppingCart;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import static Models.Human.createAccount;
import static Models.Human.getHumanData;
import static Models.Outcome.outcomeExists;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
/**Exam the state of an existing shopping cart with a family and update each item in the shopping cart*/
class ShoppingCartScenario2Test {
    private static ShoppingCart shoppingCart;
    private static Family family;
    private static Human[] members;

    //Initialization
    @BeforeAll
    public static void setUp() {
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        int id = random.nextInt(10000000);
        family = new Family("Test3" + id, String.valueOf(id), String.valueOf(0), String.valueOf(id), String.valueOf(0));
        members = new Human[5];
        //First we'll create 10 members in the family
        //i%2=0<--->member[i] is Child
        //i%2=1<--->member[i] is Parent
        for (int i = 0; i < 5; i++) {
            createAccount(family.username + (i), new Date(2000, 1, 1), family.username,
                    "Johnny" + i, (byte) (i % 4), null, i % 3 == 0, i % 2 == 0 ? "" : "Computer scientist",
                    "Johnny" + i, i % 2 == 0 ? -1 : 10000, false, i % 2 == 0 ? "Football player" : "");
            members[i] = getHumanData(family.username + (i));
        }
        shoppingCart = new ShoppingCart(members[0].username, new Date(Calendar.getInstance().getTimeInMillis() - 100000));
        for (int i = 0; i < 5; i++) {
            Outcome outcome = new Outcome(1000, new Date(Calendar.getInstance().getTimeInMillis()), members[i].username, "Table" + i);
            shoppingCart.addOutcome(outcome);
        }
    }

    @Test
    public void testUpdateOutcomes(){
        int price=2000;//bigger in 1000 then the previous price
        String title="Board";
        Outcome currentOutcome;
        for(int i=0;i<5;i++){
            currentOutcome=shoppingCart.outcomes.get(i);
            currentOutcome.username=members[(i+1)%5].username;
            currentOutcome.price=price;
            currentOutcome.title=title;
            currentOutcome.updateOutcome();
        }
        ShoppingCart currentShoppingCart=new ShoppingCart(members[0].username, new Date(Calendar.getInstance().getTimeInMillis()));
        //Up until here, we just came into the desired state
        Outcome newOutcome;
        for(int i=0;i<5;i++){
            currentOutcome=shoppingCart.outcomes.get(i);
            newOutcome=currentShoppingCart.outcomes.get(i);
            assertEquals(currentOutcome.title,newOutcome.title);
            assertEquals(currentOutcome.username,newOutcome.username);
            assertEquals(currentOutcome.price,newOutcome.price);
        }
    }


    @AfterAll
    public static void tearDown() {
        int firstId=shoppingCart.outcomes.getFirst().id;
        for (int i = 0; i < 5; i++) {
            shoppingCart.deleteOutcome(firstId+i);
            assertNull(outcomeExists(firstId+i));
        }
    }
}