package com.example.ticketoffice.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    SecretKey Key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(String email){
        return Jwts.builder().claim("email", email).signWith(Key).setExpiration(getDate()).compact();
    }

    private Date getDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR,3);

        return calendar.getTime();
    }

    public boolean validateToken(String token){
        return getClaims(token).getExpiration().after(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(token).getBody();
    }
}
