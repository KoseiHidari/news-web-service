package com.test.newswebservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "comments")
public class Comments {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer newsId;
    private Date date;
    private String text;
    private String username;

    public Comments(){}

    public Comments(Integer id, Integer newsId, Date date, String text, String username) {
        this.id = id;
        this.newsId = newsId;
        this.date = date;
        this.text = text;
        this.username = username;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
