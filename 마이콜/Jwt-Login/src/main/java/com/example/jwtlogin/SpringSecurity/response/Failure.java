package com.example.jwtlogin.SpringSecurity.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Failure implements Result{
    String msg;
}
