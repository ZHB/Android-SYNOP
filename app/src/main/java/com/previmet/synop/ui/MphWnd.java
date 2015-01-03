package com.previmet.synop.ui;

/**
 * Created by Vince on 03.01.2015.
 */
public class MphWnd implements UnitWndBehavior {

    @Override
    public double convertWnd(double wnd) {
        return (double)Math.round((wnd * 0.621) * 10) / 10; // 1mph = 0.621 km/h
    }
}
