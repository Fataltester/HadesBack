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


@Setter
@Getter
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

    public void addAssistance(Integer idUser){
        assistances.add(idUser);
    }

    public void deleteAssistance(Integer idUser){
        assistances.remove(idUser);
    }

}


