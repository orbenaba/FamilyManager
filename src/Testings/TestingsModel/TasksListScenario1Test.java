package Testings.TestingsModel;

import Models.Family;
import Models.Human;
import Models.Task;
import Models.TasksList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import static Models.Human.createAccount;
import static Models.Human.getHumanData;
import static Models.TasksList.deleteTasksList;
import static org.junit.jupiter.api.Assertions.*;

class TasksListScenario1Test {
    private static TasksList tasksList;
    private static Family family;
    private static Human[]members;
    private static Integer[]arrayOfIds;
    @BeforeAll
    public static void setUp(){
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        int id = random.nextInt(10000000);
        family = new Family("Test3" + id, String.valueOf(id), String.valueOf(0), String.valueOf(id), String.valueOf(0));
        members=new Human[10];
        //First we'll create 10 members in the family
        //i%2=0<--->member[i] is Child
        //i%2=1<--->member[i] is Parent
        for (int i = 0; i < 10; i++) {
            createAccount(family.username + (i), new Date(2000, 1, 1), family.username,
                    "Johnny" + i, (byte) (i % 4), null, i % 3 == 0, i % 2 == 0 ? "" : "Computer scientist",
                    "Johnny" + i, i % 2 == 0 ? -1 : 10000, false, i % 2 == 0 ? "Football player" : "");
            members[i]=getHumanData(family.username+(i));
        }
        tasksList=new TasksList(members[0].username);
        //tasksList must be empty
        assertTrue(tasksList.isEmpty());
    }

    @Test
    public void testAdding_AND_deleteTasksToTheList(){
        //Adding 10 tasks to the tasksList
        //Each person at the family add one task to the tasksList
        arrayOfIds=new Integer[10];
        for (int i = 0; i < 10; i++) {
            Task task = new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[i].username, "Clean the room");
            arrayOfIds[i] = (Integer)tasksList.addTask(task);
        }
        assertFalse(tasksList.isEmpty());
        //The idArray must be a math sequence which its distance is 1
        for(int i=0;i<9;i++)
            assertEquals(1,arrayOfIds[i+1]-arrayOfIds[i]);
        int len=arrayOfIds.length;
        for(int i=0;i<len;i++) {
            assertTrue(tasksList.deleteTask(arrayOfIds[i]));
            //Check if the file really deleted from the directory
            File file=new File("Tasks\\"+arrayOfIds[i]+".txt");
            assertFalse(file.exists());
        }
        tasksList=new TasksList(members[0].username);
        assertTrue(tasksList.isEmpty());
        for(int i=0;i<10;i++){
            members[i].deleteAccount();
        }
    }

    /**5 members each executed tasks by the order if 1,2,3,4,5 correspondingly*/
    @Test
    public void testActivism() {
        //Preparing the required state
        Random random = new Random();
        /**The chance for duplicate keys is low*/
        int id = random.nextInt(10000000);
        family = new Family("Test3" + id, String.valueOf(id), String.valueOf(0), String.valueOf(id), String.valueOf(0));
        members = new Human[5];
        for (int i = 0; i < 5; i++) {
            createAccount(1 + family.username + (i), new Date(2000, 1, 1), family.username,
                    "Johnny2" + i, (byte) (i % 4), null, i % 3 == 0, i % 2 == 0 ? "" : "Computer scientist",
                    "Johnny2" + i, i % 2 == 0 ? -1 : 10000, false, i % 2 == 0 ? "Football player" : "");
            members[i] = getHumanData(1 + family.username + (i));
        }
        tasksList = new TasksList(members[0].username);
        //1+2+3+4+5 is 15-->> arrayOfIds.length=15
        arrayOfIds = new Integer[15];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i + 1; j++) {
                Task currentTask = new Task(new Date(Calendar.getInstance().getTimeInMillis()), members[j].username, "Dish washer");
                arrayOfIds[(i * (i + 1) / 2) + j] = (Integer)tasksList.addTask(currentTask);//k=sum(1...i)+j--->>formula
            }
        }
        //State is ready for testing, our expectations are:
        //member[0] is the less activist
        //...........
        //member[4] is the activist activist
        String[] activism = tasksList.getActivistData();
        for (int i = 0; i < 5; i++)
            assertEquals(activism[i], members[i].username);
    }

    @AfterAll
    public static void tearDown() {
        //Destructing all the tasks by using the unused static method
        for (int i = 0; i < 5; i++)
            assertTrue(deleteTasksList(members[i].username));
        //Tear down all the accounts
        for(int i=0;i<5;i++){
            members[i].deleteAccount();
        }
    }
}