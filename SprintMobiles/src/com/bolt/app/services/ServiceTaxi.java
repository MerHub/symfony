/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;

import com.bolt.app.entities.CategoriesTaxi;
import com.bolt.app.entities.Chauffeur;
import com.bolt.app.entities.Taxi;
import com.bolt.app.entities.User;
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
public class ServiceTaxi {
    public Chauffeur chauffeur;
    ArrayList<CategoriesTaxi> cat;
    public Taxi taxi;
    private ConnectionRequest req;
    public static ServiceTaxi instance=null;    

    public ServiceTaxi() {
                req = new ConnectionRequest();
    }
            public static ServiceTaxi getInstance() {
        if (instance == null) {
            instance = new ServiceTaxi();
        }
        return instance;
    }
                    public Taxi parseTaxi(String jsonText){
            Taxi u=new Taxi()
;           
        try {
            
            JSONObject obj = new JSONObject(jsonText);
            if(obj.getString("requette").equals("null")){
                System.out.println("non");
                return u;
            }else{
                u=new Taxi();
                Chauffeur chauffeurTmp=new Chauffeur();
                chauffeurTmp.setAdresse(obj.getJSONObject("requette").getJSONObject("chauffeur").getString("adresse"));
                chauffeurTmp.setCin(obj.getJSONObject("requette").getJSONObject("chauffeur").getInt("cin"));
                chauffeurTmp.setId_user(obj.getJSONObject("requette").getJSONObject("chauffeur").getInt("id_user"));
                chauffeurTmp.setLogin(obj.getJSONObject("requette").getJSONObject("chauffeur").getString("username"));
                chauffeurTmp.setMail(obj.getJSONObject("requette").getJSONObject("chauffeur").getString("email"));
                chauffeurTmp.setN_tel(obj.getJSONObject("requette").getJSONObject("chauffeur").getInt("phone"));
                chauffeurTmp.setNom(obj.getJSONObject("requette").getJSONObject("chauffeur").getString("nom"));
                chauffeurTmp.setPrenom(obj.getJSONObject("requette").getJSONObject("chauffeur").getString("prenom"));
                chauffeurTmp.setPhoto(obj.getJSONObject("requette").getJSONObject("chauffeur").getString("photo_chauffeur"));
                
                u.setChauffeur(chauffeurTmp);
                CategoriesTaxi ct=new CategoriesTaxi();
                ct.setImage(obj.getJSONObject("requette").getJSONObject("categorieTaxi").getString("photo_taxi"));
                u.setCatTaxi(ct);
                
                u.setId_taxi(obj.getJSONObject("requette").getJSONObject("taxi").getInt("id_taxi"));
                u.setNum_chassis(obj.getJSONObject("requette").getJSONObject("taxi").getString("num_chassis"));
            }
            
            
        } catch (JSONException ex) {
            System.out.println("erruer de format json");
        }
                        System.out.println(u);
        return u;
    }
                    
                 public  ArrayList<CategoriesTaxi> parseCategorieTaxi(String jsonText){
                     cat=new ArrayList<>();
                                 JSONObject obj;
        try {
            obj = new JSONObject(jsonText);
                        JSONArray arr = obj.getJSONArray("liste");
            for (int i = 0; i < arr.length(); i++) {
                CategoriesTaxi ch=new CategoriesTaxi();
                         try {
                             ch.setId(arr.getJSONObject(i).getInt("id"));
                             ch.setModel(arr.getJSONObject(i).getInt("model"));
                             ch.setImage(arr.getJSONObject(i).getString("image"));
                             cat.add(ch); 
                         } catch (JSONException ex) {
                         }
               
            }
        } catch (JSONException ex) {
        }

                
                     return cat;
                 }
                    
                                public Taxi getTaxi(int id_chauffeur){
        String url = Statics.BASE_URL+"/Taxi/serviceGetTaxi/"+id_chauffeur;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                taxi = parseTaxi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return taxi;
    }
                                
       public ArrayList<CategoriesTaxi> getCategorieTaxi(){
        String url = Statics.BASE_URL+"/Taxi/GetCategorieTaxi";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cat = parseCategorieTaxi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cat;
    }
    
}
