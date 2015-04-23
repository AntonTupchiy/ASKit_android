package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by user on 15.04.2015.
 */
public class DBConnection {
    private static final String LOG_TAG = "myLogs";
    private static String Url = "jdbc:jtds:sqlserver://208.118.63.109:1433;databaseName=DB_9BD928_AskIt;";
    private static String Login = "DB_9BD928_AskIt_admin";
    private static String Password = "q1w2E3r4";
    private static String driver = "net.sourceforge.jtds.jdbc.Driver";
    private static Connection con = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;
    boolean result;

    private String sql = "INSERT INTO [dbo].[Users] ([Login], [Name], [Surname], [E-Mail], [Password], [Knowledge]) " +
            "VALUES ('vasya','vasya','vasya','vasya@google.ru','vasya', 'vasya')";

    //Connection to mssql database server
    private static Connection getDBConnection() {
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            Log.d(LOG_TAG, "Where is your MySQL JDBC Driver?");
            e.printStackTrace();
        }

        Log.d(LOG_TAG,"MySQL JDBC Driver Registered!");

        try {
            con = DriverManager.getConnection(Url, Login, Password);
            return con;

        } catch (Exception e) {
            Log.d(LOG_TAG, "Connection Failed! Check output console");
            e.printStackTrace();
        }
        return con;
    }

    //region #Test shit
    //Insert query to mssql
    public void mysqlInit() {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    stmt.execute(sql);
                    resultSet = stmt.executeQuery(sql);
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                    return;
                }
