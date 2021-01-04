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
 * Création des contacts
 */

public class MainActivity extends AppCompatActivity {

    private Button NextPage, Ajouter,View,Supp;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
           this.NextPage = findViewById(R.id.Next);
           this.Ajouter = findViewById(R.id.Ajouter);
           this.View = findViewById(R.id.View);
           this.Supp = findViewById(R.id.Supprimer);

           NextPage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent send = new Intent(getApplicationContext(), ChooseContact.class);
                   startActivity(send);
                   finish();
               }

           });



           Ajouter.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent_a = new Intent(getApplicationContext(), AjoutContact.class);
                   startActivity(intent_a);
                   finish();
               }

           });

           View.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent_a = new Intent(getApplicationContext(), ViewContact.class);
                   startActivity(intent_a);
                   finish();
               }

           });

           Supp.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent_a = new Intent(getApplicationContext(), DeleteContact.class);
                   startActivity(intent_a);
                   finish();
               }

           });


       }
}