package com.checkin.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate  = new Date();
        Date expireDate = new Date(currentDate.getTime()+ SecurityConstants.JWT_EXPIRATION);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key,SignatureAlgorithm.HS512).compact();
        return token;
    }

    public static Claims getEmailFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired");
            throw new AuthenticationCredentialsNotFoundException("Jwt was expired");
        } catch (MalformedJwtException | SignatureException | IllegalArgumentException e) {
            System.out.println("Invalid token");
            throw new AuthenticationCredentialsNotFoundException("Jwt was incorrect");
        }
    }



}
