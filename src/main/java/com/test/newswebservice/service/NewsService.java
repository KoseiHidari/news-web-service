package com.test.newswebservice.service;

import com.test.newswebservice.model.News;
import com.test.newswebservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    //создание новой новости
    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    //создание нескольких новых новостей
    public List<News> saveAllNews(List<News> allNews) {
        return newsRepository.saveAll(allNews);
    }

    //получение всей информации новостей
    public Page<News> getAllNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    //получение одной новости по ID
    public News getNewsByID(Integer id) {
        return newsRepository.findById(id).orElse(null);
    }

    //удаление новости по ID
    public boolean deleteNews(Integer id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //изменение новости по ID
    public boolean updateByID (News news, Integer id) {
        if (newsRepository.existsById(id)) {
            News existingNews = newsRepository.findById(id).orElse(null);
            news.setId(id);
            existingNews.setText(news.getText());
            existingNews.setTitle(news.getTitle());
            existingNews.setDate(news.getDate());
            newsRepository.save(news);
            return true;
        }
        return false;
    }

}
