package com.be1.plant4you.dto.response;

import com.be1.plant4you.enumerate.PostCat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.*;

@Getter
public class PostResponse {

    private Long postId;

    private String writerName;

    @JsonFormat(pattern = "MM/dd HH:mm", shape = STRING)
    private LocalDateTime createdDate;

    private PostCat cat;

    private String title;

    private String content;

    private Long hits;

    private Long likes;

    private Long scraps;

    private Boolean isLikes;

    private Boolean isScrap;

    public PostResponse(Long postId, String writerName, LocalDateTime createdDate,
                        PostCat cat, String title, String content,
                        Long hits, Long likes, Long scraps,
                        Boolean isLikes, Boolean isScrap) {
        this.postId = postId;
        this.writerName = writerName;
        this.createdDate = createdDate;
        this.cat = cat;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.likes = likes;
        this.scraps = scraps;
        this.isLikes = isLikes;
        this.isScrap = isScrap;
    }

    //    private List<CommentResponse> commentList = new ArrayList<>();
}
