package com.mymusic.auth.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Token {
    private String access;
    private String refresh;

    public Token(String access, String refresh) {
        this.access = access;
        this.refresh = refresh;
    }
}
