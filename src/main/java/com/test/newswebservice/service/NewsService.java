package com.test.newswebservice.service;

import com.test.newswebservice.entity.News;
import org.springframework.web.bind.annotation.*;

@RestController
public interface NewsService {
    void create(News news);//создание новости
    News read(Integer id);//чрение новости по ID
    boolean update(News news, Integer id);// редактирование новости по ID в соответствии с новым news
    boolean delete(Integer id); // удаление новости по ID
}
