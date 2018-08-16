package com.example.aadhilahmed.mapboxdeliveries1;

import com.example.aadhilahmed.mapboxdeliveries1.Models.ChildViewModel;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.deliveryDetails;

/**
 * Created by aadhil.ahmed on 16-Nov-17.
 */

public class ExpandableListDataPump {
    public static class DataLoader{

        public static Map<String,List<ChildViewModel>> expandedItems=new HashMap<>();
        public static  List<String> headers=new ArrayList<>();

        public static List<String> getTitles(){
            headers= Arrays.asList("product","product_id","delivery_type","address","order_status");

            return headers;
        }
        public static Map<String,List<ChildViewModel>> getExpandableItems(){
            expandedItems.put(headers.get(0),getProducts());
            expandedItems.put(headers.get(1),getProductId());
            expandedItems.put(headers.get(2),getDeliveryTypes());
            expandedItems.put(headers.get(3),getAddress());
            expandedItems.put(headers.get(4),getOrderStatus());

            return expandedItems;
        }


        public static List<ChildViewModel> getProducts() {
            List<ChildViewModel> products=new ArrayList<>();
            for(int i=0;i<deliveryDetails.size();i++){
                ChildViewModel model=new ChildViewModel(deliveryDetails.get(i).getProduct(),false);
                products.add(model);
            }
            return products;
        }

        public static List<ChildViewModel> getProductId() {
            List<ChildViewModel> productId=new ArrayList<>();
            for(int i=0;i<deliveryDetails.size();i++){
                ChildViewModel model=new ChildViewModel(deliveryDetails.get(i).getProduct_id(),false);
                if(!productId.contains(model)){
                    productId.add(model);
                }
                Collections.sort(productId, new Comparator<ChildViewModel>() {
                    @Override
                    public int compare(ChildViewModel o1, ChildViewModel o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            }
            return productId;
        }



        public static List<ChildViewModel> getDeliveryTypes() {
            List<ChildViewModel> deliveryTypes=new ArrayList<>();
            for(int i=0;i<deliveryDetails.size();i++){
                ChildViewModel model=new ChildViewModel(deliveryDetails.get(i).getDelivery_type(),false);
                if(!deliveryTypes.contains(model))
                    deliveryTypes.add(model);
            }
            return deliveryTypes;
        }

        public static List<ChildViewModel> getAddress() {
            List<ChildViewModel> address=new ArrayList<>();
            for(int i=0;i<deliveryDetails.size();i++){
                ChildViewModel model=new ChildViewModel(deliveryDetails.get(i).getAddress(),false);
                if(!address.contains(model))
                    address.add(model);
            }
            return address;
        }


        public static List<ChildViewModel> getOrderStatus() {
            List<ChildViewModel> orderStatus=new ArrayList<>();
            for(int i=0;i<deliveryDetails.size();i++){
                ChildViewModel model=new ChildViewModel(deliveryDetails.get(i).getOrder_status(),false);
                if(!orderStatus.contains(model))
                    orderStatus.add(model);
            }
            return orderStatus;
        }
    }
}
