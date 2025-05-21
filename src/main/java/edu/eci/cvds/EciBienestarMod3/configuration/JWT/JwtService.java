package edu.eci.cvds.EciBienestarMod3.configuration.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

/**
 * Service for handling JWT validation logic.
 * This class is responsible for verifying the authenticity of a JWT token
 * using a predefined secret key and the HMAC256 algorithm.
 */
@Service
public class JwtService {

    private final String SECRET_KEY = "ContraseñaSuperSecreta123";

    /**
     * Validates the given JWT token.
     * This method verifies the token's integrity and authenticity. If the token
     * is invalid or expired, it throws a JWTVerificationException.
     *
     * @param token the JWT token to validate
     * @throws JWTVerificationException if the token is invalid or has been tampered with
     */
    public void validateToken(String token) throws JWTVerificationException {
        JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
    }
}
