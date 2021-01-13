package com.example.iut_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/*
 * Création des contacts
 */

public class MainActivity extends AppCompatActivity  {

    private static final int PERMS_CALL_ID = 1234;
    private Button aide,param;


    ///Permission
    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }


    private void checkPermissions(){

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS

            },PERMS_CALL_ID);

            return;
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            },PERMS_CALL_ID);

            return;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMS_CALL_ID){
            checkPermissions();
        }
    }


    ///Différent choix
       @Override
    protected void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

           this.aide = findViewById(R.id.help);
           this.param = findViewById(R.id.retour);


           aide.setOnClickListener(new android.view.View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getApplication(), ChooseContact.class);
                   startActivity(intent);
                   finish();
               }

           });

           param.setOnClickListener(new android.view.View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(getApplication(), Setting.class);
                   startActivity(intent);
                   finish();
               }

           });



       }
}