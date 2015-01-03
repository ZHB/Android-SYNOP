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

    UnitTmpBehavior unitTmpBehavior;
    UnitWndBehavior unitWndBehavior;


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

        // set default temperature to celcius
        this.unitTmpBehavior = new CelsiusTmp();
        this.unitWndBehavior = new KmhWnd();
    }

    public void setUnitTmpBehavior(UnitTmpBehavior unitTmpBehavior) {
        this.unitTmpBehavior = unitTmpBehavior;
    }

    public void setUnitWndBehavior(UnitWndBehavior unitWndBehavior) {
        this.unitWndBehavior = unitWndBehavior;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTmp() {

        try {
            double cTmp = Double.parseDouble(tmp);

            tmp = String.valueOf(unitTmpBehavior.convertTmp(cTmp));
        } catch (NumberFormatException e) {
            return tmp;
        }

        return tmp;
    }

    public String getDpt() {
        return dpt;
    }


    public String getWndSpd() {

        try {
            double cWndSpd = Double.parseDouble(wndSpd);

            wndSpd = String.valueOf(unitWndBehavior.convertWnd(cWndSpd));
        } catch (NumberFormatException e) {
            return wndSpd;
        }

        return wndSpd;
    }

    public String getWndDir() {
        return wndDir;
    }

    public String getWndAvg() {

        try {
            double cWndSpd = Double.parseDouble(wndAvg);

            wndAvg = String.valueOf(unitWndBehavior.convertWnd(cWndSpd));
        } catch (NumberFormatException e) {
            return wndAvg;
        }

        return wndAvg;
    }

    public String getWndGust() {
        try {
            double cWndSpd = Double.parseDouble(wndGust);

            wndGust = String.valueOf(unitWndBehavior.convertWnd(cWndSpd));
        } catch (NumberFormatException e) {
            return wndGust;
        }

        return wndGust;
    }

    public String getHr() {
        return hr;
    }
}
