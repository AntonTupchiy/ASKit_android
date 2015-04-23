package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by GreenQ on 23.04.2015.
 */
public class MessageArrayAdapter extends ArrayAdapter {

        private DBConnection dbConnection;
        private Context context;
        private int resource;
        private Message [] objects;

        public MessageArrayAdapter(Context context, int resource, Message[] objects) {
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

                TextView userName = (TextView)
                        row.findViewById(R.id.txtUserName);
                TextView sedingTime = (TextView)
                        row.findViewById(R.id.txtSendingTime);
                TextView message = (TextView)
                        row.findViewById(R.id.txtMessage);

                userName.setText(
                        dbConnection.GetUserName(objects[position].authorId).toString());
                sedingTime.setText(
                        objects[position].time.toString());
                message.setText(
                        objects[position].text.toString());
                return row;
            }
            catch(Exception ex)
            {

            }
            return row;
        }
}
