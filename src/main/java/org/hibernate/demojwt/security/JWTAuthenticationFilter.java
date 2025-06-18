package org.hibernate.demojwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.demojwt.entity.AuthCredentials;
import org.hibernate.demojwt.service.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final TokenUtils tokenUtils;

    /**
     * Intenta autenticar al usuario a partir del cuerpo de la solicitud.
     *
     * @param request  solicitud HTTP
     * @param response respuesta HTTP
     * @return objeto Authentication si las credenciales son válidas
     * @throws AuthenticationException si ocurre un error de autenticación
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            AuthCredentials authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    authCredentials.getEmail(),
                    authCredentials.getPassword(),
                    Collections.emptyList()
            );

            log.debug("Intentando autenticar usuario: {}", authCredentials.getEmail());
            return getAuthenticationManager().authenticate(authToken);

        } catch (IOException e) {
            log.error("Error al leer las credenciales de autenticación", e);
            throw new RuntimeException("Error al procesar la solicitud de autenticación", e);
        }
    }

    /**
     * Se ejecuta cuando la autenticación es exitosa. Genera y agrega el JWT a la respuesta.
     *
     * @param request    solicitud HTTP
     * @param response   respuesta HTTP
     * @param chain      cadena de filtros
     * @param authResult resultado de autenticación
     * @throws IOException      si ocurre un error de E/S
     * @throws ServletException si ocurre un error de servlet
     */

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
        String token = tokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername());

        // Asegura que la respuesta sea 200 (OK)
        response.setStatus(HttpServletResponse.SC_OK);

        // Define el tipo de contenido como JSON y ajusta la codificación
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Agrega la cabecera de autorización con el token
        response.setHeader("Authorization", "Bearer " + token);

        // Serializa el objeto JSON con Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> respuestaJson = Map.of("token", token);
        String jsonResponse = objectMapper.writeValueAsString(respuestaJson);

        // Escribe la respuesta en la salida
        response.getWriter().write(jsonResponse);
        response.getWriter().flush();

        log.info("Autenticación exitosa para usuario: {}", userDetails.getUsername());
    }

}
