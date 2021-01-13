package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AjoutContact extends AppCompatActivity {
    MySQLiteOpenHelper myDB;
    EditText editName, editSurname, editTel;
    ImageButton Ajout;
    Button viewAll;
    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_contact);
        myDB = new MySQLiteOpenHelper(this);
        editName = (EditText) findViewById(R.id.Name);
        editSurname = (EditText) findViewById(R.id.Surname);
        editTel = (EditText) findViewById(R.id.Phone);
        this.Ajout = (ImageButton) findViewById(R.id.help);
        viewAll = (Button) findViewById(R.id.test);
        back = (ImageButton) findViewById(R.id.back);

        final String[] num_tel = {""};

        final int[] ID = {0};


        Ajout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editTel.getText().length() == 10 && editName.getText().length() != 0 && editSurname.getText().length() != 0) {
                            Cursor res = myDB.getAllData();
                            if (res.getCount() >= 3) {
                                showMessage("Error", "3 Contacts déjà présent.");
                            } else {
                                boolean isInserted = myDB.insertData(editName.getText().toString(),
                                        editSurname.getText().toString(),
                                        editTel.getText().toString());
                                if (isInserted == true) {
                                    Toast.makeText(AjoutContact.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                    Intent intent_a = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent_a);
                                    finish();
                                }else {
                                    Toast.makeText(AjoutContact.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                                }
                            }


                        }
                        else{
                                showMessage("Error", "Champs non remplit ou mal remplit.");
                            }

                    }
                }
        );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent_a);
                finish();
            }

        });

        viewAll.setOnClickListener(
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
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    }