//                 finally {
//                    try {
//                        if (con != null)
//                            con.close();
//                        else if (stmt != null)
//                            stmt.close();
//                    } catch (SQLException e){
//                        e.printStackTrace();
//                    }
//                }
            }
        });
        thread.start();
    }
    //endregion

    //region #INSERT procedures
    public void NewUser(String login, String password, String email, String interests)
    {
        final String query = "INSERT INTO [dbo].[Users] ([Login], [Name], [Surname], [E-Mail], [Password], [Knowledge]) " +
                "VALUES ('"+ login+"','"+ "_" +"','"+"_"+"','"+email+"','"+password+"', '"+interests+"')";

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    stmt.execute(query);
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                    return;
                }
            }
        });
        thread.start();
    }
    public void NewChatRoom(String question, java.util.Date date, Context context)
    {
            java.sql.Time sqlDate = new java.sql.Time(date.getTime());
            final String query = "INSERT INTO [dbo].[Room] ([AuthorID], [Time], [Question]) " +
                    "VALUES ('" + Globals.user.ID + "','" + sqlDate + "','" + question + "')";

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        con = getDBConnection();
                        stmt = con.createStatement();
                        stmt.execute(query);
                    } catch (Exception e) {
                        Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                        e.printStackTrace();
                        return;
                    }
                }
            });
            thread.start();
    }

    public void NewMessage(String message, java.util.Date date)
    {
        java.sql.Time sqlDate = new java.sql.Time(date.getTime());
        final String query = "INSERT INTO [dbo].[Messages] ([RoomID], [AuthorID], [Text], [Time]) " +
                "VALUES ('" + Globals.room.ID + "', '" + Globals.user.ID + "', '" + message + "', '" + sqlDate + "')";

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    stmt.execute(query);
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                    return;
                }
            }
        });
        thread.start();
    }
    //endregion

    //region #SELECT queries
    public boolean CheckCredentialsCorrectness(final String login, final String password) throws InterruptedException {
        final String query = "SELECT * FROM [dbo].[Users] WHERE [Login]='" + login + "' AND [Password]='" + password + "'";
        final AtomicBoolean b = new AtomicBoolean(false);
        Thread threadOnCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    resultSet = stmt.executeQuery(query);
                    while (resultSet.next()){
                        String log = resultSet.getString("Login");
                        String pass = resultSet.getString("Password");
                        Log.d(LOG_TAG, log);
                        Log.d(LOG_TAG, pass);
                        if (login.equals(log) && password.equals(pass)) {
                            Log.d(LOG_TAG, "User exist in db");
                            b.set(true);
                        }
                    }
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                }
            }
        });

        threadOnCheck.start();
        threadOnCheck.join();
        return b.get();
    }

    public User GetUserData(final String login) throws InterruptedException {
        final String query = "SELECT * FROM [dbo].[Users] WHERE [Login]='" + login + "'";
        final User user = new User();
        final AtomicBoolean b = new AtomicBoolean(false);
        Thread threadOnCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    resultSet = stmt.executeQuery(query);
                    while (resultSet.next()){
                        user.ID = resultSet.getInt("ID");
                        user.Login = resultSet.getString("Login");
                        user.Name = resultSet.getString("Name");
                        user.Surname = resultSet.getString("Surname");
                        user.Email = resultSet.getString("E-mail");
                        user.Password = resultSet.getString("Password");
                        user.Knowledge = resultSet.getString("Knowledge");
                    }
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                }
            }
        });

        threadOnCheck.start();
        threadOnCheck.join();
        return user;
    }

    public String GetUserName(final int id) throws InterruptedException {
        final String query = "SELECT [Login] FROM [dbo].[Users] WHERE [ID]='" + id + "'";

        final AtomicBoolean b = new AtomicBoolean(false);
        Thread threadOnCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    resultSet = stmt.executeQuery(query);
                    while (resultSet.next()){
                        Globals.Login = resultSet.getString("Login");
                    }
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                }
            }
        });

        threadOnCheck.start();
        threadOnCheck.join();

        return Globals.Login;
    }

    public int GetUserId(final String login) throws InterruptedException {
        final String query = "SELECT [ID] FROM [dbo].[Users] WHERE [Login]='" + login + "'";

        final AtomicBoolean b = new AtomicBoolean(false);
        Thread threadOnCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    resultSet = stmt.executeQuery(query);
                    while (resultSet.next()){
                        Globals.UID = resultSet.getInt("ID");
                    }
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                }
            }
        });

        threadOnCheck.start();
        threadOnCheck.join();

        return Globals.UID;
    }

    public Room GetRoomData(final String qustion) throws InterruptedException {
        final String query = "SELECT * FROM [dbo].[Room] WHERE [Question]='" + qustion + "'";
        final Room room = new Room();
        final AtomicBoolean b = new AtomicBoolean(false);
        Thread threadOnCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    resultSet = stmt.executeQuery(query);
                    while (resultSet.next()){
                        room.ID = resultSet.getInt("ID");
                        room.authorId = resultSet.getInt("AuthorID");
                        room.time = resultSet.getTime("Time");
                        room.question = resultSet.getString("Question");
                    }
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                }
            }
        });

        threadOnCheck.start();
        threadOnCheck.join();
        return room;
    }

    public ArrayList<Room> GetAllRoomsData() throws InterruptedException {
        final String query = "SELECT * FROM [dbo].[Room]";
        final ArrayList<Room> returnList= new ArrayList<Room>();
        final AtomicBoolean b = new AtomicBoolean(false);
        Thread threadOnCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    resultSet = stmt.executeQuery(query);
                    while (resultSet.next()){
                        Room room = new Room();
                        room.ID = resultSet.getInt("ID");
                        room.authorId = resultSet.getInt("AuthorID");
                        room.time = resultSet.getTime("Time");
                        room.question = resultSet.getString("Question");
                        returnList.add(room);
                    }
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                }
            }
        });

        threadOnCheck.start();
        threadOnCheck.join();
        return returnList;
    }

    public ArrayList<Message> GetAllMessagesData() throws InterruptedException {
        final String query = "SELECT * FROM [dbo].[Messages ]";
        final ArrayList<Message> returnList= new ArrayList<Message>();
        final AtomicBoolean b = new AtomicBoolean(false);
        Thread threadOnCheck = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    resultSet = stmt.executeQuery(query);
                    while (resultSet.next()){
                        Message message = new Message();
                        message.ID = resultSet.getInt("MessageID");
                        message.authorId = resultSet.getInt("AuthorID");
                        message.roomID = resultSet.getInt("RoomID");
                        message.time = resultSet.getTime("Time");
                        message.text = resultSet.getString("Text");
                        returnList.add(message);
                    }
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                }
            }
        });

        threadOnCheck.start();
        threadOnCheck.join();
        return returnList;
    }
    //endregion
}