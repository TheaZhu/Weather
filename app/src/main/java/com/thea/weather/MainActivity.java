package com.thea.weather;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private String[] items = {"宁波", "杭州", "上海"};

    private WeatherRequest mWeatherRequest;

    private AppCompatSpinner mTitleSpinner;

    private Response.Listener<JSONObject> mListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            Log.i(TAG, response.toString());
            try {
                String aqi = response.getJSONObject("aqi").getJSONObject("city")
                        .getString("aqi");
                String airQlty = response.getJSONObject("aqi").getJSONObject("city")
                        .getString("qlty");
                String pm25 = response.getJSONObject("aqi").getJSONObject("city")
                        .getString("pm25");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.i(TAG, "error");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        initActionBar();

//        mTitleSpinner = (AppCompatSpinner) findViewById(R.id.acs_titles);
//        mTitleSpinner.setAdapter(new TitleSpinnerAdapter());
        mWeatherRequest = new WeatherRequest(this);
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        /*MenuItem item = menu.findItem(R.id.action_city);
        AppCompatSpinner spinner = (AppCompatSpinner) MenuItemCompat.getActionView(item);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                items));*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            mWeatherRequest.get("city=beijing", mListener, mErrorListener);
        }
        else if (id == R.id.action_city_management) {

        }
        else if (id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
    }
}
