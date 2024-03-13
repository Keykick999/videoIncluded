package com.example.oldestVersion.entity;

import jakarta.persistence.*;

@Entity
public class Content2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String context;
    @Column
    private String videoId;
    @Column
    private int views;

    public Content2(){}

    public Content2(String title, String author, String context, String videoId, int view) {
        this.title = title;
        this.author = author;
        this.context = context;
        this.videoId = videoId;
        this.views = views;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getViews() {
        return views;
    }

    public void setView(int view) {
        this.views = views;
    }

    public void plusView() {
        this.views++;
    }

    @Override
    public String toString() {
        return "Content2{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", context='" + context + '\'' +
                ", videoId='" + videoId + '\'' +
                ", views=" + views +
                '}';
    }
}
