package com.be1.plant4you.dto.response;

import com.be1.plant4you.enumerate.PostCat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostShortResponse {

    private Long postId;

    private PostCat cat;

    private String title;

    private Long hits;

    private Long likes;

    private Long scraps;
}
