package com.example.aadhilahmed.mapboxdeliveries1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadhilahmed.mapboxdeliveries1.Activity.ChoroplethActivity;
import com.example.aadhilahmed.mapboxdeliveries1.Activity.ManagerActivity;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.annotations.Polygon;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.aadhilahmed.mapboxdeliveries1.CircleOperations.CircleImplementer.clearCircle;
import static com.example.aadhilahmed.mapboxdeliveries1.LocationFetcher.personMarker;
import static com.example.aadhilahmed.mapboxdeliveries1.LocationFetcher.picIdentifier;
import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.address;
import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.deliveryDetails;

/**
 * Created by aadhil.ahmed on 26-Oct-17.
 */

public class MapboxOperator {

    public EditText inputRadius;
    public List<Marker> locationMarker=new ArrayList<>();
    public final HashMap<String,String> markerContentHolder=new HashMap<>();
    public LatLng point;
    public String placeName;

    public void assignAttributes(LatLng point,String placeName){
        this.point=point;
        this.placeName=placeName;
    }
    public static void clearMap(MapView mapView){
       mapView.getMapAsync(new OnMapReadyCallback() {
           @Override
           public void onMapReady(MapboxMap mapboxMap) {
               mapboxMap.clear();
           }
       });

    }
    public static void clearMarker(final Marker marker, MapView mapView){
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.removeMarker(marker);
            }
        });
    }

    public static void clearMarkers(final List<Marker> markers,MapView mapView){
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                mapboxMap.removeAnnotations(markers);
            }
        });
    }

    public void drawPolygonMap(final List<List<LatLng>> points, final List<String> colour){
        ChoroplethActivity.choroplethMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                Polygon[] polygons=new Polygon[points.size()];
                for(int m=0;m< colour.size();m++) {
                    polygons[m]=mapboxMap.addPolygon(new PolygonOptions()
                            .addAll(points.get(m))
                            .fillColor(Color.parseColor(colour.get(m)))
                    );
                }

            }
        });
    }

    public List<Marker> drawMarkerMap(MapView mapView, final List<Tasks> address, final Context context){


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                IconFactory iconFactory=IconFactory.getInstance(context);
                for(int i=0;i<address.size();i++){
                    double lat=address.get(i).getGeometry()[0];
                    double longt=address.get(i).getGeometry()[1];
                    if(address.get(i).getOrder_status().equals("undelivered")){
                        locationMarker.add(mapboxMap.addMarker(new MarkerViewOptions()
                                .title(address.get(i).getProduct())
                                .snippet(address.get(i).getAddress())
                                .position(new LatLng(lat,longt))
                                .icon(iconFactory.fromResource(R.drawable.ic_location_violet))));

                    }else if(address.get(i).getOrder_status().equals("delivered")){
                        locationMarker.add(mapboxMap.addMarker(new MarkerViewOptions()
                                .title(address.get(i).getProduct())
                                .snippet(address.get(i).getAddress())
                                .position(new LatLng(lat,longt))
                                .icon(iconFactory.fromResource(R.drawable.ic_location_green))));

                    }else {
                        locationMarker.add(mapboxMap.addMarker(new MarkerViewOptions()
                                .title(address.get(i).getProduct())
                                .snippet(address.get(i).getAddress())
                                .position(new LatLng(lat,longt))
                                .icon(iconFactory.fromResource(R.drawable.ic_location_yellow))));

                    }
                    markerContentHolder.put(address.get(i).getProduct(),address.get(i).getDelivery_type());
                }

            }

        });
        return locationMarker;
    }

    public void drawMarker(MapView mapView, final Tasks tasks, final Context context){
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                IconFactory iconFactory=IconFactory.getInstance(context);

                    double lat=tasks.getGeometry()[0];
                    double longt=tasks.getGeometry()[1];
                    if(tasks.getOrder_status().equals("undelivered")){
                        locationMarker.add(mapboxMap.addMarker(new MarkerViewOptions()
                                .title(tasks.getProduct())
                                .snippet(tasks.getAddress())
                                .position(new LatLng(lat,longt))
                                .icon(iconFactory.fromResource(R.drawable.ic_location_violet))));

                    }else if(tasks.getOrder_status().equals("delivered")){
                        locationMarker.add(mapboxMap.addMarker(new MarkerViewOptions()
                                .title(tasks.getProduct())
                                .snippet(tasks.getAddress())
                                .position(new LatLng(lat,longt))
                                .icon(iconFactory.fromResource(R.drawable.ic_location_green))));

                    }else {
                        locationMarker.add(mapboxMap.addMarker(new MarkerViewOptions()
                                .title(tasks.getProduct())
                                .snippet(tasks.getAddress())
                                .position(new LatLng(lat,longt))
                                .icon(iconFactory.fromResource(R.drawable.ic_location_yellow))));

                    }
                    markerContentHolder.put(tasks.getProduct(),tasks.getDelivery_type());

            }

        });
    }

