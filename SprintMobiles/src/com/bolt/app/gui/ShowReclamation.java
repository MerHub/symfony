/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.entities.Chauffeur;
import com.bolt.app.entities.Velo;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;

/**
 *
 * @author armand
 */
public class ShowReclamation extends Form{
    Form current;
        public ShowReclamation(Form previous,Chauffeur chauffeur) {
        current=this;
                                Toolbar tb=this.getToolbar();
            Container c=BorderLayout.east(new Label(""));
            c.setUIID("TopleftBarIndexpage1");
        setTitle("Reclamation");
        setUIID("registerPage");
                getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
    }
}
