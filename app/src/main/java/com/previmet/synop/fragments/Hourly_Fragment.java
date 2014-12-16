package com.previmet.synop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.previmet.synop.R;


public class Hourly_Fragment extends Fragment {

    private ListView hourlyListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_hourly, container, false);


        // get the list view
        hourlyListView = (ListView) rootView.findViewById(R.id.fListViewHourly);

        //hourlyListView.setAdapter(new HourlyListAdapter());

        //hourlyListView = (ListView) findViewById(R.id.left_drawer);


        return rootView;
    }



}
