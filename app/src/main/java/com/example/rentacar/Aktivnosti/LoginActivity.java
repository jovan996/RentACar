package com.example.rentacar.Aktivnosti;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rentacar.BazaPodataka.DatabaseHelper;
import com.example.rentacar.R;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {

    private int id;

    private EditText email;

    private EditText lozinka;

    private Button submitLogin;

    private TextView greske;

    private DatabaseHelper db;

    //@Nullable
    //@BindView(R.id.detailToolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Prijava");*/

        toolbar = (Toolbar) findViewById(R.id.loginToolbar);
        toolbar.setTitle("Prijava");
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        email = (EditText) findViewById(R.id.emailUnosLogin);
        lozinka = (EditText) findViewById(R.id.lozinkaUnosLogin);
        submitLogin = (Button) findViewById(R.id.prijava);
        greske = (TextView) findViewById(R.id.loginGreske);

        submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String lozinkaText = lozinka.getText().toString();

                String rezultat = db.prijava(emailText, lozinkaText);

                if (rezultat.length() == 0) {
                    Intent intent = new Intent(LoginActivity.this, MasterViewActivity.class);
                    LoginActivity.this.startActivity(intent);
                }
                else {
                    greske.setText("Pogresan format:" + rezultat);
                }
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MasterViewActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EditText getEmail() {
        return email;
    }

    public void setEmail(EditText email) {
        this.email = email;
    }

    public EditText getLozinka() {
        return lozinka;
    }

    public void setLozinka(EditText lozinka) {
        this.lozinka = lozinka;
    }

    public Button getSubmitLogin() {
        return submitLogin;
    }

    public void setSubmitLogin(Button submitLogin) {
        this.submitLogin = submitLogin;
    }
}
