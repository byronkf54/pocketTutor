package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText UsernameET;
    EditText PasswordET;
    Button login;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UsernameET = findViewById(R.id.username);
        PasswordET = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, Register.class);
                startActivity(registerIntent);
            }
        });
    }

    public void onLogin(View view) {
        AES aes = new AES();
        String UID = aes.encrypt(UsernameET.getText().toString());
        String passwordAES = aes.encrypt(PasswordET.getText().toString());

        String type = "login";

        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, UID, passwordAES);
    }
}
