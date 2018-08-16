package com.example.aadhilahmed.mapboxdeliveries1.Models;

/**
 * Created by aadhil.ahmed on 03-Nov-17.
 */

public class OptimisationResponse {
    private Trips[] trips;

    private Waypoints[] waypoints;

    private String code;

    public OptimisationResponse(Trips[] trips,Waypoints[] waypoints,String code){
        this.trips=trips;
        this.waypoints=waypoints;
        this.code=code;
    }

    public Trips[] getTrips ()
    {
        return trips;
    }

    public Waypoints[] getWaypoints ()
    {
        return waypoints;
    }


    public String getCode ()
    {
        return code;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [trips = "+trips+", waypoints = "+waypoints+", code = "+code+"]";
    }
}
