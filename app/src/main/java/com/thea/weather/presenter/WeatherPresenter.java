package com.thea.weather.presenter;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.thea.weather.model.HttpRequest;
import com.thea.weather.view.IDailyWeatherView;
import com.thea.weather.view.ITodayWeatherView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Thea on 2016/1/22 0022.
 */
public class WeatherPresenter {
    private static final String TAG = WeatherPresenter.class.getSimpleName();

    private HttpRequest mHttpRequest;
    private ITodayWeatherView mTodayWeatherView;
    private IDailyWeatherView mDailyWeatherView;

    private Calendar lastUpdateTime;
    private JSONArray mHourlyWeather;

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

    public WeatherPresenter(Context context, ITodayWeatherView todayWeatherView,
                            IDailyWeatherView dailyWeatherView) {
        mHttpRequest = new HttpRequest(context);
        mTodayWeatherView = todayWeatherView;
        mDailyWeatherView = dailyWeatherView;
    }

    public void init() {

    }

    public void refresh(String cityId) {
        mHttpRequest.getWeather(cityId, mListener, mErrorListener);
    }

    public void parseResponse(JSONObject response) {
        try {
            JSONArray jsonArray = response.getJSONArray("HeWeather data service 3.0");
            if (jsonArray.length() <= 0)
                return;
            JSONObject data = jsonArray.getJSONObject(0);
            String status = data.getString("status");
            if (status.equalsIgnoreCase("ok")) {
                Calendar locTime = stringToTime(data.getJSONObject("basic")
                        .getJSONObject("update").getString("loc"));
                if (lastUpdateTime == null || !locTime.equals(lastUpdateTime)) {
                    extractTodayWeather(data);
                    extractWeekWeather(data);
                    mHourlyWeather = data.getJSONArray("hourly_forecast");
                    lastUpdateTime = locTime;
                }
            }
            else if (status.equalsIgnoreCase("unknown city")) {
            }
            else if (status.equalsIgnoreCase("anr")) {
            }
            else {
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void extractTodayWeather(JSONObject data) throws JSONException {
        JSONObject current = data.getJSONObject("now");
        String temperature = current.getString("tmp");
        String weather = current.getJSONObject("cond").getString("txt");
        String weatherCode = current.getJSONObject("cond").getString("code");
        String humidity = current.getString("hum");
        String windDir = current.getJSONObject("wind").getString("dir");
        String windPower = current.getJSONObject("wind").getString("sc");

        JSONObject aqiCity = data.getJSONObject("aqi").getJSONObject("city");
        String aqi = aqiCity.getString("aqi");
        String airQlty = aqiCity.getString("qlty");
        String pm25 = aqiCity.getString("pm25");

        mTodayWeatherView.setCurrent(temperature, weatherCode, weather);
        mTodayWeatherView.setHumidity("舒适", humidity);
        mTodayWeatherView.setWind(windDir, windPower);
        mTodayWeatherView.setEnvironment(pm25, airQlty, aqi);
    }

    public void extractWeekWeather(JSONObject data) throws JSONException {
        JSONArray dailyForecast = data.getJSONArray("daily_forecast");

        int k = 0;
        for (int i = 0; i < dailyForecast.length(); i++) {
            JSONObject day1 = dailyForecast.getJSONObject(i);
            Calendar date = stringToDate(day1.getString("date"));
            String highest = day1.getJSONObject("tmp").getString("max");
            String lowest = day1.getJSONObject("tmp").getString("min");
            String weatherCode = day1.getJSONObject("cond").getString("code_d");

            if (isSameDay(date, Calendar.getInstance())) {
                mTodayWeatherView.setTemperatures(highest, lowest);
                k = 1;
            }
            else
                mDailyWeatherView.setDailyWeather(i - k, date.get(Calendar.DAY_OF_WEEK), weatherCode,
                        highest + "°/" + lowest + "°");
        }
    }

    private boolean isSameDay(Calendar c1, Calendar c2) {
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
                c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    private Calendar stringToDate(String time) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            calendar.setTime(dateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }

    private Calendar stringToTime(String time) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        try {
            calendar.setTime(dateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
