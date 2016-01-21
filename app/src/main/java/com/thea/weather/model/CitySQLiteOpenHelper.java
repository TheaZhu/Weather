package com.thea.weather.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thea on 2015/6/16.
 */
public class CitySQLiteOpenHelper extends SQLiteOpenHelper {

    public CitySQLiteOpenHelper(Context context) {
        super(context, "City.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table City(" +
                "cityId VARCHAR(11) primary key, town VARCHAR(40)," +
                "region VARCHAR(40), province VARCHAR(40)," +
                "used BOOLEAN default false)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
