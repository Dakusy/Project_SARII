package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Setting extends AppCompatActivity {

    private Button retour, Ajouter,View,Supp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        this.retour = findViewById(R.id.retour);
        this.Ajouter = findViewById(R.id.help);
        this.View = findViewById(R.id.View);
        this.Supp = findViewById(R.id.Supprimer);

        retour.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent choice = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(choice);
                finish();
            }

        });



        Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajout = new Intent(getApplicationContext(), AjoutContact.class);
                startActivity(ajout);
                finish();
            }

        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewcontact = new Intent(getApplicationContext(), ViewContact.class);
                startActivity(viewcontact);
                finish();
            }

        });

        Supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent delete = new Intent(getApplicationContext(), DeleteContact.class);
                startActivity(delete);
                finish();
            }

        });

    }
}