package com.example.finalproject.controleur;

import android.content.Context;
import android.util.Log;

import com.example.finalproject.Outils.Serializer;
import com.example.finalproject.modele.AccesLocal;
import com.example.finalproject.modele.Event;
import com.example.finalproject.modele.Rdv;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Controle {

    private static Controle instance = null;
    private static Event event;
    private static Rdv rdv;
    private static String nomFic = "saveEvent";
    private static AccesLocal accesLocal;

    List<Event> tempEvent = new ArrayList<Event>();


    private Controle(){
        super();
    }

    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            //recupSerialize(context);
            accesLocal = new AccesLocal(context);
            //event = accesLocal.recupDernier();
        }
        return Controle.instance;
    }

    public void creerEvent(String libelle, Integer jour, Integer mois, Integer annee, Integer heure, Integer minute, Integer heure2, Integer minute2, Context context){
        event = new Event(new Date(), libelle, jour, mois, annee, heure, minute, heure2, minute2);
        accesLocal.ajout(event);
        //Serializer.serialize(nomFic, event, context);
    }

    public void creerRdv(String libelle, Integer jour, Integer mois, Integer annee, Integer heure, Integer minute, Integer heure2, Integer minute2, String type, String personne, Context context){
        rdv = new Rdv(new Date(), libelle, jour, mois, annee, heure, minute, heure2, minute2, type, personne);
        accesLocal.ajoutRdv(rdv);
        //Serializer.serialize(nomFic, event, context);
    }

    public Boolean recupEvent(String libelle, Integer jour, Integer mois, Integer annee, Integer heure, Integer minute, Integer heure2, Integer minute2, Context context){
        boolean valid = false;
        event = new Event(new Date(), libelle, jour, mois, annee, heure, minute, heure2, minute2);
        valid = accesLocal.recupDernierSpe(event);
        return valid;
    }

    public List<Event> listeRdv(){
        tempEvent = accesLocal.getAllElements();
        return tempEvent;

    }

    public String getAll(String libelle, Integer jour, Integer mois, Integer annee, Integer heure, Integer minute){
        return "Event{" +
                ", libelle='" + libelle + '\'' +
                ", jour=" + jour +
                ", mois=" + mois +
                ", annee=" + annee +
                ", heure=" + heure +
                ", minute=" + minute +
                '}';
    }

    private static void recupSerialize(Context context){
        event = (Event) Serializer.deSerialize(nomFic, context);
    }

    public String getLibelle(){
        if(event == null){
            return null;
        }else{
            return event.getLibelle();
        }
    }

    public Integer getJour(){
        if(event == null){
            return null;
        }else{
            return event.getJour();
        }
    }

    public Integer getMois(){
        if(event == null){
            return null;
        }else{
            return event.getMois();
        }
    }
    public Integer getAnnee(){
        if(event == null){
            return null;
        }else{
            return event.getAnnee();
        }
    }

    public Integer getHeure(){
        if(event == null){
            return null;
        }else{
            return event.getHeure();
        }
    }

    public Integer getMinute(){
        if(event == null){
            return null;
        }else{
            return event.getMinute();
        }
    }

    public Integer getHeure2(){
        if(event == null){
            return null;
        }else{
            return event.getHeure2();
        }
    }

    public Integer getMinute2(){
        if(event == null){
            return null;
        }else{
            return event.getMinute2();
        }
    }

    public String getTypeRdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getType();
        }
    }

    public String getPersonneRdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getPersonne();
        }
    }

    public String getLibelleRdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getLibelle();
        }
    }

    public Integer getJourRdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getJour();
        }
    }

    public Integer getMoisRdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getMois();
        }
    }
    public Integer getAnneeRdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getAnnee();
        }
    }

    public Integer getHeureRdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getHeure();
        }
    }

    public Integer getMinuteRdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getMinute();
        }
    }

    public Integer getHeure2Rdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getHeure2();
        }
    }

    public Integer getMinute2Rdv(){
        if(rdv == null){
            return null;
        }else{
            return rdv.getMinute2();
        }
    }


}
