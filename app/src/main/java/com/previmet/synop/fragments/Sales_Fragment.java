package com.previmet.synop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.previmet.synop.R;


/**
 * Created by Daniel on 09.11.2014.
 */
public class Sales_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_preview, container, false);



        // textView

        // Preparing user interface

        final TextView myAwesomeTextView = (TextView) rootView.findViewById(R.id.textView);

        //in your OnCreate() method
        myAwesomeTextView.setText("Now on sales view yeahhhh");

        return rootView;
    }

}
