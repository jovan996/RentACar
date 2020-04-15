package com.example.rentacar.Aktivnosti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.rentacar.R;

public class AboutActivity extends AppCompatActivity {

    private TextView appIme;

    private TextView appVerzija;

    private TextView datumInstalacije;

    private TextView appMemorija;

    private ListView appDozvole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
