package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Envoyer extends AppCompatActivity {


    private Button Pan,Pb,Ba,cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envoyer);

        Pan=(Button) findViewById(R.id.Panne);
        Pb=(Button) findViewById(R.id.Pb_Medicale);
        Ba=(Button) findViewById(R.id.Besoin_Aide);
        cc = (Button) findViewById(R.id.cc);
        final String Numero = getIntent().getStringExtra("Number");
        final String num = Numero;
        String zero = "0";
        final String num_final = zero + num;


        cc.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast.makeText(Envoyer.this, num_final , Toast.LENGTH_LONG).show();
                    }
                }
        );
        final String finalNumero = Numero;
        Pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num= num_final;
                String msg = "Je suis en panne";

                Intent intent = new Intent(getApplicationContext(), Envoyer.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(num, null, msg, pi, null);

                Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
            }
        });
        Pb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String num=num_final;

                String msg = "J'ai un problème";
                Intent intent = new Intent(getApplicationContext(), Envoyer.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(num, null, msg, pi, null);

                Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
            }

        });

        Ba.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String num=num_final;
                String msg = "J'ai besoin d'aide medicale";
                Intent intent = new Intent(getApplicationContext(), Envoyer.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(num, null, msg, pi, null);

                Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
            }

        });


    }
}