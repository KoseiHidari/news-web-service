package com.test.newswebservice.service;

import com.test.newswebservice.entity.Comments;
import com.test.newswebservice.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Comments> getCommentsByIdNews(Integer newsId, Pageable pagination) {
        Page<Comments> comments = commentsRepository.findAllByNewsId(newsId, pagination);
        return comments;
    }

    //изменение комментария
    public boolean updateCommentsByID (Comments comment, Integer id) {
        if (commentsRepository.existsById(id)) {
            Comments existingComment = commentsRepository.findById(id).orElse(null);
            comment.setId(id);
            comment.setNewsId(existingComment.getNewsId());
            existingComment.setText(comment.getText());
            existingComment.setUsername(comment.getUsername());
            existingComment.setDate(comment.getDate());
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


