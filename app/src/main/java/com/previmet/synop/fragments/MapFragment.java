package com.previmet.synop.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.previmet.synop.R;
import com.previmet.synop.activities.StationActivity;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;
import java.util.HashMap;


public class MapFragment extends SupportMapFragment {
    private static final double DEFAULT_LATITUDE = 46.4594;
    private static final double DEFAULT_LONGITUDE = 4.7356;
    private static final float DEFAULT_ZOOM = 5.0f;
    private GoogleMap mGoogleMap;
    private ArrayList<Station> stationListItems;
    private HashMap<String, Station> mExtraMarkerContent = new HashMap<String, Station>();

    @Override
    public void onCreate(Bundle arg0) {

        super.onCreate(arg0);

        /*
        * Retrieve station object pass to the fragment
        */
        Bundle args = getArguments();

        if (args != null && args.containsKey("stations_list")) {
            stationListItems = args.getParcelableArrayList("stations_list");
        }
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
        if (status != ConnectionResult.SUCCESS) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, getActivity(), 10);
            dialog.show();
        } else {

            createMapView();
            addMarker();

            // Zoom the map
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(DEFAULT_LATITUDE, DEFAULT_LONGITUDE)).zoom(DEFAULT_ZOOM).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mGoogleMap.moveCamera(cameraUpdate);

            // get visible bounds position on camera change
            mGoogleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {

                    LatLngBounds bounds = mGoogleMap.getProjection().getVisibleRegion().latLngBounds;


                    String boundsRest = "?nelat=" + bounds.northeast.latitude + "?nelng=" + bounds.northeast.longitude +
                            "?swlat=" + bounds.southwest.latitude + "?swlng=" + bounds.southwest.longitude;

                    Toast.makeText(getActivity(), boundsRest, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * Initialises the mapview
     */
    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if (null == mGoogleMap) {
                mGoogleMap = getMap();

                mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        View view = getActivity().getLayoutInflater()
                                .inflate(R.layout.google_maps_infowindows, null);

                        TextView markerStation = (TextView) view.findViewById(R.id.marker_station);
                        TextView markerCountry = (TextView) view.findViewById(R.id.marker_country);

                        // pluralisation for station elevation
                        Resources res = getResources();
                        String elevation = res.getQuantityString(R.plurals.stationElevationUnit,
                                mExtraMarkerContent.get(marker.getId()).getElevation(),
                                mExtraMarkerContent.get(marker.getId()).getElevation());


                        // set text to the layout
                        markerStation.setText(marker.getTitle());
                        markerCountry.setText(mExtraMarkerContent
                                .get(marker.getId()).getCountry() + " - " + elevation);

                        return view;
                    }
                });

                mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
                        marker.showInfoWindow();
                        return true;
                    }
                });

                mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {

                        // get station object from HashMap, then pass it to new intent
                        Station station = mExtraMarkerContent.get(marker.getId());

                        Intent intent = new Intent(getActivity(), StationActivity.class);
                        intent.putExtra("station", station);
                        startActivity(intent);
                    }
                });

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == mGoogleMap) {
                    Toast.makeText(getActivity()
                            .getApplicationContext(), "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }

    /**
     * Add marker to map and create an HashMap of theses markers with station
     * object content.
     */
    private void addMarker() {
        /** Make sure that the map has been initialised **/
        if (null != mGoogleMap) {
            for (Station s : stationListItems) {
                // Create your marker like normal, nothing changes
                Marker marker = mGoogleMap.addMarker(new MarkerOptions()
                        .position(new LatLng(s.getLatitude(), s.getLongitude()))
                        .title(s.getName())
                        .draggable(false)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

                // Save the entiere object into the hashMap
                mExtraMarkerContent.put(marker.getId(), s);
            }
        }
    }

}
