package com.example.pockettutor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SubTopReq")

public class STRDB {

    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String SUBJECT;
    private String TOPIC;

    public STRDB(String SUBJECT, String TOPIC) {
        this.SUBJECT = SUBJECT;
        this.TOPIC = TOPIC;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getSUBJECT() {
        return SUBJECT;
    }

    public String getTOPIC() {
        return TOPIC;
    }
}
