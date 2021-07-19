package com.test.newswebservice.service;

import com.test.newswebservice.entity.News;
import com.test.newswebservice.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

//    private static final AtomicInteger NEWS_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(News news) {
        newsRepository.save(news);
    }

    @Override
    public News read(Integer id) {
        return newsRepository.getOne(id);
    }

    @Override
    public boolean update(News news, Integer id) {
        if (newsRepository.existsById(id)) {
            news.setId(id);
            newsRepository.save(news);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (newsRepository.existsById(id)) {
            newsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
