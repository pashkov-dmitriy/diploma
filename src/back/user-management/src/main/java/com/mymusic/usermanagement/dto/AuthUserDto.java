package com.mymusic.usermanagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthUserDto {
    private Long userId;
    private String email;
    private String passwordHash;
}
