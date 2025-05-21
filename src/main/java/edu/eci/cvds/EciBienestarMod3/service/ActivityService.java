package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.dto.ActivityOptionalRequest;
import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;
import edu.eci.cvds.EciBienestarMod3.model.EveryDay;
import edu.eci.cvds.EciBienestarMod3.repository.ActivityMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class for managing activities, including creation, update,
 * retrieval and deletion of Activity objects. It also integrates with
 * the ScheduleService to manage associated schedule data.
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityMongoRepository activityRepo;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Saves a new Activity in the database.
     *
     * @param activity The Activity to be created.
     * @return The saved Activity object.
     */
    public Activity createActicity(Activity activity) {
        return activityRepo.save(activity);
    }

    /**
     * Retrieves all activities from the database.
     *
     * @return A list of all Activity objects.
     */
    public List<Activity> getAllActivities() {
        return activityRepo.findAll();
    }

    /**
     * Finds a specific activity by its type, year, and semester.
     *
     * @param activity The activity to search for.
     * @return The matching Activity, or null if not found.
     */
    public Activity getActivityBySchedule(Activity activity) {
        return activityRepo.getActivityByGeneralSchedules(
                activity.getActivityType(), activity.getYear(), activity.getSemester());
    }

    /**
     * Retrieves a list of activities based on filtering options from a request.
     * Defaults to the current year and semester if not provided.
     *
     * @param actReq The request containing filter parameters.
     * @return A list of matching activities.
     */
    public List<Activity> getActivitiesByOptions(ActivityOptionalRequest actReq) {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int semester = (month <= 6) ? 1 : 2;
        int year = today.getYear();

        if (actReq.getYear() == 0){
            actReq.setYear(year);
        }
        if (actReq.getSemester() == 0){
            actReq.setSemester(semester);
        }
        if (actReq.getActivityType() == null){
            actReq.setActivityType("");
        }
        if (actReq.getTeacherName() == null){
            actReq.setTeacherName("");
        }

        return activityRepo.findActivityByOptions(
                actReq.getYear(), actReq.getSemester(),
                actReq.getTeacherName(), actReq.getActivityType()
        );
    }

    /**
     * Updates an existing activity based on optional request parameters.
     * Allows changes to type, teacher, days, and resources.
     *
     * @param actOpt The request containing the update options.
     * @throws EciBienestarException If any update constraint is violated.
     */
    public void updateActivityByOptions(ActivityOptionalRequest actOpt) throws EciBienestarException {
        LocalDate today = LocalDate.now();
        int semester = (today.getMonthValue() <= 6) ? 1 : 2;

        // Find current activity
        Activity savedActivity = activityRepo.getActivityByGeneralSchedules(
                actOpt.getActivityType(), today.getYear(), semester);

        if (savedActivity != null) {
            // Change activity type if new type is provided and not already used
            if (actOpt.getNewActivity() != null) {
                Activity other = activityRepo.getActivityByGeneralSchedules(
                        actOpt.getNewActivity(), today.getYear(), semester);
                if (other == null) {
                    savedActivity.setActivityType(actOpt.getNewActivity());
                }
            }
            // Update teacher information
            if (actOpt.getTeacherName() != null && actOpt.getTeacherId() != 0) {
                savedActivity.setTeacher(actOpt.getTeacherName());
                savedActivity.setTeacherId(actOpt.getTeacherId());
            }
            // Update schedule days
            if (actOpt.getDays() != null) {
                // Delete old schedules
                List<String> oldSchedules = savedActivity.getSchedules();
                for (String oldSchedule : oldSchedules) {
                    scheduleService.deleteAdminShcedule(oldSchedule);
                }
                // Create new schedules
                for (EveryDay everyDay : actOpt.getDays()) {
                    oldSchedules = scheduleService.createScheduleBetweenTwoDates(
                            semester, savedActivity.getId(), everyDay.getDayWeek());
                }
                savedActivity.setDays(actOpt.getDays());
                savedActivity.setSchedules(oldSchedules);
            }
            // Update resources
            if (actOpt.getResources() != null) {
                savedActivity.setResources(actOpt.getResources());
            }

            activityRepo.save(savedActivity);
        }
    }

    /**
     * Deletes an activity from the database.
     *
     * @param activity The activity to delete.
     */
    public void deleteActivity(Activity activity) {
        activityRepo.delete(activity);
    }
}
