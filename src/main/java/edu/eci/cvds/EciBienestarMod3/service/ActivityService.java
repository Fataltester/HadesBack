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

@Service
public class ActivityService {

    @Autowired
    private ActivityMongoRepository activityRepo;

    @Autowired
    private ScheduleService scheduleService;

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

    public void updateActivityByOptions(ActivityOptionalRequest actOpt) throws EciBienestarException {
        //time
        LocalDate today = LocalDate.now();
        int semester = (today.getMonthValue() <= 6) ? 1 : 2;
        //current activity
        Activity savedActivity = activityRepo.getActivityByGeneralSchedules(
                actOpt.getActivityType(), today.getYear(), semester);
        if (savedActivity != null) {
            //change to other type activity
            if (actOpt.getNewActivity() != null) {
                Activity other = activityRepo.getActivityByGeneralSchedules(
                        actOpt.getNewActivity(), today.getYear(), semester);
                if (other == null) {
                    savedActivity.setActivityType(actOpt.getNewActivity());
                }
            }
            //change teacher
            if (actOpt.getTeacherName() != null && actOpt.getTeacherId() != 0) {
                savedActivity.setTeacher(actOpt.getTeacherName());
                savedActivity.setTeacherId(actOpt.getTeacherId());
            }
            //change days schedule
            if (actOpt.getDays() != null) {
                savedActivity.setDays(actOpt.getDays());
                List<String> oldSchedules = savedActivity.getSchedules();
                for (String oldSchedule : oldSchedules) {
                    scheduleService.deleteAdminShcedule(oldSchedule);
                }
                for (EveryDay everyDay : actOpt.getDays()) {
                    oldSchedules = scheduleService.createScheduleBetweenTwoDates(
                            semester, savedActivity.getId(), everyDay.getDayWeek());
                }
                savedActivity.setSchedules(oldSchedules);
            }
            //change resources
            if (actOpt.getResources() != null) {
                savedActivity.setResources(actOpt.getResources());
            }
            activityRepo.save(savedActivity);
        }
    }

    public void deleteActivity(Activity activity) {
        activityRepo.delete(activity);
    }
}
