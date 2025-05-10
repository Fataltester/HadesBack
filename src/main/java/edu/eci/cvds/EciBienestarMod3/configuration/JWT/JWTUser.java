package edu.eci.cvds.EciBienestarMod3.configuration.JWT;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class JWTUser {
    private final String userName;
    private final String rol;

    public JWTUser(String userName, String rol){
        this.userName = userName;
        this.rol = rol;
    }
}
