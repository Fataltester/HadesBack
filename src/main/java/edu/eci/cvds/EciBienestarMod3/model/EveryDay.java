package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

/**
 * Represents a weekly day and its time slot for an activity.
 */
@Getter
@Setter
@NoArgsConstructor
public class EveryDay {

    private LocalTime startHour;
    private LocalTime endHour;
    private DayOfWeek dayWeek;

    /**
     * Sets the start hour if it's after 7:00 AM.
     *
     * @param startHour the start time
     * @throws EciBienestarException if the time is before 7:00 AM
     */
    public void setStartHour(LocalTime startHour) throws EciBienestarException {
        if (startHour.isBefore(LocalTime.of(7, 0))) {
            throw new EciBienestarException(EciBienestarException.STARTHOUR_BEFORE_SEVEN);
        }
        this.startHour = startHour;
    }

    /**
     * Sets the end hour with validations:
     * - Must be before 7:00 PM
     * - Must be after the start hour
     * - Duration must not exceed 6 hours
     *
     * @param endHour the end time
     * @throws EciBienestarException if validations fail
     */
    public void setEndHour(LocalTime endHour) throws EciBienestarException {
        if (endHour.isAfter(LocalTime.of(19, 0))) {
            throw new EciBienestarException(EciBienestarException.STARTHOUR_AFTER_SEVEN);
        }
        if (this.startHour == null) {
            throw new EciBienestarException(EciBienestarException.STARTHOUR_NOT_DEFINE);
        }
        if (endHour.isBefore(startHour)) {
            throw new EciBienestarException(EciBienestarException.ENDHOUR_BEFORE_STARTHOUR);
        }
        if (Duration.between(startHour, endHour).toHours() > 6) {
            throw new EciBienestarException(EciBienestarException.ENDHOUR_MORE_THAN_SIX_HOURS);
        }
        this.endHour = endHour;
    }
}
