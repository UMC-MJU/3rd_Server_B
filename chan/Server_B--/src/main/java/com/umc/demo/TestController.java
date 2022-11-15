package com.umc.demo;

import com.umc.demo.config.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public BaseResponse test() {
        // return "Hello world";
        return new BaseResponse<>("tt");
    }
}
