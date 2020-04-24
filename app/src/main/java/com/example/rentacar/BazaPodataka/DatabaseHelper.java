package com.example.rentacar.BazaPodataka;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "rentacar.db";
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
        String createAutomobil = "CREATE TABLE " + AUTOMOBIL_TABLE + " (AUTOMOBIL_ID integer not null, AUTOMOBIL_MARKA varchar(50) not null," +
                "AUTOMOBIL_MODEL varchar(50) not null, AUTOMOBIL_BROJ_SEDISTA integer not null, AUTOMOBIL_BROJ_VRATA integer not null," +
                "AUTOMOBIL_KUBIKAZA integer not null, AUTOMOBIL_MOTOR VARCHAR(50), AUTOMOBIL_SNAGA_MOTORA integer not null," +
                " constraint PK_AUTOMOBIL primary key (AUTOMOBIL_ID))";

        String createIndexAutomobil = "create unique index AUTOMOBIL_PK on " + AUTOMOBIL_TABLE + " (\n" +
                "AUTOMOBIL_ID ASC);";

        String createKorisnik =  "CREATE TABLE " + KORISNIK_TABLE + " (KORISNIK_ID integer AUTOINCREMENT not null, KORISNIK_IME varchar(50) not null," +
                "   KORISNIK_PREZIME     varchar(50)                    not null,\n" +
                "   KORISNIK_EMAIL       varchar(50)                    not null,\n" +
                "   KORISNIK_BR_TEL      varchar(20)                    not null,\n" +
                "   KORISNIK_JMBG        varchar(13)                    not null,\n" +
                "   KORISNIK_LOZINKA     varchar(100)                   not null,\n" +
                "   KORISNIK_SALT        varchar(50)                    not null,\n" +
                "   constraint PK_KORISNIK primary key (KORISNIK_ID)\n" +
                ")";

        String createIndexKorisnik = "create unique index KORISNIK_PK on " + KORISNIK_TABLE + " (\n" +
                "KORISNIK_ID ASC);";

        String createFirma = "CREATE TABLE " + FIRMA_TABLE + "(FIRMA_ID integer not null,\n" +
                "   FIRMA_IME            varchar(50)                    not null,\n" +
                "   FIRMA_PIB            varchar(30)                    not null,\n" +
                "   FIRMA_OPIS           text                   not null,\n" +
                "   FIRMA_ADRESA         varchar(50)                    not null,\n" +
                "   FIRMA_GRAD           varchar(50)                    not null,\n" +
                "   FIRMA_EMAIL          varchar(50)                    not null,\n" +
                "   FIRMA_BROJ_TELEFONA  varchar(20)                    not null,\n" +
                "   FIRMA_LAT            float                          not null,\n" +
                "   FIRMA_LONG           float                          not null,\n" +
                "   constraint PK_FIRMA primary key (FIRMA_ID)\n" +
                ")";

        String createIndexFirma = "create unique index FIRMA_PK on " + FIRMA_TABLE + " (\n" +
                "FIRMA_ID ASC\n" +
                ");";

        String createSlika =  "CREATE TABLE " + SLIKA_TABLE + "(SLIKA_ID integer not null,\n" +
                "   AUTOMOBIL_ID         integer                        not null,\n" +
                "   SLIKA_PUTANJA        text                  not null,\n" +
                "   constraint PK_SLIKA primary key (SLIKA_ID)\n" +
                ")";

        String createIndexSlika = "create unique index SLIKA_PK on " + SLIKA_TABLE + " (\n" +
                "SLIKA_ID ASC\n" +
                ");";

        String createFirmaAutomobil = "CREATE TABLE " + FIRMA_AUTOMOBIL_TABLE + "(\n" +
                "   FA_ID                integer                        not null,\n" +
                "   AUTOMOBIL_ID         integer                        not null,\n" +
                "   FIRMA_ID             integer                        not null,\n" +
                "   POLISA_OSIGURANJA    integer                        not null,\n" +
                "   CENA_PO_DANU         integer                        not null,\n" +
                "   STATUS               smallint                       not null,\n" +
                "   BOJA                 varchar(20)                    not null,\n" +
                "   GODISTE              integer                        not null,\n" +
                "   KILOMETRAZA          integer                        not null,\n" +
                "   constraint PK_FIRMA_AUTO primary key (FA_ID)\n" +
                ")";

        String createIndexFirmaAutomobil = "create unique index FIRMA_AUTO_PK on " + FIRMA_AUTOMOBIL_TABLE + " (\n" +
                "FA_ID ASC\n" +
                ");";

        String createEvidencija = "CREATE TABLE " + EVIDENCIJA_TABLE + "(\n" +
                "   EVIDENCIJA_ID        integer                        not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   AUTOMOBIL_ID         integer                        not null,\n" +
                "   EVIDENCIJA_DATUM     date                           not null,\n" +
                "   DATUM_UZIMANJA       date                           not null,\n" +
                "   DATUM_VRACANJA       date                           not null,\n" +
                "   constraint PK_EVIDENCIJA primary key (EVIDENCIJA_ID)\n" +
                ")";

        String createIndexEvidencija = "create unique index EVIDENCIJA_PK on " + EVIDENCIJA_TABLE + " (\n" +
                "EVIDENCIJA_ID ASC\n" +
                ");";

        String createKomentar = "CREATE TABLE " + KOMENTAR_TABLE + "(\n" +
                "   KOMENTAR_ID          integer                        not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   FA_ID                integer                        not null,\n" +
                "   KOMENTAR_NASLOV      varchar(100)                   not null,\n" +
                "   KOMENTAR_TEKST       text                   not null,\n" +
                "   constraint PK_KOMENTAR primary key (KOMENTAR_ID)\n" +
                ");\n";

        String createIndexKOmentar = "create unique index KOMENTAR_PK on " + KOMENTAR_TABLE + " (\n" +
                "KOMENTAR_ID ASC\n" +
                ");";

        String createOcjena = "CREATE TABLE " + OCJENA_TABLE + "(\n" +
                "   OCJENA_ID             integer                       not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   FA_ID                integer                        not null,\n" +
                "   OCJENA_BROJ           integer                       not null,\n" +
                "   constraint PK_OCJENA primary key (OCJENA_ID)\n" +
                ")";

        String createIndexOcjena = "create unique index OCJENA_PK on " + OCJENA_TABLE + " (\n" +
                "OCJENA_ID ASC\n" +
                ");";

        String createOmiljeni = "CREATE TABLE " + OMILJENI_TABLE + "(\n" +
                "   OMILJENI_ID          integer                        not null,\n" +
                "   KORISNIK_ID          integer                        not null,\n" +
                "   FA_ID                integer                        not null,\n" +
                "   constraint PK_OMILJENI primary key (OMILJENI_ID)\n" +
                ")";

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

        String relationship5 = "create index RELATIONSHIP_5_FK on " + SLIKA_TABLE + " (\n" +
                "AUTOMOBIL_ID ASC\n" +
                ");\n";

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

        String createIndexOmiljeni = "create unique index OMILJENI_PK on " + OMILJENI_TABLE + " (\n" +
                "OMILJENI_ID ASC\n" +
                ");";

        String alterTables = "alter table " + EVIDENCIJA_TABLE + "\n" +
                "  add constraint FK_EVIDENCI_RELATIONS_KORISNIK foreign key (KORISNIK_ID)\n" +
                "      references KORISNIK (KORISNIK_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + EVIDENCIJA_TABLE + "\n" +
                "   add constraint FK_EVIDENCI_RELATIONS_AUTOMOBI foreign key (AUTOMOBIL_ID)\n" +
                "      references AUTOMOBIL (AUTOMOBIL_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + FIRMA_AUTOMOBIL_TABLE + "\n" +
                "   add constraint FK_FIRMA_AU_RELATIONS_AUTOMOBI foreign key (AUTOMOBIL_ID)\n" +
                "      references AUTOMOBIL (AUTOMOBIL_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + FIRMA_AUTOMOBIL_TABLE + "\n" +
                "   add constraint FK_FIRMA_AU_RELATIONS_FIRMA foreign key (FIRMA_ID)\n" +
                "      references FIRMA (FIRMA_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + KOMENTAR_TABLE + "\n" +
                "   add constraint FK_KOMENTAR_RELATIONS_KORISNIK foreign key (KORISNIK_ID)\n" +
                "      references KORISNIK (KORISNIK_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + KOMENTAR_TABLE + "\n" +
                "   add constraint FK_KOMENTAR_RELATIONS_FIRMA_AU foreign key (FA_ID)\n" +
                "      references FIRMA_AUTO (FA_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + OCJENA_TABLE + "\n" +
                "   add constraint FK_OCJENA_RELATIONS_KORISNIK foreign key (KORISNIK_ID)\n" +
                "      references KORISNIK (KORISNIK_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + OCJENA_TABLE + "\n" +
                "   add constraint FK_OCJENA_RELATIONS_FIRMA_AU foreign key (FA_ID)\n" +
                "      references FIRMA_AUTO (FA_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + OMILJENI_TABLE + "\n" +
                "   add constraint FK_OMILJENI_RELATIONS_KORISNIK foreign key (KORISNIK_ID)\n" +
                "      references KORISNIK (KORISNIK_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + OMILJENI_TABLE + "\n" +
                "   add constraint FK_OMILJENI_RELATIONS_FIRMA_AU foreign key (FA_ID)\n" +
                "      references FIRMA_AUTO (FA_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;\n" +
                "\n" +
                "alter table " + SLIKA_TABLE + "\n" +
                "   add constraint FK_SLIKA_RELATIONS_AUTOMOBI foreign key (AUTOMOBIL_ID)\n" +
                "      references AUTOMOBIL (AUTOMOBIL_ID)\n" +
                "      on update restrict\n" +
                "      on delete restrict;";

        db.execSQL(createAutomobil);
        /*db.execSQL(createIndexAutomobil);
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
        db.execSQL(alterTables);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AUTOMOBIL_TABLE);
        /*db.execSQL("DROP TABLE IF EXISTS " + KORISNIK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FIRMA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FIRMA_AUTOMOBIL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + KOMENTAR_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OCJENA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OMILJENI_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EVIDENCIJA_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SLIKA_TABLE);*/
        onCreate(db);
    }

    public boolean insertAutomobil(String marka, String model, int brojSedista, int brojVrata, int kubikaza, String motor, int snaga) {
        SQLiteDatabase sqLiteDb = this.getWritableDatabase();

        ContentValues vrednosti = new ContentValues();
        vrednosti.put("AUTOMOBIL_MARKA", marka);
        vrednosti.put("AUTOMOBIL_MODEL", model);
        vrednosti.put("AUTOMOBIL_BROJ_SEDISTA", brojSedista);
        vrednosti.put("AUTOMOBIL_BROJ_VRATA", brojVrata);
        vrednosti.put("AUTOMOBIL_KUBIKAZA", kubikaza);
        vrednosti.put("AUTOMOBIL_MOTOR", motor);
        vrednosti.put("AUTOMOBIL_SNAGA_MOTOR", snaga);

        sqLiteDb.insert(AUTOMOBIL_TABLE, null, vrednosti);
        return true;
    }

    public ArrayList<String> getAutomobili() {
        SQLiteDatabase sqLiteDb = this.getReadableDatabase();
        ArrayList<String> listaAutomobila = new ArrayList<>();
        Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM " + AUTOMOBIL_TABLE, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()) {
            listaAutomobila.add(cursor.getString(cursor.getColumnIndex("AUTOMOBIL_MARKA")));
            cursor.moveToNext();
        }
        return listaAutomobila;
    }

}
