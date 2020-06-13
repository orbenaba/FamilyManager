package Testings.TestingsModel;

import Models.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.charset.Charset;
import java.util.Random;
import static Models.Outcome.getFirstOutcome;
import static org.junit.jupiter.api.Assertions.*;


class OutcomeTest {
    private Outcome outcome;

    @BeforeEach
    public void setOutcome(){
        outcome=getFirstOutcome();
    }
    @Test
    public void updateOutcome() {
        if (outcome != null) {
            Random random = new Random();
            int price = random.nextInt(10000);
            //randomize general title
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String title = new String(array, Charset.forName("UTF-8"));
            outcome.title = title;
            outcome.price = price;
            outcome.updateOutcome();
            Outcome updatedOutcome = getFirstOutcome();
            assertNotEquals(updatedOutcome, outcome);
        }
    }
}