package Testings;

import Models.Family;
import Models.Human;
import Models.Task;
import Models.TasksList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import static Models.Human.createAccount;
import static Models.Human.getHumanData;
import static Models.TasksList.deleteTasksList;
import static org.junit.jupiter.api.Assertions.*;

class TasksListScenario2Test {
    private static TasksList tasksList;
    private static Family family;
    private static Human[]members;
    private static Integer[]arrayOfIds;
    /**Testing the UPDATE function deeply*/
    @BeforeAll
    public static void setUp(){
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        int id = random.nextInt(10000000);
        family = new Family("Test4" + id, String.valueOf(id), String.valueOf(0), String.valueOf(id), String.valueOf(0));
        members=new Human[10];
        //First we'll create 5 members in the family
        //i%2=0<--->member[i] is Child
        //i%2=1<--->member[i] is Parent
        for (int i = 0; i < 5; i++) {
            createAccount(family.username + (i), new Date(2000, 1, 1), family.username,
                    "Johnny" + i, (byte) (i % 4), null, i % 3 == 0, i % 2 == 0 ? "" : "Computer scientist",
                    "Johnny" + i, i % 2 == 0 ? -1 : 10000, false, i % 2 == 0 ? "Football player" : "");
            members[i]=getHumanData(family.username+(i));
        }
        tasksList=new TasksList(members[0].username);
        //tasksList must be empty
        assertTrue(tasksList.isEmpty());
        assertEquals(tasksList.familyUsername,family.username);
        //Adding 5 tasks to the tasksList
        //Each person at the family add one task to the tasksList
        arrayOfIds=new Integer[5];
        for (int i = 0; i < 5; i++) {
            Task task = new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[i].username, "Frying fishes");
            arrayOfIds[i] = (Integer)tasksList.addTask(task);
        }
        assertFalse(tasksList.isEmpty());
    }

    @Test
    public void testUpdateTasks(){
        String newTitle="Doing nothing";
        Task currentTask;
        for(int i=0;i<5;i++){
            currentTask=tasksList.tasks.get(i);
            currentTask.username=members[(i+1)%5].username;
            currentTask.title=newTitle;
            assertTrue(currentTask.updateTask());
        }
        TasksList newTaskList=new TasksList(members[0].username);
        Task newTask;
        for(int i=0;i<5;i++){
            newTask=newTaskList.tasks.get(i);
            currentTask=tasksList.tasks.get(i);
            assertEquals(newTask.id,currentTask.id);
            assertEquals(newTask.username,currentTask.username);
            assertEquals(newTask.title,currentTask.title);
        }
    }

    @AfterAll
    public static void tearDown(){
        //Destructing all the tasks by using the unused static method
        for (int i = 0; i < 5; i++)
            assertTrue(deleteTasksList(members[i].username));
        tasksList=new TasksList(members[0].username);
        assertTrue(tasksList.isEmpty());
        //Tear down all the accounts
        for(int i=0;i<5;i++){
            members[i].deleteAccount();
        }
    }

}