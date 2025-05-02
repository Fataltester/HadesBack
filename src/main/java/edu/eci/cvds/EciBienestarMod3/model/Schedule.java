package edu.eci.cvds.EciBienestarMod3.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

@Document(collection = "Schedule")
public class Schedule {

    @Id
    private String id;

    private int numberDay;
    private Month month;

    private int capacityCurrent;
    private String state;
    private String idActivity;

    private List<Integer> assistances = new ArrayList<>();

    public Schedule() {}

    //SETTERS

    public void setId(String id) {
        this.id = id;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCapacityCurrent(int capacityCurrent) {
        this.capacityCurrent = capacityCurrent;
    }

    public void setIdActivity(String idActivity) {
        this.idActivity = idActivity;
    }

    public void addAssistance(int assistance) {
        assistances.add(assistance);
    }

    public void removeAssistance(int assistance) {
        assistances.remove(assistance);
    }

    //GETTERS

    public String getId() {
        return id;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public Month getMonth() {
        return month;
    }

    public String getState() {
        return state;
    }

    public int getCapacityCurrent() {
        return capacityCurrent;
    }

    public String getIdActivity() {
        return idActivity;
    }

    public List<Integer> getAssistances() {
        return assistances;
    }

    public int getTotalAssistances() {
        return assistances.size();
    }
}
