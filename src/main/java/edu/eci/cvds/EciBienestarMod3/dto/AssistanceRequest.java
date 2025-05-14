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
    // ASSISTANCE DATA
    private int idUser;
    private String userName;
    private String rolUser;
    private Boolean confirmation;

    //DATA FOR SEARCH THE ACTIVITY
    private String activityType;
    private int year;
    private int semester;

    //DATA FOR SEARCH THE SCHEDULE
    private Month month;
    private int numberDay;

}
