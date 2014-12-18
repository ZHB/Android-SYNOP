package com.previmet.synop.activities;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.previmet.synop.R;
import com.previmet.synop.adapter.DrawerAdapter;
import com.previmet.synop.fragments.Blog_Fragment;
import com.previmet.synop.fragments.Bookmark_Fragment;
import com.previmet.synop.fragments.FavoritesFragment;
import com.previmet.synop.fragments.Sales_Fragment;
import com.previmet.synop.fragments.StationsFragment;
import com.previmet.synop.ui.Items;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private String[] mDrawerTitles;
    private TypedArray mDrawerIcons;
    private ArrayList<Items> drawerItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerContainer;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        drawerItems = new ArrayList<Items>();
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

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(mDrawerTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        LayoutInflater inflater = getLayoutInflater();
        final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header,
                mDrawerContainer, false);

        final ViewGroup footer = (ViewGroup) inflater.inflate(R.layout.footer,
                mDrawerContainer, false);


        // add header view to the navigation drawer container
        mDrawerContainer.addHeaderView(header, null, false); // true = clickable
        // add footer view to the navigation drawer container
        mDrawerContainer.addFooterView(footer, null, true); // true = clickable


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
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerContainer);
        //menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

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

        // navigation drawer width must be the smallest screen with size minus toolbar height
        // see : https://medium.com/sebs-top-tips/material-navigation-drawer-sizing-558aea1ad266
        if(width >=  height) {
            width = height;
        }

        return width - actionBarHeight;
    }


    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {

        Fragment fragment = null;

        //Toast.makeText(getApplicationContext(),"[DEBUG] Button clicked is " + position, Toast.LENGTH_LONG).show();

        switch (position) {
            case 1:
                fragment = new FavoritesFragment();
                break;
            case 2:
                fragment = new StationsFragment();
                break;
            case 3:
                fragment = new Blog_Fragment();
                break;
            case 4:
                fragment = new Bookmark_Fragment();
                break;
        }

        /*
         * Insert fragment into main content view
         */
        if (fragment != null) {
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.main_content, fragment)
                    .commit();
        }

        // Highlight the selected item, update the title, and close the drawer
        mDrawerContainer.setItemChecked(position, true);
        setTitle(mDrawerTitles[position - 1]);
        mDrawerLayout.closeDrawer(mDrawerContainer);
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
