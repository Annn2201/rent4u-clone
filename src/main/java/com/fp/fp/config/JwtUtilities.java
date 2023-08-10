package com.fp.fp.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
public class JwtUtilities {
    @Value("${secret}")
    private String secret;
    @Value("${jwtExpiration}")
    private Long jwtExpiration;
    private static final String TOKEN_COOKIE_NAME = "jwt";
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolve) {
        final Claims claims = extractClaims(token);
        return claimsResolve.apply(claims);
    }
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public String generateToken(String username, List<String> userRoles) {
        return Jwts.builder().setSubject(username).claim("userRoles", userRoles).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid: {}", e);
        }
        return false;
    }
    public String extractToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null){
            for (Cookie cookie: cookies) {
                if (TOKEN_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
