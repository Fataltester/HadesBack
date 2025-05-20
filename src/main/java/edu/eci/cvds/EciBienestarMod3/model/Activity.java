package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * Represents a wellness program activity.
 * Contains information about the activity type, teacher, location, capacity,
 * weekly schedules, resources, and assigned days.
 */
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Activity")
public class Activity {

    @Id
    private String id;

    // Academic information
    private int year;
    private int semester;

    // Activity type (must be in the allowed list)
    private String activityType;

    // Teacher information
    private String teacher;
    private int teacherId;

    // Location and capacity
    private String location;
    private int capacityMaximum;

    // Weekly schedules, days, and resources
    private List<String> schedules = new ArrayList<>();
    private List<EveryDay> days = new ArrayList<>();
    private List<Resource> resources = new ArrayList<>();

    // Allowed activity types
    private static List<String> types = new ArrayList<>();

    static {
        types.addAll(List.of(
                "Ajedrez", "Aerobicos", "Baloncesto", "Baile para no Bailarines", "Danza",
                "Fotografia", "Futbol Femenino", "Futbol Masculino", "Futbol Administrativos", "Futsal Femenino",
                "Futsal Masculino", "Guitarra", "Juegos Escenicos", "Pilates", "Rumba", "Teatro", "Tecnica Vocal",
                "Vitrales", "Voleybol Femenino y Masculino", "Taekwondo", "Tenis de Campo", "Tenis de mesa", "Yoga"
        ));
    }

    /**
     * Sets the activity type if it is valid.
     *
     * @param activityType the type of activity
     * @throws EciBienestarException if the activity type is not allowed
     */
    public void setActivityType(String activityType) throws EciBienestarException {
        if (types.contains(activityType)) {
            this.activityType = activityType;
        } else {
            throw new EciBienestarException(EciBienestarException.TYPE_NOT_FOUND);
        }
    }

    /**
     * Sets the semester based on the current month.
     * Semester 1 = January to June, Semester 2 = July to December.
     */
    public void setSemester() {
        int month = LocalDate.now().getMonthValue();
        this.semester = (month <= 6) ? 1 : 2;
    }

    /**
     * Sets the current year.
     */
    public void setYear() {
        this.year = LocalDate.now().getYear();
    }

    /**
     * Sets the maximum capacity for the activity.
     *
     * @param capacityMaximum the maximum number of participants
     * @throws EciBienestarException if the capacity is out of bounds (1-99)
     */
    public void setCapacityMaximum(int capacityMaximum) throws EciBienestarException {
        if (capacityMaximum > 0 && capacityMaximum < 100) {
            this.capacityMaximum = capacityMaximum;
        } else {
            throw new EciBienestarException(EciBienestarException.MAXIMUM_CAPACITY_NOT_POSSIBLE);
        }
    }

    // Resource management
    public void addResource(Resource resource) {
        resources.add(resource);
    }
    public void removeResource(Resource resource) {
        resources.remove(resource);
    }

    // Schedule management
    public void addSchedule(String schedule) {
        schedules.add(schedule);
    }
    public void removeSchedule(String schedule) {
        schedules.remove(schedule);
    }

    // Day management
    public void addDays(EveryDay days) {
        this.days.add(days);
    }
    public void removeDays(EveryDay days) {
        this.days.remove(days);
    }

    // Static methods for modifying allowed types
    public static void addType(String type) {
        types.add(type);
    }
    public static void removeType(String type) {
        types.remove(type);
    }
}
