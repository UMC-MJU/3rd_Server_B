package com.umc.board_practice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    private List<AuthorityDto> authorityDtoList;

//    public static UserDto from(User user) {
//        if(user == null) return null;
//
//        return UserDto.builder()
//                .username(user.getUsername())
//                .nickname(user.getNickname())
//                .authorityDtoSet(user.getAuthorities().stream()
//                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
//                        .collect(Collectors.toSet()))
//                .build();
//    }
}