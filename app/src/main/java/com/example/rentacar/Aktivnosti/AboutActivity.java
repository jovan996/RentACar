package com.example.rentacar.Aktivnosti;

import android.widget.ListView;
import android.widget.TextView;

public class AboutActivity {

    private TextView appIme;

    private TextView appVerzija;

    private TextView datumInstalacije;

    private TextView appMemorija;

    private ListView appDozvole;

    public AboutActivity(){

    }

    public AboutActivity(TextView appIme, TextView appVerzija, TextView datumInstalacije, TextView appMemorija, ListView appDozvole) {
        this.appIme = appIme;
        this.appVerzija = appVerzija;
        this.datumInstalacije = datumInstalacije;
        this.appMemorija = appMemorija;
        this.appDozvole = appDozvole;
    }

    public TextView getAppIme() {
        return appIme;
    }

    public void setAppIme(TextView appIme) {
        this.appIme = appIme;
    }

    public TextView getAppVerzija() {
        return appVerzija;
    }

    public void setAppVerzija(TextView appVerzija) {
        this.appVerzija = appVerzija;
    }

    public TextView getDatumInstalacije() {
        return datumInstalacije;
    }

    public void setDatumInstalacije(TextView datumInstalacije) {
        this.datumInstalacije = datumInstalacije;
    }

    public TextView getAppMemorija() {
        return appMemorija;
    }

    public void setAppMemorija(TextView appMemorija) {
        this.appMemorija = appMemorija;
    }

    public ListView getAppDozvole() {
        return appDozvole;
    }

    public void setAppDozvole(ListView appDozvole) {
        this.appDozvole = appDozvole;
    }
}
