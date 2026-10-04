package com.postengine.post.service;

import com.postengine.post.controller.dto.CommentDto;
import com.postengine.post.controller.entity.Article;
import com.postengine.post.controller.entity.Comment;
import com.postengine.post.repository.ArticleRepository;
import com.postengine.post.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream().map(c->CommentDto.createCommentDto(c)) // transform entity to DTO
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto createComment(Long articleId,CommentDto dto) {

        // 1.find an Article id to write comment
      Article article=articleRepository.findById(articleId)
              .orElseThrow(()->new IllegalArgumentException("찾는 게시글이 없습니다."));

      // 2. create a new Entity to write an existing article
          Comment   comment =Comment.createComment(dto,article);

        // 3. save created Entity to DB
         Comment  created=commentRepository.save(comment);
        // 4. transform saved Entity to DTO

        return CommentDto.createCommentDto(created);

    }

    @Transactional
    public CommentDto patchComment(CommentDto commentDto, Long id) {
      //1.댓글 조회 및 예외 발생
        Comment target=commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException(
                "failed to update comment!" + "No comment found")
        );
      //2. 댓글 수정
        target.patch(commentDto);
        // 3. DB로 갱신
    Comment patched=commentRepository.save(target); //commentRepository.save(target)는 Comment 엔티티를 반환
        //4. 댓글 entity를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(patched);

    }

    @Transactional
    public CommentDto delete(Long id) {
        //1. find a comment and exception
     Comment  deletedId=commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException(
             "failed to delete a comment" +"there is no such comment"
     ));
        // 2. delete comment
      commentRepository.delete(deletedId);
        // 3. transform deleted comment onto DTO and return it
        return CommentDto.createCommentDto(deletedId);
    }
}
