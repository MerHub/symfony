/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.codename1.ui.Button;
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

/**
 *
 * @author armand
 */
public class editProfilPage extends Form{
    private Form current;
    public editProfilPage() {
        current=this;
        current.setTitle("Edit Profil");
        current.setUIID("indexPage");

Toolbar tb=current.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
tb.addComponentToSideMenu(c);
Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_ACCOUNT_BOX, s);

        getTitleArea().setUIID("entetePageIndex");
        
         Container block2 = new Container(BoxLayout.y());
         block2.setUIID("headEditProfil");
         Button btn1=new Button(FontImage.MATERIAL_LOGOUT);
         Button btn2=new Button(FontImage.MATERIAL_CAMERA_ALT);
         btn1.setUIID("btnDisconnect");
         btn2.setUIID("btnimage");
         block2.add(btn1);
         block2.add(btn2);
         
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
         
                btnEdit.addActionListener(e->{
                    if(MyApplication.userConnect.getType().equals("chauffeur")){
                      block3.setUIID("headEditProfil1");  
                    }
           this.refreshTheme();
       });
               
                 
         block3.add(btnEdit);
        if(MyApplication.userConnect.getType().equals("chauffeur")){
                     btnDriver.addActionListener(e->{
           block3.setUIID("headEditChauffeur");
           this.refreshTheme();
       });
        btnCar.addActionListener(e->{
           block3.setUIID("headEditCar");
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
         
         Button saveEdit=new Button("Save ",FontImage.MATERIAL_CHECK,"re");
         saveEdit.setUIID("saveEdit");
         
         Button changePassword=new Button("change password ",FontImage.MATERIAL_ARROW_RIGHT,"re");
         changePassword.setUIID("changepass");
         
         blockEditProfil.add(saveEdit);
         blockEditProfil.add(changePassword);
         add(block2);
         add(block4);
         
         add(blockEditProfil);
    }
    
}
