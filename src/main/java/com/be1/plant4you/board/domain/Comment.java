package com.be1.plant4you.board.domain;

import com.be1.plant4you.common.domain.BaseTimeEntity;
import com.be1.plant4you.auth.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "writer_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @Builder.Default
    @OneToMany(mappedBy = "parent")
    private List<Comment> childList = new ArrayList<>();

    @Column(nullable = false, length = 500)
    private String content;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isDelete = false;

    public void changeContent(String content) {
        this.content = content;
    }

    public void deleteComment2() {
        this.isDelete = true;
    }

    //연관관계 편의 메소드
    public void changeUser(User user) {
        this.user = user;
        user.getCommentList().add(this);
    }

    public void changePost(Post post) {
        this.post = post;
        post.getCommentList().add(this);
    }

    public void changeParent(Comment parent) {
        this.parent = parent;
        parent.getChildList().add(this);
    }
}