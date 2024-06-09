package com.mymusic.usermanagement.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateUserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String description;
}
