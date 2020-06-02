/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.gui;

import com.bolt.app.MyApplication;
import static com.bolt.app.MyApplication.theme;
import com.bolt.app.entities.Client;
import com.bolt.app.entities.Event;
import com.bolt.app.entities.Inscription;
import com.bolt.app.services.OpinionDAO;
import com.bolt.app.services.ServiceInscription;
import com.bolt.app.services.TwilioSMS;
import com.codename1.components.ImageViewer;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;

/**
 *
 * @author armand
 */
public class ShowEvent extends Form {

    Form current;

    public ShowEvent(Form previous, Event event) {
        current = this;
        String login = MyApplication.userConnect.getLogin();
        //   Container ContainerVertical = new Container(BoxLayout.y());
        /* Container c = BorderLayout.east(new Label(""));
         c.setUIID("TopleftBarIndexpage1");*/
        //    setUIID("backgroundEvent");
        setTitle(event.getNom());
        //  setUIID("registerPage");
        getTitleArea().setUIID("entetePageIndex");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        Toolbar tb = this.getToolbar();
        //   tb.addMaterialCommandToSideMenu("Contactez - nous !", FontImage.MATERIAL_MAIL, e -> new OpinionDAO());
        tb.addCommandToOverflowMenu("Contactez - nous !", MyApplication.theme.getImage("icon.png"), e -> new OpinionDAO());

        /*  Container block1 = new Container(BoxLayout.y());
         block1.setUIID("blockRegister2");*/
        ImageViewer img = null;
        try {
            img = new ImageViewer(Image.createImage("/event.jpg"));
        } catch (IOException ex) {
        }
        Container block2 = new Container(BoxLayout.y());
        //  block2.setUIID("block2");

        Label text2 = new Label("                          WELCOME   " + login);
        text2.setUIID("text31");
        Label text3 = new Label("You will recieve an SMS when you register.");
        text3.setUIID("text33");
        Label lDepart = new Label("Departure : " + event.getDepart());
        lDepart.setUIID("text31");
        Label lArrivee = new Label("Destination : " + event.getArrivee());
        lArrivee.setUIID("text31");
        Label text4 = new Label("Places remaining : " + event.getNbr_place());
        text4.setUIID("text31");
        Button btn = new Button("Register");
        btn.setUIID("submit");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                boolean eee = Dialog.show("Registration", "Do you really want to Register ? ", "Yes", "No");
                if (eee) {
                            ImageViewer img = null;
        try {
            img = new ImageViewer(Image.createImage("/book.jpg"));
        } catch (IOException ex) {
        }
                    Form frm = new Form("Confirmation", BoxLayout.y());
                    frm.setTitle("Confirmation");
                    frm.getTitleArea().setUIID("entetePageIndex");
                     Toolbar t = frm.getToolbar();
                     t.addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> showBack());
                    Label lbb = new Label("How many places to book ?");
                    lbb.setUIID("text34");
                    TextField tf = new TextField(null, "Number of places");
                    tf.setUIID("text34");
                    Button booknow = new Button("Book now");
                    booknow.setUIID("submit");
                    frm.add(lbb);
                    frm.add(tf);
                    frm.add(booknow);
                    frm.add(img);
                    booknow.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            int npb = Integer.parseInt(tf.getText());
                            Inscription i = new Inscription();
                            Client c = new Client();
                            c.setId_user(MyApplication.userConnect.getId_user());
                            i.setClient(c);
                            int nbr = event.getNbr_place();
                            if (nbr - npb < 0) {
                                Dialog.show("Alert", "No places remaining, Sorry !", "Ok", null);
                            } else {
                                nbr = nbr - npb;
                                event.setNbr_place(nbr);
                                Label lbpn = new Label("New places remaining: " + event.getNbr_place() + "        ");
                                lbpn.setUIID("text31");
                                text4.setVisible(false);
                                block2.add(lbpn);
                                i.setEvent(event);
                                
                    /*      TwilioSMS s=new TwilioSMS("AC25f45c1761b39ed76baec6796d15dd2e","36d95069a768d0411f06707a19e36882","+12076067369");
                         s.sendSmsAsync("+21650081019","Mrs,Mr you are registred in :"+event.getNom()+"  succefully"   
                         +MyApplication.userConnect.getLogin()+"Welcome, we are waiting for you  "   );*/
                                ServiceInscription.getInstance().addInscri(i, npb);
                             boolean ee=   Dialog.show("SUCCESS", "You are now registred !", "Ok", null);
                                    if(ee){
                                    showBack();
                                    }
 
                            }
                        }
                    }
                    );

                    frm.show();

                }
            }
        });

        block2.add(text2);

        block2.add(lDepart);
        block2.add(lArrivee);
        block2.add(text4);
        block2.add(btn);

        add(block2);
        add(img);

        /*    add(text2);
         add(text3);
         add(lDepart);
         add(lArrivee);
         add(btn);*/
    }
}
