package com.tomaskostadinov.openbeatz.model;

/**
 * Created by tomas on 05.04.17
 */

import java.util.List;
public class NewsItemContainer {

    private Integer page;
    private Integer totalPages;
    private List<Message> messages = null;

    /**
     * No args constructor for use in serialization
     */
    public NewsItemContainer() {
    }

    /**
     * @param page
     * @param messages
     * @param totalPages
     */
    public NewsItemContainer(Integer page, Integer totalPages, List<Message> messages) {
        super();
        this.page = page;
        this.totalPages = totalPages;
        this.messages = messages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}