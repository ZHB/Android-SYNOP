package com.previmet.synop.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.previmet.synop.R;
import com.previmet.synop.adapter.StationListAdapter;
import com.previmet.synop.db.Db;
import com.previmet.synop.db.DbContract;
import com.previmet.synop.db.DbCursor;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;

public class AddFavoriteActivity extends ActionBarActivity {

    private ArrayList<Station> mStations;
    private StationListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite);

        // check for our toolbar xml layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // redefine default action bar with new toolbar
        if (toolbar != null) {

            setSupportActionBar(toolbar);

            // display the back button and set title
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_add_favorite));
        }

        /*
        * create a new array list for our navigation drawer that will contain Items object.
        * Items are created with text and icons.
        */
        mStations = new ArrayList<>();

        DbCursor sCursor = Db.getStations();
        while(sCursor.moveToNext()) {
            // The Cursor is now set to the right position
            mStations.add(new Station(
                    sCursor.getLong(sCursor.getColumnIndex(DbContract.Station._ID)),
                    sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_STATION)),
                    sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_WMO)),
                    sCursor.getString(sCursor.getColumnIndex(DbContract.Country.COLUMN_NAME_COUNTRY)),
                    sCursor.getInt(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_ELEVATION)),
                    sCursor.getDouble(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LATITUDE)),
                    sCursor.getDouble(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LONGITUDE)))
            );
        }


        ListView stationListContainer = (ListView) findViewById(R.id.list_stations);

        // Set the adapter for the list view
        adapter = new StationListAdapter(this, R.layout.station_list_item, mStations);
        stationListContainer.setAdapter(adapter);


        stationListContainer.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                // get clicked station
                Station station = mStations.get(position);

                // add the favorite to database
                Db.addFavorite(station.getId());

                // stop the activity
                Intent returnIntent = new Intent();
                returnIntent.putExtra("add_edit_station", station);
                setResult(RESULT_OK, returnIntent);

                finish();
            }
        });


        // get edit text for list filtering
        EditText sSearch = (EditText) findViewById(R.id.eText_sSearch);
        sSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                AddFavoriteActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_no_action, menu);
        return true;
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // close the current activity
        finish();

        return super.onOptionsItemSelected(item);
    }
}
