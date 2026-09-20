package com.postengine.post.controller.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

//@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //대표키

    @Column
    private String nickname; // 대글 단 필명

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article; //해당 댓글의 부모 게시글

    @Column
    private String body; //댓글 본문
}
