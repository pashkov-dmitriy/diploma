package com.mymusic.auth;

import com.mymusic.auth.configuration.CustomUserDetails;
import com.mymusic.auth.configuration.JwtTokenProvider;
import com.mymusic.auth.domain.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
class AuthApplicationTests {

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private JwtTokenProvider provider;

	@Test
	void contextLoads() {
	}

	@Test
	void tokenGenerates() {
		var parser = Jwts.parserBuilder()
				.setSigningKey(secret)
				.build();

		User user = new User(32L, "example@ema.il", "weruwruiwrywi");

		String token = provider.generateToken(user, provider.getACCESS_EXPIRATION_IN_SEC());
		Claims claims = parser.parseClaimsJws(token).getBody();
		assert Objects.equals(claims.getSubject(), user.getUserId().toString());
		assert Objects.equals(claims.get("ROLE", String.class), user.getRole());
	}
}
