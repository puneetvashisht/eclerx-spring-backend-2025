package com.pv.spring_boot_security_demo;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {

    private static final String SECRET_KEY = "a_very_long_and_complex_secret_key";
    private static final byte[] SECRET_KEY_BYTES = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
    

    // generate token
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            // .claims("roles", userDetails.getAuthorities()
            //     )
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY_BYTES), SignatureAlgorithm.HS256)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + (1000*60*60)))
            .compact();
	}

    // validate token
    public boolean isTokenValid(String token, UserDetails userDetails){
        return true;
    }


    // validate token expiry
}
