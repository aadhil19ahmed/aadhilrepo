package com.example.aadhilahmed.mapboxdeliveries1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.aadhilahmed.mapboxdeliveries1.JsonDataFetcher;
import com.example.aadhilahmed.mapboxdeliveries1.MapboxOperator;
import com.example.aadhilahmed.mapboxdeliveries1.Models.OptimisationResponse;
import com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass;
import com.example.aadhilahmed.mapboxdeliveries1.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import java.util.ArrayList;
import java.util.List;

import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.deliveryDetails;
import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.requestQueue;

public class OptimisedRouteActivity extends AppCompatActivity {

    public static MapView optimisedRouteMapview;
    private RelativeLayout navigateButton;
    public List<LatLng> coordinates=new ArrayList<>();
    String baseUrl="https://api.mapbox.com/optimized-trips/v1/mapbox/driving/";
    public static OptimisationResponse result;
    MapboxOperator operator=new MapboxOperator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimised_route);
        setToolbar();

        optimisedRouteMapview=(MapView)findViewById(R.id.mapView);
        optimisedRouteMapview.onCreate(savedInstanceState);

        navigateButton=(RelativeLayout)findViewById(R.id.navigateButton);

        for(int i=0;i<deliveryDetails.size();i++){
            double lat=deliveryDetails.get(i).getGeometry()[0];
            double longt=deliveryDetails.get(i).getGeometry()[1];
            coordinates.add(new LatLng(lat,longt));
        }
        String pointsForUrl= MyApplicationClass.pointsFormatter(coordinates);

        String accessToken="access_token="+ Mapbox.getAccessToken();

        String url=baseUrl+pointsForUrl+accessToken;

        JsonDataFetcher<OptimisationResponse> request=new JsonDataFetcher<>(url,OptimisationResponse.class,null,createSuccessListener(),createErrorListener());
        requestQueue.add(request);

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
                List<String> waypointIndex=new ArrayList<>();
                for(int i=0;i<result.getWaypoints().length;i++){
                    waypointIndex.add(result.getWaypoints()[i].getWaypoint_index());
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        optimisedRouteMapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        optimisedRouteMapview.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        optimisedRouteMapview.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        optimisedRouteMapview.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        optimisedRouteMapview.onDestroy();
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
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar5);
        setSupportActionBar(tb);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.title_optimisedroute_activity);
    }
}
