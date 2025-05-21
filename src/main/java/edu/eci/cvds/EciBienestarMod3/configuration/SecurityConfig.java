package edu.eci.cvds.EciBienestarMod3.configuration;

import edu.eci.cvds.EciBienestarMod3.configuration.JWT.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * Configuration class for setting up Spring Security with JWT filtering.
 * Enables method-level security and configures public endpoints, CORS, CSRF, and JWT-based authentication.
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Configures the security filter chain
     * @param http the {@link HttpSecurity} object to configure
     * @return the built {@link SecurityFilterChain}
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll() // Allow public access to Swagger docs
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for REST APIs
                .cors(Customizer.withDefaults()) // Enable CORS with default settings
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build();
    }
}


