package com.example.iut_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;


/*
 * Création des contacts
 */

public class MainActivity extends AppCompatActivity  implements LocationListener {

    MySQLiteOpenHelper myDB;
    private static final int PERMS_CALL_ID = 1234;
    private Button Pan, Pb, Ba;
    private LocationManager lm;
    public double latitude=50.2536263, longitude=3.283738;

    boolean first = true;
    int millis = 6000;
    final String[] num_tel = {""};
    final int[] ID = {0};

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
    public void onPause() {
        super.onPause();
        if(lm != null){
            lm.removeUpdates(this);
        }

    }

    public void onLocationChanged(Location localisation){
        Log.d("GPS","localisation : "+localisation.toString());
        String coordonnes = String.format("Latitude :" + localisation.getLatitude() + "- Longitude : " + localisation.getLongitude());
        Log.d("GPS", "coordonnees: " + coordonnes);
        latitude = localisation.getLatitude();
        longitude = localisation.getLongitude();
    }


    public void onStatusChanged(String fournisseur, int status, Bundle extras){
        Toast.makeText(null , fournisseur + "état : " + status, Toast.LENGTH_SHORT).show();
    }

    public void onProviderEnable(String fournisseur){
        Toast.makeText(null, fournisseur + "activé ! ", Toast.LENGTH_SHORT).show();
    }

    public void onProviderDisabled(String fournisseur){
        Toast.makeText(null, fournisseur + "désactivé ! ", Toast.LENGTH_SHORT).show();
    }


    private String convert(double latitude, double longitude) {
        StringBuilder builder = new StringBuilder();
        builder.append("/maps/place/");
        String latitudeDegrees = Location.convert(Math.abs(latitude), Location.FORMAT_SECONDS);
        String[] latitudeSplit = latitudeDegrees.split(":");
        builder.append(latitudeSplit[0]);
        builder.append("°");
        builder.append(latitudeSplit[1]);
        builder.append("'");
        builder.append(latitudeSplit[2]);

        if (latitude < 0) {
            builder.append("S+");
        } else {
            builder.append("N+");
        }



        String longitudeDegrees = Location.convert(Math.abs(longitude), Location.FORMAT_SECONDS);
        String[] longitudeSplit = longitudeDegrees.split(":");
        builder.append(longitudeSplit[0]);
        builder.append("°");
        builder.append(longitudeSplit[1]);
        builder.append("'");
        builder.append(longitudeSplit[2]);

        if (longitude < 0) {
            builder.append("W");
        } else {
            builder.append("E");
        }
        return builder.toString();
    }
    ///SEND
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new MySQLiteOpenHelper(this);
        Pan=(Button) findViewById(R.id.Panne);
        Pb=(Button) findViewById(R.id.Pb_Medicale);
        Ba=(Button) findViewById(R.id.Besoin_Aide);


        final String num ;
        final String zero = "0";
        final String num_final= "5";

        Pan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //for(int i = 1; i<=3; i++) {
                List<AllContact> contacts = myDB.readContact();
                for(final AllContact num : contacts){
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            String numero_final="0"+num.Num();

                            String adresse = "http://maps.google.com/?q=";

                            //String test = convert(latitude,longitude);
                            String msg = "Je suis en panne" + "\n" + "adresse : "+adresse+latitude+","+longitude;
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                            SmsManager sms = SmsManager.getDefault();
                            sms.sendTextMessage(numero_final, null, msg, pi, null);

                            Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
                            //   startActivity(intent);
                            //  finish();
                        }
                    }, 5000);
                }

                // Pour que la localisation fonctionne (même en mouvement) faut attentre 5 secondes



            }
        });
        Pb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                List<AllContact> contacts = myDB.readContact();
                for(final AllContact num : contacts){
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            String numero_final="0"+num.Num();

                            String adresse = "http://maps.google.com/?q=";

                            //String test = convert(latitude,longitude);
                            String msg = "J'ai un problème" + "\n" + "adresse : "+adresse+latitude+","+longitude;
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                            SmsManager sms = SmsManager.getDefault();
                            sms.sendTextMessage(numero_final, null, msg, pi, null);

                            Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
                            //   startActivity(intent);
                            //  finish();
                        }
                    }, 5000);
                }
            }



        });

        Ba.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                List<AllContact> contacts = myDB.readContact();
                for(final AllContact num : contacts){
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            String numero_final="0"+num.Num();

                            String adresse = "http://maps.google.com/?q=";

                            //String test = convert(latitude,longitude);
                            String msg = "J'ai besoin d'aide medical" + "\n" + "adresse : "+adresse+latitude+","+longitude;
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

                            SmsManager sms = SmsManager.getDefault();
                            sms.sendTextMessage(numero_final, null, msg, pi, null);

                            Toast.makeText(getApplicationContext(), "Message bien Envoyée", Toast.LENGTH_LONG).show();
                            //   startActivity(intent);
                            //  finish();
                        }
                    }, 5000);
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