package com.example.aadhilahmed.mapboxdeliveries1.Models;

/**
 * Created by aadhil.ahmed on 10-Nov-17.
 */

public class Features {
    private String[] center;

    private String id;

    private String[] place_type;

    private String text;

    private String address;

    private String place_name;

    private MapContext[] context;

    private String relevance;

    private String properties;

    private String type;

    private GeocodeGeometry geometry;

    public String[] getCenter ()
    {
        return center;
    }

    public void setCenter (String[] center)
    {
        this.center = center;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String[] getPlace_type ()
    {
        return place_type;
    }

    public void setPlace_type (String[] place_type)
    {
        this.place_type = place_type;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getPlace_name ()
    {
        return place_name;
    }

    public void setPlace_name (String place_name)
    {
        this.place_name = place_name;
    }

    public MapContext[] getContext ()
    {
        return context;
    }

    public void setContext (MapContext[] context)
    {
        this.context = context;
    }

    public String getRelevance ()
    {
        return relevance;
    }

    public void setRelevance (String relevance)
    {
        this.relevance = relevance;
    }

    public String getProperties ()
    {
        return properties;
    }

    public void setProperties (String properties)
    {
        this.properties = properties;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public GeocodeGeometry getGeometry ()
    {
        return geometry;
    }

    public void setGeometry (GeocodeGeometry geometry)
    {
        this.geometry = geometry;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [center = "+center+", id = "+id+", place_type = "+place_type+", text = "+text+", address = "+address+", place_name = "+place_name+", context = "+context+", relevance = "+relevance+", properties = "+properties+", type = "+type+", geometry = "+geometry+"]";
    }
}
