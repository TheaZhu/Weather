package com.thea.weather.bean;

/**
 * Created by Thea on 2016/1/20 0020.
 */
public class City {
    private String id;
    private String town;
    private String region;
    private String province;

    public City(String id, String town, String region, String province) {
        this.id = id;
        this.town = town;
        this.region = region;
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public String getTown() {
        return town;
    }

    public String getRegion() {
        return region;
    }

    public String getProvince() {
        return province;
    }
}
