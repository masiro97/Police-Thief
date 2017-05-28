package com.example.user.masiro;

/**
 * Created by User on 2017-05-27.
 */

public class Person  {

    String ID = "";
    String Password = "";
    int point = 0;
    int total_point = 0;
    int index = -1; //저장된 정보의 인덱스 아이디 확인 할때 사용
    int count = 0; //잡은 횟수, 훔친 횟수
    int total_count = 0;
    int identity = -1; // identity = 0 (theif) identity = 1 (police)

    public Person(String ID, String password, int identity) {
        this.ID = ID;
        Password = password;
        this.point = 0;
        this.total_point = 0;
        this.index = -1;
        this.count = 0;
        this.total_count = 0;
        this.identity = identity;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setTotal_point(int total_point) {
        this.total_point = total_point;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return Password;
    }

    public int getPoint() {
        return point;
    }

    public int getTotal_point() {
        return total_point;
    }

    public int getIndex() {
        return index;
    }

    public int getCount() {
        return count;
    }

    public int getTotal_count() {
        return total_count;
    }

    public int getIdentity() {
        return identity;
    }
}
