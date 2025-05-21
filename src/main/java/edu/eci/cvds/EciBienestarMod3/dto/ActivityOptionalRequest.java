package edu.eci.cvds.EciBienestarMod3.dto;

import edu.eci.cvds.EciBienestarMod3.model.EveryDay;
import edu.eci.cvds.EciBienestarMod3.model.Resource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO for receiving optional parameters for activity creation or update.
 */
@Getter
@Setter
@NoArgsConstructor
public class ActivityOptionalRequest {

    //for getter
    private int year; // Activity year
    private int semester; // Academic semester (1 or 2)
    private String activityType; // Type of the activity

    //for update
    private String teacherName;// Full name of the teacher in charge
    private int teacherId;// Unique ID of the teacher
    private List<EveryDay> days;// Weekly days and times when the activity occurs
    private String newActivity;// New name or type of the activity for update
    private List<Resource> resources;// List of resources needed for the activity
}
