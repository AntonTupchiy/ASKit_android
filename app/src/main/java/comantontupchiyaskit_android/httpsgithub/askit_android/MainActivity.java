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
import android.widget.EditText;


public class MainActivity extends Activity {

    //TestTYT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        //test
    }

    public void profileButton(View view){
        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
}
