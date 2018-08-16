package com.example.aadhilahmed.mapboxdeliveries1.Models;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.ArrayList;

/**
 * Created by aadhil.ahmed on 10/2/2017.
 */

public class StateData {
    private String type;

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    private ArrayList<Feature> features;

    public ArrayList<Feature> getFeatures() { return this.features; }

    public void setFeatures(ArrayList<Feature> features) { this.features = features; }
}

class Properties
{
    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private double density;

    public double getDensity() { return this.density; }

    public void setDensity(double density) { this.density = density; }
}

class Geometry
{
    private String type;

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    private ArrayList<ArrayList<LatLng>> coordinates;

    public ArrayList<ArrayList<LatLng>> getCoordinates() { return this.coordinates; }

    public void setCoordinates(ArrayList<ArrayList<LatLng>> coordinates) { this.coordinates = coordinates; }
}

class Feature
{
    private String type;

    public String getType() { return this.type; }

    public void setType(String type) { this.type = type; }

    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    private Properties properties;

    public Properties getProperties() { return this.properties; }

    public void setProperties(Properties properties) { this.properties = properties; }

    private Geometry geometry;

    public Geometry getGeometry() { return this.geometry; }

    public void setGeometry(Geometry geometry) { this.geometry = geometry; }
}
