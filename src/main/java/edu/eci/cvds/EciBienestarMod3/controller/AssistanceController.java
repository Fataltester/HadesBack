package edu.eci.cvds.EciBienestarMod3.controller;

import edu.eci.cvds.EciBienestarMod3.dto.AssistanceRequest;
import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.repository.ScheduleMongoRepository;
import edu.eci.cvds.EciBienestarMod3.service.ActivityService;
import edu.eci.cvds.EciBienestarMod3.service.AssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * REST controller for managing user assistance (attendance) to activities.
 * Allows creating, updating, finding, and deleting assistance records.
 */
@RestController
@RequestMapping("/api/assistance")
public class AssistanceController {

    @Autowired
    private AssistanceService assistanceServ;

    @Autowired
    private ActivityService activityServ;

    @Autowired
    private ScheduleMongoRepository scheduleRep;

    /**
     * Create a new assistance record for a user on a specific activity schedule.
     *
     * @param assistanceRequest Information about user and target schedule.
     * @return Created assistance record.
     * @throws EciBienestarException if schedule is not found.
     */
    @PostMapping("/newAssistance")
    public Assistance createAssistance(@RequestBody AssistanceRequest assistanceRequest) throws EciBienestarException {
        Activity requestedActivity = new Activity();
        requestedActivity.setActivityType(assistanceRequest.getActivityType());
        requestedActivity.setYear(assistanceRequest.getYear());
        requestedActivity.setSemester(assistanceRequest.getSemester());

        Activity requiredActivity = activityServ.getActivityBySchedule(requestedActivity);
        List<String> schedules = requiredActivity.getSchedules();

        for (String schedule : schedules) {
            Schedule actSchedule = scheduleRep.getScheduleById(schedule);
            if (actSchedule.getNumberDay() == assistanceRequest.getNumberDay() &&
                    actSchedule.getMonth() == assistanceRequest.getMonth()) {
                actSchedule.addAssistance(assistanceRequest.getIdUser());
                scheduleRep.save(actSchedule);
                return assistanceServ.createAssistance(schedule, assistanceRequest);
            }
        }

        throw new EciBienestarException(EciBienestarException.TYPE_NOT_FOUND);
    }

    /**
     * Find existing assistance records by given search options.
     *
     * @param assistanceRequest Request object containing filters.
     * @return List of matching assistance records.
     * @throws EciBienestarException if schedule is not found.
     */
    @GetMapping("/find/options")
    public List<Assistance> findAssistanceByOptions(@RequestBody AssistanceRequest assistanceRequest) throws EciBienestarException {
        Activity requestedActivity = new Activity();
        requestedActivity.setActivityType(assistanceRequest.getActivityType());
        requestedActivity.setYear(assistanceRequest.getYear());
        requestedActivity.setSemester(assistanceRequest.getSemester());

        Activity requiredActivity = activityServ.getActivityBySchedule(requestedActivity);
        List<String> schedules = requiredActivity.getSchedules();

        for (String schedule : schedules) {
            Schedule actSchedule = scheduleRep.getScheduleById(schedule);
            if (actSchedule.getNumberDay() == assistanceRequest.getNumberDay() &&
                    actSchedule.getMonth() == assistanceRequest.getMonth()) {
                return assistanceServ.getAssistanceByOptions(assistanceRequest);
            }
        }

        throw new EciBienestarException(EciBienestarException.TYPE_NOT_FOUND);
    }

    /**
     * Update the confirmation status of a user's assistance.
     *
     * @param assistanceRequest Request object with confirmation and schedule info.
     * @throws EciBienestarException if schedule is not found.
     */
    @PutMapping("update/confirm")
    public void updateConfirmationForAssistance(@RequestBody AssistanceRequest assistanceRequest) throws EciBienestarException {
        Activity requestedActivity = new Activity();
        requestedActivity.setActivityType(assistanceRequest.getActivityType());
        requestedActivity.setYear(assistanceRequest.getYear());
        requestedActivity.setSemester(assistanceRequest.getSemester());

        Activity requiredActivity = activityServ.getActivityBySchedule(requestedActivity);
        List<String> schedules = requiredActivity.getSchedules();

        for (String schedule : schedules) {
            Schedule actSchedule = scheduleRep.getScheduleById(schedule);
            if (actSchedule.getNumberDay() == assistanceRequest.getNumberDay() &&
                    actSchedule.getMonth() == assistanceRequest.getMonth()) {
                assistanceServ.updateConfirmationForAssitance(assistanceRequest);
            }
        }
    }

    /**
     * Delete a user's assistance from a specific schedule.
     *
     * @param assistanceRequest Request containing user and schedule data.
     * @throws EciBienestarException if schedule is not found.
     */
    @DeleteMapping("/delete")
    public void deleteAssistanceForUser(@RequestBody AssistanceRequest assistanceRequest) throws EciBienestarException {
        Activity requestedActivity = new Activity();
        requestedActivity.setActivityType(assistanceRequest.getActivityType());
        requestedActivity.setYear(assistanceRequest.getYear());
        requestedActivity.setSemester(assistanceRequest.getSemester());

        Activity requiredActivity = activityServ.getActivityBySchedule(requestedActivity);
        List<String> schedules = requiredActivity.getSchedules();

        for (String schedule : schedules) {
            Schedule actSchedule = scheduleRep.getScheduleById(schedule);
            if (actSchedule.getNumberDay() == assistanceRequest.getNumberDay() &&
                    actSchedule.getMonth() == assistanceRequest.getMonth()) {
                assistanceServ.deleteAssistanceForUser(assistanceRequest);
            }
        }
    }
}
