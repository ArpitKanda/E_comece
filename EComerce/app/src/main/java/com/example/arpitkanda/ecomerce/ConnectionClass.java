package com.example.arpitkanda.ecomerce;

import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Arpit Kanda on 24-Apr-18.
 */

public class ConnectionClass {
    Connection conn=null;

    public Connection conn(){
        try{
            Log.e("MYSQL","Attempt To Connecct");
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:jtds:sqlserver://mcorp.database.windows.net/Testing_Mobile_Service","Akhileshsh026","Akhilesh@123");
            Log.e("MYSQL","Connected");


        }catch(Exception ex){
            ex.printStackTrace();
            Log.e("MSSQL", ex.toString());
        }
        return conn;
    }
}
