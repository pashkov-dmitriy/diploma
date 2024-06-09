package com.mymusic.catalog.handlers;

import com.mymusic.catalog.exceptions.GenericClientException;
import com.mymusic.catalog.exceptions.ResourceNotFoundException;
import com.mymusic.catalog.exceptions.ServiceUnavailableException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@Component
public class CustomResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().isError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String body = StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8);

        switch (response.getStatusCode()) {
            case NOT_FOUND:
                throw new ResourceNotFoundException(body);
            case SERVICE_UNAVAILABLE:
                throw new ServiceUnavailableException(body);
            default:
                throw new GenericClientException(body);
        }
    }
}
