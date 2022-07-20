package com.be1.plant4you.domain.board;

import com.be1.plant4you.common.BaseTimeEntity;
import com.be1.plant4you.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Scrap extends BaseTimeEntity {

    @EmbeddedId
    private ScrapId scrapId = new ScrapId();

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
    public Scrap(User user, Post post) {
        this.user = user;
        user.getScrapList().add(this);
        this.post = post;
    }
}
