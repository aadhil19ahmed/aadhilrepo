package com.example.aadhilahmed.mapboxdeliveries1;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;

/**
 * Created by aadhil.ahmed on 23-Oct-17.
 */

public class JsonDataFetcher<T> extends Request<T>{

    private final Gson gson=new Gson();
    private final Class<T> classPojo;
    private final Map<String,String> headers;
    private final Response.Listener<T> listener;

    public JsonDataFetcher(String url, Class<T> classPojo, Map<String,String> headers,
                           Response.Listener<T> listener,Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.classPojo=classPojo;
        this.headers=headers;
        this.listener=listener;

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers !=null?headers:super.getHeaders();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, classPojo),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}
