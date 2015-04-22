package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by GreenQ on 22.04.2015.
 */
public class RoomArrayAdapter extends ArrayAdapter {

    private DBConnection dbConnection;
    private Context context;
    private int resource;
    private Room [] objects;

    public RoomArrayAdapter(Context context, int resource, Room[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        dbConnection = new DBConnection();
    }

    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent) {
        LayoutInflater inflater =
                ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);

        try {

            TextView author = (TextView)
                    row.findViewById(R.id.author);
            TextView question = (TextView)
                    row.findViewById(R.id.question);
            author.setText(
                    dbConnection.GetUserName(objects[position].authorId).toString());
            question.setText(
                    objects[position].question.toString());
            return row;
        }
        catch(Exception ex)
        {

        }
        return row;
    }
}
