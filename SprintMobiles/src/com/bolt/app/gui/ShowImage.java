/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.bolt.app.utils.Statics;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import java.io.IOException;

/**
 *
 * @author armand
 */
public class ShowImage extends Form{

    public ShowImage(Form previous,String photo) {
                        Toolbar tb=this.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
        setTitle("Upload image");
        setUIID("registerPage");
                getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        EncodedImage enc;
        try {
            enc = EncodedImage.create("/load.gif");
                        //image avec serveur
            Image imgs = Image.createImage(photo);
            ImageViewer imgv = new ImageViewer(imgs);
            add(imgv);
        } catch (IOException ex) {
        }
    }
    
    
}
