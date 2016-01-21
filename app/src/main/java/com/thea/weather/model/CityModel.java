package com.thea.weather.model;

import com.thea.weather.bean.City;

import java.util.List;

/**
 * Created by Thea on 2016/1/20 0020.
 */
public class CityModel implements ICityModel {

    @Override
    public void add(String id, String town, String region, String province) {

    }

    @Override
    public void add(City city) {
        add(city.getId(), city.getTown(), city.getRegion(), city.getProvince());
    }

    @Override
    public void used(String id, boolean used) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public String findByTown(String town) {
        return null;
    }

    @Override
    public List<City> findByRegion(String region) {
        return null;
    }

    @Override
    public List<City> findByProvince(String province) {
        return null;
    }

    @Override
    public List<String> findTowns() {
        return null;
    }

    @Override
    public List<String> findRegions() {
        return null;
    }

    @Override
    public List<String> findProvinces() {
        return null;
    }

    @Override
    public List<String> findAllUsed() {
        return null;
    }

    @Override
    public List<City> findAll() {
        return null;
    }
}
