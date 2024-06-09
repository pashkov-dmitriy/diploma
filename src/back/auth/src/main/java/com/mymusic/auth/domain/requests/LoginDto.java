package com.mymusic.auth.domain.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
