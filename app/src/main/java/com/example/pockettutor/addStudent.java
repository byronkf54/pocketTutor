package com.example.pockettutor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class addStudent extends AppCompatActivity {
    EditText UsernameET;
    EditText PasswordET;
    EditText confirm_passwordET;
    Button create_account;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        UsernameET = findViewById(R.id.username);
        PasswordET = findViewById(R.id.password);
        confirm_passwordET = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register);
        create_account = findViewById(R.id.create_account);
    }

    public void Login(View view) {
        Intent RegisterIntent = new Intent(addStudent.this, studentLogin.class);
        startActivity(RegisterIntent);
    }


    public void onRegister(View view) {
        // encrypt
        AES aes = new AES();
        String username = UsernameET.getText().toString();
        String UID = aes.encrypt(username);
        String passwordAES = aes.encrypt(PasswordET.getText().toString());
        String confAES = aes.encrypt(confirm_passwordET.getText().toString());


        if (!passwordAES.equals(confAES)) {
            Toast toast = Toast.makeText(this, "Those passwords don't match, please try again.", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(getIntent());

        }
        else if (PasswordET.getText().toString().length() < 8) {
            Toast toast = Toast.makeText(this, "That password is not long enough, please try again.", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(getIntent());
        }
        else {
            String type = "addStudent";
            backgroundWorker background_worker = new backgroundWorker(this);
            background_worker.execute(type, UID, username, passwordAES, "0");
            PasswordET.clearComposingText();
        }
    }
}
