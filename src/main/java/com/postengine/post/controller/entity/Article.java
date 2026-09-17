package com.postengine.post.controller.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String subject;
    @Column
    private String content;


    public void patch(Article article) {
        if(article.subject !=null) {
            this.subject= article.subject;
        }
        if(article.content !=null) {
            this.content= article.content;
        }
    }
}


