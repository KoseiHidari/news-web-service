package com.test.newswebservice.controller;

import com.test.newswebservice.entity.News;
import com.test.newswebservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;

    // сопоставление POST запросов
    @PostMapping("/add_news")
    public ResponseEntity<?> addNews(@RequestBody News news){
        newsService.saveNews(news);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> addAllNews(@RequestBody List<News> allNews){
        newsService.saveAllNews(allNews);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // сопоставление GET запросов
    @GetMapping("/news")
    public ResponseEntity<List> allNews() {
        final List<News> allNews = newsService.getAllNews();
        return allNews != null
                ? new ResponseEntity<>(allNews, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<News> getNewsByID (@PathVariable Integer id) {
        final News news = newsService.getNewsByID(id);
        return news != null
                ? new ResponseEntity<>(news, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody News news) {
        final boolean updated = newsService.updateByID(news, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        final boolean deleted = newsService.deleteNews(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}