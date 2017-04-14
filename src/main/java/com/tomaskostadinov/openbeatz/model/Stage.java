package com.tomaskostadinov.openbeatz.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tomas on 05.04.17
 */

public class Stage implements Serializable {

    private String title;
    private Integer id;
    private String background;
    private List<Artist> artists = null;

    /**
     * No args constructor for use in serialization
     */
    public Stage() {
    }

    /**
     * @param id
     * @param artists
     * @param title
     * @param background
     */
    public Stage(String title, Integer id, String background, List<Artist> artists) {
        super();
        this.title = title;
        this.id = id;
        this.background = background;
        this.artists = artists;
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

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void addArtist(Artist a) {
        this.artists.add(a);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getNextArtistTitle() {
        List<Artist> a = this.artists;
        if (a != null) {
            if (a.size() >= 0) {
                return a.get(0).getTitle();
            }
        }
        return "Error";

    }


}
