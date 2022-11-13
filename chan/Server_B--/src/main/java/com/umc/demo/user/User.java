package com.umc.demo.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    private String email;

    private String password;
    private String name;
    private String profile_img;
    private String text;

    @Builder
    public User(int user_id, String email, String password, String name, String profile_img, String text) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.profile_img = profile_img;
        this.text = text;
    }
}
