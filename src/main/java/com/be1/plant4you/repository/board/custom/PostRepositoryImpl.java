package com.be1.plant4you.repository.board.custom;

import com.be1.plant4you.dto.response.board.*;
import com.be1.plant4you.enumerate.board.PostCat;
import com.querydsl.core.Tuple;
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
import java.util.Map;
import java.util.stream.Collectors;

import static com.be1.plant4you.domain.QComment.*;
import static com.be1.plant4you.domain.QLikes.*;
import static com.be1.plant4you.domain.QPost.*;
import static com.be1.plant4you.domain.QScrap.*;
import static com.be1.plant4you.domain.QUser.*;

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
        PostResponse postResponse = queryFactory
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

        //해당 게시글의 댓글 조회 (대댓글 제외)
        List<CommentResponse> parentList = queryFactory
                .select(
                        Projections
                                .constructor(CommentResponse.class,
                                        comment.id,
                                        user.nickName,
                                        comment.content,
                                        comment.createdDate,
                                        comment.isDelete
                                )
                )
                .from(comment)
                .join(comment.user, user)
                .where(comment.post.id.eq(postId),
                        comment.parent.isNull())
                .orderBy(comment.createdDate.asc())
                .fetch();
        List<Long> parentIds = parentList.stream().map(CommentResponse::getCommentId).collect(Collectors.toList());

        //해당 댓글의 대댓글 조회
        List<Tuple> childLists = queryFactory
                .select(
                        Projections
                                .constructor(CommentResponse.class,
                                        comment.id,
                                        user.nickName,
                                        comment.content,
                                        comment.createdDate,
                                        comment.isDelete
                                ),
                        comment.parent.id
                )
                .from(comment)
                .join(comment.user, user)
                .where(comment.parent.id.in(parentIds))
                .orderBy(comment.createdDate.asc())
                .fetch();
        //댓글 별로 대댓글 그룹핑
        Map<Long, List<CommentResponse>> childListMap = childLists.stream().collect(
                Collectors.groupingBy(
                        t -> t.get(comment.parent.id),
                        Collectors.mapping(
                                t -> t.get(0, CommentResponse.class),
                                Collectors.toList()
                        )
                )
        );

        for (CommentResponse parent : parentList) {
            Long parentId = parent.getCommentId();
            if (childListMap.get(parentId) != null) {
                parent.changeChildList(childListMap.get(parentId));
            }
        }

        assert postResponse != null;
        postResponse.changeCommentList(parentList);

        return postResponse;
    }

    @Override
    public Page<PostListResponse> findAllByUserCmt(Long userId, Pageable pageable) {
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