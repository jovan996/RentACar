package com.example.rentacar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private static Session instanca;
    private SharedPreferences prefs;

    private Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public static Session getInstance(Context cntx) {
        if (instanca == null) {
            instanca = new Session(cntx);
        }
        return instanca;
    }

    public void setKorisnikId(String id) {
        prefs.edit().putString("korisnik_id", id).commit();
    }

    public String getKorisnikId() {
        return prefs.getString("korisnik_id","");
    }

    public void destroy() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("korisnik_id");
        editor.apply();
    }

}
