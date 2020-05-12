/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;

import com.bolt.app.entities.Avis;
import com.bolt.app.entities.Client;
import com.bolt.app.entities.Taxi;
import static com.bolt.app.services.ServiceTaxi.instance;
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
public class ServiceAvis {
    public ArrayList<Avis> listAvis;
    private ConnectionRequest req;
    public static ServiceAvis instance=null;  
    public int note;
    
        public ServiceAvis() {
                req = new ConnectionRequest();
    }
            public static ServiceAvis getInstance() {
        if (instance == null) {
            instance = new ServiceAvis();
        }
        return instance;
    }
            
     public int parseMoyenne(String jsonText){
            int u=0;
           
        try {
            
            JSONObject obj = new JSONObject(jsonText);
            u=obj.getInt("moyenne");
            
            
        } catch (JSONException ex) {
            System.out.println("erruer de format json");
        }
        return u;
    }
     
          public ArrayList<Avis> parseList(String jsonText){
              listAvis=new ArrayList<>();
            JSONObject obj;
        try {
            obj = new JSONObject(jsonText);
                        JSONArray arr = obj.getJSONArray("listeAvis");
                                    for (int i = 0; i < arr.length(); i++) {
                Avis ch=new Avis();
                ch.setNote(arr.getJSONObject(i).getInt("note"));
                ch.setMsg(arr.getJSONObject(i).getString("message"));
                Client c=new Client();
                c.setLogin(arr.getJSONObject(i).getString("username"));
                ch.setClient(c);
                listAvis.add(ch);
                
        }
        } catch (JSONException ex) {
            System.out.println("erreur de format avis");
        }
            
              return listAvis;
          }
     
      public int getMoyenne(int id_chauffeur){
        String url = Statics.BASE_URL+"/Avis/avis_moyenne/"+id_chauffeur;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                note = parseMoyenne(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return note;
    }
     
      
            public ArrayList<Avis> getListeAvis(int id_chauffeur){
        String url = Statics.BASE_URL+"/Avis/avis_service_showAll/"+id_chauffeur;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listAvis = parseList(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listAvis;
    }
}
