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
    private String id;

    private Integer numberDay;
    private Month month;
    private Integer year;
    private String idActivity;

    private ScheduleState state = ScheduleState.ESPERA;
    private Integer capacityCurrent = 0;
    private List<Integer> assistances = new ArrayList<>();

    /**
     * Adds a user ID to the list of attendances.
     *
     * @param idUser the ID of the attending user
     */
    public void addAssistance(Integer idUser) {
        assistances.add(idUser);
    }
}


