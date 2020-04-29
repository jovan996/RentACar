package com.example.rentacar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setKorisnikId(String id) {
        prefs.edit().putString("id", id).commit();
    }

    public String getKorisnikId() {
        return prefs.getString("id","");
    }
}
