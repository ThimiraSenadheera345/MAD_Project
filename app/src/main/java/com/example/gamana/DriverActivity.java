package com.example.gamana;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DriverActivity extends AppCompatActivity {

    driverDB myDB ;
    EditText name , address , age ;
    Button addDriverbtn ;
    Button viewData ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        myDB = new driverDB(this);

        name = (EditText) findViewById(R.id.driverName);
        address = (EditText) findViewById(R.id.driverAddress) ;
        age = (EditText) findViewById(R.id.driverAge) ;
        addDriverbtn = (Button) findViewById(R.id.addDriverBtn) ;
        viewData = (Button) findViewById(R.id.viewDriversBtn) ;
        addDriver();
        viewDrivers();



        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            if (bundle.getString("some") != null){

                Toast.makeText(getApplicationContext() , "data :" + bundle.getString("some") , Toast.LENGTH_LONG).show();

            }
        }
    }

    public void addDriver(){
        addDriverbtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       boolean isInserted = myDB.insertData(name.getText().toString() , address.getText().toString() , age.getText().toString());

                       if (isInserted = true){
                           Toast.makeText(DriverActivity.this, "Driver inserted", Toast.LENGTH_SHORT).show();
                       }
                       else {
                           Toast.makeText(DriverActivity.this, "Driver not inserted", Toast.LENGTH_SHORT).show();
                       }
                    }
                }
        );
    }

    public void viewDrivers(){
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getAllData();
                if (res.getCount() == 0){
                    showMessage("Error" , "No drivers");
                    return ;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Name : " + res.getString(1) + "\n");
                    buffer.append("Address: " + res.getString(2) + "\n");
                    buffer.append("Age : " + res.getString(3) + "\n\n");
                }

                showMessage("Drivers" , buffer.toString());


            }
        });
    }

    public void showMessage(String title , String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}