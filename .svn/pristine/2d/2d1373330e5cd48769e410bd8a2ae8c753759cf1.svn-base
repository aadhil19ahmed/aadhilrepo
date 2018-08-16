package com.example.aadhilahmed.mapboxdeliveries1;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;

import static com.example.aadhilahmed.mapboxdeliveries1.CircleOperations.CircleImplementer.clearCircle;
import static com.example.aadhilahmed.mapboxdeliveries1.MapboxOperator.clearMarker;

/**
 * Created by aadhil.ahmed on 09-Nov-17.
 */

public class CircleDetailsFragment extends DialogFragment {

    String radius;
    String address;
    String undelivered;
    String delivered;
    MapboxMap map;
    Marker marker;
    MapView mapView;

    public CircleDetailsFragment(){}

    public void setAttributes(String radius, String address, String undelivered, String delivered, MapboxMap map,Marker marker,MapView mapView){
        this.radius=radius;
        this.address=address;
        this.undelivered=undelivered;
        this.delivered=delivered;
        this.map=map;
        this.marker=marker;
        this.mapView=mapView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.circle_popup_layout,container);
        TextView txtAddress=(TextView)v.findViewById(R.id.centreDenoter);
        TextView txtRadius=(TextView)v.findViewById(R.id.radiusDenoter);
        TextView txtUndelivered=(TextView)v.findViewById(R.id.undeliveredDenoter);
        TextView txtDelivered=(TextView)v.findViewById(R.id.deliveredDenoter);
        Button btnDismiss=(Button) v.findViewById(R.id.btnDismiss);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        txtAddress.setText(address);
        txtRadius.setText(radius);
        txtUndelivered.setText(undelivered);
        txtDelivered.setText(delivered);

        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCircle(map);
                clearMarker(marker,mapView);
                dismiss();
            }
        });
        return v;
    }


}
