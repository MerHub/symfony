/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.bolt.app.entities.CategoriesTaxi;
import com.bolt.app.entities.Chauffeur;
import static com.bolt.app.gui.startPage.current;
import com.bolt.app.services.ServiceChauffeur;
import com.bolt.app.services.ServiceTaxi;
import com.bolt.app.services.ServiceUser;
import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class editProfilPage extends Form{
    private Form current;
        public String path="rien";
    public editProfilPage() {
        Chauffeur cx=new Chauffeur();
        if(MyApplication.userConnect.getType().equals("chauffeur")){
             ArrayList<Chauffeur> list=ServiceChauffeur.getInstance().getListChauffeur();
             for(int i=0;i<list.size();i++){
                 if(list.get(i).getId_user()==MyApplication.userConnect.getId_user()){
                  cx=list.get(i);   
                 }
             }
             
        }
        current=this;
        current.setTitle("Edit Profil");
        current.setUIID("indexPage");
        
        Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageok");
        messageError.setVisible(false);
        Label textError=new Label("");
        textError.setUIID("msgOk");
        messageError.add(textError);
        

Toolbar tb=current.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
tb.addComponentToSideMenu(c);
Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_ACCOUNT_BOX, s);
tb.addCommandToSideMenu("DashBoard",searchIcon,e->new indexClientPage().showBack());

        getTitleArea().setUIID("entetePageIndex");
        
         Container block2 = new Container(BoxLayout.y());
         block2.setUIID("headEditProfil");
         Button btn1=new Button(FontImage.MATERIAL_LOGOUT);
         btn1.addActionListener(e->{
             MyApplication.userConnect=null;
             new loginPage(new startPage()).showBack();
         });
         Button btn2=new Button(FontImage.MATERIAL_CAMERA_ALT);
                  if(MyApplication.userConnect.getType().equals("client")){
          btn1.setUIID("btnDisconnect1");   
         }else{
          btn1.setUIID("btnDisconnect");            
                  }
         
         btn2.setUIID("btnimage");
         block2.add(btn1);
         if(!MyApplication.userConnect.getType().equals("client")){
          block2.add(btn2);   
         }
         
         
         Container block3 = new Container(BoxLayout.x());
         
         if(MyApplication.userConnect.getType().equals("client")){
         block3.setUIID("headEditClient");    
         }
         if(MyApplication.userConnect.getType().equals("chauffeur")){
         block3.setUIID("headEditProfil1");    
         }
         Container block4 = new Container(BoxLayout.y());
         block4.setUIID("headEditProfil2");
         block4.add(block3);
         Button btnEdit=new Button(" ");
         if(MyApplication.userConnect.getType().equals("chauffeur")){
          btnEdit.setUIID("bouttonEdit");   
         }
                  if(MyApplication.userConnect.getType().equals("client")){
          btnEdit.setUIID("bouttonEdit1");   
         }
         
         Button btnDriver=new Button(" ");
         btnDriver.setUIID("bouttonDriver");
         Button btnCar=new Button(" ");
         btnCar.setUIID("btnCar"); 
         
                    
         block3.add(btnEdit);
        if(MyApplication.userConnect.getType().equals("chauffeur")){
                     btnDriver.addActionListener(e->{
           block3.setUIID("headEditChauffeur");
           this.refreshTheme();
       });
        
        
         block3.add(btnDriver);
         block3.add(btnCar); 
         }
        
        
        
        
        
        
        
        
        
        
        
        Container blockEditProfil = new Container(BoxLayout.y());
        blockEditProfil.setUIID("blockEditProfil");
                 TextField username=new TextField("","Username");
         username.getHintLabel().setUIID("inter1");
         username.setUIID("input1");
         
         TextField Email=new TextField("","Email");
         Email.getHintLabel().setUIID("inter1");
         Email.setUIID("input1");
         
         TextField numero=new TextField("","Number");
         numero.getHintLabel().setUIID("inter1");
         numero.setUIID("input1");
         if(MyApplication.userConnect.getN_tel()!=0){
            numero.setText(Integer.toString(MyApplication.userConnect.getN_tel()));
         }
         TextField password=new TextField("","Current password");
         password.getHintLabel().setUIID("inter1");
         password.setUIID("input1");
         username.setText(MyApplication.userConnect.getLogin());
         Email.setText(MyApplication.userConnect.getMail());
         if(MyApplication.userConnect.getN_tel()!=0){
          numero.setText(Integer.toString(MyApplication.userConnect.getN_tel()));   
         }
         blockEditProfil.add(username);
         blockEditProfil.add(Email);
         blockEditProfil.add(numero);
         blockEditProfil.add(password);
         password.setConstraint(TextField.PASSWORD);
         
         Button saveEdit=new Button("Save ",FontImage.MATERIAL_CHECK,"re");
         saveEdit.setUIID("saveEdit");
         saveEdit.addActionListener(e-> {
             if(numero.getText().trim().equals("") || password.getText().trim().equals("") || username.getText().trim().equals("") || Email.getText().trim().equals("")){
                               messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* Remplir tous les champs");
         $(() -> {
           $("messageErrorShow").fadeIn(); 
       });                 
             }else{
                         String retour=  ServiceUser.getInstance().setUser(username.getText(),password.getText(),Email.getText(),numero.getText(),MyApplication.userConnect.getId_user());
           
           if(retour.equals("finish")){
              messageError.setUIID("messageok");
                        textError.setUIID("msgOk");
                        textError.setText("* opération terminé");
                                                $(() -> {
           $("messageok").fadeIn(); 
       }); 
           }else{
                               messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* "+retour);
         $(() -> {
           $("messageErrorShow").fadeIn(); 
       });     
           }   
             }
         });
         
         Button changePassword=new Button("change password ",FontImage.MATERIAL_ARROW_RIGHT,"re");
         changePassword.setUIID("changepass");
         
         blockEditProfil.add(saveEdit);
         blockEditProfil.add(changePassword);
         add(block2);
         add(messageError);
         add(block4);
        add(blockEditProfil);
        
         $(() -> {
             $("headEditProfil2").fadeIn();
           $("blockEditProfil").slideDown(); 
       });
         
        Container blockEditProfil2 = new Container(BoxLayout.y());
        blockEditProfil2.setUIID("blockEditProfil2");
        
         TextField adresse=new TextField("","Adresse");
         adresse.getHintLabel().setUIID("inter1");
         adresse.setUIID("input1");
         blockEditProfil2.add(adresse);
         adresse.setText(cx.getAdresse());
         
         TextField cin=new TextField("","Cin");
         cin.getHintLabel().setUIID("inter1");
         cin.setUIID("input1");
         blockEditProfil2.add(cin);
         cin.setText(String.valueOf(cx.getCin()));
         
         TextField permis=new TextField("","Permis");
         permis.getHintLabel().setUIID("inter1");
         permis.setUIID("input1");
         blockEditProfil2.add(permis);
         permis.setText(cx.getPermis());
         
         TextField nom=new TextField("","Nom");
         nom.getHintLabel().setUIID("inter1");
         nom.setUIID("input1");
         blockEditProfil2.add(nom);
         nom.setText(cx.getNom());
         
         TextField prenom=new TextField("","prenom");
         prenom.getHintLabel().setUIID("inter1");
         prenom.setUIID("input1");
         blockEditProfil2.add(prenom);
         prenom.setText(cx.getPrenom());
         
         Button saveImage=new Button(FontImage.MATERIAL_CAMERA_ALT);
         saveImage.setUIID("saveImage");
         blockEditProfil2.add(saveImage);
         saveImage.addActionListener(e->{
             path = Capture.capturePhoto();
             new ShowImage(current, path).show();
             path=path.replace('/', '&');
         });
         Button saveEdits=new Button("Save ",FontImage.MATERIAL_CHECK,"re");
         saveEdits.setUIID("saveEdit2");
         blockEditProfil2.add(saveEdits);
         blockEditProfil2.setHidden(true);
         add(blockEditProfil2);
         saveEdits.addActionListener(e->{
             if(adresse.getText().trim().equals("") || cin.getText().trim().equals("") || nom.getText().trim().equals("") || prenom.getText().trim().equals("") ||
                     adresse.getText().trim().equals("")){
                                                              messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* remplir tous les champs");
         $(() -> {
           $("messageErrorShow").fadeIn(); 
       });              
             }else{
           
                   String reponse=   ServiceChauffeur.getInstance().setChauffeur(adresse.getText(),Integer.parseInt(cin.getText()), permis.getText(), nom.getText(), prenom.getText(), path, MyApplication.userConnect.getId_user()) ;
             if(reponse!="ok"){
                                                messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* "+reponse);
         $(() -> {
           $("messageErrorShow").fadeIn(); 
       });
             }else{
                               messageError.setUIID("messageok");
                        textError.setUIID("msgOk");
                        textError.setText("* opération terminé");
                                                $(() -> {
           $("messageok").fadeIn(); 
       }); 
             } 
             }

         });
        Container blockEditProfil3 = new Container(BoxLayout.y());
        blockEditProfil3.setUIID("blockEditProfil3");
        
         TextField NumChassis=new TextField("","NumChassis");
         NumChassis.getHintLabel().setUIID("inter1");
         NumChassis.setUIID("input1");
         blockEditProfil3.add(NumChassis);  

         
         ArrayList<CategoriesTaxi> l=ServiceTaxi.getInstance().getCategorieTaxi();
         ComboBox listeCat= new ComboBox();
         for(int i=0;i<l.size();i++){
             listeCat.addItem(String.valueOf(l.get(i).getId()));
         }
         listeCat.setUIID("input1");
         blockEditProfil3.add(listeCat);
         
         Button saveTaxi=new Button("Save ",FontImage.MATERIAL_CHECK,"re");
         saveTaxi.setUIID("saveEdit2");
         blockEditProfil3.add(saveTaxi);        
         blockEditProfil3.setHidden(true);
         add(blockEditProfil3);
         saveTaxi.addActionListener(e->{
             if(NumChassis.getText().trim().equals("")){
                                                                 messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* remplir tous les champs");
         $(() -> {
           $("messageErrorShow").fadeIn(); 
       });
             }else{
                 ServiceChauffeur.getInstance().setTaxi(MyApplication.userConnect.getId_user(),Integer.parseInt(listeCat.getSelectedItem().toString()), NumChassis.getText());
                                            messageError.setUIID("messageok");
                        textError.setUIID("msgOk");
                        textError.setText("* opération terminé");
                                                $(() -> {
           $("messageok").fadeIn(); 
       }); 
             }
         });
         
         
         if(MyApplication.userConnect.getType().equals("chauffeur")){
                  btnCar.addActionListener(e->{
           block3.setUIID("headEditCar");
           blockEditProfil.setHidden(true);
           blockEditProfil2.setHidden(true);
           blockEditProfil3.setHidden(false);
           this.refreshTheme();
       });
                btnEdit.addActionListener(e->{
                    if(MyApplication.userConnect.getType().equals("chauffeur")){
                      block3.setUIID("headEditProfil1");  
           blockEditProfil.setHidden(false);
           blockEditProfil2.setHidden(true);
           blockEditProfil3.setHidden(true);
                    }
           this.refreshTheme();
       });
                
                                     btnDriver.addActionListener(e->{
           block3.setUIID("headEditChauffeur");
           blockEditProfil.setHidden(true);
           blockEditProfil2.setHidden(false);
           blockEditProfil3.setHidden(true);
           this.refreshTheme();
       });   
         }
            
         
         
    }
    
}
