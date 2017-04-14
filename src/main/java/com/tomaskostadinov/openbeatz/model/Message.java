package com.tomaskostadinov.openbeatz.model;

/**
 * Created by tomas on 05.04.17
 */


public class Message {

    private String type;
    private String datetime;
    private String content;
    private String state;
    private String imageUrl;
    private Integer galleryId;
    private String galleryUrl;

    /**
     * No args constructor for use in serialization
     */
    public Message() {
    }

    /**
     * @param content
     * @param galleryId
     * @param imageUrl
     * @param galleryUrl
     * @param state
     * @param datetime
     * @param type
     */
    public Message(String type, String datetime, String content, String state, String imageUrl, Integer galleryId, String galleryUrl) {
        super();
        this.type = type;
        this.datetime = datetime;
        this.content = content;
        this.state = state;
        this.imageUrl = imageUrl;
        this.galleryId = galleryId;
        this.galleryUrl = galleryUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(Integer galleryId) {
        this.galleryId = galleryId;
    }

    public String getGalleryUrl() {
        return galleryUrl;
    }

    public void setGalleryUrl(String galleryUrl) {
        this.galleryUrl = galleryUrl;
    }

}