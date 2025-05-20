package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@Document(collection = "Assistance")
public class Assistance {

    @Id
    private String id;

    private Boolean confirmation;  // Indicates whether the user confirmed their attendance.
    private String userName;       // Full name of the user who registered the attendance.
    private int userId;            // Unique identifier for the user.
    private String userRol;        // Role of the user (Student, Teacher, Administrative).
    private String idSchedule;     // Reference to the schedule ID associated with this attendance.

    /**
     * Default no-argument constructor.
     */
    public Assistance() {
    }

    /**
     * Full constructor to initialize all fields of the attendance record.
     *
     * @param id           Unique identifier for this attendance record
     * @param confirmation Whether the user confirmed attendance
     * @param userName     Full name of the user
     * @param userId       Unique ID of the user
     * @param userRol      Role of the user
     * @param idSchedule   ID of the schedule associated with this attendance
     */
    public Assistance(String id, boolean confirmation, String userName, int userId, String userRol, String idSchedule) {
        this.id = id;
        this.confirmation = confirmation;
        this.userName = userName;
        this.userId = userId;
        this.userRol = userRol;
        this.idSchedule = idSchedule;
    }

    /**
     * Returns the attendance confirmation status.
     *
     * @return true if attendance is confirmed, false otherwise
     */
    public boolean getConfirmation() {
        return confirmation;
    }
}
