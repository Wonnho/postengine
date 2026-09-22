package com.postengine.post.service;

import com.postengine.post.controller.dto.CommentDto;
import com.postengine.post.controller.entity.Comment;
import com.postengine.post.repository.ArticleRepository;
import com.postengine.post.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        //1. retrieve all comments data
       List<Comment>  comments=commentRepository.findByArticleId(articleId);
        //2. transform Entity to DTO
       List<CommentDto> dtos=new ArrayList<CommentDto>();
        for(int k=0;k<comments.size();k++) {
        Comment  c=comments.get(k); // pick one by one from comments
          CommentDto  dto=CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        //3. return output
        return  dtos;
    }
}
