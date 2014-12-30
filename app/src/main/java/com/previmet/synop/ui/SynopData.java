package com.previmet.synop.ui;

/**
 * Created by Vince on 30.12.2014.
 */
public class SynopData {

    private String date;
    private String time;
    private double tmp;
    private double tmpMin;
    private double tmpMax;
    private double dpt;
    private int hr;
    private double wndSpd;
    private double wndAvg;
    private double wndGust;
    private double prmsl;
    private double prmslTrend;
    private double sun;


    public SynopData(String date, String time, double tmp, double dpt) {
        this.date = date;
        this.time = time;
        this.tmp = tmp;
        this.dpt = dpt;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getTmp() {
        return tmp;
    }

    public double getDpt() {
        return dpt;
    }
}
