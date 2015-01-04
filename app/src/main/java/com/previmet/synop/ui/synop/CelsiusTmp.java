package com.previmet.synop.ui.synop;

/**
 * Created by Vince on 03.01.2015.
 */
public class CelsiusTmp implements UnitTmpBehavior {

    @Override
    public double convertTmp(double tmp) {
        return tmp; // original data in celsius, so nothing to do
    }
}
