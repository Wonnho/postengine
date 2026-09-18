package com.postengine.post.service;

import com.postengine.post.controller.dto.ArticleForm;
import com.postengine.post.controller.entity.Article;
import com.postengine.post.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(@PathVariable Long id) {

        return articleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Article create(ArticleForm dto) {
        Article  article=dto.toEntity();
        if(article.getId()!=null) {
            return null;
        }
        return articleRepository.save(article);

    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity(); // request data

        Article target = articleRepository.findById(id).orElse(null); // DB data

        if (target == null) {
            return null;
        }
        target.patch(article); // merge request data into DB data

        return articleRepository.save(target); // save once
    }

    public Article delete(Long id) {

        Article target=articleRepository.findById(id).orElse(null);
        if(target==null) {
            return    null;
        }
        articleRepository.delete(target);
        return target;
    }
}
