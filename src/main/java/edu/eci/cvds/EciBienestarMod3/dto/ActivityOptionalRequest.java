package edu.eci.cvds.EciBienestarMod3.dto;

import edu.eci.cvds.EciBienestarMod3.model.EveryDay;
import edu.eci.cvds.EciBienestarMod3.model.Resource;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ActivityOptionalRequest {
    //for getter
    private int year;
    private int semester;
    private String activityType;

    //for update
    private String teacherName;
    private int teacherId;
    private List<EveryDay> days;
    private String newActivity;
    private List<Resource> resources;
}
