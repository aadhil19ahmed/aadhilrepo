package com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox;

import android.content.Context;
import android.content.res.AssetManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by aadhil.ahmed on 25-Oct-17.
 */

public  class LocalJsonConverter {

    public static String convertJSONFile(String filename, Context context) throws IOException{
        AssetManager manager=context.getAssets();
        InputStream file=manager.open(filename);
        byte[] formArray=new byte[file.available()];
        file.read(formArray);
        file.close();

        return new String(formArray);
    }

    public static JSONArray fetchJsonArray(String jsonResult){
        JSONObject jsonObject;
        JSONArray jsonArray = null;
        try{
            jsonObject=new JSONObject(jsonResult);
            jsonArray=jsonObject.getJSONArray("features");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
