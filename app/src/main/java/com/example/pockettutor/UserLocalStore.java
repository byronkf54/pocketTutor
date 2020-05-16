package com.example.pockettutor;

import android.content.Context;
import android.content.SharedPreferences;

public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore (Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeData(String UID) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("UID", UID);
        spEditor.apply();
    }

    public String getLoggedInUser() {
        return userLocalDatabase.getString("UID", "");
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.apply();
    }
}
