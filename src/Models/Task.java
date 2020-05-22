package Models;

import java.sql.Date;

public class Task {
    protected int id;//an identifier of the object
    public String username,title;//connect with family class
    public Date executedDate;//presents the date with seconds
    public Task(int id,Date executedDate, String username,String title) {
        this.id = id;
        this.username = username;
        this.executedDate = executedDate;
        this.title=title;
    }
}
