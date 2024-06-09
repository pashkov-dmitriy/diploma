package com.mymusic.auth;

import com.mymusic.auth.configuration.CustomUserDetails;
import com.mymusic.auth.configuration.JwtTokenProvider;
import com.mymusic.auth.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtTokenProviderTest {

    @Mock
    private User user;

    @Mock
    private CustomUserDetails userDetails;

    private JwtTokenProvider jwtTokenProvider;

    private final String SECRET = "mysecretkeymysecretkeymysecretkeymysecretkey";
    private final long ACCESS_EXPIRATION_IN_SEC = 3600; // 1 hour
    private final long REFRESH_EXPIRATION_IN_SEC = 86400; // 1 day

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtTokenProvider = new JwtTokenProvider(SECRET, ACCESS_EXPIRATION_IN_SEC, REFRESH_EXPIRATION_IN_SEC);
    }

    @Test
    void generateAccessToken_ShouldGenerateValidToken() {
        when(user.getUserId()).thenReturn(1L);
        when(user.getRole()).thenReturn("USER");

        String token = jwtTokenProvider.generateAccessToken(user);

        assertNotNull(token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
        assertEquals("1", claims.getSubject());
        assertEquals("USER", claims.get("ROLE"));
    }

    @Test
    void generateRefreshToken_ShouldGenerateValidToken() {
        when(user.getUserId()).thenReturn(1L);
        when(user.getRole()).thenReturn("USER");

        String token = jwtTokenProvider.generateRefreshToken(user);

        assertNotNull(token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
        assertEquals("1", claims.getSubject());
        assertEquals("USER", claims.get("ROLE"));
    }

    @Test
    void isValid_ShouldReturnTrueForValidToken() {
        when(user.getUserId()).thenReturn(1L);
        when(user.getRole()).thenReturn("ROLE_USER");
        String token = jwtTokenProvider.generateAccessToken(user);

        assertTrue(jwtTokenProvider.isValid(token, user));
    }

    @Test
    void isValid_ShouldReturnFalseForInvalidToken() {
        when(userDetails.getUser()).thenReturn(user);
        when(user.getUserId()).thenReturn(1L);

        String token = "invalid.token.here";

        assertFalse(jwtTokenProvider.isValid(token, user));
    }
}
