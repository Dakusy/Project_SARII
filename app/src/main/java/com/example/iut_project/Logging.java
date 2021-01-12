package com.example.iut_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Logging extends AppCompatActivity {

    private EditText Name;
    private EditText Surname;
    private Button Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewById(R.id.etName);
        Surname = (EditText) findViewById((R.id.etSurname));
        Next = (Button) findViewById(R.id.button);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(), Surname.getText().toString());
            }
        });
    }

    private void validate(String userName, String userSurname){

        String strName = Name.getText().toString();
        String strSurname = Surname.getText().toString();

        if (!(strName.equals("")) && !(strSurname.equals(""))) {
            Intent intent = new Intent(Logging.this, MainActivity.class);
            startActivity(intent);
        }
        else if(!(strName.equals("")))
            Toast.makeText(Logging.this, "Enter a Surname", Toast.LENGTH_SHORT).show();
        else Toast.makeText(Logging.this, "Enter a Name", Toast.LENGTH_SHORT).show();
    }
}