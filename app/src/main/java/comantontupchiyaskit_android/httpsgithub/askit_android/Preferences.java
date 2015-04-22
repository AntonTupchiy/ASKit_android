package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by GreenQ on 20.04.2015.
 */
public class Preferences {

    SharedPreferences preferencesID;
    SharedPreferences preferencesLogin;
    SharedPreferences preferencesName;
    SharedPreferences preferencesSurname;
    SharedPreferences preferencesPassword;
    SharedPreferences preferencesEmail;
    SharedPreferences preferencesKnowledge;

    public Preferences(Context context)
    {
        preferencesID = context.getSharedPreferences("ID", Context.MODE_PRIVATE);
        preferencesLogin = context.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        preferencesName = context.getSharedPreferences("NAME", Context.MODE_PRIVATE);
        preferencesSurname = context.getSharedPreferences("SURNAME", Context.MODE_PRIVATE);
        preferencesPassword = context.getSharedPreferences("PASSWORD", Context.MODE_PRIVATE);
        preferencesEmail = context.getSharedPreferences("EMAIL", Context.MODE_PRIVATE);
        preferencesKnowledge = context.getSharedPreferences("KNOWLEDGE", Context.MODE_PRIVATE);
    }
    //region #Getting authorized user's credentials
    public int GetAuthorizedUserID()
    {
        return preferencesID.getInt("UID", 0);
    }

    public String GetAuthorizedUserLogin()
    {
        return preferencesLogin.getString("LOGIN", "_");
    }

    public String GetAuthorizedUserName()
    {
        return preferencesName.getString("NAME", "_");
    }

    public String GetAuthorizedUserSurname()
    {
        return preferencesSurname.getString("SURNAME", "_");
    }

    public String GetAuthorizedUserEmail()
    {
        return preferencesEmail.getString("EMAIL", "_");
    }

    public String GetAuthorizedUserPassword()
    {
        return preferencesPassword.getString("PASSWORD", "_");
    }

    public String GetAuthorizedUserKnowledge()
    {
        return preferencesKnowledge.getString("Knowledge", "_");
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
        SharedPreferences.Editor editor = preferencesID.edit();
        editor.putInt("UID", id);
        editor.commit();
    }

    public void SetAuthorizedUserLogin(String login)
    {
        SharedPreferences.Editor editor = preferencesLogin.edit();
        editor.putString("LOGIN", login);
        editor.commit();
    }

    public void SetAuthorizedUserName(String name)
    {
        SharedPreferences.Editor editor = preferencesName.edit();
        editor.putString("NAME", name);
        editor.commit();
    }

    public void SetAuthorizedUserSurname(String surname)
    {
        SharedPreferences.Editor editor = preferencesSurname.edit();
        editor.putString("SURNAME", surname);
        editor.commit();
    }

    public void SetAuthorizedUserEmail(String email)
    {
        SharedPreferences.Editor editor = preferencesEmail.edit();
        editor.putString("EMAIL", email);
        editor.commit();
    }

    public void SetAuthorizedUserPassword(String password)
    {
        SharedPreferences.Editor editor = preferencesPassword.edit();
        editor.putString("PASSWORD", password);
        editor.commit();
    }

    public void SetAuthorizedUserKnowledge(String knowledge)
    {
        SharedPreferences.Editor editor = preferencesKnowledge.edit();
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

    public void ForgetAuthorizedUser()
    {
        SetAuthorizedUserID(0);
        SetAuthorizedUserLogin("_");
        SetAuthorizedUserName("_");
        SetAuthorizedUserSurname("_");
        SetAuthorizedUserPassword("_");
        SetAuthorizedUserEmail("_");
        SetAuthorizedUserKnowledge("_");
    }
    //endregion

}
