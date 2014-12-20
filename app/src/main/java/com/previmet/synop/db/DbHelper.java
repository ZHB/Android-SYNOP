package com.previmet.synop.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vince on 20.12.2014.
 */
public class DbHelper extends SQLiteOpenHelper {

    private String[] countries = {"Suisse", "France", "Belgique"};
    private String[] stations = {"Suisse", "France", "Belgique"};


    public DbHelper(Context context) {

        super(context, DbContract.DATABASE_NAME, null, DbContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DbContract.Country.SQL_CREATE_TABLE);
        db.execSQL(DbContract.Station.SQL_CREATE_TABLE);
        db.execSQL(DbContract.Favorite.SQL_CREATE_TABLE);

        // populate some initial values
        Db.initialize(db);

        // countries
        long idSuisse = Db.addCountry("Suisse");
        long idFrance = Db.addCountry("France");
        long idBelgique = Db.addCountry("Belgique");

        Db.addStation("Aadorf / Tänikon", "06679", 539, 47.4798, 8.9048, idSuisse);
        Db.addStation("Acquarossa / Comprovasco", "06756", 575, 46.4594, 8.9356, idSuisse);
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