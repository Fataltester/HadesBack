package edu.eci.cvds.EciBienestarMod3.repository;

import edu.eci.cvds.EciBienestarMod3.model.Schedule;
import edu.eci.cvds.EciBienestarMod3.model.enumeration.ScheduleState;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleMongoRepository extends MongoRepository<Schedule,String> {

    //Buscar por 1 estado especifico
    List<Schedule> findByState(ScheduleState state);

    //Buscar por varios estados especificos
    List<Schedule> findByStateIn(List<ScheduleState> states);
}
