package com.thea.weather.bean;

/**
 * Created by Thea on 2016/1/20 0020.
 */
public class City {
    private String id;
    private String city;
    private String cnty;
    private String prov;
    private String lat;
    private String lon;

    public City(String id, String city, String prov, String cnty) {
        this.id = id;
        this.city = city;
        this.cnty = cnty;
        this.prov = prov;
    }

    public City(String id, String city, String cnty, String prov, String lat, String lon) {
        this.id = id;
        this.city = city;
        this.cnty = cnty;
        this.prov = prov;
        this.lat = lat;
        this.lon = lon;
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCnty() {
        return cnty;
    }

    public String getProv() {
        return prov;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }
}
