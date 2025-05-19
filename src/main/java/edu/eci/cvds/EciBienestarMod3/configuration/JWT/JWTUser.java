package edu.eci.cvds.EciBienestarMod3.configuration.JWT;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class JWTUser {
    private final String id;
    private final String userName;
    private final String email;
    private final String name;
    private final String rol;
    private final String speciality;
}
