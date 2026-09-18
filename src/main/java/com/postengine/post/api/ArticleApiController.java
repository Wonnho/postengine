package com.postengine.post.api;

import com.postengine.post.controller.dto.ArticleForm;
import com.postengine.post.controller.entity.Article;
import com.postengine.post.repository.ArticleRepository;
import com.postengine.post.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {


    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/api/articles")
    //1Get
    public List<Article> index() {

        return articleService.index();
}
    @GetMapping("/api/articles/{id}")
    //1Get
    public Article show(@PathVariable("id") Long id) {

        return articleService.show(id);
    }
    //2. POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
       Article  created=articleService.create(dto);

         return (created !=null) ?
                 ResponseEntity.status(HttpStatus.OK).body(created):
                 ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable("id") Long id, @RequestBody ArticleForm dto) {
    Article  patched=articleService.update(id,dto);

        return (patched !=null) ?
        ResponseEntity.status(HttpStatus.OK).body(patched):
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable("id") Long id) {
       Article deleted=articleService.delete(id);

        return (deleted !=null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}