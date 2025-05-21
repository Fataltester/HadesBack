package edu.eci.cvds.EciBienestarMod3;

import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.model.enumeration.ScheduleState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    private Schedule schedule;

    @BeforeEach
    public void setUp() {
        schedule = new Schedule();
    }

    @Test
    void testDefaultStateIsEspera() {
        assertEquals(ScheduleState.ESPERA, schedule.getState());
    }

    @Test
    void testAddAssistance() {
        assertTrue(schedule.getAssistances().isEmpty());
        schedule.addAssistance(1001);
        assertEquals(1, schedule.getAssistances().size());
        assertTrue(schedule.getAssistances().contains(1001));
    }

    @Test
    void testSettersAndGetters() {
        schedule.setNumberDay(15);
        schedule.setMonth(Month.SEPTEMBER);
        schedule.setYear(2025);
        schedule.setIdActivity("1234");
        schedule.setCapacityCurrent(10);
        schedule.setState(ScheduleState.ACTIVA);

        assertEquals(15, schedule.getNumberDay());
        assertEquals(Month.SEPTEMBER, schedule.getMonth());
        assertEquals(2025, schedule.getYear());
        assertEquals("1234", schedule.getIdActivity());
        assertEquals(10, schedule.getCapacityCurrent());
        assertEquals(ScheduleState.ACTIVA, schedule.getState());
    }

    @Test
    void testScheduleStateTransitions() {
        schedule.setState(ScheduleState.ESPERA);
        assertEquals(ScheduleState.ESPERA, schedule.getState());

        schedule.setState(ScheduleState.ACTIVA);
        assertEquals(ScheduleState.ACTIVA, schedule.getState());

        schedule.setState(ScheduleState.TERMINADA);
        assertEquals(ScheduleState.TERMINADA, schedule.getState());

        schedule.setState(ScheduleState.CANCELADA);
        assertEquals(ScheduleState.CANCELADA, schedule.getState());
    }
}
