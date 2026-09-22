package com.postengine.post.repository;

import com.postengine.post.controller.entity.Article;
import com.postengine.post.controller.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;
    @Test
    @DisplayName("find article by Id")
    void findByArticleId() {
     //1.prepare input data
        Long articleId=4L;
        //2.actual data
      List<Comment> actual=commentRepository.findByArticleId(articleId);
        //3. expected data
       Article article=new Article(4L,"which cafe to prefer","Kings Coffee");
         Comment   a=new Comment(1L,article,"James","Paik Coffee");
        Comment   b=new Comment(2L,article,"Thorne","Tea & leafs");
      List<Comment>  expected=Arrays.asList(a,b);
        //4. comapre and verify
        assertEquals(expected.toString(),actual.toString());

    }

    @Test
    @DisplayName("find by Nickname")
    void findByNickname() {

        // case 1: find by nickname, James

        // 1. prepare input data
        String nickname="James";

        // 2. actual data
       List<Comment> actual=commentRepository.findByNickname(nickname);
        // 3. expected data
         Comment a=  new Comment(2L,new Article(4L,"which cafe to prefer","Kings Coffee"),nickname,"Paik Coffee");
         Comment b=  new Comment( 3L, new Article(5L,"What types of coffee to prefer?","Cafe Mocha"),nickname,"Dalgona coffee");
         Comment c=  new Comment(6L, new Article(6L,"Cycling","Bycle to TTuckSeom Park"),nickname,"Canoe");
         List<Comment> expected=Arrays.asList(a,b,c);
         // 4. compare and verify
        assertEquals(expected.toString(),actual.toString());


    }
}