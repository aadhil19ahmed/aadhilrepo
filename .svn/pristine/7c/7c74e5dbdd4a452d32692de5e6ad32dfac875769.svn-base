package com.example.aadhilahmed.mapboxdeliveries1.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.aadhilahmed.mapboxdeliveries1.DeliveryLocationsDisplayer;
import com.example.aadhilahmed.mapboxdeliveries1.JsonDataFetcher;
import com.example.aadhilahmed.mapboxdeliveries1.Models.OptimisationResponse;
import com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass;
import com.example.aadhilahmed.mapboxdeliveries1.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.deliveryDetails;
import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.requestQueue;

public class LocationsActivity extends AppCompatActivity {

    public static MapView locationsMapView;
    private RelativeLayout startRoute;
    String baseUrl="https://api.mapbox.com/optimized-trips/v1/mapbox/driving/";
    OptimisationResponse result;
    public List<LatLng> coordinates=new ArrayList<>();
    Map<Integer,LatLng> optimizedPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        setToolbar();

        locationsMapView=(MapView)findViewById(R.id.mapView);
        locationsMapView.onCreate(savedInstanceState);

        startRoute=(RelativeLayout) findViewById(R.id.startRouteButton);

        DeliveryLocationsDisplayer displayer=new DeliveryLocationsDisplayer();
        displayer.showPoints(locationsMapView,this);

        for(int i=0;i<deliveryDetails.size();i++){
            double lat=deliveryDetails.get(i).getGeometry()[0];
            double longt=deliveryDetails.get(i).getGeometry()[1];
            coordinates.add(new LatLng(lat,longt));
        }


        startRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pointsForUrl= MyApplicationClass.pointsFormatter(coordinates);
                String accessToken="access_token="+ Mapbox.getAccessToken();
                String url=baseUrl+pointsForUrl+accessToken;

                JsonDataFetcher<OptimisationResponse> request=new JsonDataFetcher<>(url,OptimisationResponse.class,null,createSuccessListener(),createErrorListener());
                requestQueue.add(request);
            }
        });
    }

    private Response.ErrorListener createErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "Error : " + error.getLocalizedMessage());
            }
        };
    }

    private Response.Listener<OptimisationResponse> createSuccessListener() {
        return new Response.Listener<OptimisationResponse>() {
            @Override
            public void onResponse(OptimisationResponse response) {
                result=new OptimisationResponse(response.getTrips(),response.getWaypoints(),response.getCode());
                optimizedPoints=new TreeMap<>();
                for(int i=0;i<result.getWaypoints().length;i++){
                    LatLng tempLatLng=new LatLng();
                    tempLatLng.setLatitude(Double.parseDouble(result.getWaypoints()[i].getLocation()[1]));
                    tempLatLng.setLongitude(Double.parseDouble(result.getWaypoints()[i].getLocation()[0]));
                    optimizedPoints.put(Integer.parseInt(result.getWaypoints()[i].getWaypoint_index()),tempLatLng);
                }
                navigateToGoogleMaps();
            }
        };
    }

    private void navigateToGoogleMaps() {
        StringBuilder uriIntent=new StringBuilder("https://www.google.com/maps/dir/?api=1");
        StringBuilder origin=new StringBuilder("&origin=");
        StringBuilder destination=new StringBuilder("&destination=");
        StringBuilder waypoints=new StringBuilder("&waypoints=");
        StringBuilder travelmode=new StringBuilder("&travelmode=driving");
        for(int i=0;i<optimizedPoints.size();i++){
            if(i==0){
                origin.append(optimizedPoints.get(i).getLatitude()+","+optimizedPoints.get(i).getLongitude());
            }else if(i==(optimizedPoints.size()-1)){
                destination.append(optimizedPoints.get(i).getLatitude()+","+optimizedPoints.get(i).getLongitude());
            }else{
                if(i==(optimizedPoints.size()-2))
                    waypoints.append(optimizedPoints.get(i).getLatitude()+","+optimizedPoints.get(i).getLongitude());
                else {
                    waypoints.append(optimizedPoints.get(i).getLatitude()+","+optimizedPoints.get(i).getLongitude()+"|");
                }
            }

        }


        uriIntent.append(origin);
        uriIntent.append(destination);
        uriIntent.append(waypoints);
        uriIntent.append(travelmode);

        Uri mapUri=Uri.parse(uriIntent.toString());
        Intent intent=new Intent(Intent.ACTION_VIEW,mapUri);
        intent.setPackage("com.google.android.apps.maps");
        try{
            startActivity(intent);
        }catch (ActivityNotFoundException ex){
            Toast.makeText(LocationsActivity.this,"Install maps",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        locationsMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationsMapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationsMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationsMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationsMapView.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar4);
        setSupportActionBar(tb);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.title_locations_activity);
    }
}
