package com.umcserver.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class TestController {

    @GetMapping(value = "api/test")
    public String test() {
        return "hello~";
    }

}
