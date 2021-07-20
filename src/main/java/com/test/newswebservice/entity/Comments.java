package com.test.newswebservice.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "comments")
public class Comments {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer id_news;
    private Date date;
    private String text;
    private String username;

    public Comments(){}

    public Comments(Integer id, Integer id_news, Date date, String text, String username) {
        this.id = id;
        this.id_news = id_news;
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

    public Integer getId_news() {
        return id_news;
    }

    public void setId_news(Integer id_news) {
        this.id_news = id_news;
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
