/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;
import java.util.Objects;
import java.util.Date;



/**
 *
 * @author Belgaroui Ghazi
 */
public class Location {
    private int id_location;
    private Client client;
    private Velo velo;
    private Date date_d;
    private Date date_f;
    private double prix;
    

    public Location() {
    }

    public Location(Client client, Velo velo, Date date_d, Date date_f) {
        this.client = client;
        this.velo = velo;
        this.date_d = date_d;
        this.date_f = date_f;
    }

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Velo getVelo() {
        return velo;
    }

    public void setVelo(Velo velo) {
        this.velo = velo;
    }

    public Date getDate_d() {
        return date_d;
    }

    public void setDate_d(Date date_d) {
        this.date_d = date_d;
    }

    public Date getDate_f() {
        return date_f;
    }

    public void setDate_f(Date date_f) {
        this.date_f = date_f;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }



  

    @Override
    public String toString() {
        return "location{" + "id_location=" + id_location + ", " + client.toString() + ", " + velo.toString() + ", date_d=" + date_d + ", date_f=" + date_f + ", prix=" + prix + '}';
    }
    
    
    
}
