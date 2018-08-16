package com.example.aadhilahmed.mapboxdeliveries1.Models;

import com.mapbox.mapboxsdk.geometry.LatLng;

/**
 * Created by aadhil.ahmed on 25-Oct-17.
 */

public class DeliveryPersonModel {
    public String name;
    public int imgPic;
    public String address;
    public LatLng location;

    public DeliveryPersonModel(String name,int imgPic,LatLng location,String address){
        this.name=name;
        this.imgPic=imgPic;
        this.location=location;
        this.address=address;
    }

    public String getName(){
        return name;
    }

    public int getImgPic(){
        return imgPic;
    }

    public LatLng getLocation(){
        return location;
    }


    public String getAddress(){
        return address;
    }
}
