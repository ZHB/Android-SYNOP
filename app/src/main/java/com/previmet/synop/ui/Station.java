package com.previmet.synop.ui;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Vince on 16.12.2014.
 */
public class Station implements Parcelable {

    private long id;
    private String name;
    private String country;
    private int elevation;

    // constructor that takes a Parcel and gives you an object populated with it's values
    // reading in the same order than parcel write
    public Station(Parcel in) {
        name = in.readString();
        country = in.readString();
        elevation = in.readInt();
    }

    public Station(String name, String country, int elevation) {
        this.name = name;
        this.country = country;
        this.elevation = elevation;
    }

    public Station(long id, String name, String country, int elevation) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.elevation = elevation;
    }

    public long getId() {
        return id;
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

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(country);
        dest.writeInt(elevation);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Station> CREATOR = new Parcelable.Creator<Station>() {
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        public Station[] newArray(int size) {
            return new Station[size];
        }
    };
}
