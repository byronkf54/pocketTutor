package com.example.pockettutor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class LessonPicker extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_picker);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final STRAdapter adapter = new STRAdapter();
        recyclerView.setAdapter(adapter);

        STRViewModel strViewModel = new ViewModelProvider(this).get(STRViewModel.class);
        strViewModel.getAllData().observe(this, new Observer<List<STRDB>>() {
            @Override
            public void onChanged(List<STRDB> strdbs) {
                adapter.setData(strdbs);
            }
        });
    }
}
