package com.mymusic.auth.services;

import com.mymusic.auth.clients.UserClient;
import com.mymusic.auth.configuration.CustomUserDetails;
import com.mymusic.auth.domain.entities.User;
import com.mymusic.auth.exceptions.BadRequestException;
import com.mymusic.auth.exceptions.NotFoundException;
import com.mymusic.auth.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            var response = userClient.getAuthCredits(username);
            User user = new User(response.getBody());
            return new CustomUserDetails(user);
        }
        catch (NotFoundException | BadRequestException | UnauthorizedException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
