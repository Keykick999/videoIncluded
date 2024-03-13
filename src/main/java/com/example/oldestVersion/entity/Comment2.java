package com.example.oldestVersion.entity;

import jakarta.persistence.*;

@Entity
public class Comment2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;
    @Column
    private String author;
    @Column
    private Long contentId;

    public Comment2(String text, String author, Long contentId) {
        this.text = text;
        this.author = author;
        this.contentId = contentId;
    }

    public Comment2() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author='" + author + '\'' +
                ", contentId=" + contentId +
                '}';
    }
}
