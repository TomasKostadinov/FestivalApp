package com.tomaskostadinov.openbeatz.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomas on 06.04.17
 */

public class Guide implements Serializable {
    private String title, subtitle, icon, type, last_changed, sectionUrl;
    private Integer id;
    private List<GuideSection> sections;

    public Guide(String title, String subtitle, String icon, String type, String last_changed, String sectionUrl, Integer id) {
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
        this.type = type;
        this.last_changed = last_changed;
        this.sectionUrl = sectionUrl;
        this.id = id;
    }

    public Guide(String title, String subtitle, String icon, String type, Integer id, List<GuideSection> sections) {
        this.title = title;
        this.subtitle = subtitle;
        this.icon = icon;
        this.type = type;
        this.id = id;
        this.sections = sections;
    }

    public List<GuideSection> getSections() {
        return sections;
    }

    public void setSections(List<GuideSection> sections) {
        this.sections = sections;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLast_changed() {
        return last_changed;
    }

    public void setLast_changed(String last_changed) {
        this.last_changed = last_changed;
    }

    public String getSectionUrl() {
        return sectionUrl;
    }

    public void setSectionUrl(String sectionUrl) {
        this.sectionUrl = sectionUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
