package com.example.pockettutor;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button StudentButton = findViewById(R.id.StudentButton);
        StudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    UserLocalStore ULS = new UserLocalStore(getApplicationContext());
                    String UID = ULS.getLoggedInUser();
                    if (UID.length() > 8) {
                        System.out.println("~~~UID~~~: " + UID);
                        startActivity(new Intent(MainActivity.this, LessonRequest.class));
                    } else {
                        startActivity(new Intent(MainActivity.this, studentLogin.class));
                    }
                } catch (Exception e) {
                    startActivity(new Intent(MainActivity.this, studentLogin.class));
                }
            }
        });

        Button TutorButton = findViewById(R.id.TutorButton);
        TutorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    UserLocalStore ULS = new UserLocalStore(getApplicationContext());
                    String UID = ULS.getLoggedInUser();
                    if (UID.length() > 0) {
                        System.out.println("~~~UID~~~: " + UID);
                        startActivity(new Intent(MainActivity.this, LessonDisplay.class));
                    } else {
                        startActivity(new Intent(MainActivity.this, tutorLogin.class));
                    }
                } catch (Exception e) {
                    startActivity(new Intent(MainActivity.this, tutorLogin.class));
                }

            }
        });

    }
}
