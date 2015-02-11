package com.previmet.synop.activities;

import android.app.FragmentManager;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.adapter.HourlyFcstLstAdapter;
import com.previmet.synop.ui.Station;
import com.previmet.synop.ui.WeatherOverview;

import java.util.ArrayList;

public class HourlyForecastOverviewActivity extends ActionBarActivity {


    // The height of your fully expanded header view (same than in the xml layout)
    int headerHeight;
    // The height of your fully collapsed header view. Actually the Toolbar height (56dp)
    int minHeaderHeight;
    // The left margin of the Toolbar title (according to specs, 72dp)
    int toolbarTitleLeftMargin;
    // Added after edit
    int minHeaderTranslation;

    private String[] mListTitles;
    private String[] mListContent;

    private TypedArray mDrawerIcons;
    private ArrayList<WeatherOverview> drawerItems;

    private ListView listView;
    private View headerView;
    private Station station;

    private RelativeLayout headerContainer;
    private TextView headerTitle;
    private TextView headerSubtitle;

    private ImageView headerContainerImg;
    private ImageView headerDailyWeatherImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forecast_overview);


        // The height of your fully expanded header view (same than in the xml layout)
        headerHeight = (int) getResources().getDimensionPixelSize(R.dimen.header_height);
        // The height of your fully collapsed header view. Actually the Toolbar height (56dp)
        minHeaderHeight = getResources().getDimensionPixelSize(R.dimen.action_bar_height);
        // The left margin of the Toolbar title (according to specs, 72dp)
        toolbarTitleLeftMargin = getResources().getDimensionPixelSize(R.dimen.toolbar_left_margin);


        // get Station object passed to intent
        station = getIntent().getExtras().getParcelable("station");

        // check for our toolbar xml layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_hourly_forecast);

        // redefine default action bar with new toolbar
        if (toolbar != null) {

            setSupportActionBar(toolbar);

            // display the back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
            //getSupportActionBar().setSubtitle(mSdfTitle.format(titleDate.getTime()));
        }

        // get xml string and icons array for our menu res/values/strings.xml
        mListTitles = getResources().getStringArray(R.array.overview_items_title);
        mListContent = getResources().getStringArray(R.array.overview_items_content);

        drawerItems = new ArrayList<>();
        for (int i = 0; i < mListTitles.length; i++) {
            drawerItems.add(new WeatherOverview(mListTitles[i], mListContent[i]));
        }

        // Inflate your header view
        //listView = rootView.findViewById(R.id.listview);
        //headerView = getLayoutInflater().inflate(R.layout.header_toolbar_hourly_forecast_overview, listview, false);

        listView = (ListView) findViewById(R.id.lst_fcst_overview_details);

        // Init the headerHeight and minHeaderTranslation values
        headerHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        minHeaderTranslation = -headerHeight +
                getResources().getDimensionPixelOffset(R.dimen.action_bar_height);


        LayoutInflater inflater = getLayoutInflater();
        headerView = inflater.inflate(R.layout.header_toolbar_hourly_forecast_overview,
                listView, false);


        // Retrieve the header views
        headerContainer = (RelativeLayout) headerView.findViewById(R.id.header_container);
        headerTitle = (TextView) headerView.findViewById(R.id.header_title);
        headerSubtitle = (TextView) headerView.findViewById(R.id.header_subtitle);


        headerContainerImg = (ImageView) headerView.findViewById(R.id.header_container_img);
        headerDailyWeatherImg = (ImageView) headerView.findViewById(R.id.daily_weather_img);


        // add ndHeader view to the navigation drawer container
        listView.addHeaderView(headerView, null, false); // true = clickable
        //headerView.setElevation(5);
        //listView.setElevation(2);


        // Set the adapter for the list view
        listView.setAdapter(new HourlyFcstLstAdapter(getApplicationContext(), drawerItems));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                //Toast.makeText(getApplicationContext(), "Scroll state changed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //Toast.makeText(getApplicationContext(), "On scroll", Toast.LENGTH_SHORT).show();

                Integer scrollY = getScrollY(view);

                // This will collapse the header when scrolling, until its height reaches
                // the toolbar height
                headerView.setTranslationY(Math.max(0, scrollY + minHeaderTranslation));

                // Scroll ratio (0 <= ratio <= 1).
                // The ratio value is 0 when the header is completely expanded,
                // 1 when it is completely collapsed
                float offset = 1 - Math.max(
                        (float) (-minHeaderTranslation - scrollY) / -minHeaderTranslation, 0f);


                // Now that we have this ratio, we only have to apply translations, scales,
                // alpha, etc. to the header views

                // For instance, this will move the toolbar title & subtitle on the X axis
                // from its original position when the ListView will be completely scrolled
                // down, to the Toolbar title position when it will be scrolled up.
                //headerTitle.setTranslationX(toolbarTitleLeftMargin * offset);
                //headerSubtitle.setTranslationX(toolbarTitleLeftMargin * offset);

                // Or we can make the FAB disappear when the ListView is scrolled
                //headerContainer.setAlpha(1 - offset);

                headerDailyWeatherImg.setAlpha(1 - offset);
                headerContainerImg.setAlpha(1 - offset);
            }
        });


        //((NotifyingScrollView) findViewById(R.id.scroll_view)).setOnScrollChangedListener(mOnScrollChangedListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // animate weather icon
        final Animation animationFalling = AnimationUtils.loadAnimation(this, R.anim.weather_icon_falling);
        headerDailyWeatherImg.startAnimation(animationFalling);
    }


    // Method that allows us to get the scroll Y position of the ListView
    public int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);

        if (c == null)
            return 0;

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();

        int headerHeight = 0;
        if (firstVisiblePosition >= 1)
            headerHeight = this.headerHeight;

        return -top + firstVisiblePosition * c.getHeight() + headerHeight;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_station, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
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
