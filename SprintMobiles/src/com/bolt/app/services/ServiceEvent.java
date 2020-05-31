/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;

import com.bolt.app.entities.Chauffeur;
import com.bolt.app.entities.Date;
import com.bolt.app.entities.Event;
import static com.bolt.app.services.ServiceChauffeur.instance;
import com.bolt.app.utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author armand
 */
public class ServiceEvent {
    public ArrayList<Event> event ;
    private ConnectionRequest req;
    public static ServiceEvent instance=null;
    public String retour="";

    public ServiceEvent() {
        req = new ConnectionRequest();
    }
        public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }
        
            public ArrayList<Event> parseEvent(String jsonText){
        try {
            event=new ArrayList<>();
            JSONObject obj = new JSONObject(jsonText);
            JSONArray arr = obj.getJSONArray("liste");
            for (int i = 0; i < arr.length(); i++) {
                Event ch=new Event();
                ch.setId_event(arr.getJSONObject(i).getInt("idEvent"));
                ch.setNom(arr.getJSONObject(i).getString("nom"));
                ch.setDepart(arr.getJSONObject(i).getString("depart"));
                ch.setArrivee(arr.getJSONObject(i).getString("arrivee"));
                ch.setNbr_place(arr.getJSONObject(i).getInt("nbrPlace"));
                ch.setDate_allee(new Date(arr.getJSONObject(i).getJSONObject("dateAllee").getString("annee"),
                arr.getJSONObject(i).getJSONObject("dateAllee").getString("mois"),
                arr.getJSONObject(i).getJSONObject("dateAllee").getString("jour"),
                arr.getJSONObject(i).getJSONObject("dateAllee").getString("heure"),
                arr.getJSONObject(i).getJSONObject("dateAllee").getString("minute")));
                
                                ch.setDate_allee(new Date(arr.getJSONObject(i).getJSONObject("dateRetour").getString("annee"),
                arr.getJSONObject(i).getJSONObject("dateRetour").getString("mois"),
                arr.getJSONObject(i).getJSONObject("dateRetour").getString("jour"),
                arr.getJSONObject(i).getJSONObject("dateRetour").getString("heure"),
                arr.getJSONObject(i).getJSONObject("dateRetour").getString("minute")));
                ch.setDescription(arr.getJSONObject(i).getString("decription"));
                ch.setLatitude1(arr.getJSONObject(i).getDouble("latitude1"));
                ch.setLatitude2(arr.getJSONObject(i).getDouble("latitude2"));
                ch.setLongitude1(arr.getJSONObject(i).getDouble("longitude1"));
                ch.setLongitude2(arr.getJSONObject(i).getDouble("longitude2"));
                                
                                event.add(ch);
                
                }
            
        } catch (JSONException ex) {
            System.out.println("erreru");
        }
        return event;
    }
            
                        public ArrayList<Event> getListEvent(){
        String url = Statics.BASE_URL+"/Evenement/listEvent";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                event = parseEvent(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return event;
    }
}
