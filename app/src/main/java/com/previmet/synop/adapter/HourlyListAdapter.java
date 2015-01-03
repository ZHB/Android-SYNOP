package com.previmet.synop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.previmet.synop.R;
import com.previmet.synop.ui.Items;

import java.util.ArrayList;


public class HourlyListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Items> navDrawerItems;

    public HourlyListAdapter(Context context, ArrayList<Items> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.nav_drawer_list_item, parent, false);
        }

        ImageView image = (ImageView) convertView.findViewById(R.id.item_icon);
        TextView text = (TextView) convertView.findViewById(R.id.item_text);

        image.setImageResource(navDrawerItems.get(position).getIcon());
        text.setText(navDrawerItems.get(position).getTitle());

        return convertView;
    }
}
