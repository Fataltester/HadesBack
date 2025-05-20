package edu.eci.cvds.EciBienestarMod3.dto;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;

/**
 * DTO for handling assistance registration and filtering based on user, activity, and schedule data.
 */
@Getter
@Setter
public class AssistanceRequest {

    // ASSISTANCE DATA
    private int idUser; // Unique ID of the user registering attendance
    private String userName; // Full name of the user
    private String rolUser; // Role of the user
    private Boolean confirmation; // Whether the user confirms attendance

    //DATA FOR SEARCH THE ACTIVITY
    private String activityType; // Type of the activity
    private int year; // Activity year
    private int semester; // Academic semester

    //DATA FOR SEARCH THE SCHEDULE
    private Month month; // Month of the scheduled session
    private int numberDay; // Day of the month of the scheduled session
}
