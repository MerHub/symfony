/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;

import com.bolt.app.entities.Client;
import com.bolt.app.entities.Date;
import com.bolt.app.entities.Event;
import com.bolt.app.entities.Location;
import com.bolt.app.entities.User;
import com.bolt.app.entities.Velo;
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
 * @author Belgaroui Ghazi
 */
public class ServiceLocation {
    public ArrayList<Location> location ;
    private ConnectionRequest req;
    public static ServiceLocation instance=null;
    public String retour="";

    public ServiceLocation() {
        req = new ConnectionRequest();
    }
        public static ServiceLocation getInstance() {
        if (instance == null) {
            instance = new ServiceLocation();
        }
        return instance;
    }
        /*
            public ArrayList<Location> parseLocation(String jsonText){
        try {
            location=new ArrayList<>();
            JSONObject obj = new JSONObject(jsonText);
            JSONArray arr = obj.getJSONArray("liste");
            for (int i = 0; i < arr.length(); i++) {
                Location ch=new Location();
                ch.setId_location(arr.getJSONObject(i).getInt("idLocation"));
                 Client c=new Client();
                c.setId_user(arr.getJSONObject(i).getInt("id_user"));
                ch.setClient(c);
                 Velo a=new Velo();
                a.setId(arr.getJSONObject(i).getInt("id"));
                ch.setVelo(a);
                
                ch.setDate_d(new Date(arr.getJSONObject(i).getJSONObject("date_d").getString("annee"),
                arr.getJSONObject(i).getJSONObject("date_d").getString("mois"),
                arr.getJSONObject(i).getJSONObject("date_d").getString("jour"),
                arr.getJSONObject(i).getJSONObject("date_d").getString("heure"),
                arr.getJSONObject(i).getJSONObject("date_d").getString("minute")));
                
                                ch.setDate_f(new Date(arr.getJSONObject(i).getJSONObject("date_f").getString("annee"),
                arr.getJSONObject(i).getJSONObject("date_f").getString("mois"),
                arr.getJSONObject(i).getJSONObject("date_f").getString("jour"),
                arr.getJSONObject(i).getJSONObject("date_f").getString("heure"),
                arr.getJSONObject(i).getJSONObject("date_f").getString("minute")));
               ch.setPrix(arr.getJSONObject(i).getDouble("prix"));
               
                                
                                location.add(ch);
                
                }
            
        } catch (JSONException ex) {
            System.out.println("erreru");
        }
        return location;
    }
            
                        public ArrayList<Location> getListLocation(int id_Location){
        String url = Statics.BASE_URL+"/Velo/location/listlocation/"+id_Location;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                location = parseLocation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return location;
    }*/
                       public String addLocation(Location t) {
        String url = Statics.BASE_URL + "/Velo/location/newM/"+t.getVelo().getId()+"/"+t.getDate_d()+"/"+t.getDate_f()+"/"+t.getClient().getId_user()+"/"+t.getPrix();
     req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                            JSONObject obj;
                try {
                    obj = new JSONObject(new String(req.getResponseData()));
                                if(obj.getJSONObject("requette").getString("reponse").equals("no")){
               retour=  obj.getJSONObject("requette").getString("message");
            }else{
               retour=  "ok";
            }
                } catch (JSONException ex) {
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
                    return retour;
                }
    
}
