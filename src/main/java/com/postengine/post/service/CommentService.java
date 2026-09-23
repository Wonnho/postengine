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
//        //1. retrieve all comments data
//       List<Comment>  comments=commentRepository.findByArticleId(articleId);
//        //2. transform Entity to DTO
//       List<CommentDto> dtos=new ArrayList<CommentDto>();
//        for(int k=0;k<comments.size();k++) {
//        Comment  c=comments.get(k); // pick one by one from comments
//          CommentDto  dto=CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//        //3. return output
//        return  dtos;

        // alternative syntax: stream

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
}
