package com.example.aadhilahmed.mapboxdeliveries1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aadhilahmed.mapboxdeliveries1.MapboxOperator;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;
import com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass;
import com.example.aadhilahmed.mapboxdeliveries1.R;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;

import static com.example.aadhilahmed.mapboxdeliveries1.CircleOperations.CircleImplementer.clearCircle;

public class RadiusFilteringActivity extends AppCompatActivity {

    public static MapView radiusMapView;
    public List<Tasks> deliveryLocationDetails;
    MapboxOperator operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radius_filtering);
        setToolbar();

        radiusMapView=(MapView)findViewById(R.id.mapView);
        radiusMapView.onCreate(savedInstanceState);

        final Button btnShowResult=(Button)findViewById(R.id.btnShowCircleResult);

        deliveryLocationDetails= MyApplicationClass.deliveryDetails;

        if(deliveryLocationDetails!=null){
            operator=new MapboxOperator();
            operator.drawMarkerMap(radiusMapView,deliveryLocationDetails,this);
        }else {
            onCreate(savedInstanceState);
        }
        final MapboxOperator mapboxOperator=new MapboxOperator();
//        mapboxOperator.drawCircleMap(radiusMapView,this);

        btnShowResult.setTag(1);
        btnShowResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result=(Integer)v.getTag();

                if(result==1){
                    btnShowResult.setText(R.string.circle_result_button_name2);
                    v.setTag(0);
//                    int count;
//                    count=mapboxOperator.circlePointsChecker(radiusMapView,deliveryLocationDetails);

                    Toast.makeText(RadiusFilteringActivity.this,"no.of points inside are",Toast.LENGTH_SHORT).show();
                }else{
                    radiusMapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(MapboxMap mapboxMap) {
                            clearCircle(mapboxMap);
                        }
                    });
                    btnShowResult.setText(R.string.circle_result_button_name1);
                    v.setTag(1);
                }
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        radiusMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        radiusMapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        radiusMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        radiusMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        radiusMapView.onDestroy();
    }

    private void setToolbar() {
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(tb);

        //set back button and title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.title_radiusfiltering_activity);
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

}
