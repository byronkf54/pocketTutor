package com.example.pockettutor;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface STRDao {

    @Insert
    void insert(STRDB db);

    @Update
    void update(STRDB db);

    @Delete
    void delete(STRDB db);

    @Query("SELECT * FROM SubTopReq ORDER BY ID DESC")
    LiveData<List<STRDB>> getAllNodes();
}
