package edu.eci.cvds.EciBienestarMod3.configuration.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final String SECRET_KEY = "ContraseñaSuperSecreta123";

    public void validateToken(String token) throws JWTVerificationException {
        JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token);
    }
}
