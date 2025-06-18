package org.hibernate.demojwt.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.demojwt.common.PropertiesExternos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenUtils {

    @Autowired
    private PropertiesExternos properties;

    /**
     * Genera un JWT con nombre y email como claims.
     *
     * @param nombre Nombre del usuario
     * @param email  Email del usuario (usado como subject)
     * @return JWT como String
     */
    public String createToken(String nombre, String email) {
        log.debug("Generando token para usuario: {}", email);


        SecretKey key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());
        long expirationTimeMillis = Long.parseLong(properties.getValidity().getSeconds()) * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeMillis);

        Map<String, Object> claims = new HashMap<>();
        claims.put("nombre", nombre);

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(claims)
                .signWith(key)
                .compact();
    }

    /**
     * Extrae la autenticación desde un JWT.
     *
     * @param token JWT recibido
     * @return UsernamePasswordAuthenticationToken si es válido, null si no
     */
    public UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(properties.getSecret().getBytes());

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            log.debug("Token válido para usuario: {}", email);

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e) {
            log.warn("Token inválido o expirado: {}", e.getMessage());
            return null;
        }
    }
}
