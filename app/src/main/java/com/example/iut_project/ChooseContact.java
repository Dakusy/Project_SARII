package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ChooseContact extends AppCompatActivity {

    MySQLiteOpenHelper myDB;
    Button choice1, choice2, choice3,button;
    TextView contact1;
    TextView contact2;
    TextView contact3;

    ImageButton next_choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_contact);

        myDB = new MySQLiteOpenHelper(this);

        choice1 = (Button) findViewById(R.id.choice1);
        choice2 = (Button) findViewById(R.id.choice2);
        choice3 = (Button) findViewById(R.id.choice3);
        next_choice = (ImageButton) findViewById(R.id.next);
        contact1 = (TextView) findViewById(R.id.Contact1);
        contact2 = (TextView) findViewById(R.id.Contact2);
        contact3 = (TextView) findViewById(R.id.Contact3);
        final String[] num_tel = {""};
        final int[] ID = {0};


        ///Afficher les contacts
        List<AllContact> contacts = myDB.readContact();
        for(AllContact contact : contacts){
            if(contact.readID() == 1){
                contact1.append(contact.Contact());
            }
            if(contact.readID() == 2){
                contact2.append(contact.Contact());
            }
            if(contact.readID() == 3){
                contact3.append(contact.Contact());
            }
        }

        choice1.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ID[0] = 1;
                        Toast.makeText(ChooseContact.this, "Contact 1 Selected ", Toast.LENGTH_LONG).show();
                    }
                }
        );

        choice2.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ID[0] = 2;
                        Toast.makeText(ChooseContact.this, "Contact 2 Selected ", Toast.LENGTH_LONG).show();
                    }
                }
        );

        choice3.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        ID[0] = 3;
                        Toast.makeText(ChooseContact.this, "Contact 3 Selected ", Toast.LENGTH_LONG).show();
                    }
                }
        );


        next_choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDB.getOneData(ID[0]);
                Cursor allData = myDB.getAllData();
                if (ID[0] == 0) {
                    Toast.makeText(ChooseContact.this, "No Contact Selected", Toast.LENGTH_LONG).show();
                }

                if (ID[0] == 1) {
                    if (allData.getCount() == 0) {
                        // show message
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    if (allData.getCount() != 0) {
                        res.moveToFirst();
                        num_tel[0] = res.getString(3);
                        Toast.makeText(ChooseContact.this, "Nom :" + res.getString(1), Toast.LENGTH_LONG).show();
                    }
                }

                if (ID[0] == 2) {
                    if (allData.getCount() == 0 || allData.getCount() < 2) {
                        // show message
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    if (allData.getCount() != 0) {
                        res.moveToFirst();
                        num_tel[0] = res.getString(3);
                        Toast.makeText(ChooseContact.this, "Nom :" + res.getString(1), Toast.LENGTH_LONG).show();
                    }
                }

                if (ID[0] == 3) {
                    if (allData.getCount() == 0 || allData.getCount() < 3) {
                        // show message
                        showMessage("Error", "Nothing found");
                        return;
                    }

                    if (allData.getCount() != 0) {
                        res.moveToFirst();
                        num_tel[0] = res.getString(3);
                        Toast.makeText(ChooseContact.this, "Nom :" + res.getString(1), Toast.LENGTH_LONG).show();
                    }
                }
                Intent send = new Intent(getApplicationContext(), Envoyer.class);
                send.putExtra("Number", num_tel[0]);
                startActivity(send);
                finish();
            }
        });


    }


        public void showMessage(String title,String Message){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
        }
}
