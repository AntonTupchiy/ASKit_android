package comantontupchiyaskit_android.httpsgithub.askit_android;

import java.sql.Time;

/**
 * Created by GreenQ on 22.04.2015.
 */
public class Room {
    public int ID;
    public int authorId;
    public Time time;
    public String question;

    public Room(int authorId, Time time, String question)
    {
        this.authorId = authorId;
        this.time = time;
        this.question = question;
    }
}
