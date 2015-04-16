package comantontupchiyaskit_android.httpsgithub.askit_android;

import android.util.Log;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by user on 15.04.2015.
 */
public class DBConnection {
    final String LOG_TAG = "myLogs";
    private static String Url = "jdbc:sqlserver://SQL5012.Smarterasp.net:1433;" +
            "databaseName=DB_9BD928_AskIt;integratedSecurity=true;";
    private static String Login = "DB_9BD928_AskIt_admin";
    private static String Password = "q1w2E3r4";

    //init
    public void mysqlInit() {
        Log.d(LOG_TAG,"-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            Log.d(LOG_TAG,"Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        Log.d(LOG_TAG,"MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(Url, Login, Password);

        } catch (SQLException e) {
            Log.d(LOG_TAG, "Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            Log.d(LOG_TAG,"You made it, take control your database now!");
        } else {
            Log.d(LOG_TAG,"Failed to make connection!");
        }
    }
}