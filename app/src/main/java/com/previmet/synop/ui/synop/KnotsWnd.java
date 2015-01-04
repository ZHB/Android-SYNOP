package com.previmet.synop.ui.synop;

/**
 * Created by Vince on 03.01.2015.
 */
public class KnotsWnd implements UnitWndBehavior {

    @Override
    public double convertWnd(double wnd) {
        return (double)Math.round((wnd * 0.5399568) * 10) / 10; // 1knot = 0.5399568 km/h
    }
}
