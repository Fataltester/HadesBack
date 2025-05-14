package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.dto.ActivityOptionalRequest;
import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;
import edu.eci.cvds.EciBienestarMod3.repository.ActivityMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ActivityService {

    @Autowired
    private ActivityMongoRepository activityRepo;

    public Activity createActicity(Activity activity) {
        return activityRepo.save(activity);
    }

    public List<Activity> getAllActivities() {
        return activityRepo.findAll();
    }

    public Activity getActivityBySchedule(Activity activity) {
        return activityRepo.getActivityByGeneralSchedules(
                activity.getActivityType(), activity.getYear(), activity.getSemester());
    }

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
        return activityRepo.findActivityByOptions(actReq.getYear(),
                actReq.getSemester(), actReq.getTeacherName(), actReq.getActivityType());
    }

    public void updateActivityByOptions(Activity activity, ActivityOptionalRequest activityOptionalRequest) throws EciBienestarException {
        Activity savedActivity = activityRepo.getActivityByGeneralSchedules(
                activity.getActivityType(), activity.getYear(), activity.getSemester());
        if (savedActivity != null) {
            if (activityOptionalRequest.getActivityType() != null) {
                savedActivity.setActivityType(activityOptionalRequest.getActivityType());
            }
            if (activityOptionalRequest.getTeacherName() != null && activityOptionalRequest.getTeacherId() != 0) {
                savedActivity.setTeacher(activityOptionalRequest.getTeacherName());
                savedActivity.setTeacherId(activityOptionalRequest.getTeacherId());
            }
            if (activityOptionalRequest.getDays() != null) {
                savedActivity.setDays(activityOptionalRequest.getDays());
            }
            activityRepo.save(savedActivity);
        }
    }

    public void deleteActivity(Activity activity) {
        activityRepo.delete(activity);
    }

    /*
    public List<Activity> getAllActivitiesByTeacherId(int teacherId) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityRepo.findAll()) {
            if (activity.getTeacherId() == teacherId) {
                activities.add(activity);
            }
        }
        return activities;
    }

    public List<Activity> getAllActivitiesByActivityType(String activityType) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityRepo.findAll()) {
            if (activity.getActivityType().equals(activityType)) {
                activities.add(activity);
            }
        }
        return activities;
    }

    public List<Activity> getAllActivitiesByLocation(String location) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityRepo.findAll()) {
            if (activity.getLocation().equals(location)) {
                activities.add(activity);
            }
        }
        return activities;
    }

    public List<Activity> getAllActivitiesByHour(LocalTime startHour) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityRepo.findAll()) {
            if (activity.getStartHour().equals(startHour)) {
                activities.add(activity);
            }
        }
        return activities;
    }

    public List<Activity> getAllActivitiesByDayOfWeek(DayOfWeek dayOfWeek) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityRepo.findAll()) {
            if (activity.getDayWeek().equals(dayOfWeek)) {
                activities.add(activity);
            }
        }
        return activities;
    }

    public List<Activity> getAllActivitiesByYear(int year) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityRepo.findAll()) {
            if (activity.getYear() == year) {
                activities.add(activity);
            }
        }
        return activities;
    }

    public List<Activity> getAllActivitiesBySemester(int semester) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityRepo.findAll()) {
            if (activity.getSemester() == semester) {
                activities.add(activity);
            }
        }
        return activities;
    }

    public void updateActivityByStartHour(Activity activity, LocalTime startHour) {
        Activity actuallactivity = activityRepo.getActivityByGeneralSchedules(activity.getActivityType(), activity.getStartHour(),
                activity.getDayWeek(), activity.getYear(), activity.getSemester());
        actuallactivity.setStartHour(startHour);
        activityRepo.save(actuallactivity);
    }

    public void updateActivityByDayOfWeek(Activity activity, DayOfWeek dayOfWeek) {
        Activity actuallactivity = activityRepo.getActivityByGeneralSchedules(activity.getActivityType(), activity.getStartHour(),
                activity.getDayWeek(), activity.getYear(), activity.getSemester());
        actuallactivity.setDayWeek(dayOfWeek);
        activityRepo.save(actuallactivity);
    }

    public void updateActivityByYear(Activity activity, int year) {
        Activity actuallactivity = activityRepo.getActivityByGeneralSchedules(activity.getActivityType(), activity.getStartHour(),
                activity.getDayWeek(), activity.getYear(), activity.getSemester());
        actuallactivity.setYear(year);
        activityRepo.save(actuallactivity);
    }

    public void updateActivityBySemester(Activity activity, int semester) throws EciBienestarException {
        Activity actuallactivity = activityRepo.getActivityByGeneralSchedules(activity.getActivityType(), activity.getStartHour(),
                activity.getDayWeek(), activity.getYear(), activity.getSemester());
        actuallactivity.setSemester(semester);
        activityRepo.save(actuallactivity);
    }

    public void updateActivityByTeacher(Activity activity, String teacher, int teacherId) {
        Activity actuallactivity = activityRepo.getActivityByGeneralSchedules(activity.getActivityType(), activity.getStartHour(),
                activity.getDayWeek(), activity.getYear(), activity.getSemester());
        actuallactivity.setTeacher(teacher);
        actuallactivity.setTeacherId(teacherId);
        activityRepo.save(actuallactivity);
    }

    public void updateActivityByActivityType(Activity activity, String activityType) throws EciBienestarException {
        Activity actuallactivity = activityRepo.getActivityByGeneralSchedules(activity.getActivityType(), activity.getStartHour(),
                activity.getDayWeek(), activity.getYear(), activity.getSemester());
        actuallactivity.setActivityType(activityType);
        activityRepo.save(actuallactivity);
    }

    public void updateActivityByLocation(Activity activity, String location) {
        Activity actuallactivity = activityRepo.getActivityByGeneralSchedules(activity.getActivityType(), activity.getStartHour(),
                activity.getDayWeek(), activity.getYear(), activity.getSemester());
        actuallactivity.setLocation(location);
        activityRepo.save(actuallactivity);
    }

    public void updateActivityByCapacityMaximum(Activity activity, int capacityMaximum) throws EciBienestarException {
        Activity actuallactivity = activityRepo.getActivityByGeneralSchedules(activity.getActivityType(), activity.getStartHour(),
                activity.getDayWeek(), activity.getYear(), activity.getSemester());
        actuallactivity.setCapacityMaximum(capacityMaximum);
        activityRepo.save(actuallactivity);
    }*/

}
