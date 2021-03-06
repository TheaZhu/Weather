package com.thea.weather.view;

/**
 * Created by Thea on 2016/1/21 0021.
 */
public interface ITodayWeatherView {

    void setCurrent(String temperature, String weatherCode, String weather);

    void setTemperatures(String highest, String lowest);

    void setHumidity(String level, String value);

    void setWind(String direction, String power);

    void setEnvironment(String pm25, String quality, String aqi);
}
