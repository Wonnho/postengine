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
//        if (article == null) {
//            throw new IllegalArgumentException("Article does not exist. You can't create a comment");
//        }
//        if (dto.getBody() == null) {
//            throw new IllegalArgumentException("Comment body is required");
//        }

        if (dto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("Comment body is required");
        }

        // 2.create entity and return
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto commentDto) {
        //예외 발생
      //  if(this.id!=commentDto.getId()) {
        if(!this.id.equals(commentDto.getId())) {

                throw new IllegalArgumentException("failed to update comment! DB id is not equal to id of updating object");
        }

        //객체 갱신

        if(commentDto.getNickname() !=null) {
            this.nickname=commentDto.getNickname();
        }
        if(commentDto.getBody()!=null) {
            this.body=commentDto.getBody();
        }
    }
}
