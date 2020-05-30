<<<<<<< HEAD
<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.entities.PointMap;
import com.bolt.app.utils.Function;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class itineaire extends Form{

    Form current;
    public itineaire(Form prevoous,ArrayList<PointMap> route) {
       Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageErrorShow");
        messageError.setVisible(false);
        Label textError=new Label("* Username or password incorrect");
        textError.setUIID("msgError");
        messageError.add(textError);
       
        Toolbar tb=this.getToolbar();
        Container c=BorderLayout.east(new Label(""));
        c.setUIID("TopleftBarIndexpage1");
        setTitle("Routes");
        setUIID("itiPage");
        
        add(messageError);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prevoous.showBack());
        $("listRoute").setVisible(false);
                getTitleArea().setUIID("entetePageIndex");
        Container listRoute=new Container(BoxLayout.y());
        listRoute.setUIID("listRoute");
       for(int i=0;i<route.size();i++){
           Container gitem=new Container(BoxLayout.x());
           Container tTemps=new Container(BoxLayout.x());
           try {
               Image img = Image.createImage("/iconList.png");
               gitem.setUIID("gitem");
               gitem.add(img);
           } catch (IOException ex) {
           }
           Container item=new Container(BoxLayout.y());
           item.setUIID("item");
           
          SpanLabel  instruction=new SpanLabel ();
             instruction.setTextUIID("instruction");
             instruction.setText(route.get(i).getHtml_instructions());
          SpanLabel  time=new SpanLabel ();
             time.setTextUIID("time");  
             time.setText(String.valueOf(route.get(i).getDuration())+" seconds");
                       SpanLabel  distance=new SpanLabel ();
             distance.setTextUIID("distance");  
             distance.setText(String.valueOf(route.get(i).getDistance())+" meter");
             tTemps.addAll(time,distance);
                       SpanLabel  adresse=new SpanLabel ();
             adresse.setTextUIID("adresse");  
             if(route.get(i).getStart()!=null){
                    adresse.setText(Function.getInstance().getAdresse(route.get(i).getStart().getLatitude(),
                            route.get(i).getStart().getLongitude())); 
             }
                          if(route.get(i).getEnd()!=null){
                     adresse.setText(Function.getInstance().getAdresse(route.get(i).getEnd().getLatitude(),
                            route.get(i).getEnd().getLongitude()));  
             }
             item.add(instruction);
             if(route.get(i).getEnd()!=null){
              item.add(tTemps);   
             }
             item.add(adresse);
             gitem.add(item);
             listRoute.add(gitem);
             int h=500+i*500;

      }

             $(()->{
                 $("listRoute").fadeIn(500);
             });       
       add(listRoute);
                                    
        
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.entities.PointMap;
import com.bolt.app.utils.Function;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class itineaire extends Form{

    Form current;
    public itineaire(Form prevoous,ArrayList<PointMap> route) {
       Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageErrorShow");
        messageError.setVisible(false);
        Label textError=new Label("* Username or password incorrect");
        textError.setUIID("msgError");
        messageError.add(textError);
       
        Toolbar tb=this.getToolbar();
        Container c=BorderLayout.east(new Label(""));
        c.setUIID("TopleftBarIndexpage1");
        setTitle("Routes");
        setUIID("itiPage");
        
        add(messageError);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prevoous.showBack());
        $("listRoute").setVisible(false);
                getTitleArea().setUIID("entetePageIndex");
        Container listRoute=new Container(BoxLayout.y());
        listRoute.setUIID("listRoute");
       for(int i=0;i<route.size();i++){
           Container gitem=new Container(BoxLayout.x());
           Container tTemps=new Container(BoxLayout.x());
           try {
               Image img = Image.createImage("/iconList.png");
               gitem.setUIID("gitem");
               gitem.add(img);
           } catch (IOException ex) {
           }
           Container item=new Container(BoxLayout.y());
           item.setUIID("item");
           
          SpanLabel  instruction=new SpanLabel ();
             instruction.setTextUIID("instruction");
             instruction.setText(route.get(i).getHtml_instructions());
          SpanLabel  time=new SpanLabel ();
             time.setTextUIID("time");  
             time.setText(String.valueOf(route.get(i).getDuration())+" seconds");
                       SpanLabel  distance=new SpanLabel ();
             distance.setTextUIID("distance");  
             distance.setText(String.valueOf(route.get(i).getDistance())+" meter");
             tTemps.addAll(time,distance);
                       SpanLabel  adresse=new SpanLabel ();
             adresse.setTextUIID("adresse");  
             if(route.get(i).getStart()!=null){
                    adresse.setText(Function.getInstance().getAdresse(route.get(i).getStart().getLatitude(),
                            route.get(i).getStart().getLongitude())); 
             }
                          if(route.get(i).getEnd()!=null){
                     adresse.setText(Function.getInstance().getAdresse(route.get(i).getEnd().getLatitude(),
                            route.get(i).getEnd().getLongitude()));  
             }
             item.add(instruction);
             if(route.get(i).getEnd()!=null){
              item.add(tTemps);   
             }
             item.add(adresse);
             gitem.add(item);
             listRoute.add(gitem);
             int h=500+i*500;

      }

             $(()->{
                 $("listRoute").fadeIn(500);
             });       
       add(listRoute);
                                    
        
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

import com.bolt.app.entities.PointMap;
import com.bolt.app.utils.Function;
import com.codename1.components.SpanLabel;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class itineaire extends Form{

    Form current;
    public itineaire(Form prevoous,ArrayList<PointMap> route) {
       Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageErrorShow");
        messageError.setVisible(false);
        Label textError=new Label("* Username or password incorrect");
        textError.setUIID("msgError");
        messageError.add(textError);
       
        Toolbar tb=this.getToolbar();
        Container c=BorderLayout.east(new Label(""));
        c.setUIID("TopleftBarIndexpage1");
        setTitle("Routes");
        setUIID("itiPage");
        
        add(messageError);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> prevoous.showBack());
        $("listRoute").setVisible(false);
                getTitleArea().setUIID("entetePageIndex");
        Container listRoute=new Container(BoxLayout.y());
        listRoute.setUIID("listRoute");
       for(int i=0;i<route.size();i++){
           Container gitem=new Container(BoxLayout.x());
           Container tTemps=new Container(BoxLayout.x());
           try {
               Image img = Image.createImage("/iconList.png");
               gitem.setUIID("gitem");
               gitem.add(img);
           } catch (IOException ex) {
           }
           Container item=new Container(BoxLayout.y());
           item.setUIID("item");
           
          SpanLabel  instruction=new SpanLabel ();
             instruction.setTextUIID("instruction");
             instruction.setText(route.get(i).getHtml_instructions());
          SpanLabel  time=new SpanLabel ();
             time.setTextUIID("time");  
             time.setText(String.valueOf(route.get(i).getDuration())+" seconds");
                       SpanLabel  distance=new SpanLabel ();
             distance.setTextUIID("distance");  
             distance.setText(String.valueOf(route.get(i).getDistance())+" meter");
             tTemps.addAll(time,distance);
                       SpanLabel  adresse=new SpanLabel ();
             adresse.setTextUIID("adresse");  
             if(route.get(i).getStart()!=null){
                    adresse.setText(Function.getInstance().getAdresse(route.get(i).getStart().getLatitude(),
                            route.get(i).getStart().getLongitude())); 
             }
                          if(route.get(i).getEnd()!=null){
                     adresse.setText(Function.getInstance().getAdresse(route.get(i).getEnd().getLatitude(),
                            route.get(i).getEnd().getLongitude()));  
             }
             item.add(instruction);
             if(route.get(i).getEnd()!=null){
              item.add(tTemps);   
             }
             item.add(adresse);
             gitem.add(item);
             listRoute.add(gitem);
             int h=500+i*500;

      }

             $(()->{
                 $("listRoute").fadeIn(500);
             });       
       add(listRoute);
                                    
        
    }
    
}
>>>>>>> ghada
