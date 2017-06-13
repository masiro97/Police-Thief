package com.example.user.masiro;

/**
 * Created by User on 2017-05-27.
 */

public class Person  {

    private int level;
    private int curpoint;
    private int totpoint;
    private int required_exp = (int) (200 * Math.pow(1.5,level));

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getRequired_exp() {
        return required_exp;
    }

    public void setRequired_exp(int required_exp) {
        this.required_exp = required_exp;
    }

    public int getCurpoint() {
        return curpoint;
    }

    public Person(int level, int curpoint, int totpoint) {
        this.level = level;
        this.curpoint = curpoint;
        this.totpoint = totpoint;
        this.required_exp = (int) (200 * Math.pow(1.5,level));
    }

    public void setCurpoint(int curpoint) {
        this.curpoint = curpoint;
    }

    public int getTotpoint() {
        return totpoint;
    }

    public void setTotpoint(int totpoint) {
        this.totpoint = totpoint;
    }
}
