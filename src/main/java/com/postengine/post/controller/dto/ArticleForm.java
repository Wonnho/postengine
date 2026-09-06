package com.postengine.post.controller.dto;

import com.postengine.post.controller.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class ArticleForm {
    private String subject;
    private String content;

    public Article toEntity() {

        return new Article(null, subject, content);
    }
}
