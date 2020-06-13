package Testings.TestingsModel;

import Models.User;
import org.junit.jupiter.api.Test;
import static Models.Family.getFirstFamily;
import static Models.Human.getFirstHuman;
import static Models.User.isUsernameExist;
import static org.junit.jupiter.api.Assertions.*;


class UserTest {
    private String user1;//Located in the DB----->username belongs to Family
    private User user2;//Located in the DB----->username belongs to Child
    private User user3;//Located in the DB----->username belongs to Parent
    private User user4;//Not located in the DB->username=""


    @Test
    public void setAll() {
        user1 = getFirstFamily();
        user2 = getFirstHuman(false);
        user3 = getFirstHuman(true);
        user4 = new User("", "");
        if (!user1.equals(""))
            assertTrue(isUsernameExist(user1, false, user1));
        if (!user2.equals(""))
            assertTrue(isUsernameExist(user2.username, false, user2.username));
        if (!user3.equals(""))
            assertTrue(isUsernameExist(user3.username, false, user3.username));
        if (!user4.equals(""))
            assertFalse(isUsernameExist(user4.username, false, user4.username));

    }

}