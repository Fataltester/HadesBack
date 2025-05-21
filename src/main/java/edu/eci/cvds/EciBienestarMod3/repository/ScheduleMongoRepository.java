package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.model.enumeration.ScheduleState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing Schedule documents in MongoDB.
 */
@Repository
public interface ScheduleMongoRepository extends MongoRepository<Schedule, String> {

    /**
     * Finds all schedules with a specific state.
     *
     * @param state The desired state.
     * @return List of schedules matching the state.
     */
    List<Schedule> findByState(ScheduleState state);

    /**
     * Finds all schedules whose states are in the provided list.
     *
     * @param states List of states to filter by.
     * @return List of matching schedules.
     */
    List<Schedule> findByStateIn(List<ScheduleState> states);

    /**
     * Retrieves a schedule by its ID.
     *
     * @param schedule The schedule ID.
     * @return Schedule document.
     */
    Schedule getScheduleById(String schedule);
}
