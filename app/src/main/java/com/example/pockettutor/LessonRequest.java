package com.example.pockettutor;

import android.content.Context;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class LessonRequest extends AppCompatActivity {
	Database myDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lesson_request);

		myDB = new Database(this);

		final TextInputLayout subjectForm = findViewById(R.id.SubjectForm);
		final TextInputLayout topicForm = findViewById(R.id.TopicForm);
		Button ELS = findViewById(R.id.EnterLessonSpace);
		ELS.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				hideKeyboard();
				String subjectString = subjectForm.getEditText().getText().toString();
				String topicString = topicForm.getEditText().getText().toString();
				boolean result = myDB.addData( "akj43n1239", subjectString, topicString);
				saveDatabase();
				if(result) {
					toastMessage("Data Successfully Inserted!");
				}
				else {
					toastMessage("Something went wrong");
				}
			}
		});
	}

	private void saveDatabase() {
		try {
			File sd = Environment.getExternalStorageDirectory();

			if (sd.canWrite()) {
				String currentDBPath = "\\data\\data\\" + getPackageName() + "\\databases\\pocketTutor.db";
				String backupDBPath = "C:\\Users\\byron\\Desktop\\Programming\\Database\\backupname.db";
				File currentDB = new File(currentDBPath);
				File backupDB = new File(sd, backupDBPath);

				if (currentDB.exists()) {
					FileChannel src = new FileInputStream(currentDB).getChannel();
					FileChannel dst = new FileOutputStream(backupDB).getChannel();
					dst.transferFrom(src, 0, src.size());
					src.close();
					dst.close();
				}
			}
		} catch (Exception e) {

		}
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
