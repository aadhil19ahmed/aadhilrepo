package com.example.aadhilahmed.mapboxdeliveries1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.aadhilahmed.mapboxdeliveries1.Adapter.CheckBoxAdapter;
import com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox.ChoroplethManipulator;
import com.example.aadhilahmed.mapboxdeliveries1.MapboxOperator;
import com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox.LocalJsonConverter;
import com.example.aadhilahmed.mapboxdeliveries1.Models.CheckBoxItem;
import com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox.PopulateCheckboxList;
import com.example.aadhilahmed.mapboxdeliveries1.R;
import com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox.RangeOfCheckedItems;
import com.mapbox.mapboxsdk.maps.MapView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChoroplethActivity extends AppCompatActivity {

    public static MapView choroplethMapView;
    private CheckBoxAdapter mBoxAdapter;
    private List<CheckBoxItem> adapterList;
    private List<String> currentCheckedItems;
    public static JSONArray jsonRawArray;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choropleth);
        setToolbar();

        choroplethMapView=(MapView)findViewById(R.id.mapView);
        choroplethMapView.onCreate(savedInstanceState);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerViewCheckbox);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Fetching list to call CheckBoxAdapter
        adapterList=PopulateCheckboxList.fetchAdapterList();

        //Adapter call to inflate recyclerView
        mBoxAdapter=new CheckBoxAdapter(adapterList,this);
        recyclerView.setAdapter(mBoxAdapter);
        mBoxAdapter.notifyDataSetChanged();

        try {
            String jsonRawData = LocalJsonConverter.convertJSONFile("stateData.geojson", ChoroplethActivity.this);
            jsonRawArray=LocalJsonConverter.fetchJsonArray(jsonRawData);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setToolbar() {
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(tb);

        //set Back Button and title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.title_choropleth_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        choroplethMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        choroplethMapView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        choroplethMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        choroplethMapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        choroplethMapView.onDestroy();
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
