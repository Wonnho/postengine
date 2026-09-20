package com.postengine.post.service;

import com.postengine.post.controller.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;

    @Test
    void index() {

        //1. expected data
       Article a=new Article(1L,"edit","edit method");
        Article b=new Article(2L,"dummy","dummy data");
        Article c=new Article(3L,"update","action=/articles/update");

        List<Article> expected=new ArrayList<Article>(Arrays.asList(a,b,c));
        // 2. real data
        List<Article> articles =articleService.index();
        // 3. compare and verify
            assertEquals(expected.toString(),articles.toString());

    }
}
