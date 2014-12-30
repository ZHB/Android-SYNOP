package com.previmet.synop.activities;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.previmet.synop.R;
import com.previmet.synop.adapter.StationDataAdapter;
import com.previmet.synop.adapter.StationListAdapter;
import com.previmet.synop.ui.Station;
import com.previmet.synop.ui.SynopData;

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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

public class StationActivity extends ActionBarActivity {

    private ProgressBar spinner;
    private ArrayList<SynopData> synopDataItem;
    private ListView synopDataContainer;

    private TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        // get Station object passed to intent
        Station station = getIntent().getExtras().getParcelable("station");


        // check for our toolbar xml layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        // redefine default action bar with new toolbar
        if (toolbar != null) {

            setSupportActionBar(toolbar);

            // display the back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //toolbar.setTitle("Station name");
            //toolbar.setSubtitle("Lundi 2 nov. 2014 à 15:00" + station.getName());

            getSupportActionBar().setTitle(station.getName());
            getSupportActionBar().setSubtitle("Lundi 2 nov. 2014 à 15:00");
        }


        // check mobile connexion status
        if(isConnected()){
            spinner = (ProgressBar)findViewById(R.id.progressBar1);

            // retrieve JSON data on an async task
            new HttpAsyncTask().execute("http://www.prevision-meteo.ch/data/synop/2014-12-29/06712");
        }
        else{
            Toast.makeText(getApplicationContext(), "Please, verify your internet connection", Toast.LENGTH_SHORT).show();
        }
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

    /**
     * Verifiy if the mobile has an active internet connection
     *
     * @return
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
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            spinner.setVisibility(View.VISIBLE);
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
                if(inputStream != null)
                    result = convertInputStreamToString(inputStream);
                else
                    result = "Did not work!";

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }

            return result;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            spinner.setVisibility(View.GONE);


            //etResponse.setText(result);

            try {
                //JSONObject json = new JSONObject(result.trim());
                //JSONArray synop = json.getJSONArray("synop");



                synopDataItem = new ArrayList<SynopData>();
                synopDataContainer = (ListView)findViewById(R.id.list_synop_data);



                JSONObject json = new JSONObject(result.trim());
                JSONArray synop = json.getJSONArray("synop");

                for (int i = 0, size = synop.length(); i < size; i++)
                {
                    JSONObject objectInArray = synop.getJSONObject(i);

                    synopDataItem.add(
                            new SynopData(
                                    objectInArray.getString("rDATE"),
                                    objectInArray.getString("rTIME"),
                                    objectInArray.getDouble("rTMP"),
                                    objectInArray.getDouble("rDPT")
                            )
                    );
                }




                /*
                Iterator<?> keys = json.keys();
                while( keys.hasNext() ){
                    String key = (String)keys.next();
                    if( json.get(key) instanceof JSONArray ){

                        Toast.makeText(getBaseContext(), "Length: " + key, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();

                        synopDataItem.add(
                                new SynopData(
                                        synop.getJSONObject(0).getString("rDATE"),
                                        synop.getJSONObject(0).getString("rTIME"),
                                        synop.getJSONObject(0).getDouble("rTMP"),
                                        synop.getJSONObject(0).getDouble("rDPT")
                                )
                        );
                    } else {
                        Toast.makeText(getBaseContext(), "NOT!", Toast.LENGTH_LONG).show();
                    }
                }
                */



                Toast.makeText(getBaseContext(), "Length: " + synopDataItem.size(), Toast.LENGTH_LONG).show();

                //StationListAdapter adapter = new StationListAdapter(rootView.getContext(), stationListItems);
                StationDataAdapter adapter = new StationDataAdapter(StationActivity.this, R.layout.station_synop_data_item, synopDataItem);

                // Set the adapter for the list view
                synopDataContainer.setAdapter(adapter);



            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
