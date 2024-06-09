package com.mymusic.auth.clients;

import com.mymusic.auth.clients.handlers.RetreiveMessageErrorDecoder;
import com.mymusic.auth.configuration.FeignClientConfig;
import com.mymusic.auth.domain.responses.AuthUserResponseDto;
import com.mymusic.auth.exceptions.BadRequestException;
import com.mymusic.auth.exceptions.NotFoundException;
import com.mymusic.auth.exceptions.RestApiServerException;
import com.mymusic.auth.exceptions.UnauthorizedException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-management", configuration = RetreiveMessageErrorDecoder.class)
public interface UserClient {
    @GetMapping("/internal/users")
    ResponseEntity<AuthUserResponseDto> getAuthCredits(@RequestParam("email") String email)
            throws NotFoundException, BadRequestException, UnauthorizedException, RestApiServerException;
}
