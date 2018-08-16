package com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox;

import com.example.aadhilahmed.mapboxdeliveries1.MapboxOperator;
import com.mapbox.mapboxsdk.geometry.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aadhil.ahmed on 26-Oct-17.
 */

public class ChoroplethManipulator {
    List<List<LatLng>> coordinates;
    List<String> colours;
    JSONArray jsonArray;
    private List<String> currentlyChecked;
    private int[] selectedRange;

    public ChoroplethManipulator(JSONArray jsonArray,List<String> currentlyChecked,int[] selectedRange){
        this.jsonArray=jsonArray;
        this.currentlyChecked=currentlyChecked;
        this.selectedRange=selectedRange;
    }

    public void choroplethSetter() throws JSONException{
        coordinates=new ArrayList<>();
        colours=new ArrayList<>();

        for(int state=0;state<jsonArray.length();state++) {
            boolean checked = false;

            //Choose color for the area
            float orders = (float) jsonArray.getJSONObject(state).getJSONObject("properties").getDouble("density");
            int temp=0;
            for (int n = 0; n < currentlyChecked.size(); n++) {
                if ((int) orders > selectedRange[temp] && (int) orders < selectedRange[temp+1])
                    checked = true;
                temp=temp+2;
            }

            if (checked == true) {
                //Collecting coordinates for drawing polygon
                JSONObject geometry = jsonArray.getJSONObject(state).getJSONObject("geometry");
                String type = geometry.getString("type");

                if (type.equals("Polygon")) {
                    JSONArray points = geometry.getJSONArray("coordinates").getJSONArray(0);
                    List<LatLng> polygonpoints = new ArrayList<>();
                    for (int i = 0; i < points.length(); i++) {
                        polygonpoints.add(new LatLng(points.getJSONArray(i).getDouble(1), points.getJSONArray(i).getDouble(0)));
                    }
                    coordinates.add(polygonpoints);
                    colours.add(ColorSelector.ColorChooser.chooseColor(orders));
                } else {
                    JSONArray points = geometry.getJSONArray("coordinates");
                    for (int p = 0; p < points.length(); p++) {
                        JSONArray splits = points.getJSONArray(p).getJSONArray(0);
                        List<LatLng> polygonpoints = new ArrayList<>();
                        for (int x = 0; x < splits.length(); x++) {
                            polygonpoints.add(new LatLng(splits.getJSONArray(x).getDouble(1), splits.getJSONArray(x).getDouble(0)));
                        }
                        coordinates.add(polygonpoints);
                        colours.add(ColorSelector.ColorChooser.chooseColor(orders));
                    }
                }
            }
        }

        MapboxOperator operator=new MapboxOperator();
        operator.drawPolygonMap(coordinates,colours);
    }
}
