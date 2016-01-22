package com.thea.weather;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thea.weather.presenter.WeatherPresenter;
import com.thea.weather.utils.LogUtils;
import com.thea.weather.view.IDailyWeatherView;
import com.thea.weather.view.ITodayWeatherView;

public class MainActivity extends AppCompatActivity implements ITodayWeatherView, IDailyWeatherView {
    private static final String TAG = MainActivity.class.getSimpleName();

    private String[] items = {"宁波", "杭州", "上海"};

    private WeatherPresenter mPresenter;

    private AppCompatSpinner mTitleSpinner;
    
    private TextView mCurrentTemperature, mHighestTemperature, mLowestTemperature;
    private TextView mHumidityLevel, mHumidityValue;
    private TextView mWindDirection, mWindPower;
    private TextView mAirQuality, mAqi;
    private TextView mCurrentWeatherText;
    private ImageView mCurrentWeatherImage;

    private LinearLayout mDailyWeatherLayout;

    private AdapterView.OnItemSelectedListener mItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            LogUtils.i(TAG, "onItemSelected");
            mPresenter.refresh("CN101210401");
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        mTitleSpinner = (AppCompatSpinner) findViewById(R.id.acs_titles);
        initActionBarAndTitle();
        setTodayView();

        mDailyWeatherLayout = (LinearLayout) findViewById(R.id.ll_daily_weather);
        mPresenter = new WeatherPresenter(this, this, this);
    }

    private void initActionBarAndTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                R.layout.title_spinner_item_actionbar, items);
        adapter.setDropDownViewResource(R.layout.title_spinner_item);
        mTitleSpinner.setAdapter(adapter);
        mTitleSpinner.setOnItemSelectedListener(mItemSelectedListener);
    }
    
    private void setTodayView() {
        mCurrentTemperature = (TextView) findViewById(R.id.tv_current_temperature);
        mCurrentWeatherText = (TextView) findViewById(R.id.tv_current_weather);
        mCurrentWeatherImage = (ImageView) findViewById(R.id.iv_current_weather);
        
        mHighestTemperature = (TextView) findViewById(R.id.tv_highest_temperature);
        mLowestTemperature = (TextView) findViewById(R.id.tv_lowest_temperature);
        
        mHumidityLevel = (TextView) findViewById(R.id.tv_humidity_level);
        mHumidityValue = (TextView) findViewById(R.id.tv_humidity);
        
        mWindDirection = (TextView) findViewById(R.id.tv_wind_direction);
        mWindPower = (TextView) findViewById(R.id.tv_wind_power);
        
        mAirQuality = (TextView) findViewById(R.id.tv_air_quality);
        mAqi = (TextView) findViewById(R.id.tv_aqi);
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
            mPresenter.refresh("CN101210401");
        }
        else if (id == R.id.action_city_management) {

        }
        else if (id == R.id.action_settings) {

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setCurrent(String temperature, int resId, String weather) {
        mCurrentTemperature.setText(temperature);
        mCurrentWeatherImage.setImageResource(resId);
        mCurrentWeatherText.setText(weather);
    }

    @Override
    public void setTemperatures(String highest, String lowest) {
        mHighestTemperature.setText(highest + "°");
        mLowestTemperature.setText(lowest + "°");
    }

    @Override
    public void setHumidity(String level, String value) {
        mHumidityLevel.setText(level);
        mHumidityValue.setText(value);
    }

    @Override
    public void setWind(String direction, String power) {
        mWindDirection.setText(direction);
        mWindPower.setText(power);
        mWindPower.append(getText(R.string.wind_level));
    }

    @Override
    public void setEnvironment(String quality, String aqi) {
        mAirQuality.setText(quality);
        mAqi.setText(aqi);
    }

    @Override
    public void setDailyWeather(int index, int day, int resId, String temperatures) {
        int childCount = mDailyWeatherLayout.getChildCount();
        View view;
        if (index >= 0 && index < childCount) {
            view = mDailyWeatherLayout.getChildAt(index);
        }
        else {
            view = getLayoutInflater().inflate(R.layout.daily_weather, mDailyWeatherLayout, false);
            mDailyWeatherLayout.addView(view, index);
        }

        TextView dayText = (TextView) view.findViewById(R.id.tv_day);
        TextView tmpText = (TextView) view.findViewById(R.id.tv_temperatures);
        ImageView weatherImage = (ImageView) view.findViewById(R.id.iv_weather);

        dayText.setText(day2String(day));
        tmpText.setText(temperatures);
        weatherImage.setImageResource(resId);
    }

    private CharSequence day2String(int day) {
        return getResources().getTextArray(R.array.day_of_week)[day - 1];
    }
}
