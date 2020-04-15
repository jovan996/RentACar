package com.example.rentacar.Aktivnosti;

import android.widget.Button;
import android.widget.EditText;

public class LoginActivity {

    private int id;

    private EditText email;

    private EditText lozinka;

    private Button submit;

    public LoginActivity(){

    }

    public LoginActivity(EditText email, EditText lozinka, Button submit) {
        this.email = email;
        this.lozinka = lozinka;
        this.submit = submit;
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

    public Button getSubmit() {
        return submit;
    }

    public void setSubmit(Button submit) {
        this.submit = submit;
    }
}
