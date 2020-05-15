package com.example.pockettutor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class STRViewModel extends AndroidViewModel {
    private STRRepository repository;
    private LiveData<List<STRDB>> allData;

    public STRViewModel(@NonNull Application application) {
        super(application);
        repository = new STRRepository(application);
        allData = repository.getAllData();
    }

    public void insert(STRDB strdb) {
        repository.insert(strdb);
    }

    public void update(STRDB strdb) {
        repository.insert(strdb);
    }

    public void delete(STRDB strdb) {
        repository.insert(strdb);
    }

    public LiveData<List<STRDB>> getAllData() {
        return allData;
    }
}
