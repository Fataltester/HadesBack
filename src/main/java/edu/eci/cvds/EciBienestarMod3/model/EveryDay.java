package edu.eci.cvds.EciBienestarMod3.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
public class EveryDay {

    private LocalTime startHour;
    private LocalTime endHour;
    private DayOfWeek dayWeek;

    public void setStartHour(LocalTime startHour) throws EciBienestarException {
        LocalTime early = LocalTime.of(7, 0);
        if (startHour.isBefore(early)) {
            throw new EciBienestarException(EciBienestarException.STARTHOUR_BEFORE_SEVEN);
        }
        this.startHour = startHour;
    }

    public void setEndHour(LocalTime endHour) throws EciBienestarException {
        LocalTime latest = LocalTime.of(19, 0);
        if (endHour.isAfter(latest)) {
            throw new EciBienestarException(EciBienestarException.STARTHOUR_AFTER_SEVEN);
        }
        if (this.startHour == null) {
            throw new EciBienestarException(EciBienestarException.STARTHOUR_NOT_DEFINE);
        }

        if (endHour.isBefore(startHour)) {
            throw new EciBienestarException(EciBienestarException.ENDHOUR_BEFORE_STARTHOUR);
        }

        Duration duration = Duration.between(startHour, endHour);
        if (duration.toHours() > 6) {
            throw new EciBienestarException(EciBienestarException.ENDHOUR_MORE_THAN_SIX_HOURS);
        }

        this.endHour = endHour;
    }
}
