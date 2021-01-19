package com.example.iut_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Envoyer extends AppCompatActivity implements LocationListener {

    private static final int PERMS_CALL_ID = 1234;
    private Button Pan, Pb, Ba, retour;
    private LocationManager lm;
    public double latitude, longitude;
    boolean first = true;
///Localisation


    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    private void checkPermissions(){


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            },PERMS_CALL_ID);

            return;
        }
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (lm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 10000, 0, this);
        }
        if (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMS_CALL_ID){
            checkPermissions();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
       if(lm != null){
            lm.removeUpdates(this);
        }



    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();
     }

    ///SEND
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
        onPause();
        Pan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String num= num_final;
               String msg = "Je suis en panne" + "\n" + "longitude : " + longitude+ "\n" +"latitude : " + latitude;

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

                String msg = "J'ai un problème" + "\n" + "longitude : " + longitude+ "\n" +"latitude : " + latitude;
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
                String msg = "J'ai besoin d'aide medicale"+ "\n" + "longitude : " + longitude+ "\n" +"latitude : " + latitude;
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