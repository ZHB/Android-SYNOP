package com.previmet.synop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.previmet.synop.R;
import com.previmet.synop.activities.StationActivity;
import com.previmet.synop.adapter.FavoriteAdapter;
import com.previmet.synop.db.Db;
import com.previmet.synop.db.DbContract;
import com.previmet.synop.db.DbCursor;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment {

    private TextView hourlyListView;
    private ArrayList<Station> stationListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // the rootView is our Favorite_Fragment java.fragments.FavoritesFragment
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(false);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        stationListItems = new ArrayList<Station>();

        DbCursor sCursor = Db.getStations();
        while(sCursor.moveToNext()) {
            // The Cursor is now set to the right position
            stationListItems.add(new Station(
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_STATION)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Country.COLUMN_NAME_COUNTRY)),
                            sCursor.getInt(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_ELEVATION)))
            );
        }

        FavoriteAdapter fa = new FavoriteAdapter(stationListItems);
        recList.setAdapter(fa);



        /*
        recList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Toast.makeText(getActivity(),"Text!",Toast.LENGTH_SHORT).show();

            }


        });*/

        // get the list view
        //hourlyListView = (TextView) rootView.findViewById(R.id.info_text);
        //hourlyListView.setText("jdsd");

        //hourlyListView.setAdapter(new HourlyListAdapter());

        //hourlyListView = (ListView) findViewById(R.id.left_drawer);


        return rootView;
    }
}
