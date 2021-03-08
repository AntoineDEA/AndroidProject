package com.example.finalproject.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finalproject.Outils.MySQLiteOpenHelper;
import com.example.finalproject.Outils.MySQLiteOpenHelper2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccesLocal {

    private String nomBase = "bdProjet.sqlite8";
    private String nomBase2 = "bdProjet.sqlitePart2";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private MySQLiteOpenHelper2 accesBD2;
    private SQLiteDatabase bd;
    private SQLiteDatabase bd2;

    public AccesLocal(Context contexte) {
        accesBD = new MySQLiteOpenHelper(contexte, nomBase, null, versionBase);
        accesBD2 = new MySQLiteOpenHelper2(contexte,nomBase2, null, versionBase);
    }

    public void ajout(Event event){
        bd = accesBD.getWritableDatabase();
        String req = "insert into Event (dateMesure, libelle, jour, mois, annee, heure, minute, heure2, minute2) values";
        req += "(\""+event.getDateMesure()+"\",\""+event.getLibelle()+"\","+event.getJour()+","+event.getMois()+","+event.getAnnee()+","+event.getHeure()+","+event.getMinute()+","+event.getHeure2()+","+event.getMinute2()+")";
        bd.execSQL(req);
    }

    public void ajoutRdv(Rdv rdv){
        bd2 = accesBD2.getWritableDatabase();
        String req2 = "insert into Rdv (dateMesure, libelle, jour, mois, annee, heure, minute, heure2, minute2, type, personne) values";
        req2 += "(\""+rdv.getDateMesure()+"\",\""+rdv.getLibelle()+"\","+rdv.getJour()+","+rdv.getMois()+","+rdv.getAnnee()+","+rdv.getHeure()+","+rdv.getMinute()+","+rdv.getHeure2()+","+rdv.getMinute2()+",\""+rdv.getType()+"\",\""+rdv.getType()+"\")";
        bd2.execSQL(req2);
    }

    public ArrayList<Event> getAllElements() {
        bd = accesBD.getReadableDatabase();
        ArrayList<Event> list = new ArrayList<Event>();
        Event event =null;
        String selectQuery = "SELECT  * FROM Event";
        try {
            Cursor cursor = bd.rawQuery(selectQuery, null);
            try {
                if (cursor.moveToFirst()) {
                    do {
                        Event obj = new Event();

                        obj.setLibelle(cursor.getString(1));
                        obj.setJour(cursor.getInt(2));
                        obj.setMois(cursor.getInt(3));
                        obj.setAnnee(cursor.getInt(4));
                        obj.setHeure(cursor.getInt(5));
                        obj.setMinute(cursor.getInt(6));
                        obj.setHeure2(cursor.getInt(7));
                        obj.setMinute2(cursor.getInt(8));

                        list.add(obj);
                    } while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }
        } finally {
            try { bd.close(); } catch (Exception ignore) {}
        }
        return list;
    }

    public Boolean recupDernierSpe(Event event){
        int jourCompare = event.getJour();
        int anneeCompare = event.getAnnee();
        int moisCompare = event.getMois();
        int heureCompare = event.getHeure();
        int minuteCompare = event.getMinute();
        int heure2Compare = event.getHeure2();
        int minute2Compare = event.getMinute2();
        boolean valid = false;
        bd = accesBD.getReadableDatabase();
        String req = "SELECT * FROM Event WHERE jour = " + jourCompare + " AND mois = " + moisCompare + " AND annee = " + anneeCompare + " AND heure = " + heureCompare + " OR ( " + heureCompare + " >= heure AND " + heureCompare + " <= heure2 ) AND minute = " + minuteCompare + " OR ( " + minuteCompare + " minute AND " + minuteCompare + " <= minute )";
        Cursor curseur = bd2.rawQuery(req, null);
        curseur.moveToLast();
        if(!curseur.isAfterLast()){
            Date date = new Date();
            String libelle = curseur.getString(1);
            Integer jour = curseur.getInt(2);
            Integer mois = curseur.getInt(3);
            Integer annee = curseur.getInt(4);
            Integer heure = curseur.getInt(5);
            Integer minute = curseur.getInt(6);
            Integer heure2 = curseur.getInt(7);
            Integer minute2 = curseur.getInt(8);
            event = new Event(date, libelle, jour, mois, annee, heure, minute, heure2, minute2);

            if(jour != null){
                valid = true;
            }
        }
        curseur.close();
        return valid;
    }

}
