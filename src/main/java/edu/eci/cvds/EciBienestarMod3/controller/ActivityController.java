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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void updateActivity(@RequestBody ActivityOptionalRequest activityOptionalRequest) throws EciBienestarException {
        activityServ.updateActivityByOptions(activityOptionalRequest);
    }

    @GetMapping("/all")
    public List<Activity> getAllActivities() {
        return activityServ.getAllActivities();
    }

    @DeleteMapping("/activities")
    public void deleteActivity(@RequestBody Activity activity) {
        Activity currentActivity = activityServ.getActivityBySchedule(activity);
        if (currentActivity != null) {
            List<String> schedules = currentActivity.getSchedules();
            activityServ.deleteActivity(currentActivity);
            for (String id : schedules) {
                scheduleService.deleteAdminShcedule(id);
            }
        }
    }
}
