package com.previmet.synop.ui;

/**
 * Created by Vince on 30.12.2014.
 */
public class SynopData {

    private String date;
    private String time;
    private String tmp;
    private double tmpMin;
    private double tmpMax;
    private String dpt;
    private String hr;
    private String wndDir;
    private String wndSpd;
    private String wndAvg;
    private String wndGust;
    private double prmsl;
    private double prmslTrend;
    private double sun;


    public SynopData(String date, String time, String tmp, String dpt, String hr, String wndDir, String wndSpd, String wndAvg, String wndGust) {
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

    public String getTmp() {
        return tmp;
    }

    public String getDpt() {
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

    public String getHr() {
        return hr;
    }
}
