package com.example.pockettutor;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = STRDB.class, version = 1)
public abstract class DB extends RoomDatabase {
    private static DB instance;

    public abstract STRDao strDao();

    public static synchronized DB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DB.class, "SubTopReq")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
