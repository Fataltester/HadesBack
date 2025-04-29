package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Activity;
import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityMongoRepository extends MongoRepository<Activity, String> {
    @Query("{ 'schedule' : ?0 }")
    Activity findBySchedule(Schedule schedule);
}
