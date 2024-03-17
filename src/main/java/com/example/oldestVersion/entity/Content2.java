package com.example.oldestVersion.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "content2", indexes = {
        @Index(name = "idx_id", columnList = "id"),
        @Index(name = "idx_context", columnList = "context"),
        @Index(name = "idx_videoId", columnList = "videoId"),
        @Index(name = "idx_author", columnList = "author"),
        @Index(name = "idx_title", columnList = "title"),
        @Index(name = "idx_views", columnList = "views")
})
public class Content2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String context;
    @Column(nullable = false, unique = true)
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

    public Content2(Long id, String title, String author, String context, String videoId, int views) {
        this.id = id;
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
