package com.previmet.synop.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class StationListAdapter extends ArrayAdapter<Station> implements Filterable {
    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<Station> items;
    private ArrayList<Station> itemsAll;
    private ArrayList<Station> suggestions;
    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((Station) (resultValue)).getName();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Station customer : itemsAll) {
                    if (customer.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
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
            if (results != null && results.count > 0) {
                clear();
                for (Station c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };
    private int viewResourceId;
    private Context context;
    private HashMap<Integer, Boolean> mSelection = new HashMap<Integer, Boolean>();

    public StationListAdapter(Context context, int viewResourceId, ArrayList<Station> items) {
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

        }

        // add animation at bottom.
        convertView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.abc_slide_in_bottom));
        convertView.setVisibility(View.VISIBLE);

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

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

}