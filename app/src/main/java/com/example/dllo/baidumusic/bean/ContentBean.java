package com.example.dllo.baidumusic.bean;

/**
 * Created by dllo on 16/11/7.
 */
public class ContentBean {
    private String title;
    private String author;
    private String songId;

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
