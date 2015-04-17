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
    private static String Url = "jdbc:jtds:sqlserver://208.118.63.109:1433;databaseName=DB_9BD928_AskIt;";
    private static String Login = "DB_9BD928_AskIt_admin";
    private static String Password = "q1w2E3r4";
    private static String driver = "net.sourceforge.jtds.jdbc.Driver";
    private Connection  con = null;

    //init

    public void mysqlInit() {
        Log.d(LOG_TAG,"-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Log.d(LOG_TAG,"Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        Log.d(LOG_TAG,"MySQL JDBC Driver Registered!");

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    con = DriverManager.getConnection(Url, Login, Password);
                } catch (Exception e) {
                    Log.d(LOG_TAG, "Connection Failed! Check output console");
                    e.printStackTrace();
                    return;
                }

                if (con != null) {
                    Log.d(LOG_TAG,"You made it, take control your database now!");
                } else {
                    Log.d(LOG_TAG,"Failed to make connection!");
                }
            }
        });
        thread.start();
/*        try {
            con = DriverManager.getConnection(Url, Login, Password);
            //connection = DriverManager.getConnection(Url, Login, Password);

        } catch (SQLException e) {
            Log.d(LOG_TAG, "Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }*/
    }
}