package com.previmet.synop.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by Vince on 20.12.2014.
 */
public class Db {
    public static SQLiteDatabase db;


    /**
     * Database initialisation
     * @param context context used by DbHelper
     */
    public static void initialize(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * Initialize database
     * @param db database
     */
    public static void initialize(SQLiteDatabase db) {
        Db.db = db;
    }

    /**
     * Insert a child record
     * @param country country name
     * @return ID of inserted row
     */
    public static long addCountry(String country) {
        ContentValues values = new ContentValues();

        values.put(DbContract.Country.COLUMN_NAME_COUNTRY, country);
        long id = db.insert(DbContract.Country.TABLE_NAME, null, values);

        return id;
    }

    /**
     * Get all countries
     * @return cursor containing countries
     */
    public static DbCursor getCountries(){
        String[] projection = {
                DbContract.Country._ID,
                DbContract.Country.COLUMN_NAME_COUNTRY
        };

        Cursor c = db.query(DbContract.Country.TABLE_NAME, projection, null, null, null, null, DbContract.Country.COLUMN_NAME_COUNTRY);

        return new DbCursor(c);
    }

    /**
     *  Insert a station
     *
     * @param station
     * @param wmo
     * @param elevation
     * @param latitude
     * @param longitude
     * @param country
     * @return ID of inserted row
     */
    public static long addStation(String station, String wmo, Integer elevation, Double latitude, Double longitude, long country) {
        ContentValues values = new ContentValues();
        values.put(DbContract.Station.COLUMN_NAME_STATION, station);
        values.put(DbContract.Station.COLUMN_NAME_WMO, wmo);
        values.put(DbContract.Station.COLUMN_NAME_ELEVATION, elevation);
        values.put(DbContract.Station.COLUMN_NAME_LATITUDE, latitude);
        values.put(DbContract.Station.COLUMN_NAME_LONGITUDE, longitude);
        values.put(DbContract.Station.COLUMN_NAME_COUNTRY, country);

        long id = db.insert(DbContract.Station.TABLE_NAME, null, values);

        return id;
    }

    /**
     * Get all stations with countries
     * @return cursor containing countries
     */
    public static DbCursor getStations(){
        String[] projection = {
                "s." + DbContract.Station._ID,
                DbContract.Station.COLUMN_NAME_STATION,
                DbContract.Station.COLUMN_NAME_WMO,
                DbContract.Station.COLUMN_NAME_ELEVATION,
                DbContract.Station.COLUMN_NAME_LATITUDE,
                DbContract.Station.COLUMN_NAME_LONGITUDE,
                DbContract.Country.COLUMN_NAME_COUNTRY
        };

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(
                DbContract.Station.TABLE_NAME + " s " +
                DbContract.Station.SQL_JOIN_COUNTRY
        );

        Cursor c = qb.query(db, projection, null, null, null, null, DbContract.Station.COLUMN_NAME_STATION);

        return new DbCursor(c);
    }

    public static long addFavorite(Integer order, long station) {
        ContentValues values = new ContentValues();
        values.put(DbContract.Favorite.COLUMN_NAME_ORDER, order);
        values.put(DbContract.Favorite.COLUMN_NAME_STATION, station);

        long id = db.insert(DbContract.Favorite.TABLE_NAME, null, values);

        return id;
    }

    /**
     * Get all stations with countries
     * @return cursor containing countries
     */
    public static DbCursor getFavorite(){
        String[] projection = {
                "f." + DbContract.Favorite._ID,
                DbContract.Favorite.COLUMN_NAME_ORDER,
                DbContract.Station.COLUMN_NAME_STATION,
                DbContract.Station.COLUMN_NAME_WMO,
                DbContract.Station.COLUMN_NAME_ELEVATION,
                DbContract.Station.COLUMN_NAME_LATITUDE,
                DbContract.Station.COLUMN_NAME_LONGITUDE,
                DbContract.Country.COLUMN_NAME_COUNTRY
        };

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(
                DbContract.Favorite.TABLE_NAME + " f " +
                DbContract.Favorite.SQL_JOIN_STATION + " " +
                DbContract.Station.SQL_JOIN_COUNTRY
        );

        Cursor c = qb.query(db, projection, null, null, null, null, DbContract.Station.COLUMN_NAME_STATION);

        return new DbCursor(c);
    }
}
