package com.be1.plant4you.dto.request.board;

import com.be1.plant4you.enumerate.board.PostCat;
import lombok.Getter;

@Getter
public class PostRequest {

    private PostCat cat;

    private String title;

    private String content;
}
