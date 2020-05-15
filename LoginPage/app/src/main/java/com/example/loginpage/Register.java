package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    }

    public void Login(View view) {
        Intent RegisterIntent = new Intent(Register.this, MainActivity.class);
        startActivity(RegisterIntent);
    }


    public void onRegister(View view) {
        // encrypt
        AES aes = new AES();
        String username = usernameET.getText().toString();
        String UID = aes.encrypt(username);
        String passwordAES = aes.encrypt(passwordET.getText().toString());
        String confAES = aes.encrypt(confirm_passwordET.getText().toString());


        if (!passwordAES.equals(confAES)) {
            Toast toast = Toast.makeText(this, "Those passwords don't match, please try again.", Toast.LENGTH_SHORT);
            toast.show();
            Intent RegisterIntent = new Intent(Register.this, MainActivity.class);
            startActivity(RegisterIntent);

        }
        else if (passwordAES.length() < 8) {
            Toast toast = Toast.makeText(this, "That password is not long enough, please try again.", Toast.LENGTH_SHORT);
            toast.show();
            Intent RegisterIntent = new Intent(Register.this, MainActivity.class);
            startActivity(RegisterIntent);
        }
        else {
            String type = "register";
            backgroundWorker backgroundWorker = new backgroundWorker(this);
            backgroundWorker.execute(type, UID, username, passwordAES);
        }

    }
}
