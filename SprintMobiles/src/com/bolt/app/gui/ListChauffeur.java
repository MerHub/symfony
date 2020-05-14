<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import com.bolt.app.entities.Chauffeur;
import com.bolt.app.entities.PointMap;
import com.bolt.app.entities.Reservation;
import static com.bolt.app.gui.startPage.current;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.Util;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.bolt.app.gui.editProfilPage;
import com.bolt.app.services.ServiceChauffeur;
import com.bolt.app.services.ServiceUser;
import com.bolt.app.services.ServiceUtils;
import com.bolt.app.utils.Function;
import com.codename1.googlemaps.MapContainer.MapObject;
import static com.codename1.ui.ComponentSelector.$;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class ListChauffeur extends Form{
 MapContainer.MapObject sydney;
     private static final String HTML_API_KEY = "AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
    private Form current;
    boolean tapDisabled = false;
    public int i;
    public ListChauffeur(Form previous,Reservation m) {
current=this;
current.setTitle("Choose a taxi");
current.setUIID("indexPage");
current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 4, e-> previous.showBack());
current.getTitleArea().setUIID("entetePageIndex");

                setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setShowMyLocation(true);
        cnt.setMapType(3);
        
        
        
      
                FloatingActionButton btnNext = FloatingActionButton.createFAB(FontImage.MATERIAL_ARROW_RIGHT);
                btnNext.setVisible(false);
                btnNext.setUIID("btnNext");
        btnNext.addActionListener(e->{
                
        });

        
                Button btnMaposition = new Button(FontImage.MATERIAL_LOCATION_ON);
                btnMaposition.setUIID("maps");
        btnMaposition.addActionListener(e->{
            cnt.setCameraPosition(new Coord(MyApplication.userConnect.getLatitude(), MyApplication.userConnect.getLongitude()));
        });
        
                        Button btnZoomIn = new Button(FontImage.MATERIAL_ZOOM_IN);
                        btnZoomIn.setUIID("maps");
        btnZoomIn.addActionListener(e->{
            cnt.zoom(new Coord(cnt.getCameraPosition().getLatitude(),cnt.getCameraPosition().getLongitude()), (int) cnt.getZoom()+1);
        });
                                Button btnZoomOut = new Button(FontImage.MATERIAL_ZOOM_OUT);
                                btnZoomOut.setUIID("maps");
        btnZoomOut.addActionListener(e->{
            cnt.zoom(new Coord(cnt.getCameraPosition().getLatitude(),cnt.getCameraPosition().getLongitude()), (int) cnt.getZoom()-1);
        });
        
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(btnNext.bindFabToContainer(cnt)),
                                BorderLayout.north(
                BoxLayout.encloseY(btnMaposition,btnZoomIn,btnZoomOut )
                )
        );
        
        add(BorderLayout.CENTER, root);  
        
        
        //final MapContainer cnt = new MapContainer();
        cnt.addMapListener(new MapListener() {

            @Override
            public void mapPositionUpdated(Component source, int zoom, Coord center) {
            }
            
        });
                int maxZoom = cnt.getMaxZoom();
        Style s1 = new Style();
        Style s2 = new Style();
        s1.setFgColor(0x0080FF);
        s1.setBgTransparency(0);
        
        s2.setFgColor(0xF1C232);
        s2.setBgTransparency(0);
        
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s1, 7);
        FontImage markertaxi = FontImage.createMaterial(FontImage.MATERIAL_CAR_REPAIR, s2, 7);
        
        
        
        
        
        
         cnt.setCameraPosition(new Coord(MyApplication.userConnect.getLatitude(), MyApplication.userConnect.getLongitude()));
         cnt.zoom(new Coord(MyApplication.userConnect.getLatitude(), MyApplication.userConnect.getLongitude()), 18);
                                 try {
                                     cnt.addMarker(EncodedImage.create("/moi.png"), cnt.getCameraPosition(), "Hi marker", "Optional long description", new ActionListener() {
                                         public void actionPerformed(ActionEvent evt) {
                                             ToastBar.showMessage("Your position", FontImage.MATERIAL_PLACE);
                                         }
                                     });                  } catch (IOException ex) {
                                 }
            ArrayList<Chauffeur> list=ServiceChauffeur.getInstance().getListChauffeur();

                for(int i=0; i<list.size(); i++){
                if(list.get(i).getLatitude()!=0  && !list.get(i).getPhoto().trim().equals("") && list.get(i).getCin()!=0 && list.get(i).getId_user()!=0
                        && !list.get(i).getLogin().trim().equals("") && list.get(i).getN_tel()!=0 && !list.get(i).getNom().trim().equals("") && 
                        !list.get(i).getPrenom().trim().equals("") && 
                        !list.get(i).getPermis().trim().equals("")){
                    try {
                    Chauffeur j=list.get(i);
                    cnt.addMarker(EncodedImage.create("/iconTaxi.png"),new Coord(list.get(i).getLatitude(), list.get(i).getLongitude()),String.valueOf(i),String.valueOf(i),e2->{
                    m.setChauffeur(j);
                        new showChauffeur(current,j,m).show();
                    });
                    
                    
                    } catch (IOException ex) {
                    }
                }
                }
                

        
                
                
                
        MapContainer.MapObject mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-33.866, 151.195), "test", "test",e->{
            cnt.removeMapObject(sydney);
        });
        sydney = mo;
        mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-18.142, 178.431), "test", "test",e->{
            
        });
        cnt.addTapListener(e->{
            System.out.println("ouiii1");
           
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
import com.bolt.app.entities.Chauffeur;
import com.bolt.app.entities.PointMap;
import com.bolt.app.entities.Reservation;
import static com.bolt.app.gui.startPage.current;
import com.codename1.components.FloatingActionButton;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.Util;
import com.codename1.maps.BoundingBox;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.bolt.app.gui.editProfilPage;
import com.bolt.app.services.ServiceChauffeur;
import com.bolt.app.services.ServiceUser;
import com.bolt.app.services.ServiceUtils;
import com.bolt.app.utils.Function;
import com.codename1.googlemaps.MapContainer.MapObject;
import static com.codename1.ui.ComponentSelector.$;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class ListChauffeur extends Form{
 MapContainer.MapObject sydney;
     private static final String HTML_API_KEY = "AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
    private Form current;
    boolean tapDisabled = false;
    public int i;
    public ListChauffeur(Form previous,Reservation m) {
current=this;
current.setTitle("Choose a taxi");
current.setUIID("indexPage");
current.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, 4, e-> previous.showBack());
current.getTitleArea().setUIID("entetePageIndex");

                setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setShowMyLocation(true);
        cnt.setMapType(3);
        
        
        
      
                FloatingActionButton btnNext = FloatingActionButton.createFAB(FontImage.MATERIAL_ARROW_RIGHT);
                btnNext.setVisible(false);
                btnNext.setUIID("btnNext");
        btnNext.addActionListener(e->{
                
        });

        
                Button btnMaposition = new Button(FontImage.MATERIAL_LOCATION_ON);
                btnMaposition.setUIID("maps");
        btnMaposition.addActionListener(e->{
            cnt.setCameraPosition(new Coord(MyApplication.userConnect.getLatitude(), MyApplication.userConnect.getLongitude()));
        });
        
                        Button btnZoomIn = new Button(FontImage.MATERIAL_ZOOM_IN);
                        btnZoomIn.setUIID("maps");
        btnZoomIn.addActionListener(e->{
            cnt.zoom(new Coord(cnt.getCameraPosition().getLatitude(),cnt.getCameraPosition().getLongitude()), (int) cnt.getZoom()+1);
        });
                                Button btnZoomOut = new Button(FontImage.MATERIAL_ZOOM_OUT);
                                btnZoomOut.setUIID("maps");
        btnZoomOut.addActionListener(e->{
            cnt.zoom(new Coord(cnt.getCameraPosition().getLatitude(),cnt.getCameraPosition().getLongitude()), (int) cnt.getZoom()-1);
        });
        
        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(btnNext.bindFabToContainer(cnt)),
                                BorderLayout.north(
                BoxLayout.encloseY(btnMaposition,btnZoomIn,btnZoomOut )
                )
        );
        
        add(BorderLayout.CENTER, root);  
        
        
        //final MapContainer cnt = new MapContainer();
        cnt.addMapListener(new MapListener() {

            @Override
            public void mapPositionUpdated(Component source, int zoom, Coord center) {
            }
            
        });
                int maxZoom = cnt.getMaxZoom();
        Style s1 = new Style();
        Style s2 = new Style();
        s1.setFgColor(0x0080FF);
        s1.setBgTransparency(0);
        
        s2.setFgColor(0xF1C232);
        s2.setBgTransparency(0);
        
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s1, 7);
        FontImage markertaxi = FontImage.createMaterial(FontImage.MATERIAL_CAR_REPAIR, s2, 7);
        
        
        
        
        
        
         cnt.setCameraPosition(new Coord(MyApplication.userConnect.getLatitude(), MyApplication.userConnect.getLongitude()));
         cnt.zoom(new Coord(MyApplication.userConnect.getLatitude(), MyApplication.userConnect.getLongitude()), 18);
                                 try {
                                     cnt.addMarker(EncodedImage.create("/moi.png"), cnt.getCameraPosition(), "Hi marker", "Optional long description", new ActionListener() {
                                         public void actionPerformed(ActionEvent evt) {
                                             ToastBar.showMessage("Your position", FontImage.MATERIAL_PLACE);
                                         }
                                     });                  } catch (IOException ex) {
                                 }
            ArrayList<Chauffeur> list=ServiceChauffeur.getInstance().getListChauffeur();

                for(int i=0; i<list.size(); i++){
                if(list.get(i).getLatitude()!=0  && !list.get(i).getPhoto().trim().equals("") && list.get(i).getCin()!=0 && list.get(i).getId_user()!=0
                        && !list.get(i).getLogin().trim().equals("") && list.get(i).getN_tel()!=0 && !list.get(i).getNom().trim().equals("") && 
                        !list.get(i).getPrenom().trim().equals("") && 
                        !list.get(i).getPermis().trim().equals("")){
                    try {
                    Chauffeur j=list.get(i);
                    cnt.addMarker(EncodedImage.create("/iconTaxi.png"),new Coord(list.get(i).getLatitude(), list.get(i).getLongitude()),String.valueOf(i),String.valueOf(i),e2->{
                    m.setChauffeur(j);
                        new showChauffeur(current,j,m).show();
                    });
                    
                    
                    } catch (IOException ex) {
                    }
                }
                }
                

        
                
                
                
        MapContainer.MapObject mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-33.866, 151.195), "test", "test",e->{
            cnt.removeMapObject(sydney);
        });
        sydney = mo;
        mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-18.142, 178.431), "test", "test",e->{
            
        });
        cnt.addTapListener(e->{
            System.out.println("ouiii1");
           
        });

        
    }
    
}
>>>>>>> master
