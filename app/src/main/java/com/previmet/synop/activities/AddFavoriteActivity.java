package com.previmet.synop.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.previmet.synop.R;
import com.previmet.synop.adapter.StationListAdapter;
import com.previmet.synop.adapter.StationSearchAdapter;
import com.previmet.synop.db.Db;
import com.previmet.synop.db.DbContract;
import com.previmet.synop.db.DbCursor;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;

public class AddFavoriteActivity extends ActionBarActivity {


    private AutoCompleteTextView myAutoComplete;
    private ArrayList<Station> stationListItems;
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

            // display the back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setTitle("Add favorite");
        }

        /*
        * create a new array list for our navigation drawer that will contain Items object.
        * Items are created with text and icons.
        */
        stationListItems = new ArrayList<Station>();

        DbCursor sCursor = Db.getStations();
        while(sCursor.moveToNext()) {
            // The Cursor is now set to the right position
            stationListItems.add(new Station(
                            sCursor.getLong(sCursor.getColumnIndex(DbContract.Station._ID)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_STATION)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Country.COLUMN_NAME_COUNTRY)),
                            sCursor.getInt(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_ELEVATION)))
            );
        }


        ListView stationListContainer = (ListView) findViewById(R.id.list_stations);

        // Set the adapter for the list view
        //StationListAdapter adapter = new StationListAdapter(this, stationListItems);
        adapter = new StationListAdapter(this, R.layout.station_list_item, stationListItems);
        stationListContainer.setAdapter(adapter);


        stationListContainer.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                // get clicked station
                Station station = stationListItems.get(position);

                // add the favorite to database
                long fId = Db.addFavorite(station.getId());


                // stop the activity
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
        this.finish();

        return super.onOptionsItemSelected(item);

    }
}
