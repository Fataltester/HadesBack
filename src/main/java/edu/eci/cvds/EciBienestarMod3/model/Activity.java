package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

@Setter
@Getter
@Document(collection = "Activity")
@NoArgsConstructor
public class Activity {

    @Id
    private String id;

    //ID of every activity
    private int year;
    private int semester;
    private String activityType;

    //teacher information
    private String teacher;
    private int teacherId;

    //class information
    private String location;
    private int capacityMaximum;

    private List<String> schedules = new ArrayList<>(); //weekly schedule of each activity
    private List<EveryDay> days = new ArrayList<>(); //day and time per week
    private List<Resource> resources = new ArrayList<>(); //resources of class

    private static List<String> types = new ArrayList<>();
    static {
        types.addAll(List.of("Ajedrez", "Aerobicos", "Baloncesto", "Baile para no Bailarines", "Danza",
                "Fotografia", "Futbol Femenino", "Futbol Masculino", "Futbol Administrativos", "Futsal Femenino",
                "Futsal Masculino", "Guitarra", "Juegos Escenicos", "Pilates", "Rumba", "Teatro", "Tecnica Vocal",
                "Vitrales", "Voleybol Femenino y Masculino", "Taekwondo", "Tenis de Campo", "Tenis de mesa", "Yoga"));
    }

    public void setActivityType(String activityType) throws EciBienestarException {
        if (types.contains(activityType)) {
            this.activityType = activityType;
        }else{
            throw new EciBienestarException(EciBienestarException.TYPE_NOT_FOUND);
        }
    }

    public void setSemester(){
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int semester = (month <= 6) ? 1 : 2;
        this.semester = semester;
    }

    public void setYear(){
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        this.year = year;
    }

    public void setCapacityMaximum(int capacityMaximum) throws EciBienestarException {
        if (capacityMaximum > 0 && capacityMaximum < 100) {
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

    public void addDays(EveryDay days) {
        this.days.add(days);
    }

    public void removeDays(EveryDay days) {
        this.days.remove(days);
    }

}
