package com.mymusic.auth.domain.responses;

public record AuthUserResponseDto(
        Long userId,
        String email,
        String passwordHash
) {
}