package com.example.pockettutor;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;


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
			}
		});
	}


	private void toastMessage(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}


	private void hideKeyboard() {
		View view = getCurrentFocus();
		if (view != null) {
			((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
					hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}



}
