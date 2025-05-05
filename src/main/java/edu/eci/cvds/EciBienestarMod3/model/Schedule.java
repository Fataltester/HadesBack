package edu.eci.cvds.EciBienestarMod3.model;

import edu.eci.cvds.EciBienestarMod3.model.enumeration.ScheduleState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
    @NonNull
    private String id;
    @NonNull
    private ScheduleState state;

    @NonNull
    private int numberDay;
    @NonNull
    private Month month;

    @NonNull
    private String idActivity;
    @NonNull
    private int capacityCurrent;

    @NonNull
    private List<Integer> assistances = new ArrayList<>();

}
