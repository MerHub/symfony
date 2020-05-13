/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;

import com.bolt.app.entities.Chauffeur;
import com.bolt.app.entities.User;
import static com.bolt.app.services.ServiceUser.instance;
import com.bolt.app.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author armand
 */
public class ServiceChauffeur {
    public ArrayList<Chauffeur> chauffeur;
    private ConnectionRequest req;
    public static ServiceChauffeur instance=null;

    public ServiceChauffeur() {
        req = new ConnectionRequest();
    }
        public static ServiceChauffeur getInstance() {
        if (instance == null) {
            instance = new ServiceChauffeur();
        }
        return instance;
    }
    
    public ArrayList<Chauffeur> parseChauffeur(String jsonText){
        try {
            chauffeur=new ArrayList<>();
            JSONObject obj = new JSONObject(jsonText);
            JSONArray arr = obj.getJSONArray("liste");
            for (int i = 0; i < arr.length(); i++) {
                Chauffeur ch=new Chauffeur();
                ch.setId_user(Integer.parseInt(arr.getJSONObject(i).getString("id")));
                if(!arr.getJSONObject(i).getString("adresse").equals("null") && !arr.getJSONObject(i).getString("adresse").isEmpty()){
                 ch.setAdresse(arr.getJSONObject(i).getString("adresse"));   
                }
                 if(!arr.getJSONObject(i).getString("cin").equals("null") && !arr.getJSONObject(i).getString("cin").isEmpty()){
                 ch.setCin(Integer.parseInt(arr.getJSONObject(i).getString("cin")));   
                }
                if(!arr.getJSONObject(i).getString("permis").equals("null") && !arr.getJSONObject(i).getString("permis").isEmpty()){
                 ch.setPermis(arr.getJSONObject(i).getString("permis"));
                }
                if(!arr.getJSONObject(i).getString("nom").equals("null") && !arr.getJSONObject(i).getString("nom").isEmpty()){
                 ch.setNom(arr.getJSONObject(i).getString("nom"));
                }  
                if(!arr.getJSONObject(i).getString("prenom").equals("null") && !arr.getJSONObject(i).getString("prenom").isEmpty()){
                 ch.setPrenom(arr.getJSONObject(i).getString("prenom"));
                }  
                if(!arr.getJSONObject(i).getString("photo").equals("null") && !arr.getJSONObject(i).getString("photo").isEmpty()){
                 ch.setPhoto(arr.getJSONObject(i).getString("photo"));
                } 
                if(!arr.getJSONObject(i).getString("numero").equals("null") && !arr.getJSONObject(i).getString("numero").isEmpty()){
                ch.setN_tel(Integer.parseInt(arr.getJSONObject(i).getString("numero")));
                } 
                if(!arr.getJSONObject(i).getString("type").equals("null") && !arr.getJSONObject(i).getString("type").isEmpty()){
                ch.setType(arr.getJSONObject(i).getString("type"));
                } 
                if(!arr.getJSONObject(i).getString("mail").equals("null") && !arr.getJSONObject(i).getString("mail").isEmpty()){
                ch.setMail(arr.getJSONObject(i).getString("mail"));
                } 
                if(!arr.getJSONObject(i).getString("latitude").equals("null") && !arr.getJSONObject(i).getString("latitude").isEmpty()){
                ch.setLatitude(Float.parseFloat(arr.getJSONObject(i).getString("latitude")));
                } 
                if(!arr.getJSONObject(i).getString("longitude").equals("null") && !arr.getJSONObject(i).getString("longitude").isEmpty()){
                ch.setLongitude(Float.parseFloat(arr.getJSONObject(i).getString("longitude")));
                } 
                if(!arr.getJSONObject(i).getString("username").equals("null") && !arr.getJSONObject(i).getString("username").isEmpty()){
                ch.setLogin(arr.getJSONObject(i).getString("username"));
                } 
                chauffeur.add(ch);
                
        }
            
        } catch (JSONException ex) {
            System.out.println("erreru");
        }
        return chauffeur;
    }
    
                public ArrayList<Chauffeur> getListChauffeur(){
        String url = Statics.BASE_URL+"/listChaffeur/maList";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                chauffeur = parseChauffeur(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return chauffeur;
    }
    
    
}
