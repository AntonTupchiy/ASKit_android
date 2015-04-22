package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends Activity {

    EditText txtQuestion;
    DBConnection dbConnection;
    ArrayList<Room> rooms;
    //TestTYT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        TextView txtViewName = (TextView) findViewById(R.id.txtViewName);
        txtViewName.setText(Globals.user.Login.toString());
        txtQuestion = (EditText) findViewById(R.id.txtQuestion);
        dbConnection = new DBConnection();

        OrganizeTabs();
        GetAllChatRoomsList();
        DisplayAllChatRooms();

    }


    public void profileButton(View view){
        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    public void LogOut(View view)
    {
        Preferences preferences = new Preferences(getApplicationContext());
        preferences.ForgetAuthorizedUser();
        Intent i = new Intent(MainActivity.this, AuthorizationActivity.class);
        startActivity(i);
        finish();
    }

    public void AskQuestion(View view)
    {
        try{
            //ArrayList<Room> roms = new ArrayList<>();
           // roms = dbConnection.GetAllRoomsData();
            Date date = new Date();
            dbConnection.NewChatRoom(txtQuestion.getText().toString(), date, this);
            Globals.room = dbConnection.GetRoomData(txtQuestion.getText().toString());
//            Intent i = new Intent(MainActivity.this, ChatActivity.class);
//            startActivity(i);
            Toast.makeText(getApplicationContext(), "Question:" + txtQuestion.getText().toString() + " was succesfully written to db.", Toast.LENGTH_LONG).show();

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



    public void AskButton(View view)
    {
       // Room room = new Room();
    }
/*    public void ConfirmRegistration(View view)
    {
        EditText txtLog = (EditText) findViewById(R.id.txtLogin);
        EditText txtPas = (EditText) findViewById(R.id.txtPassword);
        EditText txtPasConf = (EditText) findViewById(R.id.txtPasswordConf);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
        EditText txtInterests = (EditText) findViewById(R.id.txtInterests);
        DBConnection dbConnection = new DBConnection();
        dbConnection.NewUser(txtLog.getText().toString(), txtPas.getText().toString(), txtEmail.getText().toString(), txtInterests.getText().toString());

//        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
//        dlgAlert.setMessage(txtLog.getText().toString() + txtPas.getText().toString() + txtPasConf.getText().toString() + txtEmail.getText().toString() + txtInterests.getText().toString());
//        dlgAlert.setTitle("Error occured");
//        dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int which) {
//                //dismiss the dialog
   }*/
    private void GetAllChatRoomsList()
    {
        try {
            rooms = dbConnection.GetAllRoomsData();
        }
        catch (Exception ex)
        {}
    }

    private void DisplayAllChatRooms()
    {
        final ListView allRoomsList = (ListView) findViewById(R.id.lViewallRooms);

        try {

            Room[] allRoomsArray = new Room[rooms.size()];
            rooms.toArray(allRoomsArray);
            RoomArrayAdapter roomArrayAdapter = new RoomArrayAdapter(this, R.layout.room_list_item, allRoomsArray);
            allRoomsList.setAdapter(roomArrayAdapter);
            allRoomsList.setClickable(true);
            allRoomsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                     try {
                         Globals.room.question = ((TextView) ((LinearLayout)arg1).findViewById(R.id.question)).getText().toString();
                         Globals.room.authorId = dbConnection.GetUserId(((TextView) ((LinearLayout) arg1).findViewById(R.id.author)).getText().toString());
                        //Toast.makeText(getApplicationContext(), question, Toast.LENGTH_LONG).show();
                         Intent i = new Intent(MainActivity.this, ChatActivity.class);
                         startActivity(i);
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
        TabHost tabs = (TabHost) findViewById(R.id.tabHost);

        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("tag1");

        spec.setContent(R.id.tab1);
        spec.setIndicator("All discussions");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("My discussions");
        tabs.addTab(spec);

        spec = tabs.newTabSpec("tag3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Invitations");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);
    }
}
