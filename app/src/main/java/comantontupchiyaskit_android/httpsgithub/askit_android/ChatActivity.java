package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by GreenQ on 22.04.2015.
 */
public class ChatActivity extends Activity {
    EditText mail;
    EditText passwordOld;
    EditText passwordNew;
    EditText knowledge;
    TextView txtViewQuestion;
    DBConnection dbConnection;
    ArrayList<Message> messages;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            setContentView(R.layout.chat);

            dbConnection = new DBConnection();
            ArrayList<Room> rooms = new ArrayList<Room>();

            ListView chat = (ListView) findViewById(R.id.listMessages);

            try {
//                rooms = dbConnection.GetAllRoomsData();
//                Room[] rooms1 = new Room[rooms.size()];
//                rooms.toArray(rooms1);
//                    RoomArrayAdapter roomArrayAdapter = new RoomArrayAdapter(this, R.layout.room_list_item, rooms1);
//                    chat.setAdapter(roomArrayAdapter);
            }
            catch (Exception ex)
            {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage(ex.getMessage());
                dlgAlert.setTitle("Error in communicating with database");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }

            //region #Trash
           // ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, R.id.chatListView, ar);

        OrganizeTabs();

        txtViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        txtViewQuestion.setText(Globals.room.question);
        GetAllMessagesList();
        DisplayAllMessages();
            }
            //endregion

    public void SendMessage(View view)
    {
        Date date = new Date();
        EditText txtMessage = (EditText) findViewById(R.id.txtNewMessage);
        dbConnection.NewMessage(txtMessage.getText().toString(), date);
    }

    private void GetAllMessagesList()
    {
        try {
            messages = dbConnection.GetAllMessagesData();
        }
        catch (Exception ex)
        {}
    }

    private void DisplayAllMessages()
    {
        final ListView allRoomsList = (ListView) findViewById(R.id.listMessages);

        try {

            final Message[] allMessagesArray = new Message[messages.size()];
            messages.toArray(allMessagesArray);
            MessageArrayAdapter messageArrayAdapter = new MessageArrayAdapter(this, R.layout.message_list_item, allMessagesArray);
            allRoomsList.setAdapter(messageArrayAdapter);
            allRoomsList.setClickable(true);
            allRoomsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    try {
//                        Globals.room.question = ((TextView) ((LinearLayout)arg1).findViewById(R.id.question)).getText().toString();
//                        Globals.room.authorId = dbConnection.GetUserId(((TextView) ((LinearLayout) arg1).findViewById(R.id.author)).getText().toString());
//                        Globals.room.ID = getRoomId(allMessagesArray, Globals.room.question);
//                        //Toast.makeText(getApplicationContext(), question, Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(MainActivity.this, ChatActivity.class);
//                        startActivity(i);
                    }
                    catch (Exception ex){}
                }
            });
        }
        catch (Exception ex)
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage(ex.getMessage());
            dlgAlert.setTitle("Error in communicating with database");
            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //dismiss the dialog
                }
            });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }
    private void OrganizeTabs()
    {
        TabHost tabs = (TabHost) findViewById(R.id.tabHost2);

        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("tag5");

        spec.setContent(R.id.linearLayout3);
        spec.setIndicator("Messages");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag6");
        spec.setContent(R.id.linearLayout4);
        spec.setIndicator("Chat participants");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }
}


