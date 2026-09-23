package com.postengine.post.controller.entity;

import com.postengine.post.controller.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//@NoArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //대표키
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article; //해당 댓글의 부모 게시글
    @Column
    private String nickname; // 대글 단 필명
    @Column
    private String body; //댓글 본문


    public static Comment createComment(CommentDto dto, Article article) {
        //1.exception
        if (dto.getId() != null) {
            throw new IllegalArgumentException("No id should not exist in comment. You can't create a comment");
        }
        if (article == null) {
            throw new IllegalArgumentException("Article does not exist. You can't create a comment");
        }
        if (dto.getBody() == null) {
            throw new IllegalArgumentException("Comment body is required");
        }


        // 2.create entity and return
        return new Comment(
                null,
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }
}
