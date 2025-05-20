package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.model.enumeration.ScheduleState;
import edu.eci.cvds.EciBienestarMod3.repository.ScheduleMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class responsible for handling schedule-related operations,
 * including creation, deletion, and state management of schedule records.
 */
@Service
public class ScheduleService {

    @Autowired
    ScheduleMongoRepository scheduleRepository;

    /**
     * Creates a new schedule if it does not already exist in the repository.
     *
     * @param schedule The schedule to be created.
     * @return The saved Schedule object, or null if the schedule already exists.
     */
    Schedule createSchedule(Schedule schedule) {
        if (schedule.getId() == null) {
            schedule.setId(UUID.randomUUID().toString());
        }

        if (scheduleRepository.existsById(schedule.getId())) {
            return null;
        } else {
            return scheduleRepository.save(schedule);
        }
    }

    /**
     * Finds a schedule by its ID.
     *
     * @param id The ID of the schedule.
     * @return The corresponding Schedule object, or null if not found.
     */
    public Schedule findScheduleById(String id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    /**
     * Creates multiple schedules between two semester-defined dates for a given day of the week.
     *
     * @param semester   The semester number (1 or 2).
     * @param activityId The activity ID associated with the schedules.
     * @param dayOfWeeks The day of the week for which schedules are created.
     * @return A list of schedule IDs created for the specified criteria.
     */
    public List<String> createScheduleBetweenTwoDates(int semester, String activityId, DayOfWeek dayOfWeeks) {
        LocalDate startDate;
        LocalDate endDate;

        if (semester == 1) {
            startDate = LocalDate.of(2025, 2, 1);
            endDate = LocalDate.of(2025, 5, 31);
        } else {
            startDate = LocalDate.of(2025, 8, 1);
            endDate = LocalDate.of(2025, 11, 30);
        }

        List<String> schedules = new ArrayList<>();
        List<LocalDate> dates = getDatesForWeekday(startDate, endDate, dayOfWeeks);

        for (LocalDate date : dates) {
            Schedule schedule = new Schedule();
            schedule.setNumberDay(date.getDayOfMonth());
            schedule.setMonth(date.getMonth());
            schedule.setYear(date.getYear());
            schedule.setIdActivity(activityId);
            Schedule newSchedule = scheduleRepository.save(schedule);
            schedules.add(newSchedule.getId());
        }

        return schedules;
    }

    /**
     * Cancels a schedule if it is not already canceled or finished.
     *
     * @param schedule The schedule to cancel.
     * @return The updated Schedule object with state set to CANCELADA, or the original if not modifiable.
     */
    public Schedule deleteSchedule(Schedule schedule) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule.getId());

        if (scheduleOptional.isPresent()) {
            Schedule scheduleUpdate = scheduleOptional.get();
            if (scheduleUpdate.getState() != ScheduleState.CANCELADA &&
                    scheduleUpdate.getState() != ScheduleState.TERMINADA) {
                scheduleUpdate.setState(ScheduleState.CANCELADA);
                return scheduleRepository.save(scheduleUpdate);
            } else {
                return scheduleUpdate;
            }
        } else {
            return null;
        }
    }

    /**
     * Permanently deletes a schedule by its ID (admin-level operation).
     *
     * @param schedule The ID of the schedule to delete.
     */
    public void deleteAdminShcedule(String schedule) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(schedule);

        if (scheduleOptional.isPresent()) {
            Schedule scheduleDelete = scheduleOptional.get();
            scheduleRepository.deleteById(scheduleDelete.getId());
        }
    }

    /**
     * Changes the state of a schedule given its ID.
     *
     * @param id       The ID of the schedule.
     * @param newState The new state to set.
     * @return The updated Schedule object, or null if not found.
     */
    public Schedule changeState(String id, ScheduleState newState) {
        Optional<Schedule> scheduleOptional = scheduleRepository.findById(id);

        if (scheduleOptional.isPresent()) {
            Schedule schedule = scheduleOptional.get();
            schedule.setState(newState);
            return scheduleRepository.save(schedule);
        } else {
            return null;
        }
    }

    /**
     * Finds schedules by a specific state.
     *
     * @param state The state to filter by.
     * @return A list of schedules matching the given state.
     */
    List<Schedule> findByState(ScheduleState state) {
        return scheduleRepository.findByState(state);
    }

    /**
     * Finds schedules that match any of the provided states.
     *
     * @param states A list of states to filter by.
     * @return A list of schedules matching any of the provided states.
     */
    List<Schedule> findByStates(List<ScheduleState> states) {
        return scheduleRepository.findByStateIn(states);
    }

    /**
     * Utility method to get all dates between two dates that match a specific day of the week.
     *
     * @param startDate  Start date of the range.
     * @param endDate    End date of the range.
     * @param targetDay  The day of the week to match.
     * @return A list of LocalDate objects representing all matching days.
     */
    private List<LocalDate> getDatesForWeekday(LocalDate startDate, LocalDate endDate, DayOfWeek targetDay) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = startDate;

        while (current.getDayOfWeek() != targetDay) {
            current = current.plusDays(1);
        }

        while (!current.isAfter(endDate)) {
            dates.add(current);
            current = current.plusWeeks(1);
        }
        return dates;
    }
}
