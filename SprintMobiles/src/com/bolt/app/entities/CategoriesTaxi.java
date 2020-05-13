<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;

import java.util.Objects;

/**
 *
 * @author armand
 */
public class CategoriesTaxi {
    int id;
    int model;
    String image;

    public CategoriesTaxi(int id, int model, String image) {
        this.id = id;
        this.model = model;
        this.image = image;
    }

    public CategoriesTaxi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final CategoriesTaxi other = (CategoriesTaxi) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.model != other.model) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategoriesTaxi{" + "id=" + id + ", model=" + model + ", image=" + image + '}';
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.entities;

import java.util.Objects;

/**
 *
 * @author armand
 */
public class CategoriesTaxi {
    int id;
    int model;
    String image;

    public CategoriesTaxi(int id, int model, String image) {
        this.id = id;
        this.model = model;
        this.image = image;
    }

    public CategoriesTaxi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final CategoriesTaxi other = (CategoriesTaxi) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.model != other.model) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategoriesTaxi{" + "id=" + id + ", model=" + model + ", image=" + image + '}';
    }
    
}
>>>>>>> master
