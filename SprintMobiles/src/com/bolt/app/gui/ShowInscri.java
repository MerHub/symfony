/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.bolt.app.entities.Inscription;
import com.codename1.ui.Form;
import com.codename1.ui.Label;

/**
 *
 * @author Meriem
 */
public class ShowInscri extends Form {
     Form current;

    public ShowInscri(Form previous,Inscription inscri) {
    current=this;
    String login= MyApplication.userConnect.getLogin();
    Label Llogin = new Label("Hello "+login);
    
    
    
    
    
    }
    
    
}
