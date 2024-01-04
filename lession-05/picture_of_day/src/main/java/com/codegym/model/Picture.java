package com.codegym.model;

import javax.persistence.*;

@Entity
@Table
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "author")
    private String authorName;
    private String comment;
    private Integer rate;
    private Integer likes;

    public Picture() {
    }

    public Picture(int id, String authorName, String comment, Integer rate, Integer likes) {
        this.id = id;
        this.authorName = authorName;
        this.comment = comment;
        this.rate = rate;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
