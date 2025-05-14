package edu.eci.cvds.EciBienestarMod3.dto;

import edu.eci.cvds.EciBienestarMod3.model.EveryDay;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.DayOfWeek;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ActivityOptionalRequest {
    private int year;
    private int semester;
    private String teacherName;
    private int teacherId;
    private List<EveryDay> days;
    private String activityType;
}
