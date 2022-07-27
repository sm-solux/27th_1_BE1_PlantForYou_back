package com.be1.plant4you.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "연결 테스트용 API")
@RequestMapping("/api")
@RestController
public class TestController {

    @GetMapping("/test")
    public String hello() {
        return "hello world!";
    }
}
