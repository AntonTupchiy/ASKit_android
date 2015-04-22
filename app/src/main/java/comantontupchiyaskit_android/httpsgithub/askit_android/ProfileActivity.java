package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

/**
 * Created by user on 21.04.2015.
 */
public class ProfileActivity extends Activity {
    EditText mail;
    EditText passwordOld;
    EditText passwordNew;
    EditText knowledge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.profile);

        mail = (EditText) findViewById(R.id.txtEmail);
        mail.setText(Globals.user.Email);

        passwordOld = (EditText) findViewById(R.id.txtPasswordOld);
        passwordOld.setText(Globals.user.Password);

        passwordNew = (EditText) findViewById(R.id.txtPasswordNew);

        knowledge = (EditText) findViewById(R.id.txtKnowledge);
        knowledge.setText(Globals.user.Knowledge);
    }

}
