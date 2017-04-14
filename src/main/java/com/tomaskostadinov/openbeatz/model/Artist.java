package com.tomaskostadinov.openbeatz.model;

/**
 * Created by tomas on 05.04.17
 */


import java.io.Serializable;

public class Artist implements Serializable{

    private String title;
    private Integer id;
    private String image;
    private String thumbnail;
    private String start;
    private String end;
    private String day;
    private Integer dayId;
    private String date;
    private String stage;
    private Integer stageId;

    /**
     * No args constructor for use in serialization
     */
    public Artist() {
    }

    /**
     * @param stageId
     * @param id
     * @param title
     * @param thumbnail
     * @param dayId
     * @param start
     * @param image
     * @param day
     * @param date
     * @param stage
     * @param end
     */
    public Artist(String title, Integer id, String image, String thumbnail, String start, String end, String day, Integer dayId, String date, String stage, Integer stageId) {
        super();
        this.title = title;
        this.id = id;
        this.image = image;
        this.thumbnail = thumbnail;
        this.start = start;
        this.end = end;
        this.day = day;
        this.dayId = dayId;
        this.date = date;
        this.stage = stage;
        this.stageId = stageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }



}