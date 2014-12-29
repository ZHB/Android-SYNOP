package com.previmet.synop.db;

import android.provider.BaseColumns;

/**
 * Created by Vince on 20.12.2014.
 */
public class DbContract {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "SYNOP.sqlite";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DECIMAL_TYPE = " REAL";
    private static final String DATETIME_TYPE = " DATETIME";
    private static final String COMMA_SEP = ",";

    /**
     * Countries table definition
     */
    public static abstract class Country implements BaseColumns {
        public static final String TABLE_NAME = "country";
        public static final String COLUMN_NAME_COUNTRY = "country";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_COUNTRY + TEXT_TYPE +
                        ")";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    /**
     * Station table definition
     */
    public static abstract class Station implements BaseColumns {
        public static final String TABLE_NAME = "station";
        public static final String COLUMN_NAME_STATION = "station";
        public static final String COLUMN_NAME_WMO = "wmo";
        public static final String COLUMN_NAME_ELEVATION = "elevation";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_COUNTRY = "id_country";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_STATION     + TEXT_TYPE     + COMMA_SEP +
                        COLUMN_NAME_WMO         + TEXT_TYPE     + COMMA_SEP +
                        COLUMN_NAME_ELEVATION   + INTEGER_TYPE  + COMMA_SEP +
                        COLUMN_NAME_LATITUDE    + DECIMAL_TYPE  + COMMA_SEP +
                        COLUMN_NAME_LONGITUDE   + DECIMAL_TYPE  + COMMA_SEP +
                        COLUMN_NAME_COUNTRY     + INTEGER_TYPE  +
                        ")";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SQL_JOIN_COUNTRY =
                "LEFT JOIN " + Country.TABLE_NAME + " c " +
                " ON c." + Country._ID + " = " + COLUMN_NAME_COUNTRY;
    }


    /**
     * Favorites table definition
     */
    public static abstract class Favorite implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_NAME_ORDER = "orderchoice";
        public static final String COLUMN_NAME_STATION = "id_station";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_ORDER       + TEXT_TYPE     + COMMA_SEP +
                        COLUMN_NAME_STATION     + INTEGER_TYPE +
                        ")";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String SQL_JOIN_STATION =
                "LEFT JOIN " + Station.TABLE_NAME + " s " +
                        " ON s." + Station._ID + " = " + COLUMN_NAME_STATION;
    }

    /**
     * Favorites table definition
     */
    public static abstract class Data implements BaseColumns {
        public static final String TABLE_NAME = "data";
        public static final String COLUMN_NAME_DATETIME = "datetime";
        public static final String COLUMN_NAME_TMP = "temperature";
        public static final String COLUMN_NAME_DEWPOINT = "dewpoint";
        public static final String COLUMN_NAME_WINDDIR = "wnddir";
        public static final String COLUMN_NAME_WINDSPEED = "wndspd";
        public static final String COLUMN_NAME_WINDAVG = "wndavg";
        public static final String COLUMN_NAME_WINDGUST = "wndgust";
        public static final String COLUMN_NAME_HUMIDITY = "humidity";
        public static final String COLUMN_NAME_PRESSURE = "pressure";
        public static final String COLUMN_NAME_VISIBILITY = "visibility";
        public static final String COLUMN_NAME_NEBULOSITY = "nebulosity";
        public static final String COLUMN_NAME_CONDITION = "condition";
        public static final String COLUMN_NAME_STATION = "id_station";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_DATETIME    + DATETIME_TYPE    + COMMA_SEP +
                        COLUMN_NAME_TMP         + DECIMAL_TYPE     + COMMA_SEP +
                        COLUMN_NAME_DEWPOINT    + DECIMAL_TYPE     + COMMA_SEP +
                        COLUMN_NAME_WINDDIR     + TEXT_TYPE        + COMMA_SEP +
                        COLUMN_NAME_WINDSPEED   + DECIMAL_TYPE     + COMMA_SEP +
                        COLUMN_NAME_WINDAVG     + DECIMAL_TYPE     + COMMA_SEP +
                        COLUMN_NAME_WINDGUST    + DECIMAL_TYPE     + COMMA_SEP +
                        COLUMN_NAME_HUMIDITY    + INTEGER_TYPE     + COMMA_SEP +
                        COLUMN_NAME_PRESSURE    + DECIMAL_TYPE     + COMMA_SEP +
                        COLUMN_NAME_VISIBILITY  + INTEGER_TYPE     + COMMA_SEP +
                        COLUMN_NAME_NEBULOSITY  + INTEGER_TYPE     + COMMA_SEP +
                        COLUMN_NAME_CONDITION   + INTEGER_TYPE     + COMMA_SEP +
                        COLUMN_NAME_STATION     + INTEGER_TYPE     +
                        ")";

        public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
