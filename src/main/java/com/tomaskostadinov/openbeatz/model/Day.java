package com.tomaskostadinov.openbeatz.model;

/**
 * Created by tomas on 06.04.17
 */

public class Day {

    private String day, date, last_change, stages_url;

    public Day(String day, String date, String last_change, String stages_url) {
        this.day = day;
        this.date = date;
        this.last_change = last_change;
        this.stages_url = stages_url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLast_change() {
        return last_change;
    }

    public void setLast_change(String last_change) {
        this.last_change = last_change;
    }

    public String getStages_url() {
        return stages_url;
    }

    public void setStages_url(String stages_url) {
        this.stages_url = stages_url;
    }

    public String getDay() {

        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
