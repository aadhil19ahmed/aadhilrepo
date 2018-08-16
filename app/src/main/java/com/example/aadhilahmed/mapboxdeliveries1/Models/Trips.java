package com.example.aadhilahmed.mapboxdeliveries1.Models;

/**
 * Created by aadhil.ahmed on 03-Nov-17.
 */

public class Trips {
    private String duration;

    private String distance;

    private String weight;

    private String weight_name;

    private Legs[] legs;

    private String geometry;

    public Trips(String duration,String distance,String weight,String weight_name,Legs[] legs,String geometry){
        this.duration=duration;
        this.distance=distance;
        this.weight=weight;
        this.weight_name=weight_name;
        this.legs=legs;
        this.geometry=geometry;
    }

    public String getDuration ()
    {
        return duration;
    }


    public String getDistance ()
    {
        return distance;
    }



    public String getWeight ()
    {
        return weight;
    }


    public String getWeight_name ()
    {
        return weight_name;
    }

    public Legs[] getLegs ()
    {
        return legs;
    }

    public String getGeometry ()
    {
        return geometry;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", distance = "+distance+", weight = "+weight+", weight_name = "+weight_name+", legs = "+legs+", geometry = "+geometry+"]";
    }
}
