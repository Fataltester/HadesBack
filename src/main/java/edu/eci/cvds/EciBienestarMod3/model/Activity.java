package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "Activity")
public class Activity {

    @Id
    private String id;

    private LocalTime startHour;
    private LocalTime endHour;
    private DayOfWeek dayWeek;
    private int year;
    private int semester;

    private String teacher;
    private int teacherId;
    private String activityType;
    private String location;
    private int capacityMaximum;

    private List<String> schedules = new ArrayList<>();
    private List<Resource> resources = new ArrayList<>();

    private static List<String> types = new ArrayList<>();
    static {
        types.addAll(List.of("Ajedrez", "Aerobicos", "Baloncesto", "Baile para no Bailarines", "Danza",
                "Fotografia", "Futbol Femenino", "Futbol Masculino", "Futbol Administrativos", "Futsal Femenino",
                "Futsal Masculino", "Guitarra", "Juegos Escenicos", "Pilates", "Rumba", "Teatro", "Tecnica Vocal",
                "Vitrales", "Voleybol Femenino y Masculino", "Taekwondo", "Tenis de Campo", "Tenis de mesa", "Yoga"));
    }

    public Activity(){}

    //GETTERS

    public String getId() {
        return id;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getActivityType() {
        return activityType;
    }

    public String getLocation() {
        return location;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public DayOfWeek getDayWeek() {
        return dayWeek;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public static List<String> getTypes() {
        return new ArrayList<>(types);
    }

    public List<Resource> getResources() {
        return resources;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getCapacityMaximum() {
        return capacityMaximum;
    }

    //SETTERS


    public void setId(String id) {
        this.id = id;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setActivityType(String activityType) throws EciBienestarException {
        if (types.contains(activityType)) {
            this.activityType = activityType;
        }else{
            throw new EciBienestarException(EciBienestarException.TYPE_NOT_FOUND);
        }
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public void setDayWeek(DayOfWeek dayWeek) {
        this.dayWeek = dayWeek;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSemester(int semester) throws EciBienestarException {
        if (semester == 1 || semester == 2) {
            this.semester = semester;
        }else {
            throw new EciBienestarException(EciBienestarException.SEMESTER_LONGER_THAN_REQUIRED);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void setCapacityMaximum(int capacityMaximum) throws EciBienestarException {
        if (capacityMaximum > 0) {
            this.capacityMaximum = capacityMaximum;
        }else {
            throw new EciBienestarException(EciBienestarException.MAXIMUM_CAPACITY_NOT_POSSIBLE);
        }
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    public void addSchedule(String schedule) {
        schedules.add(schedule);
    }

    public void removeSchedule(String schedule) {
        schedules.remove(schedule);
    }

    public static void addType(String type) {
        types.add(type);
    }

    public static void removeType(String type) {
        types.remove(type);
    }
}
