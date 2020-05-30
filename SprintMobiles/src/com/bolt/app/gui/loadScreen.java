/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.demos.GifImage;
import static com.codename1.ui.CN.getResourceAsStream;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UITimer;
import java.io.IOException;

/**
 *
 * @author armand
 */
public class loadScreen extends Form{

    Form current;
    public loadScreen(Object page) {
        current=this;
        Container load=new Container(BoxLayout.y());
        load.setUIID("load");
              Label textLoad=new Label("Pleace wait ...");
      textLoad.setUIID("textLoad");
        try {
      ScaleImageLabel b= new ScaleImageLabel(GifImage.decode(getResourceAsStream("/loader.gif"), 1177720));
    load.add(b);
    load.add(textLoad);
} catch(IOException err) {
    System.out.println("<-------------");
}
        
        add(load);
                        new UITimer(new Runnable() {
                    public void run() 
                    {   
                          if(page instanceof startPage){
                              
                                                      $(() -> {
           $("load").fadeOut(2000); 
           new startPage().show();
       });
                                                      
                              current.getContentPane().animateLayout(2000);
                             // new startPage().getContentPane().animateLayout(2000);
                          }
                          if(page instanceof indexClientPage){
                                                       $(() -> {
           $("load").fadeOut(2000); 
           new indexClientPage().show();
       });                             
                          }
                                                    if(page instanceof indexChauffeurPage){
                                                       $(() -> {
           $("load").fadeOut(2000); 
           new indexChauffeurPage().show();
       });                             
                          }
                                                                              if(page instanceof editProfilPage){
                                                       $(() -> {
           $("load").fadeOut(2000); 
           new editProfilPage().show();
       });                             
                          }

                    }
                }).schedule(2500, false, current);
    }
    
}
