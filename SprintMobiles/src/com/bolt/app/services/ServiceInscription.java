/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;

import com.bolt.app.MyApplication;
import com.bolt.app.entities.Client;
import com.bolt.app.entities.Event;
import com.bolt.app.entities.Inscription;
import com.bolt.app.utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Meriem
 */
public class ServiceInscription {
    
         public static ServiceInscription instance=null;
      private ConnectionRequest req;
          public Boolean reponse;
     public ServiceInscription(){
      req = new ConnectionRequest();
     }
    public static ServiceInscription getInstance(){

        if (instance == null) {
            instance = new ServiceInscription();
        }
        return instance;
}  

            public Boolean addInscri(Inscription i,int nn){
                
        String url = Statics.BASE_URL+"/Evenement/inscription/"+i.getEvent().getId_event()+"/"+i.getClient().getId_user()+"/"+nn+"/newM";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                        try {
            
            JSONObject obj = new JSONObject(new String(req.getResponseData()));
            if(obj.getJSONObject("requette").getString("reponse").equals("no")){
                MyApplication.messageGobal=obj.getJSONObject("requette").getString("message");
                reponse= false;
            }else{
                reponse =true;
            }
            
            
        } catch (JSONException ex) {
        }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reponse;
    }        

}
