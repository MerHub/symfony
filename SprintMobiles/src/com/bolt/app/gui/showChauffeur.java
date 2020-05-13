/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.bolt.app.entities.Avis;
import com.bolt.app.entities.Chauffeur;
import com.bolt.app.entities.Reservation;
import com.bolt.app.entities.Taxi;
import com.bolt.app.entities.User;
import com.bolt.app.services.ServiceAvis;
import com.bolt.app.services.ServiceTaxi;
import com.bolt.app.services.ServiceUser;
import com.bolt.app.utils.Statics;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class showChauffeur extends Form{
    Form current;
    public showChauffeur(Form previous,Chauffeur c,Reservation m) {
        
        current=this;
current.setTitle("+216 "+c.getN_tel());
current.setUIID("showChauffeur");
current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 4, e-> previous.showBack());
current.getTitleArea().setUIID("entetePageIndex");

        EncodedImage enc;
        try {
            Container containergrand= new Container(BoxLayout.y());
            containergrand.setUIID("containergrand");
            
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
            Image imgs = URLImage.createToStorage(enc,  Statics.BASE_IMAGE_PROFILE+"/"+c.getPhoto(),  Statics.BASE_IMAGE_PROFILE+"/"+c.getPhoto());
            Label nom= new Label(c.getNom()+" "+c.getPrenom());
            nom.setUIID("nom");
            Label cin= new Label("Cin "+String.valueOf(c.getCin()));
             cin.setUIID("cin");
             Label permis= new Label("Permis "+c.getPermis());
             permis.setUIID("permis");
            corpsInformation.add(nom);
            corpsInformation.add(cin);
            corpsInformation.add(permis);
            Taxi t=ServiceTaxi.getInstance().getTaxi(c.getId_user());

       if(t.getCatTaxi()!=null){
                Image img = URLImage.createToStorage(enc,  Statics.BASE_MODEL_VOITURE+"/"+t.getCatTaxi().getImage(),  Statics.BASE_MODEL_VOITURE+"/"+t.getCatTaxi().getImage());
            corpsImage.add(img);
            corps.add(corpsInformation);
            corps.add(corpsImage);
            
            Label note=new Label(String.valueOf(ServiceAvis.getInstance().getMoyenne(c.getId_user())));
            note.setUIID("moyenne");
            corpsFoot.add(createStarRankSlider(false,ServiceAvis.getInstance().getMoyenne(c.getId_user())));
            entetephoto.add(imgs);
            
            containergrand.add(entetephoto);
            containergrand.add(corps);
            containergrand.add(corpsFoot);
            
            
            add(containergrand);
          current.getToolbar().addCommandToOverflowMenu("add a review",MyApplication.theme.getImage("icon.png"),e->{
           new AddAvis(current,c).show();
       });
                    current.getToolbar().addCommandToOverflowMenu("add a reclamation",MyApplication.theme.getImage("icon.png"),e->{
           //pageConnexion.show();
       });
            
                     Button submit=new Button("Select this taxi");
                 submit.addActionListener(e-> {
                     
                 });
                 
                 if(m!=null){
                    if(m.getType_reservation().equals("livraison")){
         TextField username=new TextField("","Code livaison");
         username.getHintLabel().setUIID("inter1");
         username.setUIID("input1");
         add(username);
                    }
                      add(submit);
                 }
                
            
                     submit.setUIID("submit");
            Container contenuAvis= new Container(BoxLayout.y());
            contenuAvis.setUIID("contenuAvis");
            
            ArrayList<Avis> liste=ServiceAvis.getInstance().getListeAvis(c.getId_user());
            
            for(int i=0;i<liste.size();i++){
                Avis a=liste.get(i);
                Container avis= new Container(BoxLayout.y());
                avis.setUIID("avis");
                Label username=new Label(a.getClient().getLogin());
                username.setUIID("usernameAvis");
                SpanLabel message=new SpanLabel(a.getMsg());
                message.setTextUIID("messageAvis");
                System.out.println("++++"+a.getNote());
                //Slider rating=;
                //rating.setUIID("rating");
                avis.add(username);
                avis.add(message);
                Container rating=new Container(BoxLayout.x());
                rating.setUIID("rating");
                rating.add(createStarRankSlider(false,a.getNote()));
                avis.add(rating);
                
                contenuAvis.add(avis);
            }
                            add(contenuAvis);
       }else{
    

            System.out.println("indisponible .");       
       }
        } catch (IOException ex) {
        }
        
        
    }
    
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}

private Slider createStarRankSlider(Boolean edit,int defaults) {
    Slider starRank = new Slider();
    starRank.setEditable(edit);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    starRank.setProgress(defaults);
    Font fnt = Font.createTrueTypeFont("Handlee", "Handlee-Regular.ttf").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    return starRank;
}
    
}
