package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.Assistance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AssistanceMongoRepository extends MongoRepository<Assistance, String> {
    @Query("{ 'idSchedule' : ?0 }")
    List<Assistance> findByIdSchedule(String idSchedule);

    @Query("{ 'userId' : ?0 }")
    List<Assistance> findByUserId(int userId);

    @Query("{ 'userName': { $regex: ?0, $options: 'i' } }")
    List<Assistance> findByUserNameContaining(String userName);

    @Query("{ 'userRol' : ?0 }")
    List<Assistance> findByUserRol(String userRol);

    @Query("{ 'idSchedule' : ?0, 'confirmation' : ?1 }")
    List<Assistance> findByIdScheduleAndConfirmation(String idSchedule, boolean confirmation);

    @Query("{ 'userId' : ?0, 'idSchedule' : ?1 }")
    Assistance findByUserIdAndIdSchedule(int userId, String idSchedule);

    // Por si acas
    @Query("{ 'idSchedule': { $in: ?0 } }")
    List<Assistance> findByScheduleIds(List<String> scheduleIds);

    @Query("{ 'userName' : ?0, 'userRol' : ?1, 'idSchedule' : ?2}")
    Assistance getAssistanceByGeneralSchedules(String userName, String userRol, String idSchedule);
}
