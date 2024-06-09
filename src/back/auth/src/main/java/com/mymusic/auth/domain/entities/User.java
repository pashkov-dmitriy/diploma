package com.mymusic.auth.domain.entities;

import com.mymusic.auth.domain.responses.AuthUserResponseDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class User {
    private Long userId;
    private String email;
    private String bcryptPassword;
    private String role = "ROLE_USER";

    public User(Long id, String email, String bcryptPassword) {
        this.userId = id;
        this.email = email;
        this.bcryptPassword = bcryptPassword;
    }

    public User(AuthUserResponseDto auth) {
        this.userId = auth.userId();
        this.email = auth.email();
        this.bcryptPassword = auth.passwordHash();
    }
}
