package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    EditText usernameET;
    EditText passwordET;
    EditText confirm_passwordET;
    Button create_account;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameET = findViewById(R.id.username);
        passwordET = findViewById(R.id.password);
        confirm_passwordET = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register);
        create_account = findViewById(R.id.create_account);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(Register.this, MainActivity.class);
            }
        });
    }

    public void onRegister() {
        // check password
        // hash password
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String type = "register";

        backgroundWorker backgroundWorker = new backgroundWorker(this);
        backgroundWorker.execute(type, username, password);

    }
}
