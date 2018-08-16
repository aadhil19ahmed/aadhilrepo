package com.example.aadhilahmed.mapboxdeliveries1.Models;

/**
 * Created by aadhil.ahmed on 10-Nov-17.
 */

public class ReverseGeocodeModel {
    private String[] query;

    private Features[] features;

    private String type;

    private String attribution;

    public String[] getQuery ()
    {
        return query;
    }

    public void setQuery (String[] query)
    {
        this.query = query;
    }

    public Features[] getFeatures ()
    {
        return features;
    }

    public void setFeatures (Features[] features)
    {
        this.features = features;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getAttribution ()
    {
        return attribution;
    }

    public void setAttribution (String attribution)
    {
        this.attribution = attribution;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [query = "+query+", features = "+features+", type = "+type+", attribution = "+attribution+"]";
    }
}
