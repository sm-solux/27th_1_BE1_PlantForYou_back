package com.be1.plant4you.exception.plant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class PlantException extends RuntimeException {

    private final PlantErrorCode errorCode;
}
