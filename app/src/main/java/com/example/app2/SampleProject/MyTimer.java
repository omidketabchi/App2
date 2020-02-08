package com.example.app2.SampleProject;

import java.util.Timer;

public class MyTimer {
    private int hour;
    private int minute;
    private int second;

    Timer timer = new Timer();

    public MyTimer(int hour, int minute, int second) {
        setTime(hour, minute, second);
    }

    private void setHour(int hour) {

        if (hour < 24) {
            this.hour = hour;
        } else {
            this.hour = 0;
        }
    }

    private int getHour() {
        return this.hour;
    }

    private void setMinute(int minute) {
        if (minute < 60) {
            this.minute = minute;
        } else {
            this.minute = 0;
        }
    }

    private int getMinute() {
        return this.minute;
    }

    private void setSecond(int second) {

        if (second < 60) {
            this.second = second;
        } else {
            this.second = 0;
        }
    }

    private int getSecond() {
        return this.second;
    }

    private void setTime(int hour, int minute, int second) {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }
}
