package com.be1.plant4you.board.domain;

import com.be1.plant4you.common.domain.BaseTimeEntity;
import com.be1.plant4you.auth.domain.User;
import com.be1.plant4you.board.enumerate.PostCat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "writer_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "post")
    private List<Comment> commentList = new ArrayList<>();

    @Enumerated(value = STRING)
    private PostCat cat;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Builder.Default
    @Column(nullable = false)
    private Long hits = 0L;

    @Builder.Default
    @Column(nullable = false)
    private Long likes = 0L;

    @Builder.Default
    @Column(nullable = false)
    private Long scraps = 0L;

    public void plusLikes() {
        likes += 1;
    }

    public void plusScraps() {
        scraps += 1;
    }

    public void minusLikes() {
        likes -= 1;
    }

    public void minusScraps() {
        scraps -= 1;
    }

    //연관관계 편의 메소드
    public void changeUser(User user) {
        this.user = user;
        user.getPostList().add(this);
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
