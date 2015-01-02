package com.previmet.synop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;

/**
 * Created by Vince on 31.12.2014.
 */
public class SearchSuggestionAdapter extends CursorAdapter {

    private ArrayList<Station> mStations;

    private TextView mStationName;
    private TextView mStationComplementary;

    public SearchSuggestionAdapter(Context context, Cursor cursor, ArrayList<Station> stations) {

        super(context, cursor, false);

        this.mStations = stations;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // pluralisation for station elevation
        Resources res = context.getResources();
        String elevation = res.getQuantityString(R.plurals.stationElevationUnit, mStations.get(cursor.getPosition()).getElevation(), mStations.get(cursor.getPosition()).getElevation());

        mStationName.setText(mStations.get(cursor.getPosition()).getName());
        mStationComplementary.setText(mStations.get(cursor.getPosition()).getCountry() + " - " + elevation);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.search_suggestion_item, parent, false);

        mStationName = (TextView) view.findViewById(R.id.station_name);
        mStationComplementary = (TextView) view.findViewById(R.id.station_complementary);

        return view;
    }
}