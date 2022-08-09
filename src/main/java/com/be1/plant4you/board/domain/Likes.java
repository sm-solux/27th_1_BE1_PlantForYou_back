package com.be1.plant4you.board.domain;

import com.be1.plant4you.common.domain.BaseTimeEntity;
import com.be1.plant4you.auth.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Likes extends BaseTimeEntity {

    @EmbeddedId
    private LikesId likesId = new LikesId();

    @MapsId("userId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("postId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    //연관관계 편의 메소드
    @Builder
    public Likes(User user, Post post) {
        this.user = user;
        user.getLikesList().add(this);
        this.post = post;
    }
}