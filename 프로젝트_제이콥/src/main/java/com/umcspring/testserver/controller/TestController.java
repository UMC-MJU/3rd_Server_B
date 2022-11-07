package com.umcspring.testserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "api test";
    }

    @GetMapping("/test2")
    public String test2(){
        return "hi jacob";
    }
}
