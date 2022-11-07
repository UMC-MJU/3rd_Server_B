package com.umcspring.testserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @GetMapping("/board")
    public String getBoard(){
        return "board_gyehwan";
    }
}
