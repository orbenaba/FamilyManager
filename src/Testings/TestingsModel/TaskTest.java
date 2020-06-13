package Testings.TestingsModel;

import Models.Outcome;
import Models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Random;

import static Models.Outcome.getFirstOutcome;
import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;


    @BeforeEach
    public void setOutcome(){
        task=Task.getFirstTask();
    }
    @Test
    public void updateTask() {
        if (task != null) {
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String title = new String(array, Charset.forName("UTF-8"));
            task.title = title;
            task.updateTask();
            Outcome updatedOutcome = getFirstOutcome();
            assertNotEquals(updatedOutcome.title, task.title);
        }
    }
}