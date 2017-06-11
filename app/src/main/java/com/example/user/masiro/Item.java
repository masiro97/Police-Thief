package com.example.user.masiro;

/**
 * Created by User on 2017-06-11.
 */

public class Item {

    private String Geopoint;
    private int point;

    public String getGeopoint() {
        return Geopoint;
    }

    public void setGeopoint(String geopoint) {
        Geopoint = geopoint;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Item(String geopoint, int point) {
        Geopoint = geopoint;
        this.point = point;
    }
}
