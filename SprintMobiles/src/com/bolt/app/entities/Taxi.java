/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;

import java.util.Objects;



/**
 *
 * @author HP
 */
public class Taxi {
        private int id_taxi;
        private Chauffeur chauffeur;
        private String photo;
        private String num_chassis;
        CategoriesTaxi catTaxi;

public Taxi()
{
}

    public int getId_taxi() {
        return id_taxi;
    }

    public void setId_taxi(int id_taxi) {
        this.id_taxi = id_taxi;
    }

    public Chauffeur getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(Chauffeur chauffeur) {
        this.chauffeur = chauffeur;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNum_chassis() {
        return num_chassis;
    }

    public void setNum_chassis(String num_chassis) {
        this.num_chassis = num_chassis;
    }

    public CategoriesTaxi getCatTaxi() {
        return catTaxi;
    }

    public void setCatTaxi(CategoriesTaxi catTaxi) {
        this.catTaxi = catTaxi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id_taxi;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Taxi other = (Taxi) obj;
        if (this.id_taxi != other.id_taxi) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.num_chassis, other.num_chassis)) {
            return false;
        }
        if (!Objects.equals(this.chauffeur, other.chauffeur)) {
            return false;
        }
        if (!Objects.equals(this.catTaxi, other.catTaxi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Taxi{" + "id_taxi=" + id_taxi + ", chauffeur=" + chauffeur + ", photo=" + photo + ", num_chassis=" + num_chassis + ", catTaxi=" + catTaxi + '}';
    }


    
    
  

}
