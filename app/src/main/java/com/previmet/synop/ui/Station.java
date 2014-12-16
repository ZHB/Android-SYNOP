package com.previmet.synop.ui;

/**
 * Created by Vince on 16.12.2014.
 */
public class Station {

    private String name;
    private String country;
    private int elevation;

    public Station() {

    }

    public Station(String name, String country, int elevation) {
        this.name = name;
        this.country = country;
        this.elevation = elevation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
}
