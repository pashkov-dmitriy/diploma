package com.mymusic.auth.configuration;

import com.mymusic.auth.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    private final String SECRET;
    @Getter
    private final long ACCESS_EXPIRATION_IN_SEC;
    @Getter
    private final long REFRESH_EXPIRATION_IN_SEC;

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-expiration}") long accessExp,
            @Value("${jwt.refresh-expiration}") long refreshExp
    ) {
        this.SECRET = secret;
        this.ACCESS_EXPIRATION_IN_SEC = accessExp;
        this.REFRESH_EXPIRATION_IN_SEC = refreshExp;
    }

    private Key getSecretKey() {
        byte[] byteKey = Decoders.BASE64.decode(this.SECRET);
        return Keys.hmacShaKeyFor(byteKey);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, REFRESH_EXPIRATION_IN_SEC);
    }

    public String generateAccessToken(User user) {
        return generateToken(user, ACCESS_EXPIRATION_IN_SEC);
    }

    public String generateToken(User user, Long expTime) {
        Claims claims = Jwts.claims()
                .setSubject(user.getUserId().toString());

        claims.put("ROLE", user.getRole());

        var now = Instant.now();
        claims.setIssuedAt(Date.from(now));
        claims.setExpiration(Date.from(now.plus(expTime, TimeUnit.SECONDS.toChronoUnit())));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(getSecretKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Long extractUserId(String token) {
        return Long.valueOf(extractClaim(token, Claims::getSubject));
    }

    public boolean isValid(String token, User user) {
        try {
            Long userId = user.getUserId();
            return (Objects.equals(userId, extractUserId(token))) && isExpired(token);
        }
        catch (MalformedJwtException e) {
            return false;
        }
    }
    
    private boolean isExpired(String token) {
        var exp = extractClaim(token, Claims::getExpiration);
        var now = new Date();
        return now.before(exp);
    }
}
