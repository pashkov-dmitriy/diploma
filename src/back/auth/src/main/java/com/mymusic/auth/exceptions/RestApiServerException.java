package com.mymusic.auth.exceptions;

import lombok.Getter;

public class RestApiServerException extends RuntimeException {
    @Getter
    private int code;
    public RestApiServerException(String messg, int code) {
        super(messg);
        this.code = code;
    }
}
