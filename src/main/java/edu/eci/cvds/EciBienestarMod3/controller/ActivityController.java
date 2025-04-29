package edu.eci.cvds.EciBienestarMod3.controller;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityServ;

    @PostMapping("/")
    public Activity createActivity(@RequestBody Activity activity) {
        Activity createActiv = activityServ.createActicity(activity);
        return createActiv;
    }

    @DeleteMapping("/activities")
    public void deleteActivity(@RequestBody Schedule schedule) {
        Activity deleteActiv = activityServ.getActivityById(schedule);
    }

    @GetMapping("/{userId}")
    public List<Activity> getActivityByUserId(@PathVariable int userId) {
        List<Activity> activityList = activityServ.getAllActivitiesByUserId(userId);
        return activityList;
    }
}
