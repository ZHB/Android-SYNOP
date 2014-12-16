package com.previmet.synop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.adapter.StationListAdapter;
import com.previmet.synop.ui.Items;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;


public class StationsFragment extends Fragment {

    private String[] stationList;
    private ArrayList<Station> stationListItems;
    private ListView stationListContainer;
    private TextView hourlyListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // the rootView is our station list res.layout.fragmentStations
        View rootView = inflater.inflate(R.layout.fragment_stations, container, false);


        // TODO: get stations from database
        // get stations list from xml
        stationList = getResources().getStringArray(R.array.stations_list);

        // get navigation drawer container
        //StationContainer = (ListView) findViewById(R.id.left_drawer);
        stationListContainer = (ListView) rootView.findViewById(R.id.list_stations);

        /*
        * create a new array list for our navigation drawer that will contain Items object.
        * Items are created with text and icons.
        */
        stationListItems = new ArrayList<Station>();
        for (int i = 0; i < stationList.length; i++) {
            stationListItems.add(new Station(stationList[i], "Suisse", 500));
        }


        // Set the adapter for the list view
        stationListContainer.setAdapter(new StationListAdapter(rootView.getContext(), stationListItems));


        // get the list view
        //hourlyListView = (TextView) rootView.findViewById(R.id.info_text);
        //hourlyListView.setText("jdsd");

        //hourlyListView.setAdapter(new HourlyListAdapter());

        //hourlyListView = (ListView) findViewById(R.id.left_drawer);


        return rootView;
    }



}
