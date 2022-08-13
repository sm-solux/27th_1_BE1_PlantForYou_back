package com.be1.plant4you.board.repository.custom;

import com.be1.plant4you.board.dto.response.CommentResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.be1.plant4you.auth.domain.QUser.user;
import static com.be1.plant4you.board.domain.QComment.comment;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentResponse> findCommentListByPostId(Long postId) {
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
        return parentList;
    }
}
