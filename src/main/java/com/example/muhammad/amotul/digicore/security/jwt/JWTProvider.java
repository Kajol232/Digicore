package com.example.muhammad.amotul.digicore.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JWTProvider {
    @Value("$(jwt.secret)")
    private String secret;

    public String generateToken(String accountNumber) {
        Date date = Date.from(LocalDate.now().plusDays(15).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder().setSubject(accountNumber).setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e.getClass() + ": " + e.getMessage());
        }
        return false;
    }

    public String getAccountNumberFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
