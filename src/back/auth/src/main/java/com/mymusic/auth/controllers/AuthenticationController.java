package com.mymusic.auth.controllers;

import com.mymusic.auth.domain.entities.Token;
import com.mymusic.auth.domain.requests.LoginDto;
import com.mymusic.auth.services.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login, HttpServletResponse response) {
        try {
            Token tokens = authenticationService.login(login.getEmail(), login.getPassword());
            response.addHeader("Authorization", "Bearer " + tokens.getAccess());
            response.addCookie(
                    new Cookie("Refresh", tokens.getRefresh())
            );
            return ResponseEntity.noContent().build();
        }
        catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch (BadCredentialsException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
