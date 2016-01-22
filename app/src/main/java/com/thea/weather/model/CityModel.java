package com.thea.weather.model;

import android.database.sqlite.SQLiteDatabase;

import com.thea.weather.bean.City;

import java.util.List;

/**
 * Created by Thea on 2016/1/20 0020.
 */
public class CityModel implements ICityModel {
    private CitySQLiteOpenHelper helper;

    public CityModel(CitySQLiteOpenHelper helper) {
        this.helper = helper;
    }

    @Override
    public void add(String id, String city, String prov, String cnty) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into City (cityId,region,province,cnty,used) values (?,?,?,?,?)",
                new Object[]{id,city,prov,cnty,false});
        db.close();
    }

    @Override
    public void add(City city) {
        add(city.getId(), city.getCity(), city.getProv(), city.getCnty());
    }

    @Override
    public void used(String id, boolean used) {
        /*SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update Cloth set clothname = ? where id = ?", new Object[]{name, id});
        db.close();*/
    }

    @Override
    public void delete(String id) {
        /*SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from Cloth where id = ?", new Object[]{id});
        db.close();*/
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
        /*SQLiteDatabase db = helper.getReadableDatabase();
        List<Cloth> clothes = new ArrayList<>();
        Cursor cursor = db.rawQuery("select id, clothname, filename, createdTime, description from Cloth " +
                "order by createdTime desc", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String filename = cursor.getString(2);
            Timestamp createdTime = Timestamp.valueOf(cursor.getString(3));
            String description = cursor.getString(4);
            clothes.add(new Cloth(id, name, filename, createdTime, description));
        }

        cursor.close();
        db.close();*/
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
