package com.mymusic.usermanagement.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserRegisterRequestDto {
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String description;
    private String profilePic;
}
