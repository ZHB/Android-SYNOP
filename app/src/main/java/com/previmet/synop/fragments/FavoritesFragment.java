package com.previmet.synop.fragments;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.previmet.synop.R;
import com.previmet.synop.activities.AddFavoriteActivity;
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
    private FavoriteAdapter fa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // the rootView is our Favorite_Fragment java.fragments.FavoritesFragment
        final View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.cardList);
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
                            sCursor.getString(sCursor.getColumnIndex(DbContract.Country.COLUMN_NAME_COUNTRY)),
                            sCursor.getInt(sCursor.getColumnIndex(DbContract.Station.COLUMN_NAME_ELEVATION)))
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
                getActivity().startActivity(intent);
            }

        });

        return rootView;
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

                        // remove item from the current list, then notify the adapter
                        Db.deleteFavorite(stationListItems.get(position).getId());
                        stationListItems.remove(position);
                        fa.notifyItemRemoved(position);
                    }
                }

        );
    }
}
