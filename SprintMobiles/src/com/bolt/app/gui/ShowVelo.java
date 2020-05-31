/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;


import com.bolt.app.MyApplication;
import com.bolt.app.entities.Location;
import com.bolt.app.entities.Taxi;
import com.bolt.app.entities.Velo;
import com.bolt.app.services.OpinionDAO;
import com.bolt.app.services.ServiceAvis;
import com.bolt.app.services.ServiceLocation;
import com.bolt.app.services.ServiceTaxi;
import com.bolt.app.services.ServiceVelo;
import com.bolt.app.utils.Statics;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import java.io.IOException;

/**
 *
 * @author armand
 */
public class ShowVelo extends Form{
        Form current;

    public ShowVelo(Form previous,Velo velo) {
        try{
        current=this;
        
        String login=MyApplication.userConnect.getLogin();
        Label user=new Label("Hello "+login);
       
          EncodedImage enc;
            Container containergrand= new Container(BoxLayout.y());
            containergrand.setUIID("containergrand2");
            
            Container entetephoto= new Container(BoxLayout.y());
            entetephoto.setUIID("entetephoto");
            Container entete= new Container(BoxLayout.y());
            entete.setUIID("enteteChauffeur");
            Container corps= new Container(BoxLayout.x());
            corps.setUIID("corpsChauffeur");
            Container corpsInformation= new Container(BoxLayout.y());
            corpsInformation.setUIID("corpsInformation");
            Container corpsImage= new Container(BoxLayout.y());
            corpsInformation.setUIID("corpsImage");
            Container corpsFoot= new Container(BoxLayout.x());
            corpsFoot.setUIID("corpsFoot");
                    
            enc = EncodedImage.create("/load.gif");
            Image imgs = URLImage.createToStorage(enc,  Statics.BASE_IMAGE_VELO+"/"+velo.getPhoto(),  Statics.BASE_IMAGE_VELO+"/"+velo.getPhoto());
            Label type= new Label("Name: "+velo.getType());
           
            type.setUIID("nom");
            Label adresse= new Label("Adresse: "+velo.getAdresse());
            adresse.setUIID("permis");
            Label prix= new Label("Price: "+String.valueOf(velo.getPrix()));
            prix.setUIID("permis");
            Label qte= new Label("Quantity: "+String.valueOf(velo.getQte()));
            qte.setVisible(true);
            prix.setUIID("permis");
            this.add(user);
            corpsInformation.add(type);
            corpsInformation.add(adresse);
            corpsInformation.add(prix);
            corpsInformation.add(qte);
           
            corps.add(corpsInformation);
            corps.add(corpsImage);
           
            entetephoto.add(imgs);
            
            containergrand.add(entetephoto);
            containergrand.add(corps);
            containergrand.add(corpsFoot);
         
            add(containergrand);
        
  
        
         Button submit=new Button("Rent it now");
                 submit.addActionListener(e-> {
                     Location t=new Location();
                  new ShowLocation(current,t).show();
                  qte.setVisible(false);
                  int nbr=velo.getQte();
                  nbr--;
                  
                  Label qte2= new Label("Quantity remaining: "+nbr);
            prix.setUIID("permis");
            //qte2.setVisible(true);
             corpsInformation.add(qte2);
           
                 });
                    
         submit.setUIID("submit");
         add(submit);
         
         Toolbar tb=this.getToolbar();
         tb.addMaterialCommandToRightBar("",FontImage.MATERIAL_MAIL,e-> new OpinionDAO());
   
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
        setTitle("Bic Details");
        setUIID("registerPage");
                getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
       // this.addAll(user,ltype,adresse,rent);
        } catch (IOException ex) {
        }
        
        
        
 
        
        
        
    }
      private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
}
