package com.postengine.post.api;

import com.postengine.post.controller.dto.CommentDto;
import com.postengine.post.controller.entity.Comment;
import com.postengine.post.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;
    // 1. retrieve comments
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> Comments(@PathVariable("articleId") Long articleId) {
           List<CommentDto>  dtos=commentService.comments(articleId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 2. create comments
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable("articleId") Long articleId,
                             @RequestBody CommentDto dto) {
      CommentDto  commentDto=commentService.createComment(articleId,dto);

        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }
    // 3. update comments
    // 4. delete comments

}
