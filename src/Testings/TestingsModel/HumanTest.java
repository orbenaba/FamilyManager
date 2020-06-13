package Testings.TestingsModel;

import Models.Child;
import Models.Human;
import Models.Parent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static Models.Human.getFirstHuman;
import static Models.User.isUsernameExist;
import static org.junit.jupiter.api.Assertions.*;


class HumanTest {
    /**
     * username1,human1 tests child position
     * username2,human2 tests parent position
     */
    private Human h1, h2;

    @BeforeEach
    public void setHuman() {
        h1 = getFirstHuman(false);
        h2 = getFirstHuman(true);
        if (h1 != null && h2 != null) {
            assertNotNull(h1.username);
            assertNotNull(h2.username);
            assertTrue(h1 instanceof Child);
            assertTrue(h2 instanceof Parent);
        }
    }

    @Test
    public void deleteAccounts() {
        if (h1 != null && h2 != null) {
            h1.deleteAccount();
            h2.deleteAccount();
            assertFalse(isUsernameExist(h1.username, true, h1.username));
            assertFalse(isUsernameExist(h2.username, true, h2.username));
        }
    }
}