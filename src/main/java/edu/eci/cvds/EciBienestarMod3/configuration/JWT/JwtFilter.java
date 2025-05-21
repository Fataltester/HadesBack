package edu.eci.cvds.EciBienestarMod3.configuration.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filter that intercepts HTTP requests to validate JWT tokens.
 *
 * This filter ensures that each request contains a valid JWT in the Authorization header.
 * If the token is valid, it extracts user information and sets the authentication context.
 * Otherwise, it rejects the request with an unauthorized status.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    /**
     * Intercepts each HTTP request to verify the JWT token.
     * Checks if the Authorization header is present and correctly formatted.
     * If valid, extracts claims and sets the authentication context for the request.
     * If invalid or missing, the request is rejected with an unauthorized error.
     *
     * @param request the incoming HTTP request
     * @param response the HTTP response
     * @param chain the filter chain
     * @throws ServletException if a servlet error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token missing or invalid");
            return;
        }

        String token = header.substring(7);

        try {
            jwtService.validateToken(token);

            String id = JWT.decode(token).getClaim("id").asString();
            String userName = JWT.decode(token).getClaim("userName").asString();
            String email = JWT.decode(token).getClaim("email").asString();
            String name = JWT.decode(token).getClaim("name").asString();
            String role = JWT.decode(token).getClaim("role").asString();
            String specialty= JWT.decode(token).getClaim("specialty").asString();

            JWTUser user = new JWTUser(id,userName,email,name,role,specialty);

            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
            var authToken = new UsernamePasswordAuthenticationToken(user, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authToken);
            chain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
        }

    }
}
