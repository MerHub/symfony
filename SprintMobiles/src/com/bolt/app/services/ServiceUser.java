/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;

import com.bolt.app.MyApplication;
import com.bolt.app.entities.User;
import com.bolt.app.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author armand
 */
public class ServiceUser {
    public User uc;
    public Boolean reponse;
    public String msgBack;
    
    private ConnectionRequest req;
    
        public static ServiceUser instance=null;
    public boolean resultOK;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    
        public User parseUser(String jsonText){
            User u=null;
           
        try {
            
            JSONObject obj = new JSONObject(jsonText);
            if(obj.getJSONObject("requette").getString("reponse").equals("no")){
                return u;
            }else{
                u=new User();
                u.setId_user((int) obj.getJSONObject("requette").getJSONObject("user").getInt("id_user"));
                u.setLogin(obj.getJSONObject("requette").getJSONObject("user").getString("username"));
                u.setMail(obj.getJSONObject("requette").getJSONObject("user").getString("email"));
                u.setType(obj.getJSONObject("requette").getJSONObject("user").getString("type"));
                u.setN_tel((int) obj.getJSONObject("requette").getJSONObject("user").getInt("numero"));
                u.setLatitude(Double.parseDouble(obj.getJSONObject("requette").getJSONObject("user").getString("latitude")));
                u.setLongitude(Double.parseDouble(obj.getJSONObject("requette").getJSONObject("user").getString("longitude")));
            }
            
            
        } catch (JSONException ex) {
            System.out.println("erruer de format json");
        }
        return u;
    }
            public User getUser(String username,String password){
        String url = Statics.BASE_URL+"/serviceLogin/"+username+"/"+password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                uc = parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return uc;
    }
            
            
     public String setUser(String username,String password,String mail,String numero,int id){
         
        String url = Statics.BASE_URL+"/servicesetUsers/"+username+"/"+mail+"/"+password+"/"+numero+"/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                            JSONObject obj;
                try {
                    obj = new JSONObject(new String(req.getResponseData()));
                                if(obj.getJSONObject("requette").getString("reponse").equals("no")){
               msgBack=  obj.getJSONObject("requette").getString("message");
            }else{
               MyApplication.userConnect.setLogin(username);
               MyApplication.userConnect.setMail(mail);
               MyApplication.userConnect.setN_tel(Integer.parseInt(numero));
               msgBack=  "finish";
            }
                } catch (JSONException ex) {
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return msgBack;
    }            
                public Boolean addUser(String username,String password,String email,String type){
        String url = Statics.BASE_URL+"/ServiceAddUser/"+email+"/"+username+"/"+password+"/"+type;
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
