package com.umc.board_practice.domain;

import com.umc.board_practice.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto toUserDto() {
        return UserDto.builder()
                .password(this.getPassword())
                .name(this.getName())
                .build();
    }

}
