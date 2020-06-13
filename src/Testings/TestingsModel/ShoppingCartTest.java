package Testings.TestingsModel;

import Models.Human;
import Models.Outcome;
import Models.ShoppingCart;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static Models.Human.getFirstHuman;
import static Models.Outcome.outcomeExists;
import static org.junit.jupiter.api.Assertions.*;


class ShoppingCartTest {
    private static ShoppingCart shoppingCart;
    private static String username;
    private static Human human;
    @BeforeAll
    public static void setShoppingCart() {
        //No difference between parent and child in the shoppingCart ctor
        human = getFirstHuman(false);
        if (human != null) {
            java.util.Date minDate = new java.util.Date();
            minDate.setMonth(minDate.getMonth() - 1);
            username = human.familyUsername;
            shoppingCart = new ShoppingCart(human.username, new java.sql.Date(minDate.getTime()));
            assertNotEquals("", human.username);
            assertNotEquals("", shoppingCart.familyUsername);
        }
    }
    @Test
    public void calculateIncomes(){
    }

    @Test
    public void deleteOutcome() {
        if(human!=null) {
            int id;
            if (!shoppingCart.outcomes.isEmpty()) {
                id = shoppingCart.outcomes.getFirst().id;
                assertTrue(shoppingCart.deleteOutcome(id));
            } else {
                assertTrue(shoppingCart.isEmpty());
            }
        }
    }

    @Test
    public void addOutcome() {
        if (human != null) {
            Outcome outcome = new Outcome(1000, new java.sql.Date(2019, 1, 1),
                    human.username, "Chair");
            Integer id = shoppingCart.addOutcome(outcome);
            Outcome updated = outcomeExists(id);
            assertNotNull(updated);
            assertEquals(updated.id, id);
            assertEquals(updated.price, 1000);
            assertEquals(updated.title, "Chair");
            assertEquals(updated.username, human.username);
        }
    }
    @Test
    public void deleteShoppingCart() {
        if (human != null) {
            int countOutcomes = 0;
            for (Outcome c : shoppingCart.outcomes) {
                if (c.username.equals(human.username))
                    countOutcomes++;
            }
            assertEquals(countOutcomes, ShoppingCart.deleteShoppingCart(human.username));
        }
    }
}