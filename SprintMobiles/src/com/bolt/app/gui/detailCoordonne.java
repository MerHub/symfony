<<<<<<< HEAD
<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;


import com.bolt.app.MyApplication;
import com.bolt.app.entities.Client;
import com.bolt.app.entities.Date;
import com.bolt.app.entities.PointMap;
import com.bolt.app.entities.Reservation;
import static com.bolt.app.gui.startPage.current;
import com.bolt.app.services.ServiceUtils;
import com.bolt.app.utils.Function;
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Calendar;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author armand
 */
public class detailCoordonne extends Form{

        public String type="reservation";
        
    public detailCoordonne(Form previous, Double latitude,Double longitude,ArrayList<PointMap> route) {
        Double tempsTotal=0.0;
        Double distanceToatal=0.0;
        
        for(int i=0;i<route.size();i++){
            tempsTotal+=route.get(i).getDuration();
            distanceToatal+=route.get(i).getDistance();
        }
        Double prix=(distanceToatal*1000);
                current=this;
        current.setTitle("Reservation");
current.setUIID("indexPage");

        current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 4, e-> previous.showBack());
current.getTitleArea().setUIID("entetePageIndex");
Calendar cal = new Calendar();
        Date d=ServiceUtils.getInstance().getDate();
        System.out.println(d);
   Container block1=new Container(BoxLayout.y());
   block1.setUIID("blockdate");
   Label day=new Label(d.getJour());
   day.setUIID("day");
   Label month=new Label(d.getMois());
   month.setUIID("month");
   block1.add(day);
   block1.add(month);
   
   
   Container block2=new Container(BoxLayout.y());
   block2.setUIID("blockHour");
   Label hour=new Label(d.getHeures()+":"+d.getMinutes());
   hour.setUIID("hour");
   Label sigle=new Label((Integer.parseInt(d.getHeures())<=12)?"am":"pm");
   sigle.setUIID("sigle");
   block2.add(hour);
   block2.add(sigle);
   
   add(block1);
   add(block2);

   Container blockGlobal=new Container(BoxLayout.y());
   
   Container blockForm=new Container(BoxLayout.x());
   blockForm.setUIID("blockDestainnation");
   
   Image img1=null;
        try {
            img1 = Image.createImage("/iconList2.png");
        } catch (IOException ex) {
        }
   Container blockFormContenu=new Container(BoxLayout.y());
   blockFormContenu.setUIID("blockFormContenu");
   Label from=new Label("From");
   SpanLabel  depart=new SpanLabel (Function.getInstance().getAdresse(MyApplication.userConnect.getLatitude(),MyApplication.userConnect.getLongitude()));
   depart.setTextUIID("ouicy");
   Container im=new Container(BoxLayout.y());
   im.setUIID("transitionIMage");
   Image img2;
        try {
            img2 = Image.createImage("/transition.png");
               blockFormContenu.add(from);
   blockFormContenu.add(depart);
   im.add(img2);
   blockFormContenu.add(im);
        } catch (IOException ex) {
        }
   blockForm.add(img1);
   
   Container blockTo=new Container(BoxLayout.x());
      blockTo.setUIID("blockTo");
   Container blockToContenu=new Container(BoxLayout.y());
   Label to=new Label("To");
    SpanLabel  arrive=new SpanLabel (Function.getInstance().getAdresse(latitude,longitude));   
    arrive.setTextUIID("ouicy2");
   blockToContenu.add(to);
   blockToContenu.add(arrive);
   
   blockTo.add(blockToContenu);
   blockGlobal.setUIID("blockDetailcoord");
   blockFormContenu.add(blockTo);
   blockForm.add(blockFormContenu);
   blockGlobal.add(blockForm);

   add(blockGlobal);
   Container entete=new Container(BoxLayout.x());
   entete.setUIID("enteteInformation");
   Label Distance=new Label("Distance");
   Distance.setUIID("entetePaiement");
   Label time=new Label("Travaling time");
   time.setUIID("entetePaiement");
   
   entete.add(Distance);
   entete.add(time);
   
      Container entete1=new Container(BoxLayout.x());
   entete1.setUIID("enteteInformation1");
   Label Distance1=new Label(String.valueOf(distanceToatal)+" meters");
   Distance1.setUIID("entetePaiement1");
   Label time1=new Label(String.valueOf(tempsTotal)+" seconds");
   time1.setUIID("entetePaiement11");
   
   entete1.add(Distance1);
   entete1.add(time1);
   Container enteteotal=new Container(BoxLayout.y());
   enteteotal.setUIID("enteteotal");
   enteteotal.add(entete);
   enteteotal.add(entete1);
   
