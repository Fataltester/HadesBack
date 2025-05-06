package edu.eci.cvds.EciBienestarMod3.model;

public class EciBienestarException  extends Exception{

    public static final String TYPE_NOT_FOUND = "Type of activity not found";
    public static final String SEMESTER_LONGER_THAN_REQUIRED = "Semester number is not in the range required";
    public static final String MAXIMUM_CAPACITY_NOT_POSSIBLE = "Maximum capacity cannot be less than zero ";
    public static final String TYPE = "";

    public EciBienestarException(String message){
        super(message);
    }
}
