package edu.eci.cvds.EciBienestarMod3.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for user data sent in registration or filtering requests.
 */
@Getter
@Setter
public class UserRequest {

    private String nombre; // Full name of the user
    private String rol; // Role of the user
    private int id; // Unique ID of the user

    /**
     * Default constructor.
     */
    public UserRequest() {}

    /**
     * Full constructor to initialize all user fields.
     *
     * @param nombre Full name of the user
     * @param rol    Role of the user
     * @param id     Unique ID of the user
     */
    public UserRequest(String nombre, String rol, int id) {
        this.nombre = nombre;
        this.rol = rol;
        this.id = id;
    }
}
