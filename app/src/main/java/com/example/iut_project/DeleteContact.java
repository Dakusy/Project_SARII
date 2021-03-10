package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class DeleteContact extends AppCompatActivity {

    MySQLiteOpenHelper myDB;

    RadioGroup radioGroup;
    TextView contact1,contact2,contact3;
    ImageButton retour;
    Button supp;
    int b1=0,b2=0,b3=0;


    public void checkButton(View v){
        boolean checked = ((RadioButton) v).isChecked();
        switch(v.getId()){
            case R.id.B1:
                if(checked)
                    //myDB.deleteData("1");
                    b1=1;
                break;
            case R.id.B2:
                if(checked)
                    b2=1;
                break;
            case R.id.B3:
                if(checked)
                    b3=1;
                break;

        }
       // int radioId = radioGroup.getCheckedRadioButtonId();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        myDB = new MySQLiteOpenHelper(this);

        contact1 = (TextView) findViewById(R.id.Contact_1);
        contact2 = (TextView) findViewById(R.id.Contact_2);
        contact3 = (TextView) findViewById(R.id.Contact_3);
        retour = (ImageButton) findViewById(R.id.Retour);
        supp = (Button) findViewById(R.id.change);
        final RadioButton button1 = findViewById(R.id.B1);
        RadioButton button2 = findViewById(R.id.B2);
        RadioButton button3 = findViewById(R.id.B3);

        ///Afficher les contacts
        List<AllContact> contacts = myDB.readContact();
        int i=0;
        for(AllContact contact : contacts){
            if(i==0){
                contact1.append(contact.Contact());

            }
            if(i==1){
                contact2.append(contact.Contact());

            }
            if(i==2){
                contact3.append(contact.Contact());

            }

            i++;
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

             /*   */
                List<AllContact> contacts = myDB.readContact();
                int j=1;
                for(AllContact contact : contacts){

                    if(b1==1&&j==1){
                      //  String test = contact.readID();
                        myDB.deleteData(contact.readID());
                        b1=0;
                    }
                    if(b2==1&&j==2){
                        myDB.deleteData(contact.readID());
                        b2=0;
                    }
                    if(b3==1&&j==3){
                        myDB.deleteData(contact.readID());
                        b3=0;
                    }
                    j++;
                }

                if(b1==0 && b2==0 && b3==0){
                    showMessage("Erreur","Veuilliez choisir une personne à supprimer");
                }else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
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