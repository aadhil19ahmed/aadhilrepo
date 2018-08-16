package com.example.aadhilahmed.mapboxdeliveries1.Models;

/**
 * Created by aadhil.ahmed on 23-Oct-17.
 */

public class Tasks {
    private String product;

    private String delivery_type;

    private String product_id;

    private String address;

    private String order_id;

    private String order_status;

    private double[] geometry;

    public Tasks(String product,String delivery_type,String product_id,String address,String order_id,String order_status,double[] geometry){
        this.product=product;
        this.delivery_type=delivery_type;
        this.product_id=product_id;
        this.address=address;
        this.order_id=order_id;
        this.order_status=order_status;
        this.geometry=geometry;
    }
    public String getProduct ()
    {
        return product;
    }

    public String getDelivery_type ()
    {
        return delivery_type;
    }

    public String getProduct_id ()
    {
        return product_id;
    }

    public String getAddress ()
    {
        return address;
    }

    public String getOrder_id ()
    {
        return order_id;
    }

    public String getOrder_status(){return order_status;}

    public double[] getGeometry ()
    {
        return geometry;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [product = "+product+", delivery_type = "+delivery_type+", product_id = "+product_id+", address = "+address+", order_id = "+order_id+", geometry = "+geometry+"]";
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null||getClass()!=obj.getClass()) return false;

        Tasks task=(Tasks)obj;
        if (product != null ? !product.equals(task.product) : task.product != null) return false;
        if (delivery_type != null ? !delivery_type.equals(task.delivery_type) : task.delivery_type != null) return false;
        if (product_id != null ? !product_id.equals(task.product_id) : task.product_id != null)
            return false;
        if (address != null ? !address.equals(task.address) : task.address != null)
            return false;
        return !(order_status != null ? !order_status.equals(task.order_status) : task.order_status != null);
    }
}
