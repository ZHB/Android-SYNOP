package com.previmet.synop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.previmet.synop.R;
import com.previmet.synop.activities.AddFavoriteActivity;
import com.previmet.synop.activities.StationActivity;
import com.previmet.synop.activities.StationEditActivity;
import com.previmet.synop.adapter.StationListAdapter;
import com.previmet.synop.db.Db;
import com.previmet.synop.db.DbContract;
import com.previmet.synop.db.DbCursor;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;


public class StationsFragment extends Fragment {

    private String[] stationList;
    private ArrayList<Station> stationListItems;
    private ListView stationListContainer;
    private TextView hourlyListView;
    private View rootView;
    private StationListAdapter adapter;
    private ActionMode mActionMode;
    private static int RESULT_OK = 11;
    private int item_postion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // the rootView is our station list res.layout.fragmentStations
        rootView = inflater.inflate(R.layout.fragment_stations, container, false);

        // get navigation drawer container
        //StationContainer = (ListView) findViewById(R.id.left_drawer);
        stationListContainer = (ListView) rootView.findViewById(R.id.list_stations);

        /*
        * create a new array list for our navigation drawer that will contain Items object.
        * Items are created with text and icons.
        */
        Bundle args = getArguments();
        if (args  != null && args.containsKey("stations_list")) {
            stationListItems = args.getParcelableArrayList("stations_list");
        }

        //StationListAdapter adapter = new StationListAdapter(rootView.getContext(), stationListItems);
        adapter = new StationListAdapter(rootView.getContext(), R.layout.station_list_item, stationListItems);

        // Set the adapter for the list view
        stationListContainer.setAdapter(adapter);

        stationListContainer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    if (mActionMode != null) {
                        return false;
                    }

                    // Start the CAB using the ActionMode.Callback defined above
                    mActionMode = getActivity().startActionMode(mActionModeCallback);
                    mActionMode.setTag(position);

                    view.setSelected(true);


                    return true;
                }
            }
        );


        stationListContainer.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Station station = new Station("Name", "Country", 500);

                Station station = stationListItems.get(position);

                Intent intent = new Intent(view.getContext(), StationActivity.class);
                intent.putExtra("station", station);
                getActivity().startActivity(intent);
            }
        });

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // get the station
        Station s = data.getExtras().getParcelable("add_edit_station");

        // update station object at given position
        stationListItems.set(item_postion, s);

        // notifiy the adapter from added station
        adapter.notifyDataSetChanged();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.cab_station_list, menu);

            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.cab_item_edit:

                    item_postion = Integer.parseInt(mode.getTag().toString());
                    Station s = (Station) stationListContainer.getAdapter().getItem(item_postion);

                    Intent intent = new Intent(rootView.getContext(), StationEditActivity.class);
                    intent.putExtra("station", s);
                    startActivityForResult(intent, RESULT_OK);

                    //shareCurrentItem();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {

            // reset relected items
            stationListContainer.setItemChecked(-1, true);

            mActionMode = null;
        }
    };

}
