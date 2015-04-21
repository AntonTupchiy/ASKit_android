package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by user on 21.04.2015.
 */
public class RegistrationActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.registration);

        //test
    }

    public void ConfirmRegistration(View view)
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
    }

}
