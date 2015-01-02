package com.previmet.synop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class StationListAdapterNoFilter extends ArrayAdapter<Station> {
    private ArrayList<Station> items;
    private ArrayList<Station> itemsAll;
    private ArrayList<Station> suggestions;
    private int viewResourceId;
    private Context context;
    private HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>();

    public StationListAdapterNoFilter(Context context, int viewResourceId, ArrayList<Station> items) {
        super(context, viewResourceId, items);
        this.context = context;
        this.items = items;
        this.itemsAll = (ArrayList<Station>) items.clone();
        this.suggestions = new ArrayList<Station>();
        this.viewResourceId = viewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.station_list_item, parent, false);
        }

        Station station = items.get(position);

        if (station != null) {
            // pluralisation for station elevation
            Resources res = context.getResources();
            String elevation = res.getQuantityString(R.plurals.stationElevationUnit, station.getElevation(), station.getElevation());

            TextView sName = (TextView) convertView.findViewById(R.id.station_name);
            TextView sComplementary = (TextView) convertView.findViewById(R.id.station_complementary);

            sName.setText(station.getName());
            sComplementary.setText(station.getCountry() + " - " + elevation);


            if (mSelection.get(position) != null && mSelection.get(position)) {
                convertView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light));// this is a selected position so make it red
            }
        }
        return convertView;
    }

    public void setNewSelection(int position, boolean value) {
        mSelection.put(position, value);
        notifyDataSetChanged();
    }

    public boolean isPositionChecked(int position) {
        Boolean result = mSelection.get(position);
        return result == null ? false : result;
    }

    public Set<Integer> getCurrentCheckedPosition() {
        return mSelection.keySet();
    }
    public void removeSelection(int position) {
        mSelection.remove(position);
        notifyDataSetChanged();
    }
    public void clearSelection() {
        mSelection = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }
}