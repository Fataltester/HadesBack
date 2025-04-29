package edu.eci.cvds.EciBienestarMod3.service;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.repository.ActivityMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public Activity getActivityById(Schedule schedule) {
        return activityRepo.findBySchedule(schedule);
    }

    public List<Activity> getAllActivitiesByUserId(int userId) {
        List<Activity> activities = new ArrayList<>();
        for (Activity activity : activityRepo.findAll()) {
            if (activity.getTeacherId() == userId) {
                activities.add(activity);
            }
        }
        return activities;
    }
}
