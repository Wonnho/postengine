package com.postengine.post.service;

import com.postengine.post.controller.dto.ArticleForm;
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

    @Test
    void show_success() {
        Long id=1L;
        //1. expected data
      Article  expected=new Article(id,"edit","edit method");
        //2. actual data
      Article   article=articleService.show(id);
        //3. compare and verify
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_fail() {
        Long id=1L;
        //1. expected data
        Article  expected=new Article(id,"edit","dummy data");
        //2. actual data
        Article   article=articleService.show(id);
        //3. compare and verify
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_failed_by_id() {
        Long id=11L;
        //1. expected data
        Article  expected=null;
        //2. actual data
        Article   article=articleService.show(id);
        //3. compare and verify
        assertEquals(expected,article);
    }


    @Test
    void create_success() {
          String subject="성공테스트";
          String content="새글 테스트를 성공할 경우와 실패할 경우를 구분해 보자";
        ArticleForm dto=new ArticleForm(subject,content,null);
        // 1.expected data
              Article expected=new Article(4L,subject,content);
        // 2. actual data
      Article   actual=articleService.create(dto);
        // 3. compare and verify
        assertEquals(expected.toString(),actual.toString());
    }
    @Test
    void create_failure() {
        Long id=4L;
        String subject="성공테스트";
        String content="새글 테스트를 성공할 경우와 실패할 경우를 구분해 보자";
        ArticleForm dto=new ArticleForm(subject,content,null);
        // 1.expected data
        Article expected=null;
        // 2. actual data
        Article   actual=articleService.create(dto);
        // 3. compare and verify
        assertEquals(expected,actual);
    }

    @Test
    void update_success() {


    }

    @Test
    void update_failure() {

    }

}
