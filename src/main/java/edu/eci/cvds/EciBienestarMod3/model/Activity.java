package edu.eci.cvds.EciBienestarMod3.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;

@Document(collection = "Activity")
public class Activity {

    @Id
    private String id;

    private String teacher;
    private int teacherId;
    private String activityType;
    private String state;
    private int capacityMaximum;
    private int capacityCurrent;
    private String location;
    private Schedule schedule;
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

    public String getState() {
        return state;
    }

    public int getCapacityMaximum() {
        return capacityMaximum;
    }

    public int getCapacityCurrent() {
        return capacityCurrent;
    }

    public String getLocation() {
        return location;
    }

    public static List<String> getTypes() {
        return new ArrayList<>(types);
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public int getTeacherId() {
        return teacherId;
    }

    //SETTERS


    public void setId(String id) {
        this.id = id;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCapacityMaximum(int capacityMaximum) {
        this.capacityMaximum = capacityMaximum;
    }

    public void setCapacityCurrent(int capacityCurrent) {
        this.capacityCurrent = capacityCurrent;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
    }

    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    public static void addType(String type) {
        types.add(type);
    }

    public static void removeType(String type) {
        types.remove(type);
    }
}
