package com.be1.plant4you.dto.request;

import com.be1.plant4you.enumerate.PostCat;
import lombok.Getter;

@Getter
public class PostRequest {

    private PostCat cat;

    private String title;

    private String content;
}
