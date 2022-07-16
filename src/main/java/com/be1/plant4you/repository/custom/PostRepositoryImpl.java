package com.be1.plant4you.repository.custom;

import com.be1.plant4you.dto.response.PostResponse;
import com.be1.plant4you.dto.response.PostShortResponse;
import com.be1.plant4you.enumerate.PostCat;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.be1.plant4you.domain.QComment.*;
import static com.be1.plant4you.domain.QLikes.*;
import static com.be1.plant4you.domain.QPost.*;
import static com.be1.plant4you.domain.QScrap.*;
import static com.be1.plant4you.domain.QUser.*;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostShortResponse> findAllByCat(PostCat cat) {
        return queryFactory
                .select(
                        Projections.constructor(PostShortResponse.class,
                                post.id,
                                post.cat,
                                post.title,
                                post.hits,
                                post.likes,
                                post.scraps
                        )
                )
                .from(post)
                .where(post.cat.eq(cat))
                .orderBy(post.createdDate.desc())
                .fetch();
    }

    @Override
    public List<PostShortResponse> findAllOrderByCreatedDate() {
        return queryFactory
                .select(
                        Projections.constructor(PostShortResponse.class,
                                post.id,
                                post.cat,
                                post.title,
                                post.hits,
                                post.likes,
                                post.scraps
                        )
                )
                .from(post)
                .orderBy(post.createdDate.desc())
                .fetch();
    }

    @Override
    public List<PostShortResponse> findAllOrderByLikes() {
        return queryFactory
                .select(
                        Projections.constructor(PostShortResponse.class,
                                post.id,
                                post.cat,
                                post.title,
                                post.hits,
                                post.likes,
                                post.scraps
                        )
                )
                .from(post)
                .orderBy(post.likes.desc())
                .fetch();
    }

    @Override
    public List<PostShortResponse> findAllByWriterId(Long writerId) {
        return queryFactory
                .select(
                        Projections.constructor(PostShortResponse.class,
                                post.id,
                                post.cat,
                                post.title,
                                post.hits,
                                post.likes,
                                post.scraps
                        )
                )
                .from(post)
                .where(post.user.id.eq(writerId))
                .orderBy(post.createdDate.desc())
                .fetch();
    }

    @Override
    public PostResponse findDtoById(Long userId, Long postId) {
        return queryFactory
                .select(
                        Projections.constructor(PostResponse.class,
                                post.id,
                                user.nickName,
                                post.createdDate,
                                post.cat,
                                post.title,
                                post.content,
                                post.hits,
                                post.likes,
                                post.scraps,
                                new CaseBuilder()
                                        .when(
                                                JPAExpressions
                                                        .selectFrom(likes)
                                                        .where(likes.user.id.eq(userId),
                                                                likes.post.id.eq(postId))
                                                        .exists()
                                        ).then(true)
                                        .otherwise(false),
                                new CaseBuilder()
                                        .when(
                                                JPAExpressions
                                                        .selectFrom(scrap)
                                                        .where(scrap.user.id.eq(userId),
                                                                scrap.post.id.eq(postId))
                                                        .exists()
                                        ).then(true)
                                        .otherwise(false)
                        )
                )
                .from(post)
                .join(post.user, user)
                .where(post.id.eq(postId))
                .fetchOne();
    }

    @Override
    public List<PostShortResponse> findAllByUserCmt(Long userId) {
        return queryFactory
                .select(
                        Projections
                                .constructor(PostShortResponse.class,
                                        post.id,
                                        post.cat,
                                        post.title,
                                        post.hits,
                                        post.likes,
                                        post.scraps
                                )
                ).distinct()
                .from(comment)
                .join(comment.post, post)
                .where(comment.user.id.eq(userId),
                        comment.isDelete.eq(false))
                .orderBy(comment.createdDate.desc())
                .fetch();
    }

    @Override
    public List<PostShortResponse> findAllByUserLikes(Long userId) {
        return queryFactory
                .select(
                        Projections
                                .constructor(PostShortResponse.class,
                                        post.id,
                                        post.cat,
                                        post.title,
                                        post.hits,
                                        post.likes,
                                        post.scraps
                                )
                )
                .from(likes)
                .join(likes.post, post)
                .where(likes.user.id.eq(userId))
                .orderBy(likes.createdDate.desc())
                .fetch();
    }

    @Override
    public List<PostShortResponse> findAllByUserScrap(Long userId) {
        return queryFactory
                .select(
                        Projections
                                .constructor(PostShortResponse.class,
                                        post.id,
                                        post.cat,
                                        post.title,
                                        post.hits,
                                        post.likes,
                                        post.scraps
                                )
                )
                .from(scrap)
                .join(scrap.post, post)
                .where(scrap.user.id.eq(userId))
                .orderBy(scrap.createdDate.desc())
                .fetch();
    }
}