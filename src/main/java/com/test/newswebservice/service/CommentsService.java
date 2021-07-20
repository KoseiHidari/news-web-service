package com.test.newswebservice.service;

import com.test.newswebservice.entity.Comments;
import com.test.newswebservice.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;

    //добавление одного комментария
    public Comments addComments(Comments comment) {
        return commentsRepository.save(comment);
    }

    //добавление нескольких комментариев
    public List<Comments> addAllComments(List<Comments> allComments) {
        return commentsRepository.saveAll(allComments);
    }

    //получение коментария по ID
    public Comments getCommentsByID (Integer id) {
        return commentsRepository.findById(id).orElse(null);
    }

    //получение списка коментариев по ID новости
    public List<Comments> getCommentsByIdNews(Integer newsId) {
        List<Comments> comments = commentsRepository.findAllByNewsId(newsId);
        return comments;
    }

    //изменение комментария
    public boolean updateCommentsByID (Comments comment, Integer id) {
        if (commentsRepository.existsById(id)) {
            comment.setId(id);
            commentsRepository.save(comment);
            return true;
        }
        return false;
    }

    //для удаления по ID
    public boolean deleteComment(Integer id) {
        if (commentsRepository.existsById(id)) {
            commentsRepository.deleteById(id);
            return true;
        }
        return false;
    }

}


