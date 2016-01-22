package com.thea.weather.model;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by Thea on 2016/1/20 0020.
 */
public class WeatherRequest {
    private static final String KEY = "4611090c7807474a893d7734034de1a2";
    private static final String BASE_URL = "https://api.heweather.com/x3/weather?key=" + KEY + "&"; //cityid=城市ID&
    private RequestQueue mQueue;

    public WeatherRequest(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public void get(String url, Response.Listener<JSONObject> listener,
                    Response.ErrorListener errorListener) {
        JsonObjectRequest joRequest = new JsonObjectRequest(BASE_URL + url,
                listener, errorListener);
        mQueue.add(joRequest);
    }
}
