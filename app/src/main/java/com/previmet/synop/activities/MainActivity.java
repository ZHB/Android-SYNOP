package com.previmet.synop.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Point;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.MapsInitializer;
import com.previmet.synop.R;
import com.previmet.synop.adapter.DrawerAdapter;
import com.previmet.synop.adapter.SearchSuggestionAdapter;
import com.previmet.synop.db.Db;
import com.previmet.synop.db.DbContract;
import com.previmet.synop.db.DbCursor;
import com.previmet.synop.fragments.DayOverviewFragment;
import com.previmet.synop.fragments.FavoritesFragment;
import com.previmet.synop.fragments.HourlyForecastFragment;
import com.previmet.synop.fragments.MapFragment;
import com.previmet.synop.fragments.StationsFragment;
import com.previmet.synop.ui.Items;
import com.previmet.synop.ui.Station;
import com.previmet.synop.ui.location.MyLocation;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements TextWatcher, AdapterView.OnItemClickListener {

    public static FragmentManager fragmentManager;
    /**
     * Location result listener. gotLocation is called if app got any location from
     * network or gps
     */
    MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
        @Override
        public void gotLocation(Location location) {
            //Got the location!

            Toast.makeText(getApplicationContext(), "Lat: " + location.getLatitude() + " Lng: " + location.getLongitude() + " Alt: " + location.getAltitude() + " Provider: " + location.getProvider(), Toast.LENGTH_SHORT).show();
        }
    };
    private String[] mDrawerTitles;
    private TypedArray mDrawerIcons;
    private ArrayList<Items> drawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerContainer;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private Menu menu;
    private ArrayList<Station> mSearchSuggestions = new ArrayList<Station>();
    private ArrayList<Station> stationListItems;
    private Fragment mFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        // load fragment if app is already running
        if (savedInstanceState != null) {
            mFragment = fragmentManager.findFragmentById(R.id.main_content);
        }

        // initialize database
        Db.initialize(this);


        // check for our toolbar xml layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // redefine default action bar with new toolbar
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // get xml string and icons array for our menu res/values/strings.xml
        mDrawerTitles = getResources().getStringArray(R.array.drawer_titles);
        mDrawerIcons = getResources().obtainTypedArray(R.array.drawer_icons);

        // get navigation drawer container
        mDrawerContainer = (ListView) findViewById(R.id.left_drawer);

        /*
        * create a new array list for our navigation drawer that will contain Items object.
        * Items are created with text and icons.
        */
        drawerItems = new ArrayList<>();
        for (int i = 0; i < mDrawerTitles.length; i++) {
            drawerItems.add(new Items(mDrawerTitles[i], mDrawerIcons.getResourceId(i, -(i + 1))));
        }

        /*
        * Change toolbar title when navigation drawer is closed or opened
         */
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup ndHeader = (ViewGroup) inflater.inflate(R.layout.nav_drawer_header,
                mDrawerContainer, false);

        final ViewGroup ndFooter = (ViewGroup) inflater.inflate(R.layout.nav_drawer_footer,
                mDrawerContainer, false);


        // add ndHeader view to the navigation drawer container
        mDrawerContainer.addHeaderView(ndHeader, null, false); // true = clickable
        // add navFooter view to the navigation drawer container
        mDrawerContainer.addFooterView(ndFooter, null, false); // true = clickable


        // Set the adapter for the list view
        mDrawerContainer.setAdapter(new DrawerAdapter(getApplicationContext(), drawerItems));
        // Set the list's click listener.
        mDrawerContainer.setOnItemClickListener(new DrawerItemClickListener());


        //Set width of drawer
        DrawerLayout.LayoutParams lp = (DrawerLayout.LayoutParams) mDrawerContainer.getLayoutParams();
        lp.width = calculateDrawerWidth();
        mDrawerContainer.setLayoutParams(lp);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        /*
        * create a new array list for our navigation drawer that will contain Items object.
        * Items are created with text and icons.
        */
        stationListItems = new ArrayList<>();

        DbCursor sCursor = Db.getStations();
        while (sCursor.moveToNext()) {
            // The Cursor is now set to the right position
            stationListItems.add(new Station(
                            sCursor.getLong(sCursor.getColumnIndex(DbContract.Station._ID)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_STATION)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_WMO)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Country.COLUMN_NAME_COUNTRY)),
                            sCursor.getInt(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_ELEVATION)),
                            sCursor.getDouble(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LATITUDE)),
                            sCursor.getDouble(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LONGITUDE)))
            );
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        /**
         * Get user location if localisation service is enabled
         */
        MyLocation myLocation = new MyLocation();
        myLocation.getLocation(this, locationResult);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();


        // start default fragment view if no fragment is loaded
        if (mFragment == null) {
            selectItem(1);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        this.menu = menu;

        /*
            Crate seach view with suggestion for SDK > Honeycomb
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView search = (SearchView) menu.findItem(R.id.action_search).getActionView();

            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String query) {
                    loadData(query);
                    return true;
                }

            });
        }

        return true;
    }


    /**
     * Load data on query text change from search input.
     * Get all stations that correspond to input string and set searcheable adapter for searchview
     *
     * @param query
     */
    private void loadData(String query) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            // get suggestions from database
            Cursor cursor = Db.searchStationByName(query);

            // create an array with searched stations
            mSearchSuggestions.clear();
            while (cursor.moveToNext()) {
                mSearchSuggestions.add(new Station(
                        cursor.getLong(cursor.getColumnIndex(DbContract.Station._ID)),
                        cursor.getString(cursor.getColumnIndex(DbContract.Station.COLUMN_NAME_STATION)),
                        cursor.getString(cursor.getColumnIndex(DbContract.Station.COLUMN_NAME_WMO)),
                        cursor.getString(cursor.getColumnIndex(DbContract.Country.COLUMN_NAME_COUNTRY)),
                        cursor.getInt(cursor.getColumnIndex(DbContract.Station.COLUMN_NAME_ELEVATION)),
                        cursor.getDouble(cursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LATITUDE)),
                        cursor.getDouble(cursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LONGITUDE))));
            }

            SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

            // get searchview
            final SearchView sv = (SearchView) menu.findItem(R.id.action_search).getActionView();
            sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));

            sv.setSuggestionsAdapter(new SearchSuggestionAdapter(this, cursor, mSearchSuggestions));

            // on suggestion list item click get the station then launch new intent
            sv.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                @Override
                public boolean onSuggestionClick(int position) {
                    Station station = mSearchSuggestions.get(position);

                    Intent intent = new Intent(getApplicationContext(), StationActivity.class);
                    intent.putExtra("station", station);
                    startActivity(intent);

                    sv.clearFocus();

                    return true;
                }

                @Override
                public boolean onSuggestionSelect(int position) {
                    return false;
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    /**
     * Calculate navigation drawer max width size. It must be
     * Smallest screen width - toolbar (action bar) height.
     * see : http://www.google.com/design/spec/patterns/navigation-drawer.html
     *
     * @return int maximum navigation drawer width
     */
    public int calculateDrawerWidth() {
        // Calculate ActionBar height
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }

        // get screen size
        Display display = getWindowManager().getDefaultDisplay();
        int width;
        int height;
        if (android.os.Build.VERSION.SDK_INT >= 13) {
            Point size = new Point();
            display.getSize(size);
            width = size.x;
            height = size.y;
        } else {
            width = display.getWidth();  // deprecated
            height = display.getHeight();  // deprecated
        }

        // navigation drawer width must be the smallest screen width size minus toolbar height
        // see : https://medium.com/sebs-top-tips/material-navigation-drawer-sizing-558aea1ad266
        if (width >= height) {
            width = height;
        }

        return width - actionBarHeight;
    }


    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {

        // crate a bundle with stations list as argument. Thant bundle could be passed
        // later to fragment argument
        final Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("stations_list", stationListItems);

        //Fragment fragment = null;

        switch (position) {
            case 1:
                mFragment = new DayOverviewFragment();
                break;
            case 2:
                mFragment = new HourlyForecastFragment();
                break;
            case 3:
                mFragment = new StationsFragment();
                mFragment.setArguments(bundle);
                break;
            case 4:
                mFragment = new MapFragment();
                mFragment.setArguments(bundle);
                MapsInitializer.initialize(this);
                break;
            default:
                mFragment = new FavoritesFragment();
                break;
        }

        /*
         * Insert fragment into main content view
         * http://stackoverflow.com/questions/19108843/mapfragment-return-null/19109093#19109093
         */
        if (mFragment != null) {
            // Insert the fragment by replacing any existing fragment
            //fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.main_content, mFragment)
                    .commit();

            // Highlight the selected item, update the title, and close the drawer
            mDrawerContainer.setItemChecked(position, true);
            setTitle(mDrawerTitles[position - 1]);
        }

        mDrawerLayout.closeDrawer(mDrawerContainer);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    // start Settings activity. This is the only onClick view available in the MainActivity class
    public void onClick(View v) {
        startActivity(new Intent(this, SettingsActivity.class));
    }

    /**
     * Navigation drawer list click listener.
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}
