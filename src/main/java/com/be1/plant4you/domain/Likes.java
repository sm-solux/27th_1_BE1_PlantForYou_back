package com.be1.plant4you.domain;

import com.be1.plant4you.common.LikesId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Likes {

    @EmbeddedId
    private LikesId likesId;

    @MapsId("postId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @MapsId("userId")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //연관관계 편의 메소드
    public void changeUser(User user) {
        this.user = user;
        user.getLikesList().add(this);
    }
}