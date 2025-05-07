package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Repository
public interface ActivityMongoRepository extends MongoRepository<Activity, String> {

    @Query("{ 'schedule' : ?0 }")
    Activity findBySchedule(Schedule schedule);

    @Query("{ 'activityType' : ?0, 'startHour' : ?1, 'dayWeek' : ?2, 'year' : ?3, 'semester' : ?4 }")
    Activity getActivityByGeneralSchedules(String activityType, LocalTime startHour, DayOfWeek dayWeek, int year, int semester);
}
