package com.thea.weather.view;

/**
 * Created by Thea on 2016/1/21 0021.
 */
public interface IWeekWeatherView {
    int TODAY_IS_MON = 1;
    int TODAY_IS_TUE = 2;
    int TODAY_IS_WED = 3;
    int TODAY_IS_THU = 4;
    int TODAY_IS_FRI = 5;
    int TODAY_IS_SAT = 6;
    int TODAY_IS_SUN = 7;

    void setDay1(String day, int resId, String temperatures);

    void setDay2(String day, int resId, String temperatures);

    void setDay3(String day, int resId, String temperatures);

    void setDay4(String day, int resId, String temperatures);

    void setDay5(String day, int resId, String temperatures);

    void setDay6(String day, int resId, String temperatures);
}
