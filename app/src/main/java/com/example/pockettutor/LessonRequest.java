package com.example.pockettutor;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.util.Objects;


public class LessonRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_request);

        final EditText subjectForm = findViewById(R.id.SubjectForm);
        final EditText topicForm = findViewById(R.id.TopicForm);
        Button ELS = findViewById(R.id.EnterLessonSpace);
        ELS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                String subjectString = subjectForm.getText().toString();
                String topicString = topicForm.getText().toString();
                addData(subjectString, topicString);
            }
        });
    }


    public void addData(String sub, String top) {
        backgroundWorker background_worker = new backgroundWorker(this);
        UserLocalStore ULS = new UserLocalStore(this);
        String UID = ULS.getLoggedInUser();
        background_worker.execute("subtopreq", UID, sub, top, "2");
    }


    public void logout(View view) {
        UserLocalStore ULS = new UserLocalStore(getApplicationContext());
        ULS.clearUserData();
        Intent loginIntent = new Intent(LessonRequest.this, studentLogin.class);
        startActivity(loginIntent);
    }


    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) Objects.requireNonNull(getSystemService(Context.INPUT_METHOD_SERVICE))).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}
