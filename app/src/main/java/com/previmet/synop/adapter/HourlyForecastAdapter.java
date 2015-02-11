package com.previmet.synop.adapter;

/**
 * Created by Vince on 24.12.2014.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import com.previmet.synop.R;
import com.previmet.synop.ui.Station;

import java.util.ArrayList;

/**
 * Created by Vince on 24.12.2014.
 */
public class HourlyForecastAdapter extends RecyclerView.Adapter<HourlyForecastAdapter.ContactViewHolder> {

    private ArrayList<Station> stationListItems;
    private OnItemClickListener mItemClickListener;
    private OnItemPressedListener mItemLongClickListener;

    public HourlyForecastAdapter(ArrayList<Station> stationListItems) {
        this.stationListItems = stationListItems;
    }

    @Override
    public int getItemCount() {
        return stationListItems.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Station s = stationListItems.get(i);

        //contactViewHolder.vStation.setText(s.getName());
        //contactViewHolder.vUpdated.setText("18:00 UTC");
        //contactViewHolder.vCondition1.setText("Temperature 9.4 °, wind West 3.7/11.1 km/h");
        //contactViewHolder.vCondition2.setText("Precipitation 0/1h");


    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.fragment_hourly_forecast_item, viewGroup, false);

        // add when cards appears from bottom
        itemView.startAnimation(AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.abc_slide_in_bottom));
        itemView.setVisibility(View.VISIBLE);

        return new ContactViewHolder(itemView);
    }

    public void SetOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public void SetOnItemPressedListener(OnItemPressedListener mItemLongClickListener) {
        this.mItemLongClickListener = mItemLongClickListener;
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public interface OnItemPressedListener {
        public void onItemPressed(View view, int position);
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        //protected TextView vStation;
        //protected TextView vUpdated;
        //protected TextView vCondition1;
        //protected TextView vCondition2;
        protected View v;

        public ContactViewHolder(View v) {
            super(v);

            this.v = v;

            //vStation = (TextView) v.findViewById(R.id.favorite_station);
            //vUpdated = (TextView)  v.findViewById(R.id.favorite_updated);
            //vCondition1 = (TextView)  v.findViewById(R.id.favorite_condition1);
            //vCondition2 =  (TextView) v.findViewById(R.id.favorite_condition2);

            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {

            if (mItemLongClickListener != null) {
                mItemLongClickListener.onItemPressed(v, getPosition());
            }

            return true;
        }
    }
}