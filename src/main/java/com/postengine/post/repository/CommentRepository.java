package com.postengine.post.repository;

import com.postengine.post.controller.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value="SELECT * FROM comment WHERER article_id=:articleId",nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);
}
