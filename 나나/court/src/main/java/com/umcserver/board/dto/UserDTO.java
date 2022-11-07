package com.umcserver.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private int idx;
    private String ID;
    private String password;
    private String name;
    private String email;
}
