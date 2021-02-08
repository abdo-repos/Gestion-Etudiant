package com.ensa.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class JwtUtil {

    private final JwtConfig config;
    private final Algorithm algorithm;

    public JwtUtil(JwtConfig jwtConfig) {
        this.config = jwtConfig;
        this.algorithm = Algorithm.HMAC256(this.config.getSecretKey());
    }

    public String extractEmail(String token) throws JWTVerificationException {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getSubject();
    }

    public Collection<GrantedAuthority> extractRoles(String token) throws JWTVerificationException {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        return Stream.of(roles)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private DecodedJWT getDecodedJWT(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        return jwtVerifier.verify(token);
    }

    private Boolean isTokenExpired(String token) {
        DecodedJWT decodedJWT = getDecodedJWT(token);
        return decodedJWT.getExpiresAt().before(new Date());
    }

    public String generateToken(UserDetails details) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, details, 30);
    }

    public String generateRefreshToken(UserDetails details) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, details, 24 * 60);
    }

    private String createToken(Map<String, Object> claims, UserDetails details, long dure) {
        return JWT.create().withSubject(details.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + dure * 60 * 1000))
                .withClaim("roles", details.getAuthorities()
                        .stream().map(au -> au.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public Algorithm getAlgorithm(){
        return this.algorithm;
    }

}
