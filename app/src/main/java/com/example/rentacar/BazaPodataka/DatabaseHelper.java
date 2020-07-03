package com.example.rentacar.BazaPodataka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import com.example.rentacar.Modeli.AutomobilItemModel;
import com.example.rentacar.Modeli.KomentarDTO;
import com.example.rentacar.Modeli.KomentarItemModel;
import com.example.rentacar.Modeli.FirmaModel;
import com.example.rentacar.Modeli.KomentarModel;
import com.example.rentacar.Modeli.OcjenaModel;
import com.example.rentacar.utils.Hesiranje;
import com.example.rentacar.utils.Session;
import com.example.rentacar.utils.Validation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

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

    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String createAutomobil = "CREATE TABLE " + AUTOMOBIL_TABLE + " (AUTOMOBIL_ID integer PRIMARY KEY AUTOINCREMENT not null, " +
                "AUTOMOBIL_MARKA varchar(50) not null," +
                "AUTOMOBIL_MODEL varchar(50) not null, " +
                "AUTOMOBIL_BROJ_SJEDISTA integer not null, " +
                "AUTOMOBIL_BROJ_VRATA integer not null," +
                "AUTOMOBIL_KUBIKAZA integer not null, " +
                "AUTOMOBIL_MOTOR VARCHAR(50), " +
                "AUTOMOBIL_SNAGA_MOTORA integer not null);";

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
                "   FIRMA_OPIS           varchar(50)                   not null,\n" +
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
                "   FA_ID         integer                        not null,\n" +
                "   EVIDENCIJA_DATUM     date                           not null,\n" +
                "   DATUM_UZIMANJA       date                           not null,\n" +
                "   DATUM_VRACANJA       date                           not null,\n" +
                "   FOREIGN KEY (KORISNIK_ID)\n" +
                "   REFERENCES " + KORISNIK_TABLE + "(KORISNIK_ID)," +
                "   FOREIGN KEY (FA_ID)\n" +
                "   REFERENCES " + FIRMA_AUTOMOBIL_TABLE + "(FA_ID));";

        String createIndexEvidencija = "create unique index EVIDENCIJA_PK on " + EVIDENCIJA_TABLE + " (\n" +
                "EVIDENCIJA_ID ASC\n" +
                ");";

        String createKomentar = "CREATE TABLE " + KOMENTAR_TABLE + "(\n" +
                "   KOMENTAR_ID          integer PRIMARY KEY AUTOINCREMENT                        not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   FA_ID                integer                        not null,\n" +
                "   KOMENTAR_NASLOV      varchar(100)                   not null,\n" +
                "   KOMENTAR_TEKST       varchar(100)                   not null,\n" +
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
                "FA_ID ASC\n" +
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
    public FirmaModel getFirmaById(int id){
        SQLiteDatabase sqLiteDb = this.getReadableDatabase();

        Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM " + FIRMA_TABLE + " WHERE FIRMA_ID = "+ id+ ";",null);
        FirmaModel fm= new FirmaModel();
        if (cursor.moveToFirst()) {
                fm.setId( cursor.getInt(cursor.getColumnIndex("FIRMA_ID")));
                fm.setAdresa( cursor.getString(cursor.getColumnIndex("FIRMA_ADRESA")));
                fm.setBrojTelefona( cursor.getInt(cursor.getColumnIndex("FIRMA_BROJ_TELEFONA")));
                fm.setEmail( cursor.getString(cursor.getColumnIndex("FIRMA_EMAIL")));
                fm.setGrad( cursor.getString(cursor.getColumnIndex("FIRMA_GRAD")));
                fm.setIme( cursor.getString(cursor.getColumnIndex("FIRMA_IME")));
                fm.setOpis( cursor.getString(cursor.getColumnIndex("FIRMA_OPIS")));
                fm.setPib(cursor.getInt(cursor.getColumnIndex("FIRMA_PIB")));
                fm.setMapaLat(cursor.getInt(cursor.getColumnIndex("FIRMA_LAT")));
                fm.setMapaLong(cursor.getInt(cursor.getColumnIndex("FIRMA_LONG")));
        }
        return fm;
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
                podaci.put("tip_motora", cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MOTOR")));
                podaci.put("snaga_motora", cursor.getString(cursor.getColumnIndex("AUTOMOBIL_SNAGA_MOTORA")));
                podaci.put("boja", cursor.getString(cursor.getColumnIndex("BOJA")));
                podaci.put("godiste", cursor.getString(cursor.getColumnIndex("GODISTE")));
                podaci.put("kilometraza", cursor.getString(cursor.getColumnIndex("KILOMETRAZA")));
                podaci.put("cijena", cursor.getString(cursor.getColumnIndex("CENA_PO_DANU")));
            } while (cursor.moveToNext());
        }

        return podaci;
    }
    public ArrayList<KomentarDTO> getSviKomentari() {
        SQLiteDatabase sqLiteDb = this.getReadableDatabase();
        ArrayList<KomentarDTO> listaKomentara = new ArrayList<>();

        Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM " + KOMENTAR_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("KOMENTAR_ID"));
                int fa_id = cursor.getInt(cursor.getColumnIndex("FA_ID"));
                String komNslov = cursor.getString(cursor.getColumnIndex("KOMENTAR_NASLOV"));
                String komTekst = cursor.getString(cursor.getColumnIndex("KOMENTAR_TEKST"));
                int korId = cursor.getInt(cursor.getColumnIndex("KOMENTAR_ID"));


                listaKomentara.add(new KomentarDTO(id,korId,fa_id,komTekst,komNslov));
            } while (cursor.moveToNext());
        }

        return listaKomentara;
    }

    public ArrayList<KomentarItemModel> getKomentari(int faId) {
        SQLiteDatabase sqLiteDb = this.getReadableDatabase();
        ArrayList<KomentarItemModel> listaKomentara = new ArrayList<>();
        //Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM " + AUTOMOBIL_TABLE + " as a INNER JOIN " + FIRMA_AUTOMOBIL_TABLE + " as fa ON a.AUTOMOBIL_ID = fa.AUTOMOBIL_ID" +
              //  " INNER JOIN " + SLIKA_TABLE + " as s ON fa.FA_ID = s.FA_ID WHERE fa.STATUS = 0;", null);
        Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM " + KOMENTAR_TABLE + " WHERE FA_ID = '" + faId + "'", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("FA_ID"));
                String komNslov = cursor.getString(cursor.getColumnIndex("KOMENTAR_NASLOV"));
                String komTekst = cursor.getString(cursor.getColumnIndex("KOMENTAR_TEKST"));
                //String slikaPutanja = cursor.getString(cursor.getColumnIndex("SLIKA_PUTANJA"));
                //int cijenaPoDanu = cursor.getInt(cursor.getColumnIndex("CENA_PO_DANU"));
                String ime = "Marko";
                String prezime = "Maric";
                String parts[] = komNslov.split(" ");
                if(parts[0] != null && parts[1] != null){
                    ime = parts[0];
                    prezime = parts[1];
                }

                listaKomentara.add(new KomentarItemModel(ime, prezime, komTekst, new Date()));
            } while (cursor.moveToNext());
        }

        return listaKomentara;
    }


    public boolean provjeriDaLiJeIznajmio(int korisnikId, int faId) throws ParseException {
        boolean brojac = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT KORISNIK_ID, FA_ID " +
                "FROM " + EVIDENCIJA_TABLE + " WHERE KORISNIK_ID = '" + korisnikId + "'" + " AND FA_ID = '" + faId + "'" , null);

        if (cursor.moveToFirst()){
            do {
                brojac = true;
                //yyyy-MM-dd HH:mm:ss
                //Date d = getDate(cursor, naziv);
                //str = cursor.getString(cursor.getColumnIndex("DATUM_UZIMANJA"));
                //Date strDate = sdf.parse(str);
                //if (new Date().after(d)) {
                //}
                //str = cursor.getString(cursor.getColumnIndex("DATUM_UZIMANJA"));
            } while(cursor.moveToNext());
        }
        if(brojac)
            return true;
        else
            return false;
    }
    public void ocijeniAutomobil(int ocjena, int korisnikId, int faId){
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        ContentValues vrednosti = new ContentValues();

        Cursor cursor = sqLiteDb.rawQuery("SELECT KORISNIK_ID, FA_ID " +
                "FROM " + OCJENA_TABLE + " WHERE KORISNIK_ID = '" + korisnikId + "'" + " AND FA_ID = '" + faId + "'" , null);

        if(cursor.moveToFirst()){
            sqLiteDb.delete(OCJENA_TABLE, "KORISNIK_ID=" + korisnikId + " AND FA_ID=" + faId, null);
            vrednosti.put("KORISNIK_ID", korisnikId);
            vrednosti.put("FA_ID", faId);
            vrednosti.put("OCJENA_BROJ", ocjena);
            sqLiteDb.insert(OCJENA_TABLE, null, vrednosti);
        }else{
            vrednosti.put("KORISNIK_ID", korisnikId);
            vrednosti.put("FA_ID", faId);
            vrednosti.put("OCJENA_BROJ", ocjena);
            sqLiteDb.insert(OCJENA_TABLE, null, vrednosti);
        }

    }
    public ArrayList<OcjenaModel> getSveOcjene() {
        SQLiteDatabase sqLiteDb = this.getReadableDatabase();
        ArrayList<OcjenaModel> listaOcjena = new ArrayList<>();

        Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM " + OCJENA_TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("OCJENA_ID"));
                int fa_id = cursor.getInt(cursor.getColumnIndex("FA_ID"));
                int broj = cursor.getInt(cursor.getColumnIndex("OCJENA_BROJ"));
                int korId = cursor.getInt(cursor.getColumnIndex("KORISNIK_ID"));

                listaOcjena.add(new OcjenaModel(id,korId,fa_id,broj));
            } while (cursor.moveToNext());
        }

        return listaOcjena;

    }
    public float uzmiOcjenu(int faId) {
        float korId = 0;
        int brojac = 0;
        float ocjena = 0;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT OCJENA_BROJ " +
                "FROM " + OCJENA_TABLE + " WHERE FA_ID = '" + faId + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                korId += cursor.getInt(cursor.getColumnIndex("OCJENA_BROJ"));
                brojac++;
            } while(cursor.moveToNext());
        }
        if(korId != 0 && brojac != 0){
            ocjena = korId / brojac;
        }else{
            ocjena = 0;
        }

        return ocjena;
    }
    public void dodajOmiljeni(int korisnikId, int faId) {
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        ContentValues vrednosti = new ContentValues();
        vrednosti.put("KORISNIK_ID", korisnikId);
        vrednosti.put("FA_ID", faId);
        sqLiteDb.insert(OMILJENI_TABLE, null, vrednosti);
    }

    public void obrisiOmiljeni(int korisnikId, int faId) {
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        sqLiteDb.delete(OMILJENI_TABLE, "KORISNIK_ID=" + korisnikId + " AND FA_ID=" + faId, null);
    }
    public boolean daLiJeOmiljeni(int korisnikId, int faId) {
        int korId = 0;
        int faaaId = 0;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT KORISNIK_ID, FA_ID " +
                "FROM " + OMILJENI_TABLE + " WHERE KORISNIK_ID = '" + korisnikId + "'" + " AND FA_ID = '" + faId + "'" , null);

        if (cursor.moveToFirst()) {
            korId = cursor.getInt(cursor.getColumnIndex("KORISNIK_ID"));
            faaaId = cursor.getInt(cursor.getColumnIndex("FA_ID"));
        }

        if(korId != 0 && faaaId != 0)
            return true;
        else
            return false;
    }

    public void dodajKomentarUBazu(String textKomentara, String naslovKomentara, int korisnikId, int faId){
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        ContentValues vrednosti = new ContentValues();
        String ime = "Marko";
        String prezime = "Maric";

        Cursor cursor1 = sqLiteDb.rawQuery("SELECT KORISNIK_IME, KORISNIK_PREZIME " +
                "FROM " + KORISNIK_TABLE + " WHERE KORISNIK_ID = '" + korisnikId + "'", null);

        if (cursor1.moveToFirst()){
                // Passing values
                ime = cursor1.getString(cursor1.getColumnIndex("KORISNIK_IME"));
                prezime = cursor1.getString(cursor1.getColumnIndex("KORISNIK_PREZIME"));
        }
        String naslovK = ime +" "+ prezime;

        Cursor cursor = sqLiteDb.rawQuery("SELECT KORISNIK_ID, FA_ID " +
                "FROM " + KOMENTAR_TABLE + " WHERE KORISNIK_ID = '" + korisnikId + "'" + " AND FA_ID = '" + faId + "'" , null);

        if(cursor.moveToFirst()){
            sqLiteDb.delete(KOMENTAR_TABLE, "KORISNIK_ID=" + korisnikId + " AND FA_ID=" + faId, null);
            vrednosti.put("KORISNIK_ID", korisnikId);
            vrednosti.put("FA_ID", faId);
            vrednosti.put("KOMENTAR_NASLOV", naslovK);
            vrednosti.put("KOMENTAR_TEKST", textKomentara);
            sqLiteDb.insert(KOMENTAR_TABLE, null, vrednosti);
        }else{
            vrednosti.put("KORISNIK_ID", korisnikId);
            vrednosti.put("FA_ID", faId);
            vrednosti.put("KOMENTAR_NASLOV", naslovK);
            vrednosti.put("KOMENTAR_TEKST", textKomentara);
            sqLiteDb.insert(KOMENTAR_TABLE, null, vrednosti);
        }

    }


    public String setujImeMarkuAuta(int id) {
        String marka = "";
        String model = "";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT AUTOMOBIL_MARKA, AUTOMOBIL_MODEL " +
                "FROM " + AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + id + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                marka = cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MARKA"));
                model = cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MODEL"));
            } while(cursor.moveToNext());
        }

        String ret = marka + ", " + model;
        return ret;
    }

    public int setujBrSjedista(int id){
        int BrSjedista = 5;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT AUTOMOBIL_BROJ_SJEDISTA " +
                "FROM " + AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + id + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                BrSjedista = cursor.getInt(cursor.getColumnIndex("AUTOMOBIL_BROJ_SJEDISTA"));
            } while(cursor.moveToNext());
        }

        return BrSjedista;
    }

    public int setujBrVrata(int id){
        int brVrata = 5;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT AUTOMOBIL_BROJ_VRATA " +
                "FROM " + AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + id + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                brVrata = cursor.getInt(cursor.getColumnIndex("AUTOMOBIL_BROJ_VRATA"));
            } while(cursor.moveToNext());
        }

        return brVrata;
    }

    public int setujKubikazu(int id){
        int kubikaza = 1900;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT AUTOMOBIL_KUBIKAZA " +
                "FROM " + AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + id + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                kubikaza = cursor.getInt(cursor.getColumnIndex("AUTOMOBIL_KUBIKAZA"));
            } while(cursor.moveToNext());
        }

        return kubikaza;
    }

    public String setujTipMotora(int id){
        String tip = "TDI";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT AUTOMOBIL_MOTOR " +
                "FROM " + AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + id + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                tip = cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MOTOR"));
            } while(cursor.moveToNext());
        }

        return tip;
    }

    public int setujSnaguMotora(int id){
        int snaga = 120;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT AUTOMOBIL_SNAGA_MOTORA " +
                "FROM " + AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + id + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                snaga = cursor.getInt(cursor.getColumnIndex("AUTOMOBIL_SNAGA_MOTORA"));
            } while(cursor.moveToNext());
        }

        return snaga;
    }

    public int setujCijenuPoDanu(int faId){
        int cijena = 1;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT CENA_PO_DANU " +
                "FROM " + FIRMA_AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + faId + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                cijena = cursor.getInt(cursor.getColumnIndex("CENA_PO_DANU"));
            } while(cursor.moveToNext());
        }

        return cijena;
    }

    public String setujBoju(int faId){
        String boja = "Plava";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT BOJA " +
                "FROM " + FIRMA_AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + faId + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                boja = cursor.getString(cursor.getColumnIndex("BOJA"));
            } while(cursor.moveToNext());
        }

        return boja;
    }

    public int setujKilometrazu(int faId){
        int kilometraza = 60000;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT KILOMETRAZA " +
                "FROM " + FIRMA_AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + faId + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                kilometraza = cursor.getInt(cursor.getColumnIndex("KILOMETRAZA"));
            } while(cursor.moveToNext());
        }

        return kilometraza;
    }

    public int setujGodiste(int faId){
        int godiste = 2000;

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT GODISTE " +
                "FROM " + FIRMA_AUTOMOBIL_TABLE + " WHERE AUTOMOBIL_ID = '" + faId + "'", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                godiste = cursor.getInt(cursor.getColumnIndex("GODISTE"));
            } while(cursor.moveToNext());
        }

        return godiste;
    }

    public String setujNazivFirme(int id){
        String naziv = "Firma doo";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT FIRMA_IME FROM " + FIRMA_TABLE + " as ft INNER JOIN "
                + FIRMA_AUTOMOBIL_TABLE + " as fa ON ft.FIRMA_ID = fa.FIRMA_ID WHERE fa.FA_ID = " + id + ";", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                naziv = cursor.getString(cursor.getColumnIndex("FIRMA_IME"));
            } while(cursor.moveToNext());
        }

        return naziv;
    }

    public String setujOpisFirme(int id){
        String opis = "Najbolja firma";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT FIRMA_OPIS FROM " + FIRMA_TABLE + " as ft INNER JOIN "
                + FIRMA_AUTOMOBIL_TABLE + " as fa ON ft.FIRMA_ID = fa.FIRMA_ID WHERE fa.FA_ID = " + id + ";", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                opis = cursor.getString(cursor.getColumnIndex("FIRMA_OPIS"));
            } while(cursor.moveToNext());
        }

        return opis;
    }

    public String setujGrad(int id){
        String grad = "Gacko";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT FIRMA_GRAD FROM " + FIRMA_TABLE + " as ft INNER JOIN "
                + FIRMA_AUTOMOBIL_TABLE + " as fa ON ft.FIRMA_ID = fa.FIRMA_ID WHERE fa.FA_ID = " + id + ";", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                grad = cursor.getString(cursor.getColumnIndex("FIRMA_GRAD"));
            } while(cursor.moveToNext());
        }

        return grad;
    }

    public String setujAdresu(int id){
        String adresa = "Nemanjina bb";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT FIRMA_ADRESA FROM " + FIRMA_TABLE + " as ft INNER JOIN "
                + FIRMA_AUTOMOBIL_TABLE + " as fa ON ft.FIRMA_ID = fa.FIRMA_ID WHERE fa.FA_ID = " + id + ";", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                adresa = cursor.getString(cursor.getColumnIndex("FIRMA_ADRESA"));
            } while(cursor.moveToNext());
        }

        return adresa;
    }

    public String setujEmail(int id){
        String email = "firma@gmail.com";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT FIRMA_EMAIL FROM " + FIRMA_TABLE + " as ft INNER JOIN "
                + FIRMA_AUTOMOBIL_TABLE + " as fa ON ft.FIRMA_ID = fa.FIRMA_ID WHERE fa.FA_ID = " + id + ";", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                email = cursor.getString(cursor.getColumnIndex("FIRMA_EMAIL"));
            } while(cursor.moveToNext());
        }
        return email;
    }

    public String setujBrTelefona(int id){
        String brTel = "0645698720";

        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        Cursor cursor = sqLiteDb.rawQuery("SELECT FIRMA_BROJ_TELEFONA FROM " + FIRMA_TABLE + " as ft INNER JOIN "
                + FIRMA_AUTOMOBIL_TABLE + " as fa ON ft.FIRMA_ID = fa.FIRMA_ID WHERE fa.FA_ID = " + id + ";", null);

        if (cursor.moveToFirst()){
            do {
                // Passing values
                brTel = cursor.getString(cursor.getColumnIndex("FIRMA_BROJ_TELEFONA"));
            } while(cursor.moveToNext());
        }

        return brTel;
    }

    public String registracija(String ime, String prezime, String email, String brojTelefona, String jmbg, String lozinka) {
        //if (ime.trim() == "" || prezime.trim() == "" || email.trim() == "" || brojTelefona.trim() == "" || jmbg.trim() == "" ||  lozinka.trim() == "") return "Niste unijeli jedan od podataka!";

        boolean status = true;
        StringBuilder sb = new StringBuilder();
        sb.append("");

        if (!Validation.valIme(ime)) {
            status = false;
            sb.append(" Ime");
        }

        if (!Validation.valPrezime(prezime)) {
            status = false;
            sb.append(sb.toString() == "" ? " Prezime" : " ,Prezime");
        }

        if (!Validation.valEmail(email)) {
            status = false;
            sb.append(sb.toString() == "" ? " Email" : " ,Email");
        }

        if (!Validation.valBrojTelefona(brojTelefona)) {
            status = false;
            sb.append(sb.toString() == "" ? " Broj telefona" : " ,Broj telefona");
        }

        if (!Validation.valJMBG(jmbg)) {
            status = false;
            sb.append(sb.toString() == "" ? " JMBG" : " ,JMBG");
        }

        if (!Validation.valLozinka(lozinka)) {
            status = false;
            sb.append(sb.toString() == "" ? " Lozinka" : " ,Lozinka");
        }

        if (!status) {
            SQLiteDatabase sqLiteDb = this.getWritableDatabase();
            String salt = Hesiranje.generateSalt(32);
            ContentValues vrednosti = new ContentValues();
            vrednosti.put("KORISNIK_IME", ime);
            vrednosti.put("KORISNIK_PREZIME", prezime);
            vrednosti.put("KORISNIK_EMAIL", email);
            vrednosti.put("KORISNIK_BR_TEL", brojTelefona);
            vrednosti.put("KORISNIK_JMBG", jmbg);
            vrednosti.put("KORISNIK_LOZINKA", Hesiranje.getSHA(lozinka + salt));
            vrednosti.put("KORISNIK_SALT", salt);
            sqLiteDb.insert(KORISNIK_TABLE, null, vrednosti);
            return "";
        }
        return sb.toString();
    }

    public String prijava(String email, String lozinka) {

        SQLiteDatabase sqLiteDb = this.getReadableDatabase();

        boolean status = true;
        StringBuilder sb = new StringBuilder();
        sb.append("");

        if (!Validation.valEmail(email)) {
            status = false;
            sb.append(sb.toString() == "" ? " Email" : " ,Email");
        }

        if (!Validation.valLozinka(lozinka)) {
            status = false;
            sb.append(sb.toString() == "" ? " Lozinka" : " ,Lozinka");
        }

        if (!status) {

            Cursor cursor = sqLiteDb.rawQuery("SELECT KORISNIK_ID, KORISNIK_EMAIL, KORISNIK_LOZINKA, KORISNIK_SALT " +
                    "FROM " + KORISNIK_TABLE + " WHERE KORISNIK_EMAIL = '" + email + "'", null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(cursor.getColumnIndex("KORISNIK_ID"));
                String sacuvanaLozinka = cursor.getString(cursor.getColumnIndex("KORISNIK_LOZINKA"));
                String salt = cursor.getString(cursor.getColumnIndex("KORISNIK_SALT"));

                if (Hesiranje.getSHA(lozinka + salt).equals(sacuvanaLozinka)) {
                    Session sesija = Session.getInstance(context);
                    sesija.setKorisnikId(Integer.toString(id));
                    return "";
                }

                else {
                    return "Email ili lozinka nisu validni!";
                }
            }
            return "Email ili lozinka nisu validni!";
        }
        return sb.toString();
    }

    public void iznajmiAutomobil(int korisnikId, int faId, Date evidencijaDatum, Date uzimanje, Date vracanje) {
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();

        ContentValues vrednosti = new ContentValues();
        vrednosti.put("KORISNIK_ID", korisnikId);
        vrednosti.put("FA_ID", faId);
        vrednosti.put("EVIDENCIJA_DATUM", evidencijaDatum.toString());
        vrednosti.put("DATUM_UZIMANJA", uzimanje.toString());
        vrednosti.put("DATUM_VRACANJA", vracanje.toString());

        sqLiteDb.insert(EVIDENCIJA_TABLE, null, vrednosti);
    }

    public void setStatusAutomobil(int faId) {
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();
        sqLiteDb.execSQL("UPDATE " + FIRMA_AUTOMOBIL_TABLE + " SET STATUS = 1");
    }

    public void obrisiBazu(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }

    public static Date getDate(Cursor cursor, String columnName) {
        String dateString = cursor.getString(cursor
                .getColumnIndex(columnName));
        if (dateString == null) {
            return null;
        }
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
