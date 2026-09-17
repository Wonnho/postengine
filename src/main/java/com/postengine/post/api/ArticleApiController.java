package com.postengine.post.api;

import com.postengine.post.controller.dto.ArticleForm;
import com.postengine.post.controller.entity.Article;
import com.postengine.post.repository.ArticleRepository;
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
    private ArticleRepository articleRepository;
    @GetMapping("/api/articles")
    //1Get
    public List<Article> index() {
    return articleRepository.findAll();
}
    @GetMapping("/api/articles/{id}")
    //1Get
    public Article show(@PathVariable("id") Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    //2. POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
       Article  article=dto.toEntity();
         return articleRepository.save(article);


    }
    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable("id") Long id, @RequestBody ArticleForm form) {
        Article article = form.toEntity();          // 1. request data
        Article target = articleRepository
                .findById(id)
                .orElse(null);                      // 2. existing DB data

        if (target == null) {                       // 3. safety check
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        target.patch(article);                      // 4. merge new data into old data

        Article updated = articleRepository.save(target); // 5. save merged result
        return ResponseEntity.status(HttpStatus.OK).body(updated);
     }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable("id") Long id) {
       Article target=articleRepository.findById(id).orElse(null);
       if(target==null) {
        return    ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
        articleRepository.delete(target);
       return ResponseEntity.status((HttpStatus.OK)).body(target);
    }
}
