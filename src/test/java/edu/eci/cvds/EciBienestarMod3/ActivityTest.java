package edu.eci.cvds.EciBienestarMod3;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;
import edu.eci.cvds.EciBienestarMod3.model.EveryDay;
import edu.eci.cvds.EciBienestarMod3.model.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    private Activity activity;

    @BeforeEach
    public void setUp() {
        activity = new Activity();
    }

    @Test
    void testSetValidActivityType() throws EciBienestarException {
        activity.setActivityType("Ajedrez");
        assertEquals("Ajedrez", activity.getActivityType());
    }

    @Test
    void testSetInvalidActivityType() {
        try {
            activity.setActivityType("Skate");
        } catch (EciBienestarException e){
            assertEquals(EciBienestarException.TYPE_NOT_FOUND, e.getMessage());
        }
    }

    @Test
    void testSetValidCapacityMaximum() throws EciBienestarException {
        activity.setCapacityMaximum(28);
        assertEquals(28, activity.getCapacityMaximum());
    }

    @Test
    void testSetInvalidCapacityMaximumTooHigh() {
        try {
            activity.setCapacityMaximum(150);
        } catch (EciBienestarException e){
            assertEquals(EciBienestarException.MAXIMUM_CAPACITY_NOT_POSSIBLE, e.getMessage());
        }
    }

    @Test
    void testSetSemesterAndYear() {
        activity.setSemester();
        activity.setYear();
        LocalDate today = LocalDate.now();
        int expectedYear = today.getYear();
        int expectedSemester = (today.getMonthValue() <= 6) ? 1 : 2;
        assertEquals(expectedYear, activity.getYear());
        assertEquals(expectedSemester, activity.getSemester());
    }

    @Test
    void testAddAndRemoveResource() {
        Resource r = new Resource();
        r.setName("Microfono");
        r.setAmount("3");
        activity.addResource(r);
        assertTrue(activity.getResources().contains(r));

        activity.removeResource(r);
        assertFalse(activity.getResources().contains(r));
    }

    @Test
    void testAddAndRemoveSchedule() {
        String schedule = "lunes";
        activity.addSchedule(schedule);
        assertTrue(activity.getSchedules().contains(schedule));

        activity.removeSchedule(schedule);
        assertFalse(activity.getSchedules().contains(schedule));
    }

    @Test
    void testAddAndRemoveDay() {
        EveryDay day = new EveryDay();
        day.setDayWeek(DayOfWeek.MONDAY);
        activity.addDays(day);
        assertTrue(activity.getDays().contains(day));

        activity.removeDays(day);
        assertFalse(activity.getDays().contains(day));
    }

    @Test
    void testStaticAddAndRemoveType() throws EciBienestarException {
        Activity.addType("Escalar");
        activity.setActivityType("Escalar");
        assertEquals("Escalar", activity.getActivityType());

        Activity.removeType("Escalar");
        try {
            activity.setActivityType("Escalar");
        } catch (EciBienestarException e){
            assertEquals(EciBienestarException.TYPE_NOT_FOUND, e.getMessage());
        }
    }
}
