package com.be1.plant4you.auth.domain;

import com.be1.plant4you.common.domain.BaseTimeEntity;
import com.be1.plant4you.board.domain.Comment;
import com.be1.plant4you.board.domain.Likes;
import com.be1.plant4you.board.domain.Post;
import com.be1.plant4you.board.domain.Scrap;
import com.be1.plant4you.auth.oauth.enumerate.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Likes> likesList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Scrap> scrapList = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(length = 50)
    private String nickName;

    private String imageUrl;

    @Enumerated(value = STRING)
    @Column(nullable = false)
    private Provider provider;

    @Column(nullable = false)
    private String providerId;

    public void update(String email, String name, String imageUrl, String providerId) {
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
        this.providerId = providerId;
    }
}