package com.previmet.synop.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.previmet.synop.FavoriteListener;
import com.previmet.synop.R;
import com.previmet.synop.activities.AddFavoriteActivity;
import com.previmet.synop.activities.StationActivity;
import com.previmet.synop.activities.StationEditActivity;
import com.previmet.synop.adapter.FavoriteAdapter;
import com.previmet.synop.db.Db;
import com.previmet.synop.db.DbContract;
import com.previmet.synop.db.DbCursor;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment implements FavoriteListener {

    private TextView hourlyListView;
    private ArrayList<Station> stationListItems;
    private FavoriteAdapter fa;
    private AddFavoriteActivity addFav;
    private RecyclerView recList;
    private View rootView;
    private ActionMode mActionMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // the rootView is our Favorite_Fragment java.fragments.FavoritesFragment
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        recList = (RecyclerView) rootView.findViewById(R.id.cardList);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setItemAnimator(new DefaultItemAnimator());

        stationListItems = new ArrayList<Station>();

        DbCursor sCursor = Db.getFavorite();
        while(sCursor.moveToNext()) {
            // The Cursor is now set to the right position
            stationListItems.add(new Station(
                            sCursor.getLong(sCursor.getColumnIndex(DbContract.Station._ID)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_STATION)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_WMO)),
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Country.COLUMN_NAME_COUNTRY)),
                            sCursor.getInt(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_ELEVATION)),
                            sCursor.getDouble(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LATITUDE)),
                            sCursor.getDouble(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_LONGITUDE)))
            );
        }

        fa = new FavoriteAdapter(stationListItems);
        recList.setAdapter(fa);

        // start favorite add activity
        ImageButton fabFavorite = (ImageButton) rootView.findViewById(R.id.fab_add_favorite);
        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(rootView.getContext(), AddFavoriteActivity.class);
                startActivityForResult(intent,55);
            }

        });




        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // get the station
        Station s = data.getExtras().getParcelable("added_station");
        stationListItems.add(s);

        // notifiy the adapter from added station
        fa.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(View view , Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // add a listener on the RecyclerView adapter
        fa.SetOnItemClickListener(new FavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                // get station object from clicked item position
                Station station = stationListItems.get(position);

                // start a new intent with station as extra then start activity
                Intent intent = new Intent(view.getContext(), StationActivity.class);
                intent.putExtra("station", station);
                getActivity().startActivity(intent);
            }
        });



        fa.SetOnItemPressedListener(
                new FavoriteAdapter.OnItemPressedListener() {
                    @Override
                    public void onItemPressed(View view, int position) {

                        // Start the CAB using the ActionMode.Callback defined above
                        mActionMode = getActivity().startActionMode(mActionModeCallback);
                        mActionMode.setTag(position);

                        view.setSelected(true);
                    }
                }

        );


    }

    @Override
    public void favoriteAdded(int position) {
        Toast.makeText(getActivity(),"Listener : " + position,Toast.LENGTH_SHORT).show();
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.cab_station_favorite, menu);

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

                    int item_postion=Integer.parseInt(mode.getTag().toString());

                    // remove item from the current list, then notify the adapter
                    Db.deleteFavorite(stationListItems.get(item_postion).getId());
                    stationListItems.remove(item_postion);
                    fa.notifyItemRemoved(item_postion);

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
            //recList.setItemChecked(-1, true);

            mActionMode = null;
        }
    };
}
