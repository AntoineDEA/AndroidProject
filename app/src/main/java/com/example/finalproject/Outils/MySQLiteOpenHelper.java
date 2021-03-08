package com.example.finalproject.Outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String creation = "create table Event ("
            + "datemesure TEXT PRIMARY KEY,"
            + "libelle TEXT,"
            + "jour INTEGER NOT NULL,"
            + "mois INTEGER NOT NULL,"
            + "annee INTEGER NOT NULL,"
            + "heure INTEGER NOT NULL,"
            + "minute INTEGER NOT NULL,"
            + "heure2 INTEGER NOT NULL,"
            + "minute2 INTEGER NOT NULL);";


    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(creation);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
