<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getResourceAsStream;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;

/**
 *
 * @author armand
 */
public class startPage extends Form{
public static Form current;
    public startPage() {
        current=this;
        current.setTitle("WELCOME");
current.setUIID("indexPage");

Toolbar tb=current.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
tb.addComponentToSideMenu(c);
Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_ACCOUNT_BOX, s);
tb.addCommandToSideMenu("Connection profile",searchIcon,e->new loginPage(current).show());
current.getTitleArea().setUIID("entetePageIndex");

  
  Container deSign1=new Container(BoxLayout.y());
  Container img1=new Container(BoxLayout.y());
  img1.setUIID("mg1");
  img1.setVisible(false);
  Container img2=new Container(BoxLayout.y());
  deSign1.setUIID("deSign1");
         Image image1=MyApplication.theme.getImage("debut1.png");
         Image image2=MyApplication.theme.getImage("debut2.png");
       img1.add(image1);
       img2.add(image2);
       deSign1.add(img1);
       deSign1.add(img2);     
   current.add(deSign1);
       $(() -> {
           $("mg1").fadeIn(); 
       });
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
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getResourceAsStream;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;

/**
 *
 * @author armand
 */
public class startPage extends Form{
public static Form current;
    public startPage() {
        current=this;
        current.setTitle("WELCOME");
current.setUIID("indexPage");

Toolbar tb=current.getToolbar();
Container c=BorderLayout.east(new Label(""));
c.setUIID("TopleftBarIndexpage1");
tb.addComponentToSideMenu(c);
Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_ACCOUNT_BOX, s);
tb.addCommandToSideMenu("Connection profile",searchIcon,e->new loginPage(current).show());
current.getTitleArea().setUIID("entetePageIndex");

  
  Container deSign1=new Container(BoxLayout.y());
  Container img1=new Container(BoxLayout.y());
  img1.setUIID("mg1");
  img1.setVisible(false);
  Container img2=new Container(BoxLayout.y());
  deSign1.setUIID("deSign1");
         Image image1=MyApplication.theme.getImage("debut1.png");
         Image image2=MyApplication.theme.getImage("debut2.png");
       img1.add(image1);
       img2.add(image2);
       deSign1.add(img1);
       deSign1.add(img2);     
   current.add(deSign1);
       $(() -> {
           $("mg1").fadeIn(); 
       });
    }
    
    
}
>>>>>>> master
