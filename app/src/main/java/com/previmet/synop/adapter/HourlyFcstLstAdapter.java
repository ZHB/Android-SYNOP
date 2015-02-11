package com.previmet.synop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.ui.WeatherOverview;

import java.util.ArrayList;


public class HourlyFcstLstAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<WeatherOverview> hourlyFcstItems;

    public HourlyFcstLstAdapter(Context context, ArrayList<WeatherOverview> navDrawerItems) {
        this.context = context;
        this.hourlyFcstItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return hourlyFcstItems.size();
    }

    @Override
    public Object getItem(int position) {
        return hourlyFcstItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.lst_hourly_forecast_item, parent, false);
        }


        //convertView.isSelected()
        TextView weatherParam = (TextView) convertView.findViewById(R.id.weather_param);
        TextView weatherData = (TextView) convertView.findViewById(R.id.weather_data);

        weatherParam.setText(hourlyFcstItems.get(position).getTitle());
        weatherData.setText(hourlyFcstItems.get(position).getContent());


        // add when cards appears from bottom
        //convertView.startAnimation(AnimationUtils.loadAnimation(parent.getContext(), R.anim.abc_slide_in_bottom));
        //convertView.setVisibility(View.VISIBLE);

        return convertView;
    }
}
