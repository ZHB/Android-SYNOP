package com.previmet.synop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.previmet.synop.R;


public class FavoritesFragment extends Fragment {

    private TextView hourlyListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // the rootView is our Favorite_Fragment java.fragments.FavoritesFragment
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);


        // get the list view
        //hourlyListView = (TextView) rootView.findViewById(R.id.info_text);
        //hourlyListView.setText("jdsd");

        //hourlyListView.setAdapter(new HourlyListAdapter());

        //hourlyListView = (ListView) findViewById(R.id.left_drawer);


        return rootView;
    }



}
