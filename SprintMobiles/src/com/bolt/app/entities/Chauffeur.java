<<<<<<< HEAD
<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;

import com.bolt.app.entities.User;



/**
 *
 * @author Belgaroui Ghazi
 */
public class Chauffeur extends User {
    private String adresse;
    private int cin;
    private String permis;
    private String nom;
    private String prenom;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Chauffeur() {
    }

    
    public Chauffeur(User user, String adresse, int cin, String permis, String nom, String prenom) {
        super(user.getId_user(),user.getN_tel(),user.getLogin(),user.getMdp(),user.getEtat(),user.getMail(),user.getType());
        this.adresse = adresse;
        this.cin = cin;
        this.permis = permis;
        this.nom = nom;
        this.prenom = prenom;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
     public String getAdresse() {
        return adresse;
    }

    public int getCin() {
        return cin;
    }

    public String getPermis() {
        return permis;
    }
public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        String u=super.toString();
        return "Chauffeur{"+ u + ", adresse=" + adresse + ", cin=" + cin + ", permis=" + permis + ", nom=" + nom + ", prenom=" + prenom + ",photo ="+photo+"}";
    }

   
   
    

 
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;

import com.bolt.app.entities.User;



/**
 *
 * @author Belgaroui Ghazi
 */
public class Chauffeur extends User {
    private String adresse;
    private int cin;
    private String permis;
    private String nom;
    private String prenom;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Chauffeur() {
    }

    
    public Chauffeur(User user, String adresse, int cin, String permis, String nom, String prenom) {
        super(user.getId_user(),user.getN_tel(),user.getLogin(),user.getMdp(),user.getEtat(),user.getMail(),user.getType());
        this.adresse = adresse;
        this.cin = cin;
        this.permis = permis;
        this.nom = nom;
        this.prenom = prenom;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
     public String getAdresse() {
        return adresse;
    }

    public int getCin() {
        return cin;
    }

    public String getPermis() {
        return permis;
    }
public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        String u=super.toString();
        return "Chauffeur{"+ u + ", adresse=" + adresse + ", cin=" + cin + ", permis=" + permis + ", nom=" + nom + ", prenom=" + prenom + ",photo ="+photo+"}";
    }

   
   
    

 
    
}
>>>>>>> master
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;

import com.bolt.app.entities.User;



/**
 *
 * @author Belgaroui Ghazi
 */
public class Chauffeur extends User {
    private String adresse;
    private int cin;
    private String permis;
    private String nom;
    private String prenom;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Chauffeur() {
    }

    
    public Chauffeur(User user, String adresse, int cin, String permis, String nom, String prenom) {
        super(user.getId_user(),user.getN_tel(),user.getLogin(),user.getMdp(),user.getEtat(),user.getMail(),user.getType());
        this.adresse = adresse;
        this.cin = cin;
        this.permis = permis;
        this.nom = nom;
        this.prenom = prenom;

    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
     public String getAdresse() {
        return adresse;
    }

    public int getCin() {
        return cin;
    }

    public String getPermis() {
        return permis;
    }
public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        String u=super.toString();
        return "Chauffeur{"+ u + ", adresse=" + adresse + ", cin=" + cin + ", permis=" + permis + ", nom=" + nom + ", prenom=" + prenom + ",photo ="+photo+"}";
    }

   
   
    

 
    
}
>>>>>>> ghada
