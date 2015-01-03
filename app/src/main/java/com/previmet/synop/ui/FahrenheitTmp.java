package com.previmet.synop.ui;

/**
 * Created by Vince on 03.01.2015.
 */
public class FahrenheitTmp implements UnitTmpBehavior {

    @Override
    public double convertTmp(double tmp) {
        return (double)Math.round((tmp * 9/5 + 32) * 10) / 10; // 20°C × 9/5 + 32 = 68 °F
    }
}
