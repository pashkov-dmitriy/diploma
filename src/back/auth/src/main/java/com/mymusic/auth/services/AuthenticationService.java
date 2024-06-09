package com.mymusic.auth.services;

import com.mymusic.auth.clients.UserClient;
import com.mymusic.auth.configuration.CustomUserDetails;
import com.mymusic.auth.configuration.JwtTokenProvider;
import com.mymusic.auth.domain.entities.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtTokenProvider jwtProvider;
    private final AuthenticationManager authManager;

    public Token login(String email, String password) throws BadCredentialsException, UsernameNotFoundException {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        String access = jwtProvider.generateAccessToken(user.getUser());
        String refresh = jwtProvider.generateRefreshToken(user.getUser());

        return new Token(access, refresh);
    }
}
