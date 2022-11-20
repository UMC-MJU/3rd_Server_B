package com.umc.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

/*
https://velog.io/@dktlsk6/Spring으로-카카오-로그인-구현하기
 */

@Controller
@RequestMapping("/member")
public class KaKaoController {

    @Autowired
    KaKaoService kaKaoService;

    // 로그인 화면 (이미지)
    @GetMapping("/do")
    public String loginPage()
    {
        return "kakaoCI/login";
    }

    // 자동으로 리다이렉트 됨
    @GetMapping("/kakao")
    public String getCI(@RequestParam String code, Model model) throws IOException {
        System.out.println("code = " + code);
        String access_token = kaKaoService.getToken(code);
        Map<String, Object> userInfo = kaKaoService.getUserInfo(access_token);
        model.addAttribute("code", code);
        model.addAttribute("access_token", access_token);
        model.addAttribute("userInfo", userInfo);

        //ci는 비즈니스 전환후 검수신청 -> 허락받아야 수집 가능
        return "index";
    }
}