   add(enteteotal);
   
            RadioButton reservationCheck = new RadioButton("Reservation");
           reservationCheck.setSelected(true);
         reservationCheck.addActionListener(e-> {
             type="reservation";
         });
         RadioButton livraisonCheck = new RadioButton("Livraison");
         new ButtonGroup(reservationCheck, livraisonCheck);
                  livraisonCheck.addActionListener(e-> {
          type="livraison";
         });
          Container choix=new Container(new FlowLayout(CENTER));    
                   choix.setUIID("choix");
         reservationCheck.setUIID("choixDriver");
         livraisonCheck.setUIID("choixClient");
         choix.add(reservationCheck);
         choix.add(livraisonCheck);
         add(choix);
         Button submit=new Button("choose taxi");
         submit.addActionListener(e->{
                                 boolean demande=Dialog.show("Confirmation", "Voulez vous effectuer une "+type, "oui", "non");
                    if(demande==true){
                        Reservation m=new Reservation();
                        m.setLatitude(MyApplication.userConnect.getLatitude());
                        m.setLongitude(MyApplication.userConnect.getLongitude());
                        
                        m.setLatitude2(latitude);
                        m.setLongitude2(longitude);
                        
                        m.setType_reservation(type);
                        m.setPrix(prix/1000000);
                        Client c=new Client();
                        c.setId_user(MyApplication.userConnect.getId_user());
                        m.setClient(c);
                        new ListChauffeur(current,m).show();
                        
                    }
         });
         submit.setUIID("submit");
         add(submit);
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;


import com.bolt.app.MyApplication;
import com.bolt.app.entities.Client;
import com.bolt.app.entities.Date;
import com.bolt.app.entities.PointMap;
import com.bolt.app.entities.Reservation;
import static com.bolt.app.gui.startPage.current;
import com.bolt.app.services.ServiceUtils;
import com.bolt.app.utils.Function;
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Calendar;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author armand
 */
public class detailCoordonne extends Form{

        public String type="reservation";
        
    public detailCoordonne(Form previous, Double latitude,Double longitude,ArrayList<PointMap> route) {
        Double tempsTotal=0.0;
        Double distanceToatal=0.0;
        
        for(int i=0;i<route.size();i++){
            tempsTotal+=route.get(i).getDuration();
            distanceToatal+=route.get(i).getDistance();
        }
        Double prix=(distanceToatal*1000);
                current=this;
        current.setTitle("Reservation");
current.setUIID("indexPage");

        current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 4, e-> previous.showBack());
current.getTitleArea().setUIID("entetePageIndex");
Calendar cal = new Calendar();
        Date d=ServiceUtils.getInstance().getDate();
        System.out.println(d);
   Container block1=new Container(BoxLayout.y());
   block1.setUIID("blockdate");
   Label day=new Label(d.getJour());
   day.setUIID("day");
   Label month=new Label(d.getMois());
   month.setUIID("month");
   block1.add(day);
   block1.add(month);
   
   
   Container block2=new Container(BoxLayout.y());
   block2.setUIID("blockHour");
   Label hour=new Label(d.getHeures()+":"+d.getMinutes());
   hour.setUIID("hour");
   Label sigle=new Label((Integer.parseInt(d.getHeures())<=12)?"am":"pm");
   sigle.setUIID("sigle");
   block2.add(hour);
   block2.add(sigle);
   
   add(block1);
   add(block2);

   Container blockGlobal=new Container(BoxLayout.y());
   
   Container blockForm=new Container(BoxLayout.x());
   blockForm.setUIID("blockDestainnation");
   
   Image img1=null;
        try {
            img1 = Image.createImage("/iconList2.png");
        } catch (IOException ex) {
        }
   Container blockFormContenu=new Container(BoxLayout.y());
   blockFormContenu.setUIID("blockFormContenu");
   Label from=new Label("From");
   SpanLabel  depart=new SpanLabel (Function.getInstance().getAdresse(MyApplication.userConnect.getLatitude(),MyApplication.userConnect.getLongitude()));
   depart.setTextUIID("ouicy");
   Container im=new Container(BoxLayout.y());
   im.setUIID("transitionIMage");
   Image img2;
        try {
            img2 = Image.createImage("/transition.png");
               blockFormContenu.add(from);
   blockFormContenu.add(depart);
   im.add(img2);
   blockFormContenu.add(im);
        } catch (IOException ex) {
        }
   blockForm.add(img1);
   
