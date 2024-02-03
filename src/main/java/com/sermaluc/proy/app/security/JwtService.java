package com.sermaluc.proy.app.security;

import com.sermaluc.proy.app.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Integer expiration;

    public String generatedToken(User user) {

        Calendar cal = Calendar.getInstance(Locale.UK);
        Calendar cal1 = Calendar.getInstance(Locale.UK);
        cal1.setTime(cal.getTime());
        cal1.add(Calendar.HOUR, expiration);

        Map<String, Object> clain = new HashMap<>();
        clain.put("alg", "HS256");
        clain.put("typ", "JWT");

        return Jwts.builder().setSubject(user.getEmail()).setIssuedAt(cal.getTime()).setExpiration(cal1.getTime())
                // .signWith(SignatureAlgorithm.HS256,
                // Base64.getEncoder().encodeToString(secret.getBytes()))
                .signWith(getKey(secret), SignatureAlgorithm.HS256).setHeaderParams(clain).compact();

    }

    public Claims getClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(getKey(secret)).parseClaimsJws(token).getBody();

    }

    public String getUsernamefromToken(String token) {

        return getClaimsFromToken(token).getSubject();

    }

    public Date getExpirationDate(String token) {

        return getClaimsFromToken(token).getExpiration();

    }

    public boolean isTokenExpirate(String token) {

        Date expirationDate = getExpirationDate(token);
        return expirationDate.before(new Date());

    }

    public boolean isTokenValidated(String token) {
        return !isTokenExpirate(token);
    }

    private Key getKey(String secret) {
        byte[] secretByte = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretByte);
    }
}
