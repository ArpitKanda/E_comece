package com.example.arpitkanda.ecomerce;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    EditText product_name;
    EditText product_desc;
    Button add;
    Button get;
    Button update;
    Button delete;
    Connection conn;
    Statement st;
    ResultSet rs;
    ConnectionClass connectionClass;
    PreparedStatement pst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        product_name = (EditText) findViewById(R.id.editText);
        product_desc = (EditText) findViewById(R.id.editText2);
        add = (Button) findViewById(R.id.button);
        get=(Button)findViewById(R.id.button2);
        delete=(Button)findViewById(R.id.delete_button);

        connectionClass = new ConnectionClass();
        (new DBConnect()).execute(null, null, null);
        add_product();
        get_data();
        delete_data();
    }

    class DBConnect extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection conn = connectionClass.conn();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

    }

    class InsertData extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection conn = connectionClass.conn();
                String sql="insert into Employee(Prod_Name,Prod_Desc)values('"+product_name.getText().toString()+"','"+product_desc.getText().toString()+"')";
                st=conn.createStatement();
                st.execute(sql);
                Log.e("INSER","SAVED");
//                Toast.makeText(MainActivity.this, "ADDED",
//                        Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                ex.printStackTrace();
//                Toast.makeText(MainActivity.this, ""+ex,
//                        Toast.LENGTH_LONG).show();
            }
            return null;
        }

    }
    public void  add_product(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InsertData().execute(null,null,null);
            }
        });
    }

    class Select extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                String sql_get="select * from Employee";
                Connection conn=connectionClass.conn();
                pst=conn.prepareStatement(sql_get);
                rs=pst.executeQuery();
                while (rs.next()){
                    String name=rs.getString("Prod_Name");
                    String desc=rs.getString("Prod_Desc");
                    String id=rs.getString("id");
                    Log.e("NAME",name);
                    Log.e("DESC",desc);
                    Log.e("ID",id);
                }

            }catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }
    public void get_data(){
      get.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              new Select().execute(null,null,null);
          }
      });
    }
    class Delete extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            try{
                String del_sql="delete * from Employee";
                Connection conn=connectionClass.conn();
                st=conn.createStatement();
                st.execute(del_sql);
                Log.e("DELETED","DATA DELETED");
            }catch(Exception ex){
                ex.printStackTrace();
            }
            return null;
        }
    }
    public void delete_data(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Delete().execute(null,null,null);
            }
        });
    }
}
