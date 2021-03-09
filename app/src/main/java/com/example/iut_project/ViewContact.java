package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class ViewContact extends AppCompatActivity {
    MySQLiteOpenHelper myDB;

    RadioGroup radioGroup;
    TextView contact1, contact2, contact3;
    ImageButton retour;
    Button changer;
    int b1 = 0, b2 = 0, b3 = 0;


    public void checkButton(View v) {
        boolean checked = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.B1:
                if (checked)
                    b1 = 1;
                break;
            case R.id.B2:
                if (checked)
                    b2 = 1;
                break;
            case R.id.B3:
                if (checked)
                    b3 = 1;
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
        changer = (Button) findViewById(R.id.change);
        final RadioButton button1 = findViewById(R.id.B1);
        RadioButton button2 = findViewById(R.id.B2);
        RadioButton button3 = findViewById(R.id.B3);
        final String[] saveID1 = {"1"}, saveP1 = {"1"}, saveN1={"1"},saveT1={"1"},saveID2={"2"},saveP2={"2"},saveN2={"2"},saveT2={"2"};


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

        changer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<AllContact> contacts = myDB.readContact();
                if(b1==1 && b2==1){
                    int a = 1;
                    for(AllContact contact : contacts){
                        if (a==1){
                            saveID1[0] = contact.readID();
                            saveP1[0] = contact.readPrenom();
                            saveN1[0] = contact.readNom();
                            saveT1[0] = contact.Num();

                        }
                        if(a==2){
                            saveID2[0] = contact.readID();
                            saveP2[0] = contact.readPrenom();
                            saveN2[0] = contact.readNom();
                            saveT2[0] = contact.Num();
                        }
                        a = 2;
                    }

                    myDB.updateData(saveID1[0],saveP2[0],saveN2[0],saveT2[0]);
                    myDB.updateData(saveID2[0],saveP1[0],saveN1[0],saveT1[0]);
                }
                if(b1==1 && b3==1){
                    int a = 1;
                    for(AllContact contact : contacts){
                        if (a==1){
                            saveID1[0] = contact.readID();
                            saveP1[0] = contact.readPrenom();
                            saveN1[0] = contact.readNom();
                            saveT1[0] = contact.Num();

                        }
                        if(a==3){
                            saveID2[0] = contact.readID();
                            saveP2[0] = contact.readPrenom();
                            saveN2[0] = contact.readNom();
                            saveT2[0] = contact.Num();
                        }
                        a ++;
                    }

                    myDB.updateData(saveID1[0],saveP2[0],saveN2[0],saveT2[0]);
                    myDB.updateData(saveID2[0],saveP1[0],saveN1[0],saveT1[0]);
                }
                if(b2==1 && b3==1){
                    int a = 1;
                    for(AllContact contact : contacts){
                        if (a==2){
                            saveID1[0] = contact.readID();
                            saveP1[0] = contact.readPrenom();
                            saveN1[0] = contact.readNom();
                            saveT1[0] = contact.Num();

                        }
                        if(a==3){
                            saveID2[0] = contact.readID();
                            saveP2[0] = contact.readPrenom();
                            saveN2[0] = contact.readNom();
                            saveT2[0] = contact.Num();
                        }
                        a ++;
                    }

                    myDB.updateData(saveID1[0],saveP2[0],saveN2[0],saveT2[0]);
                    myDB.updateData(saveID2[0],saveP1[0],saveN1[0],saveT1[0]);
                }

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

                }

        });



    }
}