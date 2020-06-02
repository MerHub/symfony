/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.entities.Event;
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
public class ShowVelo extends Form{
        Form current;

    public ShowVelo(Form previous,Velo velo) {
        current=this;
                                Toolbar tb=this.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
        setTitle("Velo");
        setUIID("registerPage");
                getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
    }
}
