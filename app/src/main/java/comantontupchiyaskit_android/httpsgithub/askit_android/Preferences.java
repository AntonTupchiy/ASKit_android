package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.content.SharedPreferences;

/**
 * Created by GreenQ on 20.04.2015.
 */
public class Preferences {

    SharedPreferences preferences;

    //region #Getting authorized user's credentials
    public int GetAuthorizedUserID()
    {
        return preferences.getInt("UID", 0);
    }

    public String GetAuthorizedUserLogin()
    {
        return preferences.getString("LOGIN", "_");
    }

    public String GetAuthorizedUserName()
    {
        return preferences.getString("NAME", "_");
    }

    public String GetAuthorizedUserSurname()
    {
        return preferences.getString("SURNAME", "_");
    }

    public String GetAuthorizedUserEmail()
    {
        return preferences.getString("EMAIL", "_");
    }

    public String GetAuthorizedUserPassword()
    {
        return preferences.getString("PASSWORD", "_");
    }

    public String GetAuthorizedUserKnowledge()
    {
        return preferences.getString("Knowledge", "_");
    }

    public User GetAuthorizedUser()
    {
        int userId = GetAuthorizedUserID();
        String login = GetAuthorizedUserLogin();
        String name = GetAuthorizedUserName();
        String surname = GetAuthorizedUserSurname();
        String email = GetAuthorizedUserEmail();
        String password = GetAuthorizedUserPassword();
        String knowledge = GetAuthorizedUserKnowledge();
        User user = new User(userId, login, name, surname, email, password, knowledge);
        return user;
    }
    //endregion

    //region #Setting authorized user's credentials
    public void SetAuthorizedUserID(int id)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("UID", id);
        editor.commit();
    }

    public void SetAuthorizedUserLogin(String login)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("LOGIN", login);
        editor.commit();
    }

    public void SetAuthorizedUserName(String name)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("NAME", name);
        editor.commit();
    }

    public void SetAuthorizedUserSurname(String surname)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("SURNAME", surname);
        editor.commit();
    }

    public void SetAuthorizedUserEmail(String email)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("EMAIL", email);
        editor.commit();
    }

    public void SetAuthorizedUserPassword(String password)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("PASSWORD", password);
        editor.commit();
    }

    public void SetAuthorizedUserKnowledge(String knowledge)
    {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("KNOWLEDGE", knowledge);
        editor.commit();
    }

    public void SetAuthorizedUser(User user)
    {
        SetAuthorizedUserID(user.ID);
        SetAuthorizedUserLogin(user.Login);
        SetAuthorizedUserName(user.Name);
        SetAuthorizedUserSurname(user.Surname);
        SetAuthorizedUserPassword(user.Password);
        SetAuthorizedUserEmail(user.Email);
        SetAuthorizedUserKnowledge(user.Knowledge);
    }
    //endregion

}
