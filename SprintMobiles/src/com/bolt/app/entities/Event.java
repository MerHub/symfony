    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;

import java.util.Objects;





/**
 *
 * @author Meriem
 */
public class Event {
    
    
 private int id_event;
 private String nom;
private  int nbr_place;
 private String depart;
 private String arrivee;
 private Date date_allee;
 private Date date_retour;
 private String description;
 private Double latitude1;
  private Double latitude2;
  private Double longitude1;
  private Double longitude2;

    public Event(int id_event, String nom, int nbr_place, String depart, String arrivee, Date date_allee, Date date_retour, String description, Double latitude1, Double latitude2, Double longitude1, Double longitude2) {
        this.id_event = id_event;
        this.nom = nom;
        this.nbr_place = nbr_place;
        this.depart = depart;
        this.arrivee = arrivee;
        this.date_allee = date_allee;
        this.date_retour = date_retour;
        this.description = description;
        this.latitude1 = latitude1;
        this.latitude2 = latitude2;
        this.longitude1 = longitude1;
        this.longitude2 = longitude2;
    }
 


 
    public Event() {
    }

    public Event(String nom, int nbr_place, String depart, String arrivee, Date date_allee, Date date_retour, String description, Double latitude1, Double latitude2, Double longitude1, Double longitude2) {
        this.nom = nom;
        this.nbr_place = nbr_place;
        this.depart = depart;
        this.arrivee = arrivee;
        this.date_allee = date_allee;
        this.date_retour = date_retour;
        this.description = description;
        this.latitude1 = latitude1;
        this.latitude2 = latitude2;
        this.longitude1 = longitude1;
        this.longitude2 = longitude2;
    }

    @Override
    public String toString() {
        return "Event{" + "id_event=" + id_event + ", nom=" + nom + ", nbr_place=" + nbr_place + ", depart=" + depart + ", arrivee=" + arrivee + ", date_allee=" + date_allee + ", date_retour=" + date_retour + ", description=" + description + ", latitude1=" + latitude1 + ", latitude2=" + latitude2 + ", longitude1=" + longitude1 + ", longitude2=" + longitude2 + '}';
    }

    public Double getLatitude1() {
        return latitude1;
    }

    public void setLatitude1(Double latitude1) {
        this.latitude1 = latitude1;
    }

    public Double getLatitude2() {
        return latitude2;
    }

    public void setLatitude2(Double latitude2) {
        this.latitude2 = latitude2;
    }

    public Double getLongitude1() {
        return longitude1;
    }

    public void setLongitude1(Double longitude1) {
        this.longitude1 = longitude1;
    }

    public Double getLongitude2() {
        return longitude2;
    }

    public void setLongitude2(Double longitude2) {
        this.longitude2 = longitude2;
    }




    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (this.id_event != other.id_event) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public Date getDate_allee() {
        return date_allee;
    }

    public void setDate_allee(Date date_allee) {
        this.date_allee = date_allee;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  

}
