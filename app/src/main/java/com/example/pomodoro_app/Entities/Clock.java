package com.example.pomodoro_app.Entities;

public class Clock {
    private int minute;
    private int background;
    private String name;

    public Clock(int minute,String name,int background) {
        this.minute = minute;
        this.background = background;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}
