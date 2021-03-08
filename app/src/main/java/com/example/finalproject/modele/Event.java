package com.example.finalproject.modele;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

    private Date dateMesure;
    private String libelle;
    private int jour;
    private int mois;
    private int annee;
    private int heure;
    private int minute;
    private int heure2;
    private int minute2;


    public Event(Date dateMesure, String libelle, int jour, int mois, int annee, int heure, int minute, int heure2, int minute2) {
        this.dateMesure = dateMesure;
        this.libelle = libelle;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
        this.heure2 = heure2;
        this.minute2 = minute2;
    }

    public Event() {
        this.dateMesure = dateMesure;
        this.libelle = libelle;
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
        this.heure = heure;
        this.minute = minute;
        this.heure2 = heure2;
        this.minute2 = minute2;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHeure2() {
        return heure2;
    }

    public void setHeure2(int heure2) {
        this.heure2 = heure2;
    }

    public int getMinute2() {
        return minute2;
    }

    public void setMinute2(int minute2) {
        this.minute2 = minute2;
    }

    public String afficheAll(){
            return "Event{" +
                    ", libelle='" + libelle + '\'' +
                    ", jour=" + jour +
                    ", mois=" + mois +
                    ", annee=" + annee +
                    ", heure=" + heure +
                    ", minute=" + minute +
                    '}';
        }


}

