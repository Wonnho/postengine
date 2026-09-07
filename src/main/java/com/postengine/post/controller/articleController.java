package com.postengine.post.controller;

import com.postengine.post.controller.dto.ArticleForm;
import com.postengine.post.controller.entity.Article;
import com.postengine.post.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class articleController {

    @Autowired
private ArticleRepository articleRepository;

@GetMapping("/articles/new")
    public String newArticleForm() {
    return "articles/new";
}

//    @GetMapping("/articles/{id}")
//    public String retrieve(@PathVariable("id") Long id) {
//        Article article = articleRepository.findById(id).orElse(null);
//        log.info("id={}", id);
//        return "";
//    }

    @PostMapping("/articles/create")
    public String createArticle(@ModelAttribute ArticleForm form){
    log.info(form.toString());
    //1.transform DTO onto Entity
        Article article=form.toEntity();
        log.info(article.toString());
    //2. save Entity to DB using repository
    Article  saved=articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles";
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Article articleEntity = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + id));

        model.addAttribute("article",articleEntity);
    return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

    //1, get all data
      List<Article> articleEntityList=articleRepository.findAll();
        // 2. register data to Model
        model.addAttribute("articleList",articleEntityList);
        // 3. set up view page
    return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable("id") Long id,Model model) {
   // get particular data from DB
    Article articleEntity=articleRepository.findById(id).orElse(null);
    //register data to model so that use it at view page
        model.addAttribute("article",articleEntity);
    // view page
    return "articles/edit";
    }
}