//    public void drawCircleMap(MapView mapView, final Context context){
//        mapView.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(final MapboxMap mapboxMap) {
//                mapboxMap.setOnMapLongClickListener(new MapboxMap.OnMapLongClickListener() {
//                    @Override
//                    public void onMapLongClick(@NonNull final LatLng point) {
//                        circleCentre=point;
//                        mapboxMap.addMarker(new MarkerViewOptions()
//                        .position(circleCentre));
//                        mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
//                            @Nullable
//                            @Override
//                            public View getInfoWindow(@NonNull final Marker marker) {
//                                LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                                View v= inflater.inflate(R.layout.draw_circlewindow_layout,null);
//
//                                inputRadius=(EditText)v.findViewById(R.id.txtRadiusInput);
//                                final Button btnDrawCircle=(Button)v.findViewById(R.id.btnDrawCircle);
//
//                                btnDrawCircle.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        InputMethodManager manager=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                                        manager.hideSoftInputFromWindow(btnDrawCircle.getWindowToken(),0);
//                                        if(!inputRadius.getText().toString().isEmpty()){
//                                            float radiusInMeters=1000*(Float.parseFloat(inputRadius.getText().toString()));
//                                            CircleOperations.CircleImplementer.drawCircle(mapboxMap,point,R.color.color_gray,radiusInMeters);
//                                            marker.hideInfoWindow();
//                                        }else{
//                                            Toast.makeText(context,R.string.toast_alert_circle_window,Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//
//                                return v;
//
//                            }
//                        });
//                    }
//                });
//            }
//        });
//    }

