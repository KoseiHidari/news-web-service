package com.test.newswebservice.repository;

import com.test.newswebservice.model.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    Page<Comments> findAllByNewsId(Integer newsId, Pageable pagination);
}