package com.example.aadhilahmed.mapboxdeliveries1.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.aadhilahmed.mapboxdeliveries1.Adapter.DeliveryListAdapter;
import com.example.aadhilahmed.mapboxdeliveries1.JsonDataFetcher;
import com.example.aadhilahmed.mapboxdeliveries1.Models.DeliveryPointsModel;
import com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass;
import com.example.aadhilahmed.mapboxdeliveries1.R;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;

import java.util.ArrayList;
import java.util.List;

public class DeliveryBoyActivity extends AppCompatActivity {

    List<Tasks> deliveryLocations=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private DeliveryListAdapter mAdapter;
    private RelativeLayout routingButton;
    ManagerActivity networkChecker=new ManagerActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_delivery_boy);
        setToolbar();

        if(networkChecker.isOnline(this)){
            //Fetching all the delivery points
            deliveryLocations= MyApplicationClass.deliveryDetails;
        }else {
            //Alert dialog code
            try{
                AlertDialog dialog=new AlertDialog.Builder(DeliveryBoyActivity.this).create();

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


        routingButton=(RelativeLayout)findViewById(R.id.viewRouteButton);

        mRecyclerView=(RecyclerView)findViewById(R.id.deliveryRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        if(deliveryLocations!=null) {
            mAdapter = new DeliveryListAdapter(deliveryLocations, this);
            mRecyclerView.setAdapter(mAdapter);
        }
        mAdapter.notifyDataSetChanged();

        routingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DeliveryBoyActivity.this,LocationsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_signout,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sign_out:{
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbar() {
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar3);
        setSupportActionBar(tb);

        getSupportActionBar().setTitle(R.string.title_deliveryboy_activity);
    }
}
