package edu.eci.cvds.EciBienestarMod3.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import org.springframework.data.annotation.Id;

@Document(collection = "Schedule")
public class Schedule {

    @Id
    private String id;

    private LocalTime startHour;
    private LocalTime endHour;
    private int numberDay;
    private DayOfWeek dayWeek;
    private Month month;
    private int year;

    public Schedule() {}

    //SETTERS

    public void setId(String id) {
        this.id = id;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public void setDayWeek(DayOfWeek dayWeek) {
        this.dayWeek = dayWeek;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    //GETTERS

    public String getId() {
        return id;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public DayOfWeek getDayWeek() {
        return dayWeek;
    }

    public Month getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
