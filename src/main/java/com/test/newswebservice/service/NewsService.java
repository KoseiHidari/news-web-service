package com.test.newswebservice.service;

import com.test.newswebservice.entity.News;
import com.test.newswebservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;

    //для POST методов
    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    //для всех
    public List<News> saveAllNews(List<News> allNews) {
        return newsRepository.saveAll(allNews);
    }

    //для GET методов
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public News getNewsByID(Integer id) {
        return newsRepository.findById(id).orElse(null);
    }

    //для удаления по ID
    public boolean deleteNews(Integer id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //для изменения по ID
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
