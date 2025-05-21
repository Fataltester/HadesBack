package edu.eci.cvds.EciBienestarMod3.configuration.JWT;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prueba")
public class TestController {

    @GetMapping("/token")
    public JWTUser prueba() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUser user = (JWTUser) authentication.getPrincipal();
        //System.out.println(user.getName());
        return user;
    }
}