   Container blockTo=new Container(BoxLayout.x());
      blockTo.setUIID("blockTo");
   Container blockToContenu=new Container(BoxLayout.y());
   Label to=new Label("To");
    SpanLabel  arrive=new SpanLabel (Function.getInstance().getAdresse(latitude,longitude));   
    arrive.setTextUIID("ouicy2");
   blockToContenu.add(to);
   blockToContenu.add(arrive);
   
   blockTo.add(blockToContenu);
   blockGlobal.setUIID("blockDetailcoord");
   blockFormContenu.add(blockTo);
   blockForm.add(blockFormContenu);
   blockGlobal.add(blockForm);

   add(blockGlobal);
   Container entete=new Container(BoxLayout.x());
   entete.setUIID("enteteInformation");
   Label Distance=new Label("Distance");
   Distance.setUIID("entetePaiement");
   Label time=new Label("Travaling time");
   time.setUIID("entetePaiement");
   
   entete.add(Distance);
   entete.add(time);
   
      Container entete1=new Container(BoxLayout.x());
   entete1.setUIID("enteteInformation1");
   Label Distance1=new Label(String.valueOf(distanceToatal)+" meters");
   Distance1.setUIID("entetePaiement1");
   Label time1=new Label(String.valueOf(tempsTotal)+" seconds");
   time1.setUIID("entetePaiement11");
   
   entete1.add(Distance1);
   entete1.add(time1);
   Container enteteotal=new Container(BoxLayout.y());
   enteteotal.setUIID("enteteotal");
   enteteotal.add(entete);
   enteteotal.add(entete1);
   
   add(enteteotal);
   
            RadioButton reservationCheck = new RadioButton("Reservation");
           reservationCheck.setSelected(true);
         reservationCheck.addActionListener(e-> {
             type="reservation";
         });
         RadioButton livraisonCheck = new RadioButton("Livraison");
         new ButtonGroup(reservationCheck, livraisonCheck);
                  livraisonCheck.addActionListener(e-> {
          type="livraison";
         });
          Container choix=new Container(new FlowLayout(CENTER));    
                   choix.setUIID("choix");
         reservationCheck.setUIID("choixDriver");
         livraisonCheck.setUIID("choixClient");
         choix.add(reservationCheck);
         choix.add(livraisonCheck);
         add(choix);
         Button submit=new Button("choose taxi");
         submit.addActionListener(e->{
                                 boolean demande=Dialog.show("Confirmation", "Voulez vous effectuer une "+type, "oui", "non");
                    if(demande==true){
                        Reservation m=new Reservation();
                        m.setLatitude(MyApplication.userConnect.getLatitude());
                        m.setLongitude(MyApplication.userConnect.getLongitude());
                        
                        m.setLatitude2(latitude);
                        m.setLongitude2(longitude);
                        
                        m.setType_reservation(type);
                        m.setPrix(prix/1000000);
                        Client c=new Client();
                        c.setId_user(MyApplication.userConnect.getId_user());
                        m.setClient(c);
                        new ListChauffeur(current,m).show();
                        
                    }
         });
         submit.setUIID("submit");
         add(submit);
    }
    
}
>>>>>>> master
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;


import com.bolt.app.MyApplication;
import com.bolt.app.entities.Client;
import com.bolt.app.entities.Date;
import com.bolt.app.entities.PointMap;
import com.bolt.app.entities.Reservation;
import static com.bolt.app.gui.startPage.current;
import com.bolt.app.services.ServiceUtils;
import com.bolt.app.utils.Function;
import com.codename1.capture.Capture;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Calendar;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author armand
 */
public class detailCoordonne extends Form{

        public String type="reservation";
        
    public detailCoordonne(Form previous, Double latitude,Double longitude,ArrayList<PointMap> route) {
        Double tempsTotal=0.0;
        Double distanceToatal=0.0;
        
        for(int i=0;i<route.size();i++){
            tempsTotal+=route.get(i).getDuration();
            distanceToatal+=route.get(i).getDistance();
        }
        Double prix=(distanceToatal*1000);
                current=this;
        current.setTitle("Reservation");
current.setUIID("indexPage");

        current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 4, e-> previous.showBack());
current.getTitleArea().setUIID("entetePageIndex");
Calendar cal = new Calendar();
        Date d=ServiceUtils.getInstance().getDate();
        System.out.println(d);
   Container block1=new Container(BoxLayout.y());
   block1.setUIID("blockdate");
   Label day=new Label(d.getJour());
   day.setUIID("day");
   Label month=new Label(d.getMois());
   month.setUIID("month");
   block1.add(day);
   block1.add(month);
   
   
   Container block2=new Container(BoxLayout.y());
   block2.setUIID("blockHour");
   Label hour=new Label(d.getHeures()+":"+d.getMinutes());
   hour.setUIID("hour");
   Label sigle=new Label((Integer.parseInt(d.getHeures())<=12)?"am":"pm");
   sigle.setUIID("sigle");
   block2.add(hour);
   block2.add(sigle);
   
