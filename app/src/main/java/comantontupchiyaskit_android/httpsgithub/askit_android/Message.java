package comantontupchiyaskit_android.httpsgithub.askit_android;

import java.sql.Time;

/**
 * Created by GreenQ on 23.04.2015.
 */
public class Message {
    public int ID;
    public int roomID;
    public int authorId;
    public Time time;
    public String text;

    public Message(int id, int roomID, int authorId, Time time, String text)
    {
        this.ID = id;
        this.roomID = roomID;
        this.authorId = authorId;
        this.time = time;
        this.text = text;
    }

    public Message(){}
}
