package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityMongoRepository extends MongoRepository<Activity, String> {
    /*
    @Query("{ 'schedule' : ?0 }")
    Activity findBySchedule(Schedule schedule);*/

    @Query("{ 'activityType' : ?0, 'year' : ?1, 'semester' : ?2 }")
    Activity getActivityByGeneralSchedules(String activityType, int year, int semester);

    @Query("{'year' : ?0, 'semester' : ?1, 'teacher' : ?2, 'activityType' : ?3 }")
    List<Activity> findActivityByOptions(int year, int semester, String teacher , String activityType);

    List<Activity> findActivityByYear(int year);
    List<Activity> findActivityBySemester(int semester);
    List<Activity> findActivityByTeacher(String teacher);
    List<Activity> findActivityByActivityType(String activityType);

}