//    public void drawMarkersWithInfoWindow(MapView mapView, final List<Tasks> coordinates, final List<String> waypt_index, final Context context){
//        final Marker[] markerArray;
//
//        mapView.getMapAsync(new OnMapReadyCallback() {
//            @Override
//            public void onMapReady(MapboxMap mapboxMap) {
//                IconFactory iconFactory=IconFactory.getInstance(context);
//                for(int i=0;i<coordinates.size();i++){
//                    double lat=coordinates.get(i).getGeometry()[0];
//                    double longt=coordinates.get(i).getGeometry()[1];
//                    mapboxMap.addMarker(new MarkerOptions()
//                    .position(new LatLng(lat,longt))
//                    .icon(iconFactory.fromResource(R.drawable.ic_location_green))
//
//                    .title(waypt_index.get(i)));
//                }
//
//            }
//        });
//    }


    public void manageInfoWindow(final MapView mapView, final List<Marker> locationMarkers, final Context context) {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
                mapboxMap.setInfoWindowAdapter(new MapboxMap.InfoWindowAdapter() {
                    @Nullable
                    @Override
                    public View getInfoWindow(@NonNull final Marker marker) {
                        View v=null;
                        if(personMarker.contains(marker)){
                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                                 v= inflater.inflate(R.layout.delivery_person_info_window_layout, null);

                            TextView personName = (TextView) v.findViewById(R.id.txtDeliveryPersonName);
                            TextView location = (TextView) v.findViewById(R.id.txtLocationDisplay);
                            ImageView personPic = (ImageView) v.findViewById(R.id.imgDeliveryPerson);
                            ImageButton callPerson=(ImageButton)v.findViewById(R.id.btnCall);

                            String positionInfo = marker.getSnippet();

                            personName.setText(marker.getTitle());
                            location.setText(positionInfo);
                            personPic.setImageResource(picIdentifier.get(marker.getTitle()));
                            callPerson.setImageResource(R.drawable.ic_calling);

                            callPerson.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String phoneno="9787545452";
                                    Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneno));
                                    context.startActivity(i);
                                }
                            });
                        }
                        else if(locationMarkers.contains(marker)){
//                            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//                            v=inflater.inflate(R.layout.delivery_location_info_window_layout,null);
//
//                            TextView productName=(TextView)v.findViewById(R.id.txtProductTitle);
//                            TextView deliveryType=(TextView)v.findViewById(R.id.txtDeliverySpeed);
//                            TextView addressDetail=(TextView)v.findViewById(R.id.txtAddressDisplay);
//
//                            productName.setText(marker.getTitle());
//                            addressDetail.setText(marker.getSnippet());
//                            deliveryType.setText(markerContentHolder.get(marker.getTitle()));
//                            if(deliveryType.getText().toString().equals("normal")){
//                                deliveryType.setTextColor(context.getResources().getColor(R.color.color_green));
//                            }else {
//                                deliveryType.setTextColor(context.getResources().getColor(R.color.color_orange));
//                            }
                        }else {
                            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            v=inflater.inflate(R.layout.draw_circlewindow_layout,null);

                            inputRadius=(EditText)v.findViewById(R.id.txtRadiusInput);
                            final Button btnDrawCircle=(Button)v.findViewById(R.id.btnDrawCircle);

                            btnDrawCircle.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    int deliveredCount=0;
                                    int undeliveredCount=0;

                                    //draw circle based on given radius
                                    InputMethodManager manager=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
                                    manager.hideSoftInputFromWindow(btnDrawCircle.getWindowToken(),0);
                                    if(!inputRadius.getText().toString().isEmpty()){
                                        float radiusInMeters=1000*(Float.parseFloat(inputRadius.getText().toString()));
                                        CircleOperations.CircleImplementer.drawCircle(mapboxMap,point,R.color.color_gray,radiusInMeters);
                                        marker.hideInfoWindow();
                                    }else{
                                        Toast.makeText(context,R.string.toast_alert_circle_window,Toast.LENGTH_SHORT).show();
                                    }

                                    List<LatLng> insidePoints=new ArrayList<>();
                                    insidePoints=circlePointsChecker(mapView,deliveryDetails);
                                    for(int i=0;i<deliveryDetails.size();i++){
                                        LatLng check=new LatLng(deliveryDetails.get(i).getGeometry()[0],deliveryDetails.get(i).getGeometry()[1]);
                                        if(insidePoints.contains(check)){
                                            if(deliveryDetails.get(i).getOrder_status().equals("undelivered")||deliveryDetails.get(i).getOrder_status().equals("inprogress")){
                                                undeliveredCount++;
                                            }else {
                                                deliveredCount++;
                                            }
                                        }
                                    }

                                    FragmentManager fragmentManager=((FragmentActivity)context).getSupportFragmentManager();
                                    CircleDetailsFragment infoView=new CircleDetailsFragment();
                                    infoView.setAttributes(inputRadius.getText().toString(),placeName,""+undeliveredCount,
                                            ""+deliveredCount,mapboxMap,marker,mapView);
                                    infoView.show(fragmentManager,"Fragment_name");

                                }
                            });
                        }
                        return v;
                    }
                });
            }
        });

    }

    public List<LatLng> circlePointsChecker(MapView mapView,List<Tasks> deliveryList){
        List<LatLng> insidePoints;

        insidePoints=CircleOperations.checkForPointsInsideCircle(deliveryList,point,Float.parseFloat(inputRadius.getText().toString()));
        return insidePoints;
    }
}
