<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.bolt.app.entities.User;
import com.bolt.app.services.ServiceUser;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author armand
 */
public class registerPage extends Form {

    public String type="client";
    public registerPage(Form previous) {
        
                Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageok");
        messageError.setVisible(false);
        Label textError=new Label("");
        textError.setUIID("msgError");
        messageError.add(textError);
        
                Toolbar tb=this.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
        setTitle("Sign In");
        setUIID("registerPage");
                getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        Container block2 = new Container(BoxLayout.y());
         block2.setUIID("blockRegister");
         Label text2=new Label("Register a free account on our website");
         text2.setUIID("text2");
         Label text3=new Label(" to experience the benefits of our services.");
         text3.setUIID("text3");
         
         TextField username=new TextField("","Usernme");
         username.getHintLabel().setUIID("inter1");
         username.setUIID("input1");
         TextField Email=new TextField("","Email");
         Email.getHintLabel().setUIID("inter1");
         Email.setUIID("input1");
         TextField password=new TextField("","Password");
         password.setUIID("input1");
         TextField Cpassword=new TextField("","Confirm Password");
         Cpassword.setUIID("input1");
         password.getHintLabel().setUIID("inter2");
         password.setConstraint(TextField.PASSWORD);
         Cpassword.getHintLabel().setUIID("inter2");
         Cpassword.setConstraint(TextField.PASSWORD);
         
         RadioButton driverCheck = new RadioButton("Driver");
         driverCheck.addActionListener(e-> {
             type="chauffeur";
         });
         RadioButton clientCheck = new RadioButton("Client");
         new ButtonGroup(driverCheck, clientCheck);
         clientCheck.setSelected(true);
                  clientCheck.addActionListener(e-> {
          type="client";
         });
         Button submit=new Button("Create My Free Account");
         submit.setUIID("submit1"); 
         submit.addActionListener(e-> {
             if(password.getText().equals(Cpassword.getText())){
             
                    if(!username.getText().trim().equals("") && !Email.getText().trim().equals("") && !password.getText().trim().equals("")){
                        Boolean r= ServiceUser.getInstance().addUser(username.getText(),password.getText(),Email.getText(),type);
                    if(r!=false){
                        messageError.setUIID("messageok");
                        textError.setUIID("msgOk");
                        textError.setText("* opération terminé");
                                                $(() -> {
           $("messageok").fadeIn(); 
       });
                    }else{
                     messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* "+MyApplication.messageGobal);
                                                                     $(() -> {
           $("messageErrorShow").fadeIn(); 
       });
                    }
                    }else{
                        
                     messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* remplir les champs");
                                                                     $(() -> {
           $("messageErrorShow").fadeIn(); 
       });      
                    }
         }else{
                     messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* confirmation incorrect");
                                                                     $(() -> {
           $("messageErrorShow").fadeIn(); 
       });                 
             }
                    this.refreshTheme();
                    
                 });
         
         block2.add(text2);
         block2.add(text3);
         
         block2.add(username);
         block2.add(Email);
         block2.add(password);
         block2.add(Cpassword);
         Container choix=new Container(new FlowLayout(CENTER));
         choix.setUIID("choix");
         driverCheck.setUIID("choixDriver");
         clientCheck.setUIID("choixClient");
         choix.add(clientCheck);
         choix.add(driverCheck);
         block2.add(choix);
         block2.add(submit);
         block2.setVisible(false);
         
                        $(() -> {
           $("blockRegister").fadeIn(); 
       });
         add(messageError);  
         add(block2);
                
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
import com.bolt.app.entities.User;
import com.bolt.app.services.ServiceUser;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author armand
 */
public class registerPage extends Form {

    public String type="client";
    public registerPage(Form previous) {
        
                Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageok");
        messageError.setVisible(false);
        Label textError=new Label("");
        textError.setUIID("msgError");
        messageError.add(textError);
        
                Toolbar tb=this.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
        setTitle("Sign In");
        setUIID("registerPage");
                getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        Container block2 = new Container(BoxLayout.y());
         block2.setUIID("blockRegister");
         Label text2=new Label("Register a free account on our website");
         text2.setUIID("text2");
         Label text3=new Label(" to experience the benefits of our services.");
         text3.setUIID("text3");
         
         TextField username=new TextField("","Usernme");
         username.getHintLabel().setUIID("inter1");
         username.setUIID("input1");
         TextField Email=new TextField("","Email");
         Email.getHintLabel().setUIID("inter1");
         Email.setUIID("input1");
         TextField password=new TextField("","Password");
         password.setUIID("input1");
         TextField Cpassword=new TextField("","Confirm Password");
         Cpassword.setUIID("input1");
         password.getHintLabel().setUIID("inter2");
         password.setConstraint(TextField.PASSWORD);
         Cpassword.getHintLabel().setUIID("inter2");
         Cpassword.setConstraint(TextField.PASSWORD);
         
         RadioButton driverCheck = new RadioButton("Driver");
         driverCheck.addActionListener(e-> {
             type="chauffeur";
         });
         RadioButton clientCheck = new RadioButton("Client");
         new ButtonGroup(driverCheck, clientCheck);
         clientCheck.setSelected(true);
                  clientCheck.addActionListener(e-> {
          type="client";
         });
         Button submit=new Button("Create My Free Account");
         submit.setUIID("submit1"); 
         submit.addActionListener(e-> {
             if(password.getText().equals(Cpassword.getText())){
             
                    if(!username.getText().trim().equals("") && !Email.getText().trim().equals("") && !password.getText().trim().equals("")){
                        Boolean r= ServiceUser.getInstance().addUser(username.getText(),password.getText(),Email.getText(),type);
                    if(r!=false){
                        messageError.setUIID("messageok");
                        textError.setUIID("msgOk");
                        textError.setText("* opération terminé");
                                                $(() -> {
           $("messageok").fadeIn(); 
       });
                    }else{
                     messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* "+MyApplication.messageGobal);
                                                                     $(() -> {
           $("messageErrorShow").fadeIn(); 
       });
                    }
                    }else{
                        
                     messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* remplir les champs");
                                                                     $(() -> {
           $("messageErrorShow").fadeIn(); 
       });      
                    }
         }else{
                     messageError.setUIID("messageErrorShow");
                     textError.setUIID("msgError");
                     textError.setText("* confirmation incorrect");
                                                                     $(() -> {
           $("messageErrorShow").fadeIn(); 
       });                 
             }
                    this.refreshTheme();
                    
                 });
         
         block2.add(text2);
         block2.add(text3);
         
         block2.add(username);
         block2.add(Email);
         block2.add(password);
         block2.add(Cpassword);
         Container choix=new Container(new FlowLayout(CENTER));
         choix.setUIID("choix");
         driverCheck.setUIID("choixDriver");
         clientCheck.setUIID("choixClient");
         choix.add(clientCheck);
         choix.add(driverCheck);
         block2.add(choix);
         block2.add(submit);
         block2.setVisible(false);
         
                        $(() -> {
           $("blockRegister").fadeIn(); 
       });
         add(messageError);  
         add(block2);
                
    }
    
}
>>>>>>> master
