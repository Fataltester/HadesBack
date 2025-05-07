package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;

@Setter
@Getter
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

    public Activity() {}

    public void setActivityType(String activityType) throws EciBienestarException {
        if (types.contains(activityType)) {
            this.activityType = activityType;
        }else{
            throw new EciBienestarException(EciBienestarException.TYPE_NOT_FOUND);
        }
    }

    public void setSemester(int semester) throws EciBienestarException {
        if (semester == 1 || semester == 2) {
            this.semester = semester;
        }else {
            throw new EciBienestarException(EciBienestarException.SEMESTER_LONGER_THAN_REQUIRED);
        }
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
}
