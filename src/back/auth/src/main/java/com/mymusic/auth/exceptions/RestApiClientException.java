package com.mymusic.auth.exceptions;

import lombok.Getter;

public class RestApiClientException extends RuntimeException {
    @Getter
    private int code;
    public RestApiClientException(String messg, int status) {
        super(messg);
        this.code = status;
    }
}
