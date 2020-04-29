package com.example.rentacar.utils;

import java.util.regex.Pattern;

public class Validation {

    public static boolean valIme(String ime) {
        return Pattern.matches("[A-Z][a-z]{3,32}", ime);
    }

    public static boolean valPrezime(String prezime) {
        return Pattern.matches("[A-Z][a-z]{3,32}", prezime);
    }

    public static boolean valEmail(String email) {
        return Pattern.matches("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$", email);
    }

    public static boolean valBrojTelefona(String brojTelefona) {
        return Pattern.matches("[0-9]{3}-[0-9]{3}-[0-9]{3,4}", brojTelefona);
    }

    public static boolean valJMBG(String jmbg) {
        return Pattern.matches("[0-3][0-9][0-1][0-2][0-9]{9}", jmbg);
    }

    public static boolean valLozinka(String lozinka) {
        return Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", lozinka);
    }
}