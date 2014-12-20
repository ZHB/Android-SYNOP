package com.previmet.synop.db;

import android.provider.BaseColumns;

/**
 * Created by Vince on 20.12.2014.
 */
public class DbContract {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SYNOP.sqlite";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DECIMAL_TYPE = " REAL";
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

        public static final String SQL_JOIN_COUNTRY =
                "LEFT JOIN " + Station.TABLE_NAME + " s " +
                        " ON s." + Station._ID + " = " + COLUMN_NAME_STATION;
    }
}
