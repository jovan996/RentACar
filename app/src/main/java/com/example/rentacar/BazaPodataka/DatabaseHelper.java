package com.example.rentacar.BazaPodataka;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import com.example.rentacar.Modeli.AutomobilItemModel;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rentcar.db";
    private static final String AUTOMOBIL_TABLE = "automobil";
    private static final String KORISNIK_TABLE = "korisnik";
    private static final String FIRMA_TABLE = "firma";
    private static final String SLIKA_TABLE = "slika";
    private static final String FIRMA_AUTOMOBIL_TABLE = "firma_automobil";
    private static final String EVIDENCIJA_TABLE = "evidencija";
    private static final String KOMENTAR_TABLE = "komentar";
    private static final String OCJENA_TABLE = "ocjena";
    private static final String OMILJENI_TABLE = "omiljeni";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String createAutomobil = "CREATE TABLE " + AUTOMOBIL_TABLE + " (AUTOMOBIL_ID integer PRIMARY KEY AUTOINCREMENT not null, AUTOMOBIL_MARKA varchar(50) not null," +
                "AUTOMOBIL_MODEL varchar(50) not null, AUTOMOBIL_BROJ_SJEDISTA integer not null, AUTOMOBIL_BROJ_VRATA integer not null," +
                "AUTOMOBIL_KUBIKAZA integer not null, AUTOMOBIL_MOTOR VARCHAR(50), AUTOMOBIL_SNAGA_MOTORA integer not null);";

        String createIndexAutomobil = "create unique index AUTOMOBIL_PK on " + AUTOMOBIL_TABLE + " (\n" +
                "AUTOMOBIL_ID ASC);";

        String createKorisnik =  "CREATE TABLE " + KORISNIK_TABLE + " (KORISNIK_ID integer PRIMARY KEY AUTOINCREMENT not null, KORISNIK_IME varchar(50) not null," +
                "   KORISNIK_PREZIME     varchar(50)                    not null,\n" +
                "   KORISNIK_EMAIL       varchar(50)                    not null,\n" +
                "   KORISNIK_BR_TEL      varchar(20)                    not null,\n" +
                "   KORISNIK_JMBG        varchar(13)                    not null,\n" +
                "   KORISNIK_LOZINKA     varchar(100)                   not null,\n" +
                "   KORISNIK_SALT        varchar(50)                    not null\n" +
                ");";

        String createIndexKorisnik = "create unique index KORISNIK_PK on " + KORISNIK_TABLE + " (\n" +
                "KORISNIK_ID ASC);";

        String createFirma = "CREATE TABLE " + FIRMA_TABLE + "(FIRMA_ID integer PRIMARY KEY AUTOINCREMENT not null,\n" +
                "   FIRMA_IME            varchar(50)                    not null,\n" +
                "   FIRMA_PIB            varchar(30)                    not null,\n" +
                "   FIRMA_OPIS           text                   not null,\n" +
                "   FIRMA_ADRESA         varchar(50)                    not null,\n" +
                "   FIRMA_GRAD           varchar(50)                    not null,\n" +
                "   FIRMA_EMAIL          varchar(50)                    not null,\n" +
                "   FIRMA_BROJ_TELEFONA  varchar(20)                    not null,\n" +
                "   FIRMA_LAT            float                          not null,\n" +
                "   FIRMA_LONG           float                          not null\n" +
                ");";

        String createIndexFirma = "create unique index FIRMA_PK on " + FIRMA_TABLE + " (\n" +
                "FIRMA_ID ASC\n" +
                ");";

        String createSlika =  "CREATE TABLE " + SLIKA_TABLE + "(SLIKA_ID integer PRIMARY KEY AUTOINCREMENT not null,\n" +
                "   FA_ID integer not null,\n" +
                "   SLIKA_PUTANJA text not null,\n" +
                "CONSTRAINT fk_fa\n" +
                "    FOREIGN KEY (FA_ID)\n" +
                "    REFERENCES " + FIRMA_AUTOMOBIL_TABLE + "(FA_ID));"; //promijeniti auto -> firma_auto

        String createIndexSlika = "create unique index SLIKA_PK on " + SLIKA_TABLE + " (\n" +
                "SLIKA_ID ASC\n" +
                ");";

        String createFirmaAutomobil = "CREATE TABLE " + FIRMA_AUTOMOBIL_TABLE + "(\n" +
                "   FA_ID                integer PRIMARY KEY AUTOINCREMENT                      not null,\n" +
                "   AUTOMOBIL_ID         integer                        not null,\n" +
                "   FIRMA_ID             integer                        not null,\n" +
                "   POLISA_OSIGURANJA    integer                        not null,\n" +
                "   CENA_PO_DANU         integer                        not null,\n" +
                "   STATUS               smallint                       not null,\n" +
                "   BOJA                 varchar(20)                    not null,\n" +
                "   GODISTE              integer                        not null,\n" +
                "   KILOMETRAZA          integer                        not null,\n" +
                " FOREIGN KEY (AUTOMOBIL_ID)\n" +
                " REFERENCES " + AUTOMOBIL_TABLE + "(AUTOMOBIL_ID)," +
                "FOREIGN KEY (FIRMA_ID)\n" +
                "REFERENCES " + FIRMA_TABLE + "(FIRMA_ID));";

        String createIndexFirmaAutomobil = "create unique index FIRMA_AUTO_PK on " + FIRMA_AUTOMOBIL_TABLE + " (\n" +
                "FA_ID ASC\n" +
                ");";

        String createEvidencija = "CREATE TABLE " + EVIDENCIJA_TABLE + "(\n" +
                "   EVIDENCIJA_ID        integer PRIMARY KEY AUTOINCREMENT                       not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   AUTOMOBIL_ID         integer                        not null,\n" +
                "   EVIDENCIJA_DATUM     date                           not null,\n" +
                "   DATUM_UZIMANJA       date                           not null,\n" +
                "   DATUM_VRACANJA       date                           not null,\n" +
                "   FOREIGN KEY (KORISNIK_ID)\n" +
                "   REFERENCES " + KORISNIK_TABLE + "(KORISNIK_ID)," +
                "   FOREIGN KEY (AUTOMOBIL_ID)\n" +
                "   REFERENCES " + AUTOMOBIL_TABLE + "(AUTOMOBIL_ID));";

        String createIndexEvidencija = "create unique index EVIDENCIJA_PK on " + EVIDENCIJA_TABLE + " (\n" +
                "EVIDENCIJA_ID ASC\n" +
                ");";

        String createKomentar = "CREATE TABLE " + KOMENTAR_TABLE + "(\n" +
                "   KOMENTAR_ID          integer PRIMARY KEY AUTOINCREMENT                        not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   FA_ID                integer                        not null,\n" +
                "   KOMENTAR_NASLOV      varchar(100)                   not null,\n" +
                "   KOMENTAR_TEKST       text                   not null,\n" +
                "   FOREIGN KEY (KORISNIK_ID)\n" +
                "   REFERENCES " + KORISNIK_TABLE + "(KORISNIK_ID)," +
                "   FOREIGN KEY (FA_ID)\n" +
                "   REFERENCES " + FIRMA_AUTOMOBIL_TABLE + "(FA_ID)" +
                ");\n";

        String createIndexKOmentar = "create unique index KOMENTAR_PK on " + KOMENTAR_TABLE + " (\n" +
                "KOMENTAR_ID ASC\n" +
                ");";

        String createOcjena = "CREATE TABLE " + OCJENA_TABLE + "(\n" +
                "   OCJENA_ID             integer PRIMARY KEY AUTOINCREMENT                      not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   FA_ID                integer                        not null,\n" +
                "   OCJENA_BROJ           integer                       not null,\n" +
                "   FOREIGN KEY (KORISNIK_ID)\n" +
                "   REFERENCES " + KORISNIK_TABLE + "(KORISNIK_ID)," +
                "   FOREIGN KEY (FA_ID)\n" +
                "   REFERENCES " + FIRMA_AUTOMOBIL_TABLE + "(FA_ID)" +
                ");\n";

        String createIndexOcjena = "create unique index OCJENA_PK on " + OCJENA_TABLE + " (\n" +
                "OCJENA_ID ASC\n" +
                ");";

        String createOmiljeni = "CREATE TABLE " + OMILJENI_TABLE + "(\n" +
                "   OMILJENI_ID          integer PRIMARY KEY AUTOINCREMENT                       not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   FA_ID                integer                        not null,\n" +
                "   FOREIGN KEY (KORISNIK_ID)\n" +
                "   REFERENCES " + KORISNIK_TABLE + "(KORISNIK_ID)," +
                "   FOREIGN KEY (FA_ID)\n" +
                "   REFERENCES " + FIRMA_AUTOMOBIL_TABLE + "(FA_ID)" +
                ");\n";

        String createIndexOmiljeni = "create unique index OMILJENI_PK on " + OMILJENI_TABLE + " (\n" +
                "OMILJENI_ID ASC\n" +
                ");";

        String relationship1 = "create index RELATIONSHIP_1_FK on " + EVIDENCIJA_TABLE + " (\n" +
                "KORISNIK_ID ASC\n" +
                ");";

        String relationship2 = "create index RELATIONSHIP_2_FK on " + EVIDENCIJA_TABLE + " (\n" +
                "AUTOMOBIL_ID ASC\n" +
                ");";

        String relationship3 = "create index RELATIONSHIP_3_FK on " + FIRMA_AUTOMOBIL_TABLE + " (\n" +
                "AUTOMOBIL_ID ASC\n" +
                ");";

        String relationship4 = "create index RELATIONSHIP_4_FK on " + FIRMA_AUTOMOBIL_TABLE + " (\n" +
                "FIRMA_ID ASC\n" +
                ");";

        String relationship6 = "create index RELATIONSHIP_6_FK on " + KOMENTAR_TABLE + " (\n" +
                "KORISNIK_ID ASC\n" +
                ");";

        String relationship7 = "create index RELATIONSHIP_7_FK on " + KOMENTAR_TABLE + " (\n" +
                "FA_ID ASC\n" +
                ");";

        String relationship8 = "create index RELATIONSHIP_8_FK on " + OCJENA_TABLE + " (\n" +
                "KORISNIK_ID ASC\n" +
                ");";

        String relationship9 = "create index RELATIONSHIP_9_FK on " + OCJENA_TABLE + " (\n" +
                "FA_ID ASC\n" +
                ");\n";

        String relationship10 = "create index RELATIONSHIP_10_FK on " + OMILJENI_TABLE + " (\n" +
                "KORISNIK_ID ASC\n" +
                ");";

        String relationship11 = "create index RELATIONSHIP_11_FK on " + OMILJENI_TABLE + " (\n" +
                "FA_ID ASC\n" +
                ");";

        String relationship5 = "create index RELATIONSHIP_5_FK on " + SLIKA_TABLE + " (\n" +
                "FA_ID ASC\n" + //promeni fa_id
                ");\n";


        db.execSQL(createAutomobil);
        db.execSQL(createIndexAutomobil);
        db.execSQL(createEvidencija);
        db.execSQL(createIndexEvidencija);
        db.execSQL(relationship1);
        db.execSQL(relationship2);
        db.execSQL(createFirma);
        db.execSQL(createIndexFirma);
        db.execSQL(createFirmaAutomobil);
        db.execSQL(createIndexFirmaAutomobil);
        db.execSQL(relationship3);
        db.execSQL(relationship4);
        db.execSQL(createKomentar);
        db.execSQL(createIndexKOmentar);
        db.execSQL(relationship6);
        db.execSQL(relationship7);
        db.execSQL(createKorisnik);
        db.execSQL(createIndexKorisnik);
        db.execSQL(createOcjena);
        db.execSQL(createIndexOcjena);
        db.execSQL(relationship8);
        db.execSQL(relationship9);
        db.execSQL(createOmiljeni);
        db.execSQL(createIndexOmiljeni);
        db.execSQL(relationship10);
        db.execSQL(relationship11);
        db.execSQL(createSlika);
        db.execSQL(createIndexSlika);
        db.execSQL(relationship5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AUTOMOBIL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + KORISNIK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FIRMA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FIRMA_AUTOMOBIL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + KOMENTAR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OCJENA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OMILJENI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EVIDENCIJA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SLIKA_TABLE);
        onCreate(db);
    }

    /*public boolean insertAutomobil(String marka, String model, int brojSedista, int brojVrata, int kubikaza, String motor, int snaga) {
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();

        ContentValues vrednosti = new ContentValues();
        vrednosti.put("AUTOMOBIL_MARKA", marka);
        vrednosti.put("AUTOMOBIL_MODEL", model);
        vrednosti.put("AUTOMOBIL_BROJ_SJEDISTA", brojSedista);
        vrednosti.put("AUTOMOBIL_BROJ_VRATA", brojVrata);
        vrednosti.put("AUTOMOBIL_KUBIKAZA", kubikaza);
        vrednosti.put("AUTOMOBIL_MOTOR", motor);
        vrednosti.put("AUTOMOBIL_SNAGA_MOTORA", snaga);

        sqLiteDb.insert(AUTOMOBIL_TABLE, null, vrednosti);
        return true;
    }*/

    public ArrayList<AutomobilItemModel> getAutomobili() {
        SQLiteDatabase sqLiteDb = this.getReadableDatabase();
        ArrayList<AutomobilItemModel> listaAutomobila = new ArrayList<>();
        Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM " + AUTOMOBIL_TABLE + " as a INNER JOIN " + FIRMA_AUTOMOBIL_TABLE + " as fa ON a.AUTOMOBIL_ID = fa.AUTOMOBIL_ID" +
                " INNER JOIN " + SLIKA_TABLE + " as s ON fa.FA_ID = s.FA_ID WHERE fa.STATUS = 0;", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("FA_ID"));
                String marka = cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MARKA"));
                String model = cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MODEL"));
                String slikaPutanja = cursor.getString(cursor.getColumnIndex("SLIKA_PUTANJA"));
                int cijenaPoDanu = cursor.getInt(cursor.getColumnIndex("CENA_PO_DANU"));
                listaAutomobila.add(new AutomobilItemModel(id, marka, model, cijenaPoDanu, slikaPutanja));
            } while (cursor.moveToNext());
        }

        return listaAutomobila;
    }

    public ArrayList<String> getDetailSlike(int id) {
        SQLiteDatabase sqLiteDb = this.getReadableDatabase();
        ArrayList<String> listaSlika = new ArrayList<>();
        Cursor cursor = sqLiteDb.rawQuery("SELECT SLIKA_PUTANJA FROM " + FIRMA_AUTOMOBIL_TABLE + " as fa INNER JOIN "
                + SLIKA_TABLE + " as s ON fa.FA_ID = s.FA_ID WHERE fa.FA_ID = " + id + ";", null);

        if (cursor.moveToFirst()) {
            do {
                listaSlika.add(cursor.getString(cursor.getColumnIndex("SLIKA_PUTANJA")));
            } while (cursor.moveToNext());
        }

        return listaSlika;
    }

    public HashMap<String, String> getDetails(int id) {
        SQLiteDatabase sqLiteDb = this.getReadableDatabase();
        HashMap<String, String> podaci = new HashMap<>();
        Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM " + FIRMA_AUTOMOBIL_TABLE + " as fa INNER JOIN "
                + AUTOMOBIL_TABLE + " as a ON fa.AUTOMOBIL_ID = a.AUTOMOBIL_ID WHERE fa.FA_ID = " + id + ";", null);

        if (cursor.moveToFirst()) {
            do {
                podaci.put("naslov", cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MARKA")) + " " + cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MODEL")));
                podaci.put("broj_sjedista", cursor.getString(cursor.getColumnIndex("AUTOMOBIL_BROJ_SJEDISTA")));
                podaci.put("broj_vrata", cursor.getString(cursor.getColumnIndex("AUTOMOBIL_BROJ_VRATA")));
                podaci.put("broj_kubikaza", cursor.getString(cursor.getColumnIndex("AUTOMOBIL_KUBIKAZA")));
                podaci.put("tip_motora", cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MOTORA")));
                podaci.put("snaga_motora", cursor.getString(cursor.getColumnIndex("AUTOMOBIL_SNAGA_MOTORA")));
                podaci.put("boja", cursor.getString(cursor.getColumnIndex("BOJA")));
                podaci.put("godiste", cursor.getString(cursor.getColumnIndex("GODISTE")));
                podaci.put("kilometraza", cursor.getString(cursor.getColumnIndex("KILOMETRAZA")));
                podaci.put("cijena", cursor.getString(cursor.getColumnIndex("CENA_PO_DANU")));
            } while (cursor.moveToNext());
        }

        return podaci;
    }

    public void obrisiBazu(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

}
