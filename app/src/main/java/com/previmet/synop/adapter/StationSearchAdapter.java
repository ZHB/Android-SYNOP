package com.previmet.synop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;


/**
 * Created by Vince on 28.12.2014.
 */
public class StationSearchAdapter extends ArrayAdapter<Station> {
    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<Station> items;
    private ArrayList<Station> itemsAll;
    private ArrayList<Station> suggestions;
    private int viewResourceId;
    private Context context;

    public StationSearchAdapter(Context context, int viewResourceId, ArrayList<Station> items) {
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
            convertView = vi.inflate(R.layout.station_list_item, null);
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
        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((Station)(resultValue)).getName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                suggestions.clear();
                for (Station customer : itemsAll) {
                    if(customer.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        suggestions.add(customer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Station> filteredList = (ArrayList<Station>) results.values;
            if(results != null && results.count > 0) {
                clear();
                for (Station c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}