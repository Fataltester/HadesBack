package edu.eci.cvds.EciBienestarMod3.controller;

import edu.eci.cvds.EciBienestarMod3.dto.ActivityOptionalRequest;
import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;
import edu.eci.cvds.EciBienestarMod3.model.EveryDay;
import edu.eci.cvds.EciBienestarMod3.service.ActivityService;
import edu.eci.cvds.EciBienestarMod3.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityServ;

    @Autowired
    private ScheduleService scheduleService;

    //Primer semestre Febrero 1 - Hasta Mayo 31
    //Segundo semstre Agosto 1 - Hasta Noviembre 30
    @PostMapping("/")
    public Activity createActivity(@RequestBody Activity activity) throws EciBienestarException {
        Activity newActivity = activityServ.getActivityBySchedule(activity);
        if (newActivity != null) {
            throw new EciBienestarException(EciBienestarException.ACTIVITY_ALREADY_EXIST);
        }
        List<String> schedules = new ArrayList<>();
        activity.setYear();
        activity.setSemester();
        for (EveryDay everyDay : activity.getDays()) {
            schedules.addAll(scheduleService.createScheduleBetweenTwoDates(
                    activity.getSemester(), activity.getId(), everyDay.getDayWeek()));
        }
        activity.setSchedules(schedules);
        return activityServ.createActicity(activity);
    }

    @GetMapping("/find/options")
    public List<Activity> findActivitiesByOptions(@RequestBody ActivityOptionalRequest activityOptionalRequest){
        return activityServ.getActivitiesByOptions(activityOptionalRequest);
    }

    @PutMapping("/update")
    public void updateActivity(@RequestBody Activity activity,
                               @RequestBody ActivityOptionalRequest activityOptionalRequest) throws EciBienestarException {
        activityServ.updateActivityByOptions(activity, activityOptionalRequest);
    }

    @GetMapping("/all")
    public List<Activity> getAllActivities() {
        return activityServ.getAllActivities();
    }

    @DeleteMapping("/activities")
    public void deleteActivity(@RequestBody Activity activity) {
        Activity currentActivity = activityServ.getActivityBySchedule(activity);
        List<String> schedules = currentActivity.getSchedules();
        activityServ.deleteActivity(currentActivity);
        for (String id : schedules){
            scheduleService.deleteAdminShcedule(id);
        }
    }

    /**
    @GetMapping("/search/schedules/activity")
    public Activity getActivityBySchedule(@RequestBody Activity activity) {
        return activityServ.getActivityBySchedule(activity);
    }

    @GetMapping("/search/teacherId/{teacherId}")
    public List<Activity> getActivityByUserId(@PathVariable int teacherId) {
        return activityServ.getAllActivitiesByTeacherId(teacherId);
    }

    @GetMapping("/search/activityType/{activityType}")
    public List<Activity> getActivityByActivityType(@PathVariable String activityType) {
        return activityServ.getAllActivitiesByActivityType(activityType);
    }

    @GetMapping("/search/location/{location}")
    public List<Activity> getActivityByLocation(@PathVariable String location) {
        return activityServ.getAllActivitiesByLocation(location);
    }

    @GetMapping("/search/startHour/{startHour}")
    public List<Activity> getActivityByHour(@PathVariable LocalTime startHour) {
        return activityServ.getAllActivitiesByHour(startHour);
    }

    @GetMapping("/search/dayOfWeek/{dayOfWeek}")
    public List<Activity> getActivityByDayOfWeek(@PathVariable DayOfWeek dayOfWeek) {
        return activityServ.getAllActivitiesByDayOfWeek(dayOfWeek);
    }

    @GetMapping("/search/year/{year}")
    public List<Activity> getActivityByYear(@PathVariable int year) {
        return activityServ.getAllActivitiesByYear(year);
    }

    @GetMapping("/search/semester/{semester}")
    public List<Activity> getActivityBySemester(@PathVariable int semester) {
        return activityServ.getAllActivitiesBySemester(semester);
    }

    @PutMapping("/update/startHour/{startHour}")
    public void updateActivityByStartHour(@RequestBody Activity activity, @PathVariable LocalTime startHour) {
        activityServ.updateActivityByStartHour(activity, startHour);
    }

    @PutMapping("/update/dayOfWeek/{dayOfWeek}")
    public void updateActivityByDayOfWeek(@RequestBody Activity activity, @PathVariable DayOfWeek dayOfWeek) {
        activityServ.updateActivityByDayOfWeek(activity, dayOfWeek);
    }

    @PutMapping("/update/year/{year}")
    public void updateActivityByYear(@RequestBody Activity activity, @PathVariable int year) {
        activityServ.updateActivityByYear(activity, year);
    }

    @PutMapping("/update/semester/{semester}")
    public void updateActivityBySemester(@RequestBody Activity activity, @PathVariable int semester) throws EciBienestarException {
        activityServ.updateActivityBySemester(activity, semester);
    }

    @PutMapping("/update/teacher/{teacher}/{teacherId}")
    public void updateActivityByTeacher(@RequestBody Activity activity, @PathVariable String teacher, @PathVariable int teacherId) {
        activityServ.updateActivityByTeacher(activity, teacher, teacherId);
    }

    @PutMapping("/update/activityType/{activityType}")
    public void updateActivityByActivityType(@RequestBody Activity activity, @PathVariable String activityType) throws EciBienestarException {
        activityServ.updateActivityByActivityType(activity, activityType);
    }

    @PutMapping("/update/location/{location}")
    public void updateActivityByLocation(@RequestBody Activity activity, @PathVariable String location) {
        activityServ.updateActivityByLocation(activity, location);
    }

    @PutMapping("/update/capacityMaximum/{capacityMaximum}")
    public void updateActivityByCapacityMaximum(@RequestBody Activity activity, @PathVariable int capacityMaximum) throws EciBienestarException {
        activityServ.updateActivityByCapacityMaximum(activity, capacityMaximum);
    }*/
}
