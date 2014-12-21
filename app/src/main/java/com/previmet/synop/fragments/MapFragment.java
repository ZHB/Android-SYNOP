package com.previmet.synop.fragments;

import android.app.Activity;
import android.app.AlertDialog;
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
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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


    GoogleMap mapView;

    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    public View onCreateView(LayoutInflater mInflater, ViewGroup arg1,
                             Bundle arg2) {
        return super.onCreateView(mInflater, arg1, arg2);
    }

    @Override
    public void onInflate(Activity arg0, AttributeSet arg1, Bundle arg2) {
        super.onInflate(arg0, arg1, arg2);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapView = getMap();
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.draggable(true);
        markerOptions.position(new LatLng(46.4594, 8.9356));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
        mapView.addMarker(markerOptions);
    }
}
