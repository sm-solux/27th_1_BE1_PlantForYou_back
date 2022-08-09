package com.be1.plant4you.plant.enumerate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Func {

    AIR("공기정화"), DECO("관상용"), FOOD("식용");

    private final String value;

    //역직렬화
    @JsonCreator
    public static Func from(String value) {
        for (Func func : Func.values()) {
            if (func.getValue().equals(value)) {
                return func;
            }
        }
        return DECO;
    }

    //직렬화
    @JsonValue
    public String getValue() {
        return value;
    }
}
