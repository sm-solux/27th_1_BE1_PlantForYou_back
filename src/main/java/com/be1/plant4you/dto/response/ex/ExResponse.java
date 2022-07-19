package com.be1.plant4you.dto.response.ex;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class ExResponse {

    private int status;
    private HttpStatus error;
    private String message;
    private String path;
}