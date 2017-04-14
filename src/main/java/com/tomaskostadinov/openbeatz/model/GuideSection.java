package com.tomaskostadinov.openbeatz.model;

import java.io.Serializable;

/**
 * Created by tomas on 06.04.17
 */

public class GuideSection implements Serializable {


    private Integer id;
    private String title, content;

    public GuideSection(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
