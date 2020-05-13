/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bolt.app.services;
import com.bolt.app.entities.Date;
import com.bolt.app.entities.PointMap;
import com.bolt.app.entities.User;
import com.bolt.app.utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.events.ActionListener;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author armand
 */
public class ServiceUtils {
        private ConnectionRequest req;
        Date uc;
        public ArrayList<PointMap> listeCordonne;
    
        public static ServiceUtils instance=null;

    public ServiceUtils() {
        req = new ConnectionRequest();
    }
        public static ServiceUtils getInstance() {
        if (instance == null) {
            instance = new ServiceUtils();
        }
        return instance;
    }
        
    public Date parseDate(String jsonText){
            Date u=new Date();
           
        try {
            
            JSONObject obj = new JSONObject(jsonText);
               u.setAnnee(obj.getJSONObject("date").getString("annee"));
               u.setMois(obj.getJSONObject("date").getString("mois"));
               u.setJour(obj.getJSONObject("date").getString("jour"));
               
               u.setHeures(obj.getJSONObject("date").getString("heures"));
               u.setMinutes(obj.getJSONObject("date").getString("minutes"));
            System.out.println(u);
            
        } catch (JSONException ex) {
            System.out.println("erruer de format json");
        }
        return u;
    }
    
    
    
        public ArrayList<PointMap> parseRoute(String jsonText){
            listeCordonne=new ArrayList<>();
           
        try {
            
            JSONObject obj = new JSONObject(jsonText);
            JSONArray jsonArray = obj.getJSONArray("routes");
for (int i = 0; i < jsonArray.length(); i++) {
    JSONObject explrObject = jsonArray.getJSONObject(i);
    PointMap m=new PointMap();
    m.setStart(new Coord(new Coord(Double.parseDouble(explrObject.getJSONObject("start_location").getString("lat")), Double.parseDouble(explrObject.getJSONObject("start_location").getString("lng")))));
    m.setDistance(Double.parseDouble(explrObject.getJSONObject("distance").getString("value")));
    m.setDuration(Double.parseDouble(explrObject.getJSONObject("duration").getString("value")));
    m.setHtml_instructions(explrObject.getString("html_instructions"));
    listeCordonne.add(m);
    
    JSONObject explrObjects = jsonArray.getJSONObject(i);
    PointMap ms=new PointMap();
    ms.setEnd(new Coord(new Coord(Double.parseDouble(explrObjects.getJSONObject("end_location").getString("lat")), Double.parseDouble(explrObject.getJSONObject("end_location").getString("lng")))));
    ms.setDistance(Double.parseDouble(explrObjects.getJSONObject("distance").getString("value")));
    ms.setDuration(Double.parseDouble(explrObjects.getJSONObject("duration").getString("value")));
    ms.setHtml_instructions(explrObjects.getString("html_instructions"));
   
    listeCordonne.add(ms);



} 

            
        } catch (JSONException ex) {
            System.out.println("erruer de format json");
        }
        return listeCordonne;
    }
        
            public ArrayList<PointMap> getAllRoutes(double lat1,double lon1,double lat2,double lon2){
        String url = Statics.BASE_URL+"/serviceGetDirection/"+lat1+"/"+lon1+"/"+lat2+"/"+lon2;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listeCordonne = parseRoute(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return listeCordonne;
    }
        
    
    
                public Date getDate(){
        String url = Statics.BASE_URL+"/serviceCurrentDate";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                uc = parseDate(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return uc;
    }
        
        
}
