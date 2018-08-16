package com.example.aadhilahmed.mapboxdeliveries1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aadhilahmed.mapboxdeliveries1.Activity.ManagerActivity;
import com.example.aadhilahmed.mapboxdeliveries1.Models.DeliveryPersonModel;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by aadhil.ahmed on 25-Oct-17.
 */

public class LocationFetcher {
    private MapView mapView;
    public static List<Marker> personMarker=new ArrayList<>();
    private List<DeliveryPersonModel> persons=new ArrayList<>();

    //Mock Deliveryboys location
    private DeliveryPersonModel person1 =new DeliveryPersonModel("DiMaria",R.drawable.pic_dimaria,new LatLng(34.0188,-118.2722854),"3326 S Main St, Los Angeles, CA 90007, USA");
    private DeliveryPersonModel person2=new DeliveryPersonModel("Sergio",R.drawable.pic_ramos,new LatLng(33.8537,-117.9904174),"Calendula Dr, Buena Park, CA 90620, USA");
    private DeliveryPersonModel person3=new DeliveryPersonModel("Robert",R.drawable.pic_firmino,new LatLng(33.5649,-117.206611),"Temecula Valley Fwy, Murrieta, CA 92562, USA");

    public static HashMap<String,Integer> picIdentifier=new HashMap<>();

    protected IconFactory iconFactory;

    public void showLocations(final Context context){
        mapView= ManagerActivity.mapView;
        iconFactory=IconFactory.getInstance(context);

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {

                for (int i = 0; i < persons.size(); i++) {
                    DeliveryPersonModel model = persons.get(i);
                    personMarker.add(mapboxMap.addMarker(generateMarker(model.getName(), model.getLocation(), model.getImgPic(),model.getAddress())));
                    picIdentifier.put(model.getName(), model.getImgPic());
                }
            }
        });
    }

    private MarkerOptions generateMarker(String name,LatLng pos,int image,String address) {
        MarkerOptions marker=new MarkerOptions();
        marker.position(pos);
        marker.setTitle(name);
        marker.setSnippet(address);
        marker.setIcon(iconFactory.fromResource(R.drawable.ic_location_bluish));
        return marker;
    }
}
