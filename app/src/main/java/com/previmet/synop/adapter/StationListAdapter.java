package com.previmet.synop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.ui.Items;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;


public class StationListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Station> stationListItems;

    public StationListAdapter(Context context, ArrayList<Station> stationListItems) {
        this.context = context;
        this.stationListItems = stationListItems;
    }

    @Override
    public int getCount() {
        return stationListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return stationListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.station_list_item, parent, false);
        }

        // pluralisation for station elevation
        Resources res = context.getResources();
        String elevation = res.getQuantityString(R.plurals.stationElevationUnit, stationListItems.get(position).getElevation(), stationListItems.get(position).getElevation());

        TextView sName = (TextView) convertView.findViewById(R.id.station_name);
        TextView sComplementary = (TextView) convertView.findViewById(R.id.station_complementary);

        sName.setText(stationListItems.get(position).getName());
        sComplementary.setText(stationListItems.get(position).getCountry() + " - " + elevation);


        return convertView;
    }
}
