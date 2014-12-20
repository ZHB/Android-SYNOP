package com.previmet.synop.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vince on 20.12.2014.
 */
public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.

    public DbHelper(Context context) {
        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbContract.Country.SQL_CREATE_TABLE);
        db.execSQL(DbContract.Station.SQL_CREATE_TABLE);
        db.execSQL(DbContract.Favorite.SQL_CREATE_TABLE);

        // populate some initial values
        Db.addCountry("Suisse");
        //Db.addCountry("France");
        //Db.addCountry("Belgique");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // delete database
        db.execSQL(DbContract.Country.SQL_DELETE_TABLE);
        db.execSQL(DbContract.Station.SQL_DELETE_TABLE);
        db.execSQL(DbContract.Favorite.SQL_DELETE_TABLE);

        // recreate database
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}