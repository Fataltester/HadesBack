package edu.eci.cvds.EciBienestarMod3.model;

public class EciBienestarException  extends Exception{

    public static final String TYPE_NOT_FOUND = "Type of activity not found";
    public static final String SEMESTER_LONGER_THAN_REQUIRED = "Semester number is not in the range required";
    public static final String MAXIMUM_CAPACITY_NOT_POSSIBLE = "Maximum capacity cannot be less than zero ";
    public static final String ACTIVITY_ALREADY_EXIST = "This activity already exists";
    public static final String STARTHOUR_NOT_DEFINE = "startHour must be defined before assigning endHour.";
    public static final String ENDHOUR_BEFORE_STARTHOUR = "endHour cannot be less than startHour";
    public static final String ENDHOUR_MORE_THAN_SIX_HOURS =
            "The difference between startHour and endHour cannot be greater than 6 hours.";
    public static final String STARTHOUR_BEFORE_SEVEN = "startHour can not be before 07:00 AM";
    public static final String STARTHOUR_AFTER_SEVEN = "EndHour can not be after 07:00 PM";
    public static final String EMAIL_NOT_FOUND = "Can not send an email, address or ";

    public EciBienestarException(String message){
        super(message);
    }
}
