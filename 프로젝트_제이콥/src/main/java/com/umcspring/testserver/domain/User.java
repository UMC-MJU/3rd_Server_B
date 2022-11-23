package com.umcspring.testserver.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;

    private String name;

    private String password;

    @OneToMany(mappedBy = "user")   //일대다 관계(유저는 여러개의 게시물을 가질 수 있다)
    private List<Post> posts = new ArrayList<>();
}