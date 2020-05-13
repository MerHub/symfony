/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.utils;

import com.bolt.app.services.ServiceUser;
import static com.bolt.app.services.ServiceUser.instance;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author armand
 */
public class Function {
        public String msgBack;
    
    private ConnectionRequest req;
    
        public static Function instance=null;
    public boolean resultOK;
    
        private Function() {
         req = new ConnectionRequest();
    }

    public static Function getInstance() {
        if (instance == null) {
            instance = new Function();
        }
        return instance;
    }
    
                public String getAdresse(double latitude,double longitude){
        String url = Statics.BASE_URL+"/serviceAdreeseGPS/"+latitude+"/"+longitude;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONObject obj;
                try {
                    obj = new JSONObject(new String(req.getResponseData()));
                    msgBack=obj.getJSONObject("adresse").getString("adr");
                } catch (JSONException ex) {
                }
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return msgBack;
    }
    
}
