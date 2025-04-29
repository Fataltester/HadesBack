package edu.eci.cvds.EciBienestarMod3.dto;

public class UserRequest {

    private String nombre;
    private String rol;
    private int id;

    public UserRequest() {}

    public UserRequest(String nombre, String rol, int id) {
        this.nombre = nombre;
        this.rol = rol;
        this.id = id;
    }

    //GETTER

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public int getId() {
        return id;
    }

    //SETTER
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setId(int id) {
        this.id = id;
    }
}
