package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.content.SharedPreferences;

/**
 * Created by GreenQ on 20.04.2015.
 */
public class Preferences {

    SharedPreferences preferences;

    public String GetAuthorizedUserLogin()
    {
        return preferences.getString("LOGIN", "_");
    }

    public void SetAuthorizedUserLogin(String login)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("LOGIN", login);
        editor.commit();
    }

}
