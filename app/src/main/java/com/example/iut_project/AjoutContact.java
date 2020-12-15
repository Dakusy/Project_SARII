package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AjoutContact extends AppCompatActivity {
    MySQLiteOpenHelper myDB;
    EditText editName, editSurname, editTel;
    String retrieve_text;
    ImageButton Ajout;
    Button test;
    Button retrieve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_contact);
        myDB = new MySQLiteOpenHelper(this);
        editName = (EditText) findViewById(R.id.Name);
        editSurname = (EditText) findViewById(R.id.Surname);
        editTel = (EditText) findViewById(R.id.Phone);
        this.Ajout = (ImageButton) findViewById(R.id.Ajouter);
        test = (Button) findViewById(R.id.test);
        retrieve = (Button) findViewById(R.id.retrieve);
        final TextView retrieve_text = (TextView) findViewById(R.id.retrieve_text);


        Ajout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editTel.getText().toString());
                        if (isInserted == true)
                            Toast.makeText(AjoutContact.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AjoutContact.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
        test.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Surname :"+ res.getString(2)+"\n");
                            buffer.append("Tel :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Contacts",buffer.toString());
                    }
                }
        );

        retrieve.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getOneData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        if(res.getCount() != 0){
                            res.moveToFirst();
                            Toast.makeText(AjoutContact.this, "ID :" +  res.getString(1), Toast.LENGTH_LONG).show();
                        }

                        /*StringBuffer buffer = new StringBuffer();
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Surname :"+ res.getString(2)+"\n");
                            buffer.append("Tel :"+ res.getString(3)+"\n\n");*/

                    }
                }
        );

    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    }
