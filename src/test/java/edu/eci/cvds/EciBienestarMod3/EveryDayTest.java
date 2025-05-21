package edu.eci.cvds.EciBienestarMod3;

import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;
import edu.eci.cvds.EciBienestarMod3.model.EveryDay;

import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class EveryDayTest {

    @Test
    public void testSetStartHourValid() throws EciBienestarException {
        EveryDay ed = new EveryDay();
        ed.setStartHour(LocalTime.of(8, 0));
        assertEquals(LocalTime.of(8, 0), ed.getStartHour());
    }

    @Test
    public void testSetStartHourBeforeSeven() {
        EveryDay ed = new EveryDay();
        Exception ex = assertThrows(EciBienestarException.class,
                () -> ed.setStartHour(LocalTime.of(6, 59)));
        assertEquals(EciBienestarException.STARTHOUR_BEFORE_SEVEN, ex.getMessage());
    }

    @Test
    public void testSetEndHourValid() throws EciBienestarException {
        EveryDay ed = new EveryDay();
        ed.setStartHour(LocalTime.of(8, 0));
        ed.setEndHour(LocalTime.of(13, 0));
        assertEquals(LocalTime.of(13, 0), ed.getEndHour());
    }

    @Test
    public void testSetEndHourAfterSevenPM() throws EciBienestarException {
        EveryDay ed = new EveryDay();
        ed.setStartHour(LocalTime.of(10, 0));
        Exception ex = assertThrows(EciBienestarException.class,
                () -> ed.setEndHour(LocalTime.of(20, 0)));
        assertEquals(EciBienestarException.STARTHOUR_AFTER_SEVEN, ex.getMessage());
    }

    @Test
    public void testSetEndHourWithoutStart() {
        EveryDay ed = new EveryDay();
        Exception ex = assertThrows(EciBienestarException.class,
                () -> ed.setEndHour(LocalTime.of(10, 0)));
        assertEquals(EciBienestarException.STARTHOUR_NOT_DEFINE, ex.getMessage());
    }

    @Test
    public void testSetEndHourBeforeStartHour() throws EciBienestarException {
        EveryDay ed = new EveryDay();
        ed.setStartHour(LocalTime.of(10, 0));
        Exception ex = assertThrows(EciBienestarException.class,
                () -> ed.setEndHour(LocalTime.of(9, 0)));
        assertEquals(EciBienestarException.ENDHOUR_BEFORE_STARTHOUR, ex.getMessage());
    }

    @Test
    public void testSetEndHourMoreThanSixHours() throws EciBienestarException {
        EveryDay ed = new EveryDay();
        ed.setStartHour(LocalTime.of(8, 0));
        Exception ex = assertThrows(EciBienestarException.class,
                () -> ed.setEndHour(LocalTime.of(15, 30)));
        assertEquals(EciBienestarException.ENDHOUR_MORE_THAN_SIX_HOURS, ex.getMessage());
    }

    @Test
    public void testSetDayWeek() {
        EveryDay ed = new EveryDay();
        ed.setDayWeek(DayOfWeek.FRIDAY);
        assertEquals(DayOfWeek.FRIDAY, ed.getDayWeek());
    }
}
