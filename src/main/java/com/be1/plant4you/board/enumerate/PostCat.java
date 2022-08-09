package com.be1.plant4you.board.enumerate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PostCat {

    QUES("질문"), INFO("정보"), CONV("사담");

    private final String value;

    //역직렬화
    @JsonCreator
    public static PostCat from(String value) {
        for (PostCat cat : PostCat.values()) {
            if (cat.getValue().equals(value)) {
                return cat;
            }
        }
        return null;
    }

    //직렬화
    @JsonValue
    public String getValue() {
        return value;
    }
}
