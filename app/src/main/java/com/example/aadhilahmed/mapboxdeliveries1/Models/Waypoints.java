package com.example.aadhilahmed.mapboxdeliveries1.Models;

/**
 * Created by aadhil.ahmed on 03-Nov-17.
 */

public class Waypoints {
    private String[] location;

    private String name;

    private String trips_index;

    private String waypoint_index;

    public Waypoints(String[] location,String name,String trips_index,String waypoint_index){
        this.location=location;
        this.name=name;
        this.trips_index=trips_index;
        this.waypoint_index=waypoint_index;
    }

    public String[] getLocation ()
    {
        return location;
    }


    public String getName ()
    {
        return name;
    }


    public String getTrips_index ()
    {
        return trips_index;
    }


    public String getWaypoint_index ()
    {
        return waypoint_index;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [location = "+location+", name = "+name+", trips_index = "+trips_index+", waypoint_index = "+waypoint_index+"]";
    }
}
