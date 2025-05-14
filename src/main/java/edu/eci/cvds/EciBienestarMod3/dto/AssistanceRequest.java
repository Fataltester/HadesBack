package edu.eci.cvds.EciBienestarMod3.dto;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
@Getter
@Setter
public class AssistanceRequest {
    private int idUsuario;
    private String nombreUsuario;
    private String rolUsuario;
    private int numberDay;
    private LocalTime startHour;
    private int year;
    private int semester;
    private DayOfWeek dayOfWeek;
    private boolean confirmation;
    private Month month;

    public boolean getConfirmation() {
        return confirmation;
    }
}
