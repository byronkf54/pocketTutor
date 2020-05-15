package com.example.pockettutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class tutorLogin extends AppCompatActivity {
    EditText UsernameET;
    EditText PasswordET;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_login);

        UsernameET = findViewById(R.id.username);
        PasswordET = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(tutorLogin.this, addTutor.class);
                startActivity(registerIntent);
            }
        });
    }

    public void onLogin(View view) {
        AES aes = new AES();
        String username = UsernameET.getText().toString();
        if (username.length() == 0) {
            Toast toast = Toast.makeText(this, "Username is empty, please fill in your username or register an account.", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(getIntent());
        }
        String UID = aes.encrypt(username);
        String passwordAES = aes.encrypt(PasswordET.getText().toString());

        String type = "studentLogin";

        backgroundWorker background_worker = new backgroundWorker(this);
        background_worker.execute(type, UID, username, passwordAES, "0");

    }
}
