package com.example.aadhilahmed.mapboxdeliveries1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.aadhilahmed.mapboxdeliveries1.Activity.LocationsActivity;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.HashMap;
import java.util.List;

/**
 * Created by aadhil.ahmed on 30-Oct-17.
 */

public class DeliveryLocationsDisplayer {

    private HashMap<String,String> markerContentHolder=new HashMap<>();
    public void showPoints(final MapView mapView, final Context context){

        final List<Tasks> deliveryPoints=MyApplicationClass.deliveryDetails;
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                for(int i=0;i<deliveryPoints.size();i++){
                    double lat=deliveryPoints.get(i).getGeometry()[0];
                    double longt=deliveryPoints.get(i).getGeometry()[1];
                    mapboxMap.addMarker(generateMarker(deliveryPoints.get(i).getProduct(),
                                                        deliveryPoints.get(i).getAddress(),new LatLng(lat,longt)));
                    markerContentHolder.put(deliveryPoints.get(i).getProduct(),deliveryPoints.get(i).getDelivery_type());
                }

                mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
                    @Nullable
                    @Override
                    public View getInfoWindow(@NonNull Marker marker) {
                        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                        View v=inflater.inflate(R.layout.delivery_location_info_window_layout,null);

                        TextView productName=(TextView)v.findViewById(R.id.txtProductTitle);
                        TextView deliveryType=(TextView)v.findViewById(R.id.txtDeliverySpeed);
                        TextView addressDetail=(TextView)v.findViewById(R.id.txtAddressDisplay);

                        productName.setText(marker.getTitle());
                        addressDetail.setText(marker.getSnippet());
                        deliveryType.setText(markerContentHolder.get(marker.getTitle()));
                        if(deliveryType.getText().toString().equals("normal")){
                            deliveryType.setTextColor(context.getResources().getColor(R.color.color_green));
                        }else {
                            deliveryType.setTextColor(context.getResources().getColor(R.color.color_orange));
                        }

                        return v;
                    }
                });
            }
        });
    }

    private MarkerOptions generateMarker(String product, String address, LatLng pos) {
        MarkerOptions marker=new MarkerOptions();
        marker.title(product);
        marker.snippet(address);
        marker.position(pos);
        return marker;
    }
}
