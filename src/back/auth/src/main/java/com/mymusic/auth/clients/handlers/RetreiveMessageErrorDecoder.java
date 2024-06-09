package com.mymusic.auth.clients.handlers;

import com.mymusic.auth.exceptions.*;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new BadRequestException("Bad Request");
            case 401:
                return new UnauthorizedException("Unauthorized");
            case 404:
                return new NotFoundException("Not found");
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
