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
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class indexClientPage extends Form{
 MapContainer.MapObject sydney;
     private static final String HTML_API_KEY = "AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
    private Form current;
    boolean tapDisabled = false;
    Coord selected=new Coord(0, 0);
    public ArrayList<PointMap> route;
    
    public indexClientPage(){
                             current=this;
        current.setTitle("WELCOME");
        current.setUIID("indexPage");

        Toolbar tb=current.getToolbar();
        Container c=BorderLayout.east(new Label(""));
        c.setUIID("TopleftBarIndexpage1");
        tb.addComponentToSideMenu(c);
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_ACCOUNT_BOX, s);
        current.getTitleArea().setUIID("entetePageIndex");
        if(MyApplication.userConnect.getN_tel()==0){
                     Button changePassword=new Button("completer vos infos ",FontImage.MATERIAL_ARROW_RIGHT,"re");
         changePassword.setUIID("changepass");
         add(changePassword);
         changePassword.addActionListener(e-> {
            new editProfilPage().show(); 
         });
            
        }else{

        getTitleArea().setUIID("entetePageIndex");current.getTitleArea().setUIID("entetePageIndex");
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
        current=this;
        setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setShowMyLocation(true);
        cnt.setMapType(3);
        
        
        
      
                FloatingActionButton btnNext = FloatingActionButton.createFAB(FontImage.MATERIAL_ARROW_RIGHT);
                btnNext.setVisible(false);
                btnNext.setUIID("btnNext");
        btnNext.addActionListener(e->{
            new detailCoordonne(current,selected.getLatitude(),selected.getLongitude(),route).show();
                
        });


                Button btnClose = new Button(FontImage.MATERIAL_CLOSE);
                btnClose.setUIID("btnClose");
                btnClose.setVisible(false);
        
                        Button btnRoute = new Button(FontImage.MATERIAL_TRAFFIC);
                btnRoute.setUIID("btnRoute");
                btnRoute.setVisible(false);
        btnRoute.addActionListener(e->{
            new itineaire(current,route).show();
        });
        
        btnClose.addActionListener(e->{
            btnRoute.setVisible(false);
            btnNext.setVisible(false);
            btnClose.setVisible(false);
            new indexClientPage().show();
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
                ),
                                BorderLayout.south(BoxLayout.encloseY(btnRoute,btnClose))
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
                System.out.println(list+"------------");
                for(int i=0; i<list.size(); i++){
                    //liste.get(i)
                String txt = "oui";
                if(list.get(i).getLatitude()!=0  && !list.get(i).getPhoto().trim().equals("") && list.get(i).getCin()!=0 && list.get(i).getId_user()!=0
                        && !list.get(i).getLogin().trim().equals("") && list.get(i).getN_tel()!=0 && !list.get(i).getNom().trim().equals("") && 
                        !list.get(i).getPrenom().trim().equals("") && 
                        !list.get(i).getPermis().trim().equals("")){
                    try {
                        Chauffeur cs=list.get(i);
                        cnt.addMarker(EncodedImage.create("/iconTaxi.png"),new Coord(list.get(i).getLatitude(), list.get(i).getLongitude()), txt, "", e3->{
                            new showChauffeur(current, cs, null).show();  
                        }); } catch (IOException ex) {
                    }
                }
                }
                
        Style s3 = new Style();
        s3.setFgColor(0xDF2A2A);
        s3.setBgTransparency(0);
        FontImage markerDest = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s3, 7);
        cnt.addLongPressListener(e->{
            ToastBar.showMessage("Received longPress at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
        cnt.addTapListener(e->{
            
            try {
                cnt.clearMapLayers();
                cnt.addMarker(EncodedImage.create("/moi.png"),new Coord(MyApplication.userConnect.getLatitude(), MyApplication.userConnect.getLongitude()), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        ToastBar.showMessage("Your position", FontImage.MATERIAL_PLACE);
                    }
                });
                for(int i=0; i<list.size(); i++){
                    String txt = "oui";
                    if(list.get(i).getLatitude()!=0  && !list.get(i).getPhoto().trim().equals("") && list.get(i).getCin()!=0 && list.get(i).getId_user()!=0
                        && !list.get(i).getLogin().trim().equals("") && list.get(i).getN_tel()!=0 && !list.get(i).getNom().trim().equals("") && 
                        !list.get(i).getPrenom().trim().equals("") && 
                        !list.get(i).getPermis().trim().equals("")){
                        
                        Chauffeur cs=list.get(i);
                        cnt.addMarker(EncodedImage.create("/iconTaxi.png"),new Coord(list.get(i).getLatitude(), list.get(i).getLongitude()), txt, "", e3->{
                            new showChauffeur(current, cs, null).show();
                        });
                    }
                }
                String adrs=Function.getInstance().getAdresse(
                        cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude(), cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude());
                
                
                // tracer le chemin
                route=ServiceUtils.getInstance().getAllRoutes(MyApplication.userConnect.getLatitude(),
                        MyApplication.userConnect.getLongitude(),
                        cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude(),
                        cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude());
                            
                            Coord[] myArray = new Coord[route.size()];
                                 
                            for(int i=0;i<route.size();i++){
                                if(route.get(i).getStart()!=null){
                                myArray[i]=route.get(i).getStart();     
                                }
                                if(route.get(i).getEnd()!=null){
                                myArray[i]=route.get(i).getEnd();     
                                }
                                
                             }
                            
                            cnt.setPathStrokeColor(0x7272F2);
                            cnt.setPathStrokeWidth(10);
                                     
                          cnt.addPath(myArray);

                            
                            
                            
                ToastBar.showMessage(adrs, FontImage.MATERIAL_PLACE);
                                    selected.setLatitude(cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude());
                    selected.setLongitude(cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude());
                cnt.addMarker(EncodedImage.create("/destination.png"),cnt.getCoordAtPosition(e.getX(), e.getY()), "destination", "", e3->{
                    new detailCoordonne(current,cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude(),cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude(),route).show();
                });
                                                        $(() -> {
           $("btnNext,btnClose,btnRoute").fadeIn(); 
       });
                
                // System.out.println(cnt.getComponentAt(cnt.getComponentCount()));
            } catch (IOException ex) {
            }
        });
        
                
                
                
        MapContainer.MapObject mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-33.866, 151.195), "test", "test",e->{
            cnt.removeMapObject(sydney);
        });
        sydney = mo;
        mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-18.142, 178.431), "test", "test",e->{
        });
        cnt.addTapListener(e->{
            if (tapDisabled) {
                return;
            }
            tapDisabled = true;
           /* TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2->{
                String txt = enterName.getText();
                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCoordAtPosition(e.getX(), e.getY()), enterName.getText(), "", e3->{
                    ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
                });
                dlg.dispose();
                tapDisabled = false;
            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();*/
        });

        }
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
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author armand
 */
