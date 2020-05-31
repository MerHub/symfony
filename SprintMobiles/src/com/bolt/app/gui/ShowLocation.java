/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.bolt.app.entities.Client;

import com.bolt.app.entities.Location;
import com.bolt.app.entities.Velo;
import com.bolt.app.services.ServiceLocation;
import com.bolt.app.services.ServiceVelo;
import com.bolt.app.services.TwilioSMS;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import java.util.Date;





/**
 *
 * @author Belgaroui Ghazi
 */
public class ShowLocation extends Form{
     Form current;
    public ShowLocation(Form previous,Location location) {
        current=this;
         Toolbar tb=this.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
        setTitle("Velo");
        setUIID("registerPage");
                getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        Container l=new Container(BoxLayout.y());
        
       
        Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageok");
        messageError.setVisible(false);
        Label textError=new Label("");
        textError.setUIID("msgError");
        messageError.add(textError);
        
          Container block2 = new Container(BoxLayout.y());
         
         Label text2=new Label("Rent this bic now by just picking");
         text2.setUIID("text31");
         Label text3=new Label("the begin and end date");
         text3.setUIID("text31");
         
    Picker dateD = new Picker();
dateD.setType(Display.PICKER_TYPE_DATE_AND_TIME);
dateD.setUIID("input1");
Picker dateF = new Picker();
dateF.setType(Display.PICKER_TYPE_DATE_AND_TIME);
dateF.setUIID("input1");
  
 TextArea textArea = new TextArea();
 textArea.setUIID("input1");
 Button submit=new Button("Calculate");
                 submit.addActionListener(e-> {
                                
       
         SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm");
                try {
                    // DateFormatPatterns.ISO8601
                    Date datedebut = format.parse(dateD.getText());
                    Date datefin = format.parse(dateF.getText());
                    Double prix=((datefin.getTime()-datedebut.getTime())/3.6e+6)*indexClientPage.idVelo.getPrix();
                   textArea.setText(prix.toString());
                    
                    
                } catch (Exception ex) {
               }
        
                 });
                    
         submit.setUIID("submit");
         
    Button save=new Button("Save");
                 save.addActionListener(e-> {

                     
                Location t= new Location();
                if(textArea.getText().trim().equals("")){
                    
               messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* remplir les champs");
                                                                     $(() -> {
           $("messageErrorShow").fadeIn(); 
       });  
                 
                    }else{                 messageError.setUIID("messageok");
                        textError.setUIID("msgOk");
                        textError.setText("* opération terminé");
                                                $(() -> {
           $("messageok").fadeIn(); 
       });
               
                    Client s=new Client();
                s.setId_user(MyApplication.userConnect.getId_user());
                t.setClient(s);
               
               int nb=indexClientPage.idVelo.getQte();
               nb--;
              indexClientPage.idVelo.setQte(nb);
              t.setVelo(indexClientPage.idVelo);
                 
       
         SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm");
                try {
                    // DateFormatPatterns.ISO8601
                    Date datedebut = format.parse(dateD.getText());
                    Date datefin = format.parse(dateF.getText());
                    Double prix=((datefin.getTime()-datedebut.getTime())/3.6e+6)*indexClientPage.idVelo.getPrix();
                    t.setPrix(prix);
                    t.setDate_d(datedebut);
                    t.setDate_f(datefin);
                    
                    
                } catch (Exception ex) {
               }/*
               MyApplication.userConnect.toString();
                 TwilioSMS as=new TwilioSMS("AC5346e703451802ace20c8e74aea069d8","3d330337b7ad6ea602d2d290648d3c2b","+12076067580");
                 as.sendSmsAsync("+21693894029","Hello,your rented is conformed: "
                +MyApplication.userConnect.getLogin()+" Thank you for picking Drive");*/
                            
                ServiceLocation.getInstance().addLocation(t);
              }
           
        });
                   save.setUIID("submit");
   add(messageError);  
   
    block2.add(text2);
    block2.add(text3);
         
         
         block2.add(dateD);
         block2.add(dateF);
         block2.add(textArea);
         block2.add(submit);
         block2.add(save);
       this.add(block2);
          
        
        
        
        
        
        
        

    
    }
}
