package com.test.newswebservice.controller;

import com.test.newswebservice.entity.Comments;
import com.test.newswebservice.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    // сопоставление POST запросов
    @PostMapping("/news/{id}/add_comment")
    public ResponseEntity<?> addComment(@PathVariable Integer id, @RequestBody Comments comment){
        comment.setNewsId(id);
        commentsService.addComments(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/add_all_comments")
    public ResponseEntity<?> addAllComments(@RequestBody List<Comments> allComments){
        commentsService.addAllComments(allComments);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // сопоставление GET запросов
    @GetMapping("/news/{id}/comments")
    public ResponseEntity<List> allCommentsByNews(@PathVariable Integer id) {
        final List<Comments> allComments = commentsService.getCommentsByIdNews(id);
        return allComments != null
                ? new ResponseEntity<>(allComments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comments> getCommentByID (@PathVariable Integer id) {
        final Comments comment = commentsService.getCommentsByID(id);
        return comment != null
                ? new ResponseEntity<>(comment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("update_comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id, @RequestBody Comments comment) {
        final boolean updated = commentsService.updateCommentsByID(comment, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("delete_comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        final boolean deleted = commentsService.deleteComment(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
