package edu.eci.cvds.EciBienestarMod3.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;

@Document(collection = "Assistance")
public class Assistance {

    @Id
    private String id;

    private boolean confirmation;  // Confirmación de asistencia
    private String userName;       // Nombre completo del usuario
    private int userId;            // ID del usuario
    private String userRol;        // Rol del usuario (Estudiante, Docente, etc.)
    private String idSchedule;     // ID de referencia al horario de cada asistencia

    public Assistance() {
    }

    public Assistance(String id, boolean confirmation, String userName, int userId, String userRol, String idSchedule) {
        this.id = id;
        this.confirmation = confirmation;
        this.userName = userName;
        this.userId = userId;
        this.userRol = userRol;
        this.idSchedule = idSchedule;
    }

    // GETTERS

    public String getId() {
        return id;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserRol() {
        return userRol;
    }

    public String getIdActivity() {
        return idSchedule;
    }

    // SETTERS

    public void setId(String id) {
        this.id = id;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserRol(String userRol) {
        this.userRol = userRol;
    }

    public void setIdActivity(String idActivity) {
        this.idSchedule = idActivity;
    }
}
