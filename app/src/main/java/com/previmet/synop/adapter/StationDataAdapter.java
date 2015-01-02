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
import com.previmet.synop.ui.SynopData;

import java.util.ArrayList;


public class StationDataAdapter extends ArrayAdapter<SynopData> {
        private final String MY_DEBUG_TAG = "CustomerAdapter";
        private ArrayList<SynopData> items;

        private Context context;

        public StationDataAdapter(Context context, int viewResourceId, ArrayList<SynopData> items) {
        super(context, viewResourceId, items);
        this.context = context;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.station_synop_data_item, parent, false);
        }

        SynopData station = items.get(position);

        if (station != null) {
            // pluralisation for station elevation
            Resources res = context.getResources();


            TextView sDate = (TextView) convertView.findViewById(R.id.data_date);
            TextView sTmp = (TextView) convertView.findViewById(R.id.data_tmp);
            TextView sDpt = (TextView) convertView.findViewById(R.id.data_dewpoint);
            TextView sHr = (TextView) convertView.findViewById(R.id.data_hr);
            TextView sWndDir = (TextView) convertView.findViewById(R.id.data_wnddir);
            TextView sWndSpd = (TextView) convertView.findViewById(R.id.data_wndspd);
            TextView sWndAvg = (TextView) convertView.findViewById(R.id.data_wndavg);
            TextView sWndGust = (TextView) convertView.findViewById(R.id.data_wndgust);

            sDate.setText(station.getTime()+ ":00");
            sTmp.setText(station.getTmp());
            sDpt.setText(station.getDpt());
            sHr.setText(station.getHr());
            sWndDir.setText(station.getWndDir());
            sWndSpd.setText(station.getWndSpd());
            sWndAvg.setText(station.getWndAvg());
            sWndGust.setText(station.getWndGust());
        }
        return convertView;
    }
}