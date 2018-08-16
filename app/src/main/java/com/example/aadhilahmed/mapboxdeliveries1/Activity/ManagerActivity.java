package com.example.aadhilahmed.mapboxdeliveries1.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.aadhilahmed.mapboxdeliveries1.Adapter.RightExpandableListViewAdapter;
import com.example.aadhilahmed.mapboxdeliveries1.InfoDetailsFragment;
import com.example.aadhilahmed.mapboxdeliveries1.JsonDataFetcher;
import com.example.aadhilahmed.mapboxdeliveries1.LocationFetcher;
import com.example.aadhilahmed.mapboxdeliveries1.MapboxOperator;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Features;
import com.example.aadhilahmed.mapboxdeliveries1.Models.ReverseGeocodeModel;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;
import com.example.aadhilahmed.mapboxdeliveries1.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerViewOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import static com.example.aadhilahmed.mapboxdeliveries1.ExpandableListDataPump.DataLoader.getExpandableItems;
import static com.example.aadhilahmed.mapboxdeliveries1.ExpandableListDataPump.DataLoader.getTitles;
import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.address;
import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.deliveryDetails;
import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.requestQueue;

public class ManagerActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ActionBarDrawerToggle mDrawerToggle;
    String[] mDrawerListItems;
    ExpandableListView mRightDrawerList;
    RightExpandableListViewAdapter expandableListAdapter;
    MaterialSearchView searchView;
    public static MapView mapView;
    String placeName;
    LinearLayout mRightDrawerLayout;
    Button clearFilters;
    MapboxOperator operator=new MapboxOperator();
    public static List<Marker> addressMarkers=new ArrayList<>();
    public LatLng tappedPoint;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        setToolbar();

        mapView=(MapView)findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);


        //checks for internet connection and shows alert dialog
        if(isOnline(this)){
            //Shows mock delivery boys location
            LocationFetcher fetcher=new LocationFetcher();
            fetcher.showLocations(ManagerActivity.this);
        }else {
            //Alert dialog code
            try{
                AlertDialog dialog=new AlertDialog.Builder(ManagerActivity.this).create();

                dialog.setTitle("Error");
                dialog.setMessage("Internet is not available");
                dialog.setButton(DialogInterface.BUTTON_POSITIVE,"Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        //shows all delivery locations on map
        addressMarkers=operator.drawMarkerMap(mapView,deliveryDetails,this);

        //inflates infowindow according to the marker
        operator.manageInfoWindow(mapView,addressMarkers,this);


        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {
                operator.drawMarkerMap(mapView,deliveryDetails,ManagerActivity.this);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query!=null&& !query.isEmpty()){
                    List<Tasks> searchedDetails=new ArrayList<>();

                        for(int i=0;i<deliveryDetails.size();i++){
                            if(deliveryDetails.get(i).getProduct().contains(query)){
                                searchedDetails.add(deliveryDetails.get(i));
                            }
                        }
                    mapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(MapboxMap mapboxMap) {
                            for(int i=0;i<addressMarkers.size();i++){
                                mapboxMap.removeMarker(addressMarkers.get(i));
                            }
                        }
                    });
                    operator.drawMarkerMap(mapView,searchedDetails,ManagerActivity.this);
                }else {
                    operator.drawMarkerMap(mapView,deliveryDetails,ManagerActivity.this);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
              return false;
            }
        });

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final MapboxMap mapboxMap) {
                mapboxMap.setOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull final LatLng point) {
                        String baseurl="https://api.mapbox.com/geocoding/v5/mapbox.places/";
                        String pointsUrl=""+point.getLongitude()+","+point.getLatitude()+".json?limit=1&";
                        String accessToken="access_token="+ Mapbox.getAccessToken();
                        String url=baseurl+pointsUrl+accessToken;

//                        String placeName=reverseGeocoder(url,ManagerActivity.this);
                        tappedPoint=point;
                        JsonDataFetcher<ReverseGeocodeModel> reqJson=new JsonDataFetcher<>(url,ReverseGeocodeModel.class,null,getSuccessListener(),getErrorListener());
                        requestQueue.add(reqJson);

                        //pass values to display in infowindow adapter
                        operator.assignAttributes(point,"1141,Dr.Saint Street,Los Angeles,California,US");

                        Marker centre=mapboxMap.addMarker(new MarkerViewOptions()
                        .position(point));

                        Toast.makeText(ManagerActivity.this,R.string.toast_tap_on_marker,Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });

        //Drawer layout navigates to activity
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                if(position==0){
                    intent=new Intent(ManagerActivity.this,ChoroplethActivity.class);
                    startActivity(intent);
                }else{
                    intent=new Intent(ManagerActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });

        clearFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableListAdapter.clearChecks();
            }
        });

    }

    public boolean isOnline(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    private void setToolbar() {
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        mDrawerList=(ListView)findViewById(R.id.drawerlistView);
        mRightDrawerLayout=(LinearLayout)findViewById(R.id.rightDrawer);
        mRightDrawerList=(ExpandableListView)findViewById(R.id.drawerlistViewRight);
        clearFilters=(Button)findViewById(R.id.btnClearFilter);
        mDrawerListItems=getResources().getStringArray(R.array.drawer_list);
        mDrawerList.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mDrawerListItems));
        expandableListAdapter=new RightExpandableListViewAdapter(ManagerActivity.this,getTitles(),getExpandableItems(),operator);
        mRightDrawerList.setAdapter(expandableListAdapter);

        mDrawerToggle=new ActionBarDrawerToggle(this,
                mDrawerLayout,
                tb,
                R.string.drawer_open,
                R.string.drawer_close){
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
                syncState();
            }
        };

        clearFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operator.drawMarkerMap(mapView,deliveryDetails,ManagerActivity.this);
            }
        });

        final int[] prevExpandPosition={-1};
        mRightDrawerList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(prevExpandPosition[0]>=0){
                    mRightDrawerList.collapseGroup(prevExpandPosition[0]);
                }
                prevExpandPosition[0]=groupPosition;
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setSupportActionBar(tb);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.title_manager_activity);

        searchView=(MaterialSearchView)findViewById(R.id.search_view);
        mDrawerToggle.syncState();
    }

    private Response.Listener<ReverseGeocodeModel> getSuccessListener() {
        return new Response.Listener<ReverseGeocodeModel>() {
            @Override
            public void onResponse(ReverseGeocodeModel response) {
                Features feature=response.getFeatures()[0];
                address=feature.getPlace_name();
                placeName=address;

                operator.assignAttributes(tappedPoint,placeName);
            }

        };
    }

    private Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("TAG", "Error : " + error.getLocalizedMessage());
            }
        };
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout,menu);
        MenuItem item=menu.findItem(R.id.action_search);

        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                if(mDrawerLayout.isDrawerOpen(mDrawerList)){
                    mDrawerLayout.closeDrawer(mDrawerList);
                }else {
                    mDrawerLayout.openDrawer(mDrawerList);
                }

                if(mDrawerLayout.isDrawerOpen(mRightDrawerLayout)){
                    mDrawerLayout.closeDrawer(mRightDrawerLayout);
                }
                return true;
            }
            case R.id.action_info:{
                FragmentManager manager=getSupportFragmentManager();
                InfoDetailsFragment dialog=new InfoDetailsFragment();
                dialog.show(manager,"fragment_name");

            }
            case R.id.action_filter:{
                if(mDrawerLayout.isDrawerOpen(mRightDrawerLayout)){
                    mDrawerLayout.closeDrawer(mRightDrawerLayout);
                }else {
                    mDrawerLayout.openDrawer(mRightDrawerLayout);
                }

                if(mDrawerLayout.isDrawerOpen(mDrawerList)){
                    mDrawerLayout.closeDrawer(mDrawerList);
                }
                return true;
            }
            default:return super.onOptionsItemSelected(item);
        }

    }
}
