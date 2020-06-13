package Testings.TestingsModel;

import Models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static Models.Human.getFirstHuman;
import static org.junit.jupiter.api.Assertions.*;


class TasksListTest {
    private static TasksList tasksList;
    private static Human human;
    private static String username;

    @BeforeAll
    public static void setAll() {
        //No difference between parent and child in the tasksList ctor
        human = getFirstHuman(false);
        if (human != null) {
            java.util.Date minDate = new java.util.Date();
            minDate.setMonth(minDate.getMonth() - 1);
            assertNotEquals("", human.username);
            tasksList = new TasksList(human.username);
            assertNotEquals("", tasksList.familyUsername);
        }
    }
    @Test
    public void addTask() {
        if (human != null) {
            Task task = new Task(new java.sql.Date(2019, 1, 1), human.username, "Collector");
            Integer id = tasksList.addTask(task);
            Task updated = Task.taskExists(id);
            assertNotNull(updated);
            assertEquals(updated.id, id);
            assertEquals(updated.title, "Collector");
            assertEquals(updated.username, human.username);
        }
    }
}