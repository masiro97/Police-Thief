package com.example.user.masiro;

/**
 * Created by User on 2017-05-28.
 */

public class Item {

    Double latitude = .0;
    Double longitude = .0;
    int point = 0;
    int index = 0; //생성된 몇번째 아이템인지,,, 이거 sqlite로 데이터 베이스 생성하자!!

    public Item(Double latitude, Double longitude, int point, int index) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.point = point;
        this.index = index;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
