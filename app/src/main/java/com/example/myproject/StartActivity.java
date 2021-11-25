package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }


    public void ENTER(View view) {
        EditText nameField = findViewById(R.id.editTextTextPersonName);
        String name = nameField.getText().toString();


        if (!name.isEmpty()) {
            Intent intent = new Intent(this,
                    MainActivity.class);
            intent.putExtra("username", name);

            startActivity(intent); }

        else{
            //若未输入则抛出警告
            Toast.makeText(this,
                    "Pleas write your name!", Toast.LENGTH_SHORT).show();
        }
    }

}