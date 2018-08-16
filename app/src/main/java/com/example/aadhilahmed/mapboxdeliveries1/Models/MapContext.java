package com.example.aadhilahmed.mapboxdeliveries1.Models;

/**
 * Created by aadhil.ahmed on 10-Nov-17.
 */

public class MapContext {
    private String id;

    private String text;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", text = "+text+"]";
    }
}
