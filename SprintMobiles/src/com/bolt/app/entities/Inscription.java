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
public class Inscription {
      private int id_inscription;
    private Event event;
    private Client client;

    public int getId_inscription() {
        return id_inscription;
    }

    public void setId_inscription(int id_inscription) {
        this.id_inscription = id_inscription;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Inscription{" + "id_inscription=" + id_inscription + ", event=" + event + ", client=" + client + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id_inscription;
        hash = 79 * hash + Objects.hashCode(this.event);
        hash = 79 * hash + Objects.hashCode(this.client);
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
        final Inscription other = (Inscription) obj;
        if (this.id_inscription != other.id_inscription) {
            return false;
        }
        if (!Objects.equals(this.event, other.event)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return true;
    }
    
}
