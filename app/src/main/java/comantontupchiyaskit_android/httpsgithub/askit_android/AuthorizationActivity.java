package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by GreenQ on 20.04.2015.
 */
public class AuthorizationActivity extends Activity {

    private DBConnection dbConnection = new DBConnection();
    private EditText txtLogin;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.autorisation);

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }

    public void Authorize(View view)
    {

        String login = txtLogin.getText().toString();
        String password = txtPassword.getText().toString();
        try {
            if (dbConnection.CheckCredentialsCorrectness(login, password)) {
                Intent i = new Intent(AuthorizationActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage("Such user was not found");
                dlgAlert.setTitle("Error occurred");
                dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //dismiss the dialog
                    }
                });
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
        }
        catch(Exception ex)
        {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Error occurred" + ex.getMessage());
            dlgAlert.setTitle("Error occurred");
            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //dismiss the dialog
                }
            });
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }

    public void registrationButton(View view){
        Intent i = new Intent(AuthorizationActivity.this, RegistrationActivity.class);
        startActivity(i);
    }
}
