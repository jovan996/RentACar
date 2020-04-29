package com.example.rentacar.utils;

import java.util.Date;

public class Datum {

    public static final long BROJ_MILISEKUNDI_U_DANU = 1000 * 60 * 60 * 24;

    public static int getBrojDana(Date datumUzimanja, Date datumVracanja) {
        return (int) ((datumVracanja.getTime() - datumUzimanja.getTime()) / BROJ_MILISEKUNDI_U_DANU);
    }
}
