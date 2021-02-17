package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DeleteContact extends AppCompatActivity {

    MySQLiteOpenHelper myDB;

    TextView contact1,contact2,contact3;
    ImageButton retour;
    Button supp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        myDB = new MySQLiteOpenHelper(this);

        contact1 = (TextView) findViewById(R.id.Contact_1);
        contact2 = (TextView) findViewById(R.id.Contact_2);
        contact3 = (TextView) findViewById(R.id.Contact_3);
        retour = (ImageButton) findViewById(R.id.Retour);
        supp = (Button) findViewById(R.id.Delete);
        final RadioButton button1 = findViewById(R.id.B1);
        RadioButton button2 = findViewById(R.id.B2);
        RadioButton button3 = findViewById(R.id.B3);

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

        ///Retour au main

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
                finish();
            }

        });

        ///Supprimer contact

        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
            //    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                switch(view.getId()){
                    case R.id.B1:
                        if(checked)
                            //myDB.deleteData("1");
                            showMessage("1", "coucou" );
                            break;
                    case R.id.B2:
                        if(checked)
                           // myDB.deleteData("2");
                        showMessage("2", "coucou" );
                        break;
                    case R.id.B3:
                        if(checked)
                            //myDB.deleteData("3");
                            showMessage("3", "coucou" );
                        break;

                }


              //  startActivity(intent);
                //finish();
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