package com.previmet.synop.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.renderscript.Script;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.previmet.synop.R;
import com.previmet.synop.activities.MainActivity;
import com.previmet.synop.adapter.StationListAdapter;
import com.previmet.synop.db.Db;
import com.previmet.synop.db.DbContract;
import com.previmet.synop.db.DbCursor;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;


public class MapFragment extends SupportMapFragment   {


    GoogleMap googleMap;

    @Override
    public void onCreate(Bundle arg0) {

        super.onCreate(arg0);
    }

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup arg1, Bundle arg2) {

        return super.onCreateView(mInflater, arg1, arg2);
    }

    @Override
    public void onInflate(Activity arg0, AttributeSet arg1, Bundle arg2) {
        super.onInflate(arg0, arg1, arg2);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());

        // display a connectivity error message if needed
        if(status != ConnectionResult.SUCCESS){
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, getActivity(), 10);
            dialog.show();
        } else {

            createMapView();
            addMarker();

            // Zoom the map
            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(46.4594, 4.7356)).zoom(5.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.moveCamera(cameraUpdate);
        }
    }

    /**
     * Initialises the mapview
     */
    private void createMapView(){
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == googleMap){
                googleMap = getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap) {
                    Toast.makeText(getActivity().getApplicationContext(),"Error creating map",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }

    /**
     * Adds a marker to the map
     */
    private void addMarker(){

        DbCursor sCursor = Db.getStations();

        /** Make sure that the map has been initialised **/
        if(null != googleMap){

            while(sCursor.moveToNext()) {
                // The Cursor is now set to the right position

                googleMap.addMarker(new MarkerOptions()
                                .position(
                                        new LatLng(sCursor.getDouble(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LATITUDE)),
                                                   sCursor.getDouble(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LONGITUDE))))
                                .title(sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_STATION)))
                                .draggable(false)
                );
            }
        }
    }
}
