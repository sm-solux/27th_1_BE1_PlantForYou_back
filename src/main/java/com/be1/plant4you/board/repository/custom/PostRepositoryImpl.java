package com.be1.plant4you.board.repository.custom;

import com.be1.plant4you.board.dto.response.PostListResponse;
import com.be1.plant4you.board.dto.response.PostResponse;
import com.be1.plant4you.board.enumerate.PostCat;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.be1.plant4you.auth.domain.QUser.user;
import static com.be1.plant4you.board.domain.QComment.comment;
import static com.be1.plant4you.board.domain.QLikes.likes;
import static com.be1.plant4you.board.domain.QPost.post;
import static com.be1.plant4you.board.domain.QScrap.scrap;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostListResponse> findAllByCat(PostCat cat, Pageable pageable) {
        List<PostListResponse> content = queryFactory
                .select(
                        Projections.constructor(PostListResponse.class,
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .where(post.cat.eq(cat));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<PostListResponse> findAllOrderByCreatedDate(Pageable pageable) {
        List<PostListResponse> content = queryFactory
                .select(
                        Projections.constructor(PostListResponse.class,
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<PostListResponse> findAllOrderByLikes(Pageable pageable) {
        List<PostListResponse> content = queryFactory
                .select(
                        Projections.constructor(PostListResponse.class,
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
                .orderBy(post.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<PostListResponse> findAllByWriterId(Long writerId, Pageable pageable) {
        List<PostListResponse> content = queryFactory
                .select(
                        Projections.constructor(PostListResponse.class,
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count())
                .from(post)
                .where(post.user.id.eq(writerId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public PostResponse findDtoById(Long userId, Long postId) {
        //게시글 조회 (댓글 제외)
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
                                post.user.id.eq(userId),
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
    public Page<PostListResponse> findAllByUserComment(Long userId, Pageable pageable) {
        List<PostListResponse> content = queryFactory
                .select(
                        Projections
                                .constructor(PostListResponse.class,
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
                        comment.isDelete.isFalse())
                .orderBy(comment.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(post.count()).distinct()
                .from(comment)
                .join(comment.post, post)
                .where(comment.user.id.eq(userId),
                        comment.isDelete.isFalse());

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<PostListResponse> findAllByUserLikes(Long userId, Pageable pageable) {
        List<PostListResponse> content = queryFactory
                .select(
                        Projections
                                .constructor(PostListResponse.class,
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(likes.count())
                .from(likes)
                .where(likes.user.id.eq(userId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<PostListResponse> findAllByUserScrap(Long userId, Pageable pageable) {
        List<PostListResponse> content = queryFactory
                .select(
                        Projections
                                .constructor(PostListResponse.class,
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(scrap.count())
                .from(scrap)
                .where(scrap.user.id.eq(userId));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}