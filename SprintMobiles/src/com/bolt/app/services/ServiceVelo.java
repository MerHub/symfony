/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;

import com.bolt.app.entities.Date;
import com.bolt.app.entities.Event;
import com.bolt.app.entities.Velo;
import static com.bolt.app.services.ServiceEvent.instance;
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
public class ServiceVelo {
        public ArrayList<Velo> velo ;
    private ConnectionRequest req;
        public static ServiceVelo instance=null;

    public ServiceVelo() {
        req = new ConnectionRequest();
    }
    
    public static ServiceVelo getInstance() {
        if (instance == null) {
            instance = new ServiceVelo();
        }
        return instance;
    }
    
    
                public ArrayList<Velo> parseVelo(String jsonText){
        try {
            velo=new ArrayList<>();
            JSONObject obj = new JSONObject(jsonText);
            JSONArray arr = obj.getJSONArray("liste");
            for (int i = 0; i < arr.length(); i++) {
                Velo ch=new Velo();
                ch.setId(arr.getJSONObject(i).getInt("id"));
                ch.setType(arr.getJSONObject(i).getString("type"));
                ch.setAdresse(arr.getJSONObject(i).getString("adresse"));
                ch.setPhoto(arr.getJSONObject(i).getString("photo"));
                ch.setQte(arr.getJSONObject(i).getInt("qte"));
                ch.setPrix(arr.getJSONObject(i).getDouble("prix"));
                ch.setLatitude(arr.getJSONObject(i).getDouble("latitude"));
                ch.setLongitude(arr.getJSONObject(i).getDouble("longitude"));
                velo.add(ch);
                
                }
            
        } catch (JSONException ex) {
            System.out.println("erreru");
        }
        return velo;
    }
                
      public ArrayList<Velo> getListVelo(){
        String url = Statics.BASE_URL+"/Velo/serviceList";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                velo = parseVelo(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return velo;
    }
    
    
}
