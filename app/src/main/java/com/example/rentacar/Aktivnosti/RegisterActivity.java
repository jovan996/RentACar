package com.example.rentacar.Aktivnosti;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rentacar.R;

public class RegisterActivity extends AppCompatActivity {

    private int id;

    private EditText ime;

    private EditText prezime;

    private EditText email;

    private EditText brojTelefona;

    private EditText jmbg;

    private EditText lozinka;

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
