package com.umc.board_practice.domain;

import com.umc.board_practice.dto.AuthorityDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;

    @Override
    public String getAuthority() {
        return authorityName;
    }

    public AuthorityDto toAuthorityDto() {
        return AuthorityDto.builder()
                .authorityName(this.authorityName)
                .build();
    }
}