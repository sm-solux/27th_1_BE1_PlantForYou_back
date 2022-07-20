package com.be1.plant4you.enumerate.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PostCat {

    QUES("질문"), INFO("정보"), CONV("사담");

    private final String value;

    PostCat(String value) {
        this.value = value;
    }

    //역직렬화
    @JsonCreator
    public static PostCat from(String value) {
        for (PostCat cat : PostCat.values()) {
            if (cat.getValue().equals(value)) {
                return cat;
            }
        }
        return CONV;
    }

    //직렬화
    @JsonValue
    public String getValue() {
        return value;
    }
}
