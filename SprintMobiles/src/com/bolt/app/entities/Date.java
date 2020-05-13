<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author armand
 */
public class Date{
    String annee;
    String mois;
    String jour;
    
    String heures;
    String minutes;

    public Date(String annee, String mois, String jour, String heures, String minutes) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
        this.heures = heures;
        this.minutes = minutes;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeures() {
        return heures;
    }

    public void setHeures(String heures) {
        this.heures = heures;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public String toString() {
        return "Date{" + "annee=" + annee + ", mois=" + mois + ", jour=" + jour + ", heures=" + heures + ", minutes=" + minutes + '}';
    }

    public Date() {
    }


    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author armand
 */
public class Date{
    String annee;
    String mois;
    String jour;
    
    String heures;
    String minutes;

    public Date(String annee, String mois, String jour, String heures, String minutes) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
        this.heures = heures;
        this.minutes = minutes;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public String getHeures() {
        return heures;
    }

    public void setHeures(String heures) {
        this.heures = heures;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public String toString() {
        return "Date{" + "annee=" + annee + ", mois=" + mois + ", jour=" + jour + ", heures=" + heures + ", minutes=" + minutes + '}';
    }

    public Date() {
    }


    
}
>>>>>>> master
