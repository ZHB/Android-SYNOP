package com.previmet.synop.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.previmet.synop.OnSwipeTouchListener;
import com.previmet.synop.R;
import com.previmet.synop.adapter.StationDataAdapter;
import com.previmet.synop.ui.synop.FahrenheitTmp;
import com.previmet.synop.ui.synop.KnotsWnd;
import com.previmet.synop.ui.synop.MphWnd;
import com.previmet.synop.ui.Station;
import com.previmet.synop.ui.synop.SynopData;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StationActivity extends ActionBarActivity {

    private ProgressBar mSpinner;
    private ArrayList<SynopData> mSynopDataItem;
    private ListView mSynopDataContainer;
    private Station station;
    private String mCurrentDate;
    private String mPreferenceTmp;
    private String mPreferenceWnd;

    private SimpleDateFormat mSdfTitle = new SimpleDateFormat("EEEE dd MMMM yyyy");
    private SimpleDateFormat mSdfSql = new SimpleDateFormat("yyyy-MM-dd");

    private final static String REST_URL = "http://www.prevision-meteo.ch/data/synop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        // get Station object passed to intent
        station = getIntent().getExtras().getParcelable("station");

        // check for our toolbar xml layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // get preferences for units
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(StationActivity.this);
        mPreferenceTmp = pref.getString("units_temperature_list", "0");
        mPreferenceWnd = pref.getString("units_wind_list", "0");

        // get current date
        Calendar c = Calendar.getInstance();

        //SimpleDateFormat mSdfSql = new SimpleDateFormat("yyyy-MM-dd");
        mCurrentDate = mSdfSql.format(c.getTime());

        // redefine default action bar with new toolbar
        if (toolbar != null) {

            setSupportActionBar(toolbar);

            // display the back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            Calendar titleDate  = Calendar.getInstance();
            try {
                titleDate.setTime(mSdfSql.parse(mCurrentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            getSupportActionBar().setTitle(station.getName());
            getSupportActionBar().setSubtitle(mSdfTitle.format(titleDate.getTime()));
        }


        callJson();

        ListView mcs = (ListView) findViewById(R.id.list_synop_data);


        mcs.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            @Override
            public void onSwipeLeft() throws ParseException {
                addSubNDay(1);
                callJson();
            }

            @Override
            public void onSwipeRight() {
                addSubNDay(-1);
                callJson();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_station, menu);
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

    private void addSubNDay(int day) {
        Calendar now  = Calendar.getInstance();
        try {

            Date curDate = mSdfSql.parse(mCurrentDate);

            now.setTime(curDate);

            // substract N day
            now.add(Calendar.DATE, day);
            mCurrentDate = mSdfSql.format(now.getTime());

            getSupportActionBar().setSubtitle(mSdfTitle.format(now.getTime()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void callJson() {
        // check mobile connexion status
        if(isConnected()){
            mSpinner = (ProgressBar)findViewById(R.id.progressBar1);

            // retrieve JSON data on an async task
            new HttpAsyncTask().execute( REST_URL + "/" + mCurrentDate + "/" + station.getWmo() );
        }
        else{
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Verifiy if the mobile has an active internet connection
     *
     * @return true if connected
     */
    private boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        inputStream.close();

        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... urls) {
            InputStream inputStream = null;
            String result = "";

            try {
                // create HttpClient
                HttpClient httpclient = new DefaultHttpClient();

                // make GET request to the given URL
                HttpResponse httpResponse = httpclient.execute(new HttpGet(urls[0]));

                // receive response as inputStream
                inputStream = httpResponse.getEntity().getContent();

                // convert inputstream to string
                if(inputStream != null) {
                    result = convertInputStreamToString(inputStream);
                } else {
                    result = "Did not work!";
                }

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }

            return result;
        }

        /*
         * onPostExecute displays the results of the AsyncTask.
         */
        @Override
        protected void onPostExecute(String result) {
            mSpinner.setVisibility(View.GONE);

            mSynopDataItem = new ArrayList<SynopData>();
            mSynopDataContainer = (ListView)findViewById(R.id.list_synop_data);

            try {
                JSONObject json = new JSONObject(result.trim());
                JSONArray synop = json.getJSONArray("synop");

                for (int i = 0, size = synop.length(); i < size; i++)
                {
                    JSONObject objectInArray = synop.getJSONObject(i);

                    SynopData sData = new SynopData(
                                            objectInArray.getString("rDATE"),
                                            objectInArray.getString("rTIME"),
                                            objectInArray.getString("rTMP"),
                                            objectInArray.getString("rDPT"),
                                            objectInArray.getString("rHR"),
                                            objectInArray.getString("rWNDDIR"),
                                            objectInArray.getString("rWNDSPD"),
                                            objectInArray.getString("rWNDAVG"),
                                            objectInArray.getString("rWNDGUST")
                                    );

                    // set units
                    if(mPreferenceTmp.equals("1")) {
                        sData.setUnitTmpBehavior(new FahrenheitTmp());
                    }

                    if(mPreferenceWnd.equals("1")) {
                        sData.setUnitWndBehavior(new KnotsWnd());
                    } else if(mPreferenceWnd.equals("2")) {
                        sData.setUnitWndBehavior(new MphWnd());
                    }


                    mSynopDataItem.add(sData);
                }

                // call adapter to populate data
                StationDataAdapter adapter = new StationDataAdapter(StationActivity.this, R.layout.station_synop_data_item, mSynopDataItem);

                // Set the adapter for the list view
                mSynopDataContainer.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
