package com.previmet.synop.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.previmet.synop.R;
import com.previmet.synop.TextValidator;
import com.previmet.synop.db.Db;
import com.previmet.synop.ui.Station;

public class StationEditActivity extends ActionBarActivity {

    private Station station;
    private EditText editStationName;
    private EditText editStationElevation;
    private int mCountError = 0;
    private MenuItem mMenuValidate;
    static final int EDIT_STATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_edit);

        // get Station object passed to intent
        station = getIntent().getExtras().getParcelable("station");

        // check for our toolbar xml layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // redefine default action bar with new toolbar
        if (toolbar != null) {

            setSupportActionBar(toolbar);

            // display the back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.title_activity_station_edit));
        }

        // load default data into edit texts
        editStationName = (EditText) findViewById(R.id.edit_station_name_txt);
        editStationElevation = (EditText) findViewById(R.id.edit_station_elevation_txt);


        editStationName.setText(station.getName());
        editStationElevation.setText(String.valueOf(station.getElevation()));

        // validate entries
        editStationName.addTextChangedListener(new TextValidator(editStationName) {
            @Override public void validate(TextView textView, String text) {
                if(text.length() < 1) {
                    mCountError++;
                    editStationName.setError(getResources().getString(R.string.edit_station_name_error));
                }

                if(text.length() >= 1 && mCountError > 0) {
                    mCountError--;
                }

                activateValidation();
            }
        });

        editStationElevation.addTextChangedListener(new TextValidator(editStationElevation) {
            @Override public void validate(TextView textView, String text) {
                if(text.length() < 1) {
                    mCountError++;
                    editStationElevation.setError(getResources().getString(R.string.edit_station_elevation_error));
                }

                if(text.length() >= 1 && mCountError > 0) {
                    mCountError--;
                }

                activateValidation();
            }
        });
    }


    private void activateValidation() {
        if(mCountError <= 0) {
            mMenuValidate.setVisible(true);
        } else {
            mMenuValidate.setVisible(false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_station_edit, menu);

        mMenuValidate = menu.findItem(R.id.mnu_station_edit_validate);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mnu_station_edit_validate) {

            String name = editStationName.getText().toString();
            String elevation = editStationElevation.getText().toString();

            station.setName(name);
            station.setElevation(Integer.parseInt(elevation));

            Db.updateStation(station.getId(), station.getName(), Integer.toString(station.getElevation()));

            // return updated station object
            Intent returnIntent = new Intent();
            returnIntent.putExtra("add_edit_station",station);
            setResult(RESULT_OK, returnIntent);

            this.finish();

            return true;
        }

        // close the current activity
        this.finish();


        return super.onOptionsItemSelected(item);
    }
}