   add(block1);
   add(block2);

   Container blockGlobal=new Container(BoxLayout.y());
   
   Container blockForm=new Container(BoxLayout.x());
   blockForm.setUIID("blockDestainnation");
   
   Image img1=null;
        try {
            img1 = Image.createImage("/iconList2.png");
        } catch (IOException ex) {
        }
   Container blockFormContenu=new Container(BoxLayout.y());
   blockFormContenu.setUIID("blockFormContenu");
   Label from=new Label("From");
   SpanLabel  depart=new SpanLabel (Function.getInstance().getAdresse(MyApplication.userConnect.getLatitude(),MyApplication.userConnect.getLongitude()));
   depart.setTextUIID("ouicy");
   Container im=new Container(BoxLayout.y());
   im.setUIID("transitionIMage");
   Image img2;
        try {
            img2 = Image.createImage("/transition.png");
               blockFormContenu.add(from);
   blockFormContenu.add(depart);
   im.add(img2);
   blockFormContenu.add(im);
        } catch (IOException ex) {
        }
   blockForm.add(img1);
   
   Container blockTo=new Container(BoxLayout.x());
      blockTo.setUIID("blockTo");
   Container blockToContenu=new Container(BoxLayout.y());
   Label to=new Label("To");
    SpanLabel  arrive=new SpanLabel (Function.getInstance().getAdresse(latitude,longitude));   
    arrive.setTextUIID("ouicy2");
   blockToContenu.add(to);
   blockToContenu.add(arrive);
   
   blockTo.add(blockToContenu);
   blockGlobal.setUIID("blockDetailcoord");
   blockFormContenu.add(blockTo);
   blockForm.add(blockFormContenu);
   blockGlobal.add(blockForm);

   add(blockGlobal);
   Container entete=new Container(BoxLayout.x());
   entete.setUIID("enteteInformation");
   Label Distance=new Label("Distance");
   Distance.setUIID("entetePaiement");
   Label time=new Label("Travaling time");
   time.setUIID("entetePaiement");
   
   entete.add(Distance);
   entete.add(time);
   
      Container entete1=new Container(BoxLayout.x());
   entete1.setUIID("enteteInformation1");
   Label Distance1=new Label(String.valueOf(distanceToatal)+" meters");
   Distance1.setUIID("entetePaiement1");
   Label time1=new Label(String.valueOf(tempsTotal)+" seconds");
   time1.setUIID("entetePaiement11");
   
   entete1.add(Distance1);
   entete1.add(time1);
   Container enteteotal=new Container(BoxLayout.y());
   enteteotal.setUIID("enteteotal");
   enteteotal.add(entete);
   enteteotal.add(entete1);
   
   add(enteteotal);
   
            RadioButton reservationCheck = new RadioButton("Reservation");
           reservationCheck.setSelected(true);
         reservationCheck.addActionListener(e-> {
             type="reservation";
         });
         RadioButton livraisonCheck = new RadioButton("Livraison");
         new ButtonGroup(reservationCheck, livraisonCheck);
                  livraisonCheck.addActionListener(e-> {
          type="livraison";
         });
          Container choix=new Container(new FlowLayout(CENTER));    
                   choix.setUIID("choix");
         reservationCheck.setUIID("choixDriver");
         livraisonCheck.setUIID("choixClient");
         choix.add(reservationCheck);
         choix.add(livraisonCheck);
         add(choix);
         Button submit=new Button("choose taxi");
         submit.addActionListener(e->{
                                 boolean demande=Dialog.show("Confirmation", "Voulez vous effectuer une "+type, "oui", "non");
                    if(demande==true){
                        Reservation m=new Reservation();
                        m.setLatitude(MyApplication.userConnect.getLatitude());
                        m.setLongitude(MyApplication.userConnect.getLongitude());
                        
                        m.setLatitude2(latitude);
                        m.setLongitude2(longitude);
                        
                        m.setType_reservation(type);
                        m.setPrix(prix/1000000);
                        Client c=new Client();
                        c.setId_user(MyApplication.userConnect.getId_user());
                        m.setClient(c);
                        new ListChauffeur(current,m).show();
                        
                    }
         });
         submit.setUIID("submit");
         add(submit);
    }
    
}
>>>>>>> ghada
