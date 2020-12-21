package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.jar.Attributes;


/*
 * Création des conatacts
 */

public class MainActivity extends AppCompatActivity {

    private Button NextPage;
    private ImageButton contact1;
    private ImageButton contact2;
    private ImageButton contact3;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

           this.NextPage = findViewById(R.id.Next);


           NextPage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent send = new Intent(getApplicationContext(), Envoyer.class);
                   startActivity(send);
                   finish();
               }

           });

           this.contact1 = (ImageButton) findViewById(R.id.Ajout1);

           contact1.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent_a = new Intent(getApplicationContext(), ChooseContact.class);
                   startActivity(intent_a);
                   finish();
               }

           });

       }
}