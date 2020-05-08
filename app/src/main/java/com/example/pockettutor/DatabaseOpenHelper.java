package com.example.pockettutor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.Nullable;

import static android.content.ContentValues.TAG;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "pocketTutor.db";
    public static final String TABLE_NAME = "SubTopReq";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "UID";
    public static final String COL_3 = "SUBJECT";
    public static final String COL_4 = "TOPIC";

    public DatabaseOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    /*
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, UID STRING, SUBJECT STRING, TOPIC STRING)");

    }
    */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addData (String UID, String subject, String topic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, UID);
        contentValues.put(COL_3, subject);
        contentValues.put(COL_4, topic);
        Log.d(TAG, "addData: Adding " + subject + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }
}
