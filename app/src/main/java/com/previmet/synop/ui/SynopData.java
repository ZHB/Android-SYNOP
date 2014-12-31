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
    private String wndDir;
    private String wndSpd;
    private String wndAvg;
    private String wndGust;
    private double prmsl;
    private double prmslTrend;
    private double sun;


    public SynopData(String date, String time, double tmp, double dpt, int hr, String wndDir, String wndSpd, String wndAvg, String wndGust) {
        this.date = date;
        this.time = time;
        this.tmp = tmp;
        this.dpt = dpt;
        this.hr = hr;
        this.wndDir = wndDir;
        this.wndSpd = wndSpd;
        this.wndAvg = wndAvg;
        this.wndGust = wndGust;
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


    public String getWndSpd() {
        return wndSpd;
    }

    public String getWndDir() {
        return wndDir;
    }

    public String getWndAvg() {
        return wndAvg;
    }

    public String getWndGust() {
        return wndGust;
    }

    public int getHr() {
        return hr;
    }
}
