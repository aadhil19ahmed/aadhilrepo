package com.example.aadhilahmed.mapboxdeliveries1;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aadhilahmed.mapboxdeliveries1.Models.DeliveryPointsModel;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Features;
import com.example.aadhilahmed.mapboxdeliveries1.Models.ReverseGeocodeModel;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aadhil.ahmed on 21-Oct-17.
 */

public class MyApplicationClass extends Application {

    public static List<Tasks> deliveryDetails=new ArrayList<>();
    public static RequestQueue requestQueue;
    static String url="http://www.mocky.io/v2/5a056c0c3000009623fe0a08";
    public static String address;
    @Override
    public void onCreate() {
        super.onCreate();

        Mapbox.getInstance(getApplicationContext(),"pk.eyJ1IjoiYWFkaGlsYWhtZWQiLCJhIjoiY2o2MXNzY3I5MHoyajMybW9raTI1emJmbiJ9.paxtpoOPEQDuVN19tlfpVw");

        requestQueue= Volley.newRequestQueue(this);
        
        JsonDataFetcher<DeliveryPointsModel> myReq=new JsonDataFetcher<>(url,DeliveryPointsModel.class,
                null,createSuccessListener(),createErrorListener());
        requestQueue.add(myReq);
    }

    private Response.ErrorListener createErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "Error : " + error.getLocalizedMessage());
            }
        };
    }

    private Response.Listener<DeliveryPointsModel> createSuccessListener() {
        return new Response.Listener<DeliveryPointsModel>() {
            @Override
            public void onResponse(DeliveryPointsModel response) {
                Tasks[] eachTask = response.getTasks();
                for (int i = 0; i < eachTask.length; i++) {

                    String address = eachTask[i].getAddress();
                    double lat = eachTask[i].getGeometry()[1];
                    double longt = eachTask[i].getGeometry()[0];
                    String delivery_type = eachTask[i].getDelivery_type();
                    String product_name = eachTask[i].getProduct();
                    String order_id = eachTask[i].getOrder_id();
                    String product_id = eachTask[i].getProduct_id();
                    String order_status=eachTask[i].getOrder_status();
                    double[] coordinates = {lat, longt};

                    Tasks task = new Tasks(product_name, delivery_type, product_id,address, order_id,order_status,coordinates);
                    deliveryDetails.add(task);
                }
            }
        };
    }

    public static String pointsFormatter(List<LatLng> points){
        String locations="";
        for(int i=0;i<points.size();i++){
            if(points.size()-1==i){
                String location=points.get(i).getLongitude()+","+points.get(i).getLatitude()+"?";
                locations+=""+location;
            }else {
                String location=points.get(i).getLongitude()+","+points.get(i).getLatitude()+";";
                locations+=""+location;
            }
        }
        return locations;
    }

    private static Response.Listener<ReverseGeocodeModel> getSuccessListener() {
        return new Response.Listener<ReverseGeocodeModel>() {
            @Override
            public void onResponse(ReverseGeocodeModel response) {
                Features feature=response.getFeatures()[0];
                address=feature.getPlace_name();
            }

        };
    }

    private static Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "Error : " + error.getLocalizedMessage());
            }
        };
    }


}
