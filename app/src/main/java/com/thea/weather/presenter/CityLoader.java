package com.thea.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.thea.weather.bean.City;
import com.thea.weather.model.CityModel;
import com.thea.weather.model.CitySQLiteOpenHelper;
import com.thea.weather.model.HttpRequest;
import com.thea.weather.model.ICityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Thea on 2016/1/22 0022.
 */
public class CityLoader {
    private static final String TAG = CityLoader.class.getSimpleName();

    private HttpRequest mHttpRequest;
    private ICityModel mCityModel;

    private Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.i(TAG, response.toString());
            parseResponse(response);
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i(TAG, "error");
        }
    };

    public CityLoader(Context context) {
        mHttpRequest = new HttpRequest(context);
        mCityModel = new CityModel(new CitySQLiteOpenHelper(context));
    }

    public void load() {
        mHttpRequest.getCityList(mListener, mErrorListener);
    }

    public void parseResponse(JSONObject response) {
        try {
            JSONArray cityArray = response.getJSONArray("city_info");
            Gson gson = new Gson();

            for (int i = 0; i < cityArray.length(); i++) {
                City city = gson.fromJson(cityArray.getString(i), City.class);
                mCityModel.add(city);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
