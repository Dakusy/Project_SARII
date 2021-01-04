package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Envoyer extends AppCompatActivity {


    private Button Pan,Pb,Ba,retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer);

        Pan=(Button) findViewById(R.id.Panne);
        Pb=(Button) findViewById(R.id.Pb_Medicale);
        Ba=(Button) findViewById(R.id.Besoin_Aide);
        retour = (Button) findViewById(R.id.Back);
        final String Numero = getIntent().getStringExtra("Number");
        final String num = Numero;
        String zero = "0";
        final String num_final = zero + num;

        final String finalNumero = Numero;
        Pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num= num_final;
                String msg = "Je suis en panne";

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(num, null, msg, pi, null);

                Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
        });
        Pb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String num=num_final;

                String msg = "J'ai un problème";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(num, null, msg, pi, null);

                Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }

        });

        Ba.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String num=num_final;
                String msg = "J'ai besoin d'aide medicale";
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(num, null, msg, pi, null);

                Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }

        });

        retour.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent intent_a = new Intent(getApplicationContext(), ChooseContact.class);
                        startActivity(intent_a);
                        finish();
                    }
                }
        );

    }
}