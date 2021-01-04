package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ViewContact extends AppCompatActivity {
    private MySQLiteOpenHelper myDB;
    private TextView contactView;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        myDB = new MySQLiteOpenHelper(this);
        contactView = (TextView) findViewById(R.id.contactView);
        back = (ImageButton) findViewById(R.id.Return);


        List<AllContact> contacts = myDB.readContact();
        for(AllContact contact : contacts){
            contactView.append(contact.toString() + "\n\n");
        }
        myDB.close();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(send);
                finish();
            }

        });

    }


}