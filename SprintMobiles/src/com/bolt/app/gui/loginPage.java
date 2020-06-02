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
import static com.codename1.ui.CN.CENTER;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author armand
 */
public class loginPage extends Form {

    public loginPage( Form previous ) {
        
        Container messageError=new Container(BoxLayout.y());
        messageError.setUIID("messageErrorShow");
        messageError.setVisible(false);
        Label textError=new Label("* Username or password incorrect");
        textError.setUIID("msgError");
        messageError.add(textError);
       
        Toolbar tb=this.getToolbar();
        Container c=BorderLayout.east(new Label(""));
        c.setUIID("TopleftBarIndexpage1");
        setTitle("Back to home");
        setUIID("loginPage");
        
        add(messageError);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> new indexClientPage().showBack());

        Container block1=new Container(new FlowLayout(CENTER));
        block1.setUIID("block11");
        Label text1=new Label("Don't have an account ?");
        Button btnRegister=new Button("REGISTER");
        btnRegister.addActionListener(e-> new registerPage(this).show());
        btnRegister.setUIID("btnRegister");
        text1.setUIID("text1LoginPage");
        block1.add(text1);
        block1.add(btnRegister);
        
        Container block2 = new Container(BoxLayout.y());
         block2.setUIID("block2");
         Label text2=new Label("Enter your email and password below to log");
         text2.setUIID("text2");
         Label text3=new Label("in to your account and use the benefits of our");
         text3.setUIID("text3");
         Label text4=new Label("services .");
         text4.setUIID("text4");
         
         TextField username=new TextField("","Usernme");
         username.getHintLabel().setUIID("inter1");
         username.setUIID("input1");
         TextField password=new TextField("","Password");
         password.setUIID("input2");
         password.getHintLabel().setUIID("inter2");
         password.setConstraint(TextField.PASSWORD);
         
         Button submit=new Button("Sign In");
                 submit.addActionListener(e-> {
                     if(!username.getText().trim().equals("") && !password.getText().trim().equals("")){
                      
                    User u=new User();
                    u= ServiceUser.getInstance().getUser(username.getText(),password.getText());
                    if(u!=null){
                        $(() -> {
           $("messageErrorShow").slideUp(); 
       });
                        MyApplication.userConnect=u;
                        System.out.println(u+"<------------------");
                        if(u.getN_tel()!=0){
                        if(u.getType().equals("client")){
                            new indexClientPage().show();
                        }else{
                            new indexChauffeurPage().show();
                        }                            
                        }else{
                            new editProfilPage().show();
                        }

                    }else{
                        textError.setText("* Username or password incorrect");
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
                    this.refreshTheme();
                    
                 });
                    
         submit.setUIID("submit");
         
         block2.add(text2);
         block2.add(text3);
         block2.add(text4);
         
         block2.add(username);
         block2.add(password);
         block2.add(submit);
    
        add(block1);
        add(block2);
               $(() -> {
           $("block11,block2").fadeIn(); 
       });
        getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
