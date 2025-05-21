package edu.eci.cvds.EciBienestarMod3.model;

import edu.eci.cvds.EciBienestarMod3.model.enumeration.ScheduleState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a calendar-based schedule for an activity.
 * Contains the scheduled date, activity ID, attendance list, state, and capacity.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Schedule")
public class Schedule {

    @Id
    private String id; // Unique identifier for the schedule

    private Integer numberDay; //Day of the month when the schedule occurs.
    private Month month; //Month in which the schedule is set.
    private Integer year; //Year in which the schedule is set.
    private String idActivity; //Identifier of the associated activity

    private ScheduleState state = ScheduleState.ESPERA; //Current state of the schedule (e.g., ESPERA, CANCELADA, TERMINADA).
    private Integer capacityCurrent = 0; //Number of participants currently assigned to this schedule.
    private List<Integer> assistances = new ArrayList<>(); //List of user IDs who have registered attendance for this schedule.

    /**
     * Adds a user ID to the list of attendances.
     *
     * @param idUser the ID of the attending user
     */
    public void addAssistance(Integer idUser) {
        assistances.add(idUser);
    }

    /**
     * Removes a user ID from the list of attendances.
     *
     * @param idUser the ID of the user to remove from attendance
     */
    public void deleteAssistance(Integer idUser){
        assistances.remove(idUser);
    }

}


