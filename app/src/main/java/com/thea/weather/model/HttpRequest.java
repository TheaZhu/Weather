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
public class HttpRequest {
    private static final String KEY = "4611090c7807474a893d7734034de1a2";
    private static final String BASE_URL = "https://api.heweather.com/x3";
    private static final String BASE_URL_WEATHER = BASE_URL + "/weather?key=" + KEY + "&";
    private static final String BASE_URL_CODE = BASE_URL + "/condition?key=" + KEY + "&";
    private static final String BASE_URL_CITYLIST = BASE_URL + "/citylist?key=" + KEY + "&";
    private RequestQueue mQueue;

    public HttpRequest(Context context) {
        mQueue = Volley.newRequestQueue(context);
    }

    public void getWeather(String cityId, Response.Listener<JSONObject> listener,
                           Response.ErrorListener errorListener) {
        JsonObjectRequest joRequest = new JsonObjectRequest(BASE_URL_WEATHER + "cityid=" + cityId,
                listener, errorListener);
        mQueue.add(joRequest);
    }

    public void getCode(Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        JsonObjectRequest joRequest = new JsonObjectRequest(BASE_URL_CODE + "search=allcond",
                listener, errorListener);
        mQueue.add(joRequest);
    }

    public void getCityList(Response.Listener<JSONObject> listener,
                            Response.ErrorListener errorListener) {
        JsonObjectRequest joRequest = new JsonObjectRequest(BASE_URL_CITYLIST + "search=allchina",
                listener, errorListener);
        mQueue.add(joRequest);
    }
}
