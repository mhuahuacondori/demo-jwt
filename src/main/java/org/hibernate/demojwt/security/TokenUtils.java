package org.hibernate.demojwt.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.demojwt.common.PropertiesExternos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
@Slf4j
public class TokenUtils {
    private static PropertiesExternos prop;
    @Autowired
    private TokenUtils(PropertiesExternos prop){
        TokenUtils.prop = prop;
    }
    public static String createToken(String nombre, String email){

        long expirationTime = Long.parseLong(prop.accessTokenValiditySeconds) * 1_000;
        Date expirationDate =new Date(System.currentTimeMillis()+expirationTime);

        Map<String,Object> extra = new HashMap<>();
        extra.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(prop.accessTokenSecret.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(prop.accessTokenSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }catch (JwtException e){
            return null;
        }
    }
}
