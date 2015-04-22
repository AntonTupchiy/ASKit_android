package comantontupchiyaskit_android.httpsgithub.askit_android;

/**
 * Created by GreenQ on 22.04.2015.
 */
public class User {
    public int ID;
    public String Login;
    public String Name;
    public String Surname;
    public String Email;
    public String Password;
    public String Knowledge;

    public User(int id, String login, String name, String surname, String email, String password, String knowledge)
    {
        this.ID = id;
        this.Login = login;
        this.Name = name;
        this.Surname = surname;
        this.Email = email;
        this.Password = password;
        this.Knowledge = knowledge;
    }

    public User()
    {

    }

    public void RememberUser()
    {
        Preferences preferences = new Preferences();
        preferences.SetAuthorizedUser(this);
    }
}
