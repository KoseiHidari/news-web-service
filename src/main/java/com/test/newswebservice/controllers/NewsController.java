package com.test.newswebservice.controllers;

import com.test.newswebservice.entity.News;
import com.test.newswebservice.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NewsController {
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping(value = "/news")
    public ResponseEntity<?> create(@RequestBody News news) {
        newsService.create(news);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/news/{id}")
    public ResponseEntity<News> read(@PathVariable(name = "id") Integer id) {
        final News news = newsService.read(id);

        return news != null
                ? new ResponseEntity<>(news, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/news/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Integer id, @RequestBody News news) {
        final boolean updated = newsService.update(news, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/news/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        final boolean deleted = newsService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
