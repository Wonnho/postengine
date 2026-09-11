package com.postengine.post.api;

import com.postengine.post.controller.entity.Article;
import com.postengine.post.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Article show(@PathVariable("id") Long id, Model model){

        return "";

    }
    // PATCH
    //DELETE
}
