package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AjoutContact extends AppCompatActivity {
    MySQLiteOpenHelper myDB;
    EditText editName, editSurname, editTel;
    ImageButton Ajout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_contact);
        myDB = new MySQLiteOpenHelper(this);
        editName = (EditText) findViewById(R.id.Name);
        editSurname = (EditText) findViewById(R.id.Surname);
        editTel = (EditText) findViewById(R.id.Phone);
        this.Ajout = (ImageButton) findViewById(R.id.Ajouter);

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
    }


    }

