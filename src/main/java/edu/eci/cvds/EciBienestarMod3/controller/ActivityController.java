package edu.eci.cvds.EciBienestarMod3.controller;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.EciBienestarException;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
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
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
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
    public Activity createActivity(@RequestBody Activity activity) {
        Activity newActivity = activityServ.createActicity(activity);

        LocalDate inicioSemestre;
        LocalDate finalSemestre;

        if(newActivity.getSemester() == 1){
            inicioSemestre = LocalDate.of(2025, 2, 1);
            finalSemestre = LocalDate.of(2025, 5, 31);
        }else{
            inicioSemestre = LocalDate.of(2025, 8, 1);
            finalSemestre = LocalDate.of(2025, 11, 30);
        }

        List<String> schedules =  scheduleService.createScheduleBetweenTwoDates(inicioSemestre,finalSemestre,newActivity.getId());
        newActivity.setSchedules(schedules);

        return activityServ.createActicity(newActivity);
    }

    @GetMapping("/all")
    public List<Activity> getAllActivities() {
        return activityServ.getAllActivities();
    }

    @GetMapping("/search/teacherId/{teacherId}")
    public List<Activity> getActivityByUserId(@PathVariable int teacherId) {
        List<Activity> activityList = activityServ.getAllActivitiesByTeacherId(teacherId);
        return activityList;
    }

    @GetMapping("/search/activityType/{activityType}")
    public List<Activity> getActivityByActivityType(@PathVariable String activityType) {
        List<Activity> activityList = activityServ.getAllActivitiesByActivityType(activityType);
        return activityList;
    }

    @GetMapping("/search/location/{location}")
    public List<Activity> getActivityByLocation(@PathVariable String location) {
        List<Activity> activityList = activityServ.getAllActivitiesByLocation(location);
        return activityList;
    }

    @GetMapping("/search/startHour/{startHour}")
    public List<Activity> getActivityByHour(@PathVariable LocalTime startHour) {
        List<Activity> activityList = activityServ.getAllActivitiesByHour(startHour);
        return activityList;
    }

    @GetMapping("/search/dayOfWeek/{dayOfWeek}")
    public List<Activity> getActivityByDayOfWeek(@PathVariable DayOfWeek dayOfWeek) {
        List<Activity> activityList = activityServ.getAllActivitiesByDayOfWeek(dayOfWeek);
        return activityList;
    }

    @GetMapping("/search/year/{year}")
    public List<Activity> getActivityByYear(@PathVariable int year) {
        List<Activity> activityList = activityServ.getAllActivitiesByYear(year);
        return activityList;
    }

    @GetMapping("/search/semester/{semester}")
    public List<Activity> getActivityBySemester(@PathVariable int semester) {
        List<Activity> activityList = activityServ.getAllActivitiesBySemester(semester);
        return activityList;
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
    }

    /**
    @GetMapping("/{state}")
    public List<Activity> getActivityByState(@PathVariable String state) {
        List<Activity> activityList = activityServ.getAllActivitiesByState(state);
        return activityList;
    }

    @PutMapping("/states/{state}")
    public Schedule updateSceduleByState(@RequestBody Schedule schedule, @PathVariable String state) {
        Schedule updateActiv = activityServ.updateActivityByState(state);
    }*/

    @DeleteMapping("/activities")
    public void deleteActivityBySchedule(@RequestBody Activity activity) {
        List<String> schedules = activity.getSchedules();
        activityServ.deleteActivity(activity);
        for(String scheduleId:schedules){
            scheduleService.deleteAdminShcedule(scheduleId);
        }
    }
}
