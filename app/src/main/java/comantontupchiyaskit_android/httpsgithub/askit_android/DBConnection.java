package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.util.Log;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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

    //Insert query to mssql
    public void mysqlInit() {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    con = getDBConnection();
                    stmt = con.createStatement();
                    stmt.execute(sql);
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection or Execution Failed! Check output console");
                    e.printStackTrace();
                    return;
                }
            }
        });
        thread.start();
    }
}