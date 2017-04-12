package com.tomaskostadinov.openbeatz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tomas on 05.04.17
 */

public class NewsItemContainer {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("last_change")
    @Expose
    private String lastChange;
    @SerializedName("stages")
    @Expose
    private List<Stage> stages = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public NewsItemContainer() {
    }

    /**
     *
     * @param day
     * @param date
     * @param stages
     * @param lastChange
     */
    public NewsItemContainer(String day, String date, String lastChange, List<Stage> stages) {
        super();
        this.day = day;
        this.date = date;
        this.lastChange = lastChange;
        this.stages = stages;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastChange() {
        return lastChange;
    }

    public void setLastChange(String lastChange) {
        this.lastChange = lastChange;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

}