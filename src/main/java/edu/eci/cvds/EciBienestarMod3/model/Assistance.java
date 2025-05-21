package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Document(collection = "Assistance")
public class Assistance {

    @Id
    private String id;

    private Boolean confirmation;  // Confirmación de asistencia
    private String userName;       // Nombre completo del usuario
    private int userId;            // ID del usuario
    private String userEmail;      // Email del usuario

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

    public boolean getConfirmation(){
        return confirmation;
    }

}