public class indexClientPage extends Form{
 MapContainer.MapObject sydney;
     private static final String HTML_API_KEY = "AIzaSyD2Ws0KYSjxNXXgRh8jRBGZgrXqgNHzWbI";
    private Form current;
    boolean tapDisabled = false;
    Coord selected=new Coord(0, 0);
    public ArrayList<PointMap> route;
    
    public indexClientPage(){
                             current=this;
        current.setTitle("WELCOME");
        current.setUIID("indexPage");

        Toolbar tb=current.getToolbar();
        Container c=BorderLayout.east(new Label(""));
        c.setUIID("TopleftBarIndexpage1");
        tb.addComponentToSideMenu(c);
        Style s = UIManager.getInstance().getComponentStyle("Title");
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_ACCOUNT_BOX, s);
        current.getTitleArea().setUIID("entetePageIndex");
        if(MyApplication.userConnect.getN_tel()==0){
                     Button changePassword=new Button("completer vos infos ",FontImage.MATERIAL_ARROW_RIGHT,"re");
         changePassword.setUIID("changepass");
         add(changePassword);
         changePassword.addActionListener(e-> {
            new editProfilPage().show(); 
         });
            
        }else{

        getTitleArea().setUIID("entetePageIndex");current.getTitleArea().setUIID("entetePageIndex");
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        
        current=this;
        setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setShowMyLocation(true);
        cnt.setMapType(3);
        
        
        
      
                FloatingActionButton btnNext = FloatingActionButton.createFAB(FontImage.MATERIAL_ARROW_RIGHT);
                btnNext.setVisible(false);
                btnNext.setUIID("btnNext");
        btnNext.addActionListener(e->{
            new detailCoordonne(current,selected.getLatitude(),selected.getLongitude(),route).show();
                
        });


                Button btnClose = new Button(FontImage.MATERIAL_CLOSE);
                btnClose.setUIID("btnClose");
                btnClose.setVisible(false);
        
                        Button btnRoute = new Button(FontImage.MATERIAL_TRAFFIC);
                btnRoute.setUIID("btnRoute");
                btnRoute.setVisible(false);
        btnRoute.addActionListener(e->{
            new itineaire(current,route).show();
        });
        
        btnClose.addActionListener(e->{
            btnRoute.setVisible(false);
            btnNext.setVisible(false);
            btnClose.setVisible(false);
            new indexClientPage().show();
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
                ),
                                BorderLayout.south(BoxLayout.encloseY(btnRoute,btnClose))
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
                System.out.println(list+"------------");
                for(int i=0; i<list.size(); i++){
                    //liste.get(i)
                String txt = "oui";
                if(list.get(i).getLatitude()!=0  && !list.get(i).getPhoto().trim().equals("") && list.get(i).getCin()!=0 && list.get(i).getId_user()!=0
                        && !list.get(i).getLogin().trim().equals("") && list.get(i).getN_tel()!=0 && !list.get(i).getNom().trim().equals("") && 
                        !list.get(i).getPrenom().trim().equals("") && 
                        !list.get(i).getPermis().trim().equals("")){
                    try {
                        Chauffeur cs=list.get(i);
                        cnt.addMarker(EncodedImage.create("/iconTaxi.png"),new Coord(list.get(i).getLatitude(), list.get(i).getLongitude()), txt, "", e3->{
                            new showChauffeur(current, cs, null).show();  
                        }); } catch (IOException ex) {
                    }
                }
                }
                
        Style s3 = new Style();
        s3.setFgColor(0xDF2A2A);
        s3.setBgTransparency(0);
        FontImage markerDest = FontImage.createMaterial(FontImage.MATERIAL_PLACE, s3, 7);
        cnt.addLongPressListener(e->{
            ToastBar.showMessage("Received longPress at "+e.getX()+", "+e.getY(), FontImage.MATERIAL_3D_ROTATION);
        });
        cnt.addTapListener(e->{
            
            try {
                cnt.clearMapLayers();
                cnt.addMarker(EncodedImage.create("/moi.png"),new Coord(MyApplication.userConnect.getLatitude(), MyApplication.userConnect.getLongitude()), "Hi marker", "Optional long description", new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        ToastBar.showMessage("Your position", FontImage.MATERIAL_PLACE);
                    }
                });
                for(int i=0; i<list.size(); i++){
                    String txt = "oui";
                    if(list.get(i).getLatitude()!=0  && !list.get(i).getPhoto().trim().equals("") && list.get(i).getCin()!=0 && list.get(i).getId_user()!=0
                        && !list.get(i).getLogin().trim().equals("") && list.get(i).getN_tel()!=0 && !list.get(i).getNom().trim().equals("") && 
                        !list.get(i).getPrenom().trim().equals("") && 
                        !list.get(i).getPermis().trim().equals("")){
                        
                        Chauffeur cs=list.get(i);
                        cnt.addMarker(EncodedImage.create("/iconTaxi.png"),new Coord(list.get(i).getLatitude(), list.get(i).getLongitude()), txt, "", e3->{
                            new showChauffeur(current, cs, null).show();
                        });
                    }
                }
                String adrs=Function.getInstance().getAdresse(
                        cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude(), cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude());
                
                
                // tracer le chemin
                route=ServiceUtils.getInstance().getAllRoutes(MyApplication.userConnect.getLatitude(),
                        MyApplication.userConnect.getLongitude(),
                        cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude(),
                        cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude());
                            
                            Coord[] myArray = new Coord[route.size()];
                                 
                            for(int i=0;i<route.size();i++){
                                if(route.get(i).getStart()!=null){
                                myArray[i]=route.get(i).getStart();     
                                }
                                if(route.get(i).getEnd()!=null){
                                myArray[i]=route.get(i).getEnd();     
                                }
                                
                             }
                            
                            cnt.setPathStrokeColor(0x7272F2);
                            cnt.setPathStrokeWidth(10);
                                     
                          cnt.addPath(myArray);

                            
                            
                            
                ToastBar.showMessage(adrs, FontImage.MATERIAL_PLACE);
                                    selected.setLatitude(cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude());
                    selected.setLongitude(cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude());
                cnt.addMarker(EncodedImage.create("/destination.png"),cnt.getCoordAtPosition(e.getX(), e.getY()), "destination", "", e3->{
                    new detailCoordonne(current,cnt.getCoordAtPosition(e.getX(), e.getY()).getLatitude(),cnt.getCoordAtPosition(e.getX(), e.getY()).getLongitude(),route).show();
                });
                                                        $(() -> {
           $("btnNext,btnClose,btnRoute").fadeIn(); 
       });
                
                // System.out.println(cnt.getComponentAt(cnt.getComponentCount()));
            } catch (IOException ex) {
            }
        });
        
                
                
                
        MapContainer.MapObject mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-33.866, 151.195), "test", "test",e->{
            cnt.removeMapObject(sydney);
        });
        sydney = mo;
        mo = cnt.addMarker(EncodedImage.createFromImage(markerImg, false), new Coord(-18.142, 178.431), "test", "test",e->{
        });
        cnt.addTapListener(e->{
            if (tapDisabled) {
                return;
            }
            tapDisabled = true;
           /* TextField enterName = new TextField();
            Container wrapper = BoxLayout.encloseY(new Label("Name:"), enterName);
            InteractionDialog dlg = new InteractionDialog("Add Marker");
            dlg.getContentPane().add(wrapper);
            enterName.setDoneListener(e2->{
                String txt = enterName.getText();
                cnt.addMarker(EncodedImage.createFromImage(markerImg, false), cnt.getCoordAtPosition(e.getX(), e.getY()), enterName.getText(), "", e3->{
                    ToastBar.showMessage("You clicked "+txt, FontImage.MATERIAL_PLACE);
                });
                dlg.dispose();
                tapDisabled = false;
            });
            dlg.showPopupDialog(new Rectangle(e.getX(), e.getY(), 10, 10));
            enterName.startEditingAsync();*/
        });

        }
    }
    
}
>>>>>>> master
