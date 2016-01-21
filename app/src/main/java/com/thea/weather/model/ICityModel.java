package com.thea.weather.model;

import com.thea.weather.bean.City;

import java.util.List;

/**
 * Created by Thea on 2016/1/20 0020.
 */
public interface ICityModel {

    void add(String id, String town, String region, String province);

    void add(City city);

    void used(String id, boolean used);

    void delete(String id);

    String findByTown(String town);

    List<City> findByRegion(String region);

    List<City> findByProvince(String province);

    List<String> findTowns();

    List<String> findRegions();

    List<String> findProvinces();

    List<String> findAllUsed();

    List<City> findAll();
}